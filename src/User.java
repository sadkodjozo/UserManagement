
public class User {
	
	private String id;
	private String name;
	private String surname;
	private String service;
	private String telephone;
	
		
	/** Create user
	 * @param id
	 * @param name
	 * @param surname
	 * @param service
	 * @param telephone
	 */
	public User(String id, String name, String surname, String service, String telephone) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.service = service;
		this.telephone = telephone;
	}
	

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}



	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}



	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}



	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}



	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}



	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}



	/**
	 * @return the service
	 */
	public String getService() {
		return service;
	}



	/**
	 * @param service the service to set
	 */
	public void setService(String service) {
		this.service = service;
	}



	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}



	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	@Override
	public String toString() {
		return  id + "\t\t" + name + "\t\t" + surname + "\t\t" + service + "\t\t"
				+ telephone;
	}
	
	
}	
