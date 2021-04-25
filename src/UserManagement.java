
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

	public class UserManagement  {

	private JFrame frame;
	private JLabel lblID, lblName, lblSurname, lblService, lblTel;
	private JTextField txtName, txtSurname, txtID, txtService, txtTel;
	private JButton addButton, resetButton;
	private JPanel namePanel, lastNamePanel, idPanel, buttonsPanel, servicePanel, telephonePanel;
	private JPanel mainPanel, inputPanel;

	private static JTable table;
	private DefaultTableModel tableModel;
	
	private List<User> usersList;

	static String[] columns = new String[] { "ID", "Name", "Surname", "Service", "Telephone" };

	
	
			/** Constructor UserManagement
			 * @param data 
			 * @param usersList
			 */
			UserManagement(Object[][] data,List<User> usersList) {
				this.usersList = usersList;
					
				// Labels
				lblName = new JLabel("First Name:");
				lblName.setPreferredSize(new Dimension(70, 20));

				lblSurname = new JLabel("Last name:");
				lblSurname.setPreferredSize(new Dimension(70, 20));

				lblID = new JLabel("Account ID:");
				lblID.setPreferredSize(new Dimension(70, 10));
				// idLabel.setHorizontalAlignment(JLabel.RIGHT);

				lblService = new JLabel("Service:");
				lblService.setPreferredSize(new Dimension(70, 20));
				lblService.setHorizontalAlignment(JLabel.RIGHT);

				lblTel = new JLabel("Telephone:");
				lblTel.setPreferredSize(new Dimension(70, 20));
				lblTel.setHorizontalAlignment(JLabel.RIGHT);

				// TextFields
				txtName = new JTextField();
				txtName.setPreferredSize(new Dimension(300, 22));

				txtSurname = new JTextField();
				txtSurname.setPreferredSize(new Dimension(300, 22));

				txtID = new JTextField();
				txtID.setPreferredSize(new Dimension(300, 22));

				txtService = new JTextField();
				txtService.setPreferredSize(new Dimension(300, 22));

				txtTel = new JTextField();
				txtTel.setPreferredSize(new Dimension(300, 22));

				// Panels
				namePanel = new JPanel();
				namePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
				namePanel.add(lblName);
				namePanel.add(txtName);

				lastNamePanel = new JPanel();
				lastNamePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
				lastNamePanel.add(lblSurname);
				lastNamePanel.add(txtSurname);

				idPanel = new JPanel();
				idPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
				idPanel.add(lblID);
				idPanel.add(txtID);

				servicePanel = new JPanel();
				servicePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
				servicePanel.add(lblService);
				servicePanel.add(txtService);

				telephonePanel = new JPanel();
				telephonePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
				telephonePanel.add(lblTel);
				telephonePanel.add(txtTel);

				addButton = new JButton("Add user");
				addButton.setFocusable(false);
				addButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						   
						String id = txtID.getText();
						String name = txtName.getText();
						String surname = txtSurname.getText();
						String service = txtService.getText();
						String telephone = txtTel.getText();

						if (!name.equals("") && !surname.equals("")) {

							tableModel.addRow(new String[] { id, name, surname, service, telephone });
							
							User user = new User(id, name, surname, service, telephone);
							
							addUser(user);
							appentToFile(user);
							readFile();

						} else
							JOptionPane.showMessageDialog(null, "Enter name and last name!");

						
					}
	
				});
						
				resetButton = new JButton("Reset");
				resetButton.setFocusable(false);
				resetButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						txtID.setText("");
						txtName.setText("");
						txtSurname.setText("");
						txtService.setText("");
						txtTel.setText("");
						
					}
				});

				buttonsPanel = new JPanel();
				buttonsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
				buttonsPanel.add(addButton);
				buttonsPanel.add(resetButton);

				inputPanel = new JPanel();
				inputPanel.setLayout(new GridLayout(6, 1, 0, -5));
				inputPanel.add(namePanel);
				inputPanel.add(lastNamePanel);
				inputPanel.add(idPanel);
				inputPanel.add(servicePanel);
				inputPanel.add(telephonePanel);
				inputPanel.add(buttonsPanel);

				
				tableModel = new DefaultTableModel(data,columns);

				table = new JTable(tableModel);

				mainPanel = new JPanel();
				mainPanel.setLayout(new BorderLayout());
				mainPanel.add(inputPanel, BorderLayout.NORTH);
				mainPanel.add(new JScrollPane(table));

				frame = new JFrame("User Management");

				frame.add(mainPanel);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(700, 500);
				frame.setLocation(200, 200);
				frame.setVisible(true);

			

	}

	

	public static void main(String[] args) throws IOException {

		
		User user1 = new User("1", "Sadko", "Djozo", "Premium", "061111222");
		User user2 = new User("2", "Amer", "Zildzic", "Light", "06222111");
		User user3 = new User("3", "Elisabeth", "Smitt", "Full", "061111222");
		
	    List<User> usersList = new ArrayList<>();
		
		//usersList.add(user1);
		//usersList.add(user2);
	  
		Company tcom = new Company("T-com", usersList);

		tcom.addUser(user1);
		tcom.addUser(user2);
		tcom.addUser(user3);
		tcom.getUsers().get(0).getId();

		Object[][] data = new Object[usersList.size()][];

		for (int i = 0; i < usersList.size(); i++) {

			data[i] = new Object[] { tcom.getUsers().get(i).getId(), tcom.getUsers().get(i).getName(),
					tcom.getUsers().get(i).getSurname(), tcom.getUsers().get(i).getService(), tcom.getUsers().get(i).getTelephone() };

		}

		UserManagement project = new UserManagement(data, usersList);

		writeFile(usersList);

		readFile();

	}

	protected void addUser(User user) {

		usersList.add(user);
		
	}

	private static void writeFile(List<User> usersList ) {

		// Create file if not exist

		File file = new File("Users_List.txt");
		if (file.exists()) {
			System.out.println("File size in bytes " + file.length() + "\n");

		}
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {

				e.printStackTrace();
			}
			System.out.println("File created!");
		}

		// Write users list to file
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(file));
			
			//Write table header from columns array
			for (int i = 0; i < columns.length; i++) {
				bw.write(columns[i] + "\t\t");
			}
			bw.newLine();
			
			//Write users from usersList
			for (User user : usersList) {

				bw.write(user.toString());

				bw.newLine();
			}
			

		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			try {
				bw.close();
			} catch (IOException ioe) {

				ioe.printStackTrace();
			}
		}

	}

	private static void readFile() {
		// Read file
		try {
			FileReader reader = new FileReader("Users_List.txt");
			int character = reader.read();
			while (character > -1) {
				System.out.print((char) character);
				character = reader.read();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	private void appentToFile(User user) {
				
		// Append user to file
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter("Users_List.txt"));
			bw.append(user.toString() + "\n");

			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
			} catch (IOException ioe) {

				ioe.printStackTrace();
			}
		}
	}
	
}
