package lkk.dbaprovider;
import java.util.prefs.Preferences;

public class SaveLoadProperties {
	   private Preferences userProp;
	    String cs;
	    String constr;
	    String curDir = System.getProperty("user.home");
	    public SaveLoadProperties()
	    {
	        userProp = Preferences.userNodeForPackage(SaveLoadProperties.class);
	    }
	    
	    public String getProperties(){
	    	
	    		this.cs = userProp.get("pathCS", curDir+"\\lkk.db");
	    	return cs;
	    }
	    
	    public String getConStr(){
	    		this.constr = userProp.get("ConStr", "jdbc:sqlite:"+curDir+"\\lkk.db");
	    	return constr;
	    }

	    
	    public void putProperties(String cs){
	    	userProp.put("pathCS", cs);
	    }
	    
	    public void putConStr(String constr){
	    	userProp.put("ConStr", constr );
	    }
}
