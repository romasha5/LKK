package lkk.dbaprovider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

import net.proteanit.sql.DbUtils;


public class QueryMainTable {
	private static TableModel tbmain;
	
	public static TableModel selectMainTable(  )
	  {
	    Connection c = null;
	    Statement stmt = null;
	    SaveLoadProperties slp = new SaveLoadProperties();
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection(slp.getConStr());
	      c.setAutoCommit(false);	      

	      stmt = c.createStatement();
	      ResultSet rs = stmt.executeQuery( "select lastname as \"Прізвище\","
	      									+ " name as \"Ім'я\","
	      									+ " fathersname as \"По-батькові\","
	      									+ " strftime('%d-%m-%Y', dateborn) as \"Дата нар.\","
	      									+ " adress as \"Адреса\""
	      									+ " from main order by lastname");
	      
	      tbmain=DbUtils.resultSetToTableModel(rs);	      	      
	      
	      stmt.close();
	      c.commit();
	      c.close();
	    } catch ( Exception e ) {
	      JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage() );	      
	    }
		return tbmain;	    
	  }	 
	
	public static TableModel selectALLMainTable(  )
	  {
	    Connection c = null;
	    Statement stmt = null;
	    SaveLoadProperties slp = new SaveLoadProperties();
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection(slp.getConStr());
	      c.setAutoCommit(false);	      

	      stmt = c.createStatement();
	      ResultSet rs = stmt.executeQuery( "select strftime('%d-%m-%Y', datezapovn) as \"Дата зап.\","
	      									+ "lastname as \"Прізвище\","
	      									+ " name as \"Ім'я\","
	      									+ " fathersname as \"По-батькові\","
	      									+ " strftime('%d-%m-%Y', dateborn) as \"Дата нар.\","
	      									+ " adress as \"Адреса\","
	      									+ " privid as \"Привід\","
	      									+ " zaklkom as \"Заключення\","
	      									+ " id,"
	      									+ " target"
	      									+ " from main order by datezapovn");
	      
	      tbmain=DbUtils.resultSetToTableModel(rs);	      	      
	      
	      stmt.close();
	      c.commit();
	      c.close();
	    } catch ( Exception e ) {
	      JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage() );	      
	    }
		return tbmain;	    
	  }	 
	
	public static TableModel selectMainTablePrivid(  )
	  {
	    Connection c = null;
	    Statement stmt = null;
	    SaveLoadProperties slp = new SaveLoadProperties();
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection(slp.getConStr());
	      c.setAutoCommit(false);	      

	      stmt = c.createStatement();
	      ResultSet rs = stmt.executeQuery( "select privid as \"Привід для диспансерного обліку\" from main order by privid");
	      
	      tbmain=DbUtils.resultSetToTableModel(rs);	      	      
	      
	      stmt.close();
	      c.commit();
	      c.close();
	    } catch ( Exception e ) {
	      JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage() );	      
	    }
		return tbmain;	    
	  }	 
	
	public static TableModel selectMainTableZaklkom(  )
	  {
	    Connection c = null;
	    Statement stmt = null;
	    SaveLoadProperties slp = new SaveLoadProperties();
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection(slp.getConStr());
	      c.setAutoCommit(false);	      

	      stmt = c.createStatement();
	      ResultSet rs = stmt.executeQuery( "select zaklkom as \"Заключення комісії\" from main order by zaklkom");
	      
	      tbmain=DbUtils.resultSetToTableModel(rs);	      	      
	      
	      stmt.close();
	      c.commit();
	      c.close();
	    } catch ( Exception e ) {
	      JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage() );	      
	    }
		return tbmain;	    
	  }	 
	
	public static TableModel selectMainTableTarget(  )
	  {
	    Connection c = null;
	    Statement stmt = null;
	    SaveLoadProperties slp = new SaveLoadProperties();
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection(slp.getConStr());
	      c.setAutoCommit(false);	      

	      stmt = c.createStatement();
	      ResultSet rs = stmt.executeQuery( "select target as \"Куди видано довідку\" from main order by target");
	      
	      tbmain=DbUtils.resultSetToTableModel(rs);	      	      
	      
	      stmt.close();
	      c.commit();
	      c.close();
	    } catch ( Exception e ) {
	      JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage() );	      
	    }
		return tbmain;	    
	  }	 

	public static void insertMainTable(String lastname,String name,String fathersname,String dateborn,
										String adress,String privid,String zaklkom, String datezapovn, String target){
	    Connection c = null;
	    Statement stmt = null;
	    SaveLoadProperties slp = new SaveLoadProperties();
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection(slp.getConStr());
	      c.setAutoCommit(false);
	      
	      stmt = c.createStatement();
	      String sql = "insert into main (lastname,name,fathersname,dateborn,adress,privid,zaklkom,"
	      				+ "datezapovn,target) VALUES ('"+lastname+"','"+name+"','"+fathersname+"','"+dateborn
	      				+"','"+adress+"','"+privid+"','"+zaklkom+"','"+datezapovn+"','"+target+"')"; 
	      stmt.executeUpdate(sql);

	      stmt.close();
	      c.commit();
	      c.close();
	    } catch ( Exception e ) {
	    	JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage() );
	    }
	}

	public static void updateMainTable(String lastname,String name,String fathersname,String dateborn,
			String adress,String privid,String zaklkom, String datezapovn, Integer id, String target){		  
			    Connection c = null;
			    Statement stmt = null;
			    SaveLoadProperties slp = new SaveLoadProperties();
			    try {
			      Class.forName("org.sqlite.JDBC");
			      c = DriverManager.getConnection(slp.getConStr());
			      c.setAutoCommit(false);	
			      			      

			      stmt = c.createStatement();
			      String sql = "update main set lastname='"+lastname+"',name='"+name+"',fathersname='"+fathersname
			    		  +"',dateborn='"+dateborn+"',adress='"+adress+"',privid='"+privid+"',zaklkom='"+zaklkom
			      		  +"',datezapovn='"+datezapovn+"',target='"+target+"' where ID='"+id+"';";
			      stmt.executeUpdate(sql);
			      c.commit();

			      stmt.close();
			      c.close();
			    } catch ( Exception e ) {
			    	JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage() );
			    }		  
	}
	
	
	public static void deleteMainTable(Integer id){
	    Connection c = null;
	    Statement stmt = null;
	    SaveLoadProperties slp = new SaveLoadProperties();
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection(slp.getConStr());
	      c.setAutoCommit(false);


	      stmt = c.createStatement();
	      String sql = "delete from main where ID="+id;
	      stmt.executeUpdate(sql);
	      c.commit();

	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	    	JOptionPane.showMessageDialog(null,  e.getClass().getName() + ": " + e.getMessage() );
	    }
	}
	
}
