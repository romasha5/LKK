package lkk.dbaprovider;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;


import net.proteanit.sql.DbUtils;

public class QueryKomisiyaTable {
	
	private static TableModel tbm;
	private static String[] arrcom;
	
	  public static TableModel selectKomisiyaTable(  )
	  {
	    Connection c = null;
	    Statement stmt = null;
	    SaveLoadProperties slp = new SaveLoadProperties();
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection(slp.getConStr());
	      c.setAutoCommit(false);	      

	      stmt = c.createStatement();
	      ResultSet rs = stmt.executeQuery( "select selchlen as \"#\","
	      									+ " lastname as \"Прізвище\","
	      									+ " name as \"Ім'я\","
	      									+ " fathersname as \"По-батькові\","
	      									+ " id"
	      									+ " from komisiya where flaggol=0 order by lastname");
	     
	      
	      
	      
	      tbm=DbUtils.resultSetToTableModel(rs);
	      
		    for (int i = 0; i < tbm.getRowCount(); i++) {
		    	if((Integer)tbm.getValueAt(i, 0)==0){
		    		tbm.setValueAt(false, i, 0);
		    	}
		    	else {
		    		tbm.setValueAt(true, i, 0);
				}
		    }
	      
	      
	      stmt.close();
	      c.commit();
	      c.close();
	    } catch ( Exception e ) {
	      JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage() );	      
	    }
		return tbm;	    
	  }
	  
	  public static String[] selectKomisiyaTableGol(  )
	  {
	    Connection c = null;
	    Statement stmt = null;
	    SaveLoadProperties slp = new SaveLoadProperties();
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection(slp.getConStr());
	      c.setAutoCommit(false);	      

	        stmt = c.createStatement();
	        ResultSet techrs =stmt.executeQuery("SELECT COUNT() FROM komisiya where flaggol=1");
	        int rsSize = techrs.getInt(1);
	      

	      ResultSet rs = stmt.executeQuery( "select lastname as \"Прізвище\","
	      									+ " name as \"Ім'я\","
	      									+ " fathersname as \"По-батькові\""
	      									+ " from komisiya where flaggol=1 order by lastname");
	    
	      arrcom=new String[rsSize];

	      while(rs.next()) {		    	  
	    	  arrcom[rs.getRow()-1]=rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3);	    	  
	    	}
	      	     
	      stmt.close();
	      c.commit();
	      c.close();
	    } catch ( Exception e ) {
	      JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage() );	      
	    }	    
		return arrcom;	    
	  }  

	  public static TableModel selectKomisiyaTableChange(  )
	  {
	    Connection c = null;
	    Statement stmt = null;
	    SaveLoadProperties slp = new SaveLoadProperties();
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection(slp.getConStr());
	      c.setAutoCommit(false);	      

	      stmt = c.createStatement();
	      ResultSet rs = stmt.executeQuery( "select flaggol as \"Гол.\","
	      									+ "lastname as \"Прізвище\","
	      									+ " name as \"Ім'я\","
	      									+ " fathersname as \"По-батькові\","
	      									+ " id"
	      									+ " from komisiya order by lastname");

	      tbm=DbUtils.resultSetToTableModel(rs);
	      
	    for (int i = 0; i < tbm.getRowCount(); i++) {
	    	if((Integer)tbm.getValueAt(i, 0)==0){
	    		tbm.setValueAt(false, i, 0);
	    	}
	    	else {
	    		tbm.setValueAt(true, i, 0);
			}
	    }
	      
	      
	      stmt.close();
	      c.commit();
	      c.close();
	    } catch ( Exception e ) {
	      JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage() );	      
	    }
		return tbm;	    
	  }

	  public static void UpdateData(String lastname,String name,String fathersname,Boolean flaggol,Integer id){
		    Connection c = null;
		    Statement stmt = null;
		    SaveLoadProperties slp = new SaveLoadProperties();
		    try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection(slp.getConStr());
		      c.setAutoCommit(false);	
		      
		      int s=flaggol?1:0;

		      stmt = c.createStatement();
		      String sql = "update komisiya set lastname='"+lastname+"',name='"+name+"',fathersname='"+fathersname+"',flaggol='"+s+"' where ID='"+id+"';";
		      stmt.executeUpdate(sql);
		      c.commit();

		      stmt.close();
		      c.close();
		    } catch ( Exception e ) {
		    	JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage() );
		    }
	  }
	  
	  public static void UpdateData(Boolean selchlen,Integer id){
		    Connection c = null;
		    Statement stmt = null;
		    SaveLoadProperties slp = new SaveLoadProperties();
		    try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection(slp.getConStr());
		      c.setAutoCommit(false);			      		    

		      int s=selchlen?1:0;
		      
		      stmt = c.createStatement();
		      String sql = "update komisiya set selchlen='"+s+"' where ID='"+id+"';";
		      
		      stmt.executeUpdate(sql);
		      c.commit();

		      stmt.close();
		      c.close();
		    } catch ( Exception e ) {
		    	JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage() );
		    }
	  }
	  
	  public static void AddData(String lastname,String name,String fathersname,Boolean flaggol){
		    Connection c = null;
		    Statement stmt = null;
		    SaveLoadProperties slp = new SaveLoadProperties();
		    try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection(slp.getConStr());
		      c.setAutoCommit(false);
		      
		      int s=flaggol?1:0;
		      stmt = c.createStatement();
		      String sql = "insert into komisiya (lastname,name,fathersname,flaggol) " +
		                   "VALUES ('"+lastname+"','"+name+"','"+fathersname+"','"+s+"')"; 
		      stmt.executeUpdate(sql);

		      stmt.close();
		      c.commit();
		      c.close();
		    } catch ( Exception e ) {
		    	JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage() );
		    }
	  }
	  
	  public static void DeleteData(Integer id){
		    Connection c = null;
		    Statement stmt = null;
		    SaveLoadProperties slp = new SaveLoadProperties();
		    try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection(slp.getConStr());
		      c.setAutoCommit(false);


		      stmt = c.createStatement();
		      String sql = "delete from komisiya where ID="+id;
		      stmt.executeUpdate(sql);
		      c.commit();

		      stmt.close();
		      c.close();
		    } catch ( Exception e ) {
		    	JOptionPane.showMessageDialog(null,  e.getClass().getName() + ": " + e.getMessage() );
		    }
	  }
}
