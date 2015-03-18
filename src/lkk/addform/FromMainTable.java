package lkk.addform;

import java.sql.Date;

public class FromMainTable {
	
	private String lastname;
	private String name;
	private String fathersname;
	private Date dateborn;
	private String adress;
	
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFathersname() {
		return fathersname;
	}
	public void setFathersname(String fathersname) {
		this.fathersname = fathersname;
	}
	public Date getDateborn() {
		return dateborn;
	}
	public void setDateborn(Date dateborn) {
		this.dateborn = dateborn;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	/**
	 * @param lastname
	 * @param name
	 * @param fathersname
	 * @param dateborn
	 * @param adress
	 */
	public FromMainTable(String lastname, String name, String fathersname,
			Date dateborn, String adress) {
		super();
		this.lastname = lastname;
		this.name = name;
		this.fathersname = fathersname;
		this.dateborn = dateborn;
		this.adress = adress;
	}	
}
