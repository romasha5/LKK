package lkk.dbaprovider;

import java.io.File;
import java.sql.*;

import javax.swing.JOptionPane;

public class ConnectDBA {
	static Connection c;
	public void CDBA()
	  {
		SaveLoadProperties slp = new SaveLoadProperties();
		File file = new File(slp.getProperties());
		c = null;
	   
		if(file.exists()){
			try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection(slp.getConStr());
		      c.close();
		    } catch ( Exception e ) {
		      JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		    }
		    JOptionPane.showMessageDialog(null,"БД підключено.");
		}
		else {
	    	int in =JOptionPane.showConfirmDialog(null, "DataBase is not found. Creat it?",
    				"База даних відсутня, створити її ?",JOptionPane.YES_NO_OPTION);
		    	if(in==1){
		    		System.exit(0);
		    	}
		    	else{
			    	try {
			    		Class.forName("org.sqlite.JDBC");
			    		c = DriverManager.getConnection(slp.getConStr());
		    			CreateDataBase.creatDB(c);
		    			c = DriverManager.getConnection(slp.getConStr());
		    			c.close();
		    		} 
		    	catch ( ClassNotFoundException |SQLException e ) {
		    			JOptionPane.showMessageDialog(null, "Неможливо стврити БД.");
		    			System.exit(0);
		    		} 
		    	}
		}
	  }
}
