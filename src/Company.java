import java.util.List;

public class Company {
	private String name;
	private List<User> users;
	/**
	 * @param name
	 * @param users
	 */
	public Company(String name, List<User> users) {
		super();
		this.name = name;
		this.users = users;
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
	 * @return the users
	 */
	public List<User> getUsers() {
		return users;
	}
	/**
	 * @param users the users to set
	 */
	public void addUser(User user) {
		users.add(user);
	}

	
	
}
