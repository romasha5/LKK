package lkk.dbaprovider;

import java.sql.Connection;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class CreateDataBase {
	static Statement stmt;	  
	static String sql;
	public static void creatDB(Connection c) {
		stmt=null;
	    try {
	      stmt = c.createStatement();
	      sql = "CREATE TABLE [komisiya] ("
	      		+ "[id] INTEGER NOT NULL PRIMARY KEY ON CONFLICT ROLLBACK AUTOINCREMENT,"
	      		+ "[lastname] TEXT NOT NULL,"
	      		+ "[name] TEXT NOT NULL,"
	      		+ "[fathersname] TEXT NOT NULL,"
	      		+ "[flaggol] BOOLEAN DEFAULT 0,"
	      		+ "[selchlen] BOOLEAN DEFAULT 0)";	      		 
	      stmt.executeUpdate(sql);
	      
	      sql = "CREATE TABLE [main] ("
	      		+ "[id] INTEGER NOT NULL ON CONFLICT ROLLBACK PRIMARY KEY ON CONFLICT ROLLBACK AUTOINCREMENT,"
	      		+ "[lastname] TEXT NOT NULL,"
	      		+ "[name] TEXT NOT NULL,"
	      		+ "[fathersname] TEXT NOT NULL,"
	      		+ "[dateborn] DATE,"
	      		+ "[adress] TEXT,"
	      		+ "[privid] TEXT,"
	      		+ "[zaklkom] TEXT,"
	      		+ "[datezapovn] DATE,"
	      		+ "[target] TEXT)";
	      stmt.executeUpdate(sql);
	      
	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	      JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage());
	      System.exit(0);
	    }
	    
	    JOptionPane.showMessageDialog(null, "БД створено.");
		
	}

}
