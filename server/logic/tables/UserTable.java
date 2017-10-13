package server.logic.tables;

import java.util.ArrayList;
import java.util.List;

import server.logic.model.User;

import utilities.Trace;
import org.apache.log4j.Logger;

public class UserTable {
	private Logger logger = Trace.getInstance().getLogger("opreation_file");

	List<User> userList = new ArrayList<User>();

	private static class UserListHolder {
		private static final UserTable INSTANCE = new UserTable();
	}

	private UserTable() {
		String[] passwordList = new String[] { "jim" };
		String[] usernameList = new String[] { "jim@carleton.ca" };

		for (int i = 0; i < usernameList.length; i++) {
			User theUser = new User(i, usernameList[i], passwordList[i], true);
			userList.add(theUser);
		}
		logger.info(String.format("Operation:Initialize UserTable;UserTable: %s", userList));

	};

	public Object createuser(String string, String string2) {
		boolean result = true;
		int flag = 0;
		for (int i = 0; i < userList.size(); i++) {
			String email = (userList.get(i)).getUsername();
			if (email.equalsIgnoreCase(string)) {
				flag = flag + 1;
			} else {
				flag = flag + 0;
			}
		}
		if (flag == 0) {
			User newuser = new User(userList.size(), string, string2, true);
			result = userList.add(newuser);
			logger.info(String.format("Operation:Create New User;User Info:[%s,%s];State:Success", string,string2));

		} else {
			logger.info(String.format("Operation:Create New User;User Info:[%s,%s];State:Fail;Reason:The User already existed.", string,string2));

			result = false;
		}
		return result;
	}

	public static final UserTable getInstance() {
		return UserListHolder.INSTANCE;
	}

	public List<User> getUserTable() {
		return userList;
	}

	public int lookup(String string) {
		int userid = -1;
		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i).getUsername().equalsIgnoreCase(string)) {
				userid = i;
			}
		}
		// System.out.println("userid"+userid+string);

		return userid;
	}

	public boolean lookup(int j) {

		boolean result = true;
		int flag = 0;
		for (int i = 0; i < userList.size(); i++) {
			int userid = (userList.get(i)).getUserid();
			if (userid == j) {
				flag = flag + 1;
			} else {
				flag = flag + 0;
			}
		}
		if (flag == 0) {
			result = false;
		}
		return result;
	}

	public Object delete(int i) {

		String result = "";
		boolean loan = LoanTable.getInstance().checkUser(i);
		int flag = 0;
		int index = 0;
		for (int j = 0; j < userList.size(); j++) {
			if (userList.get(j).getUserid() == i) {
				index = j;
				flag = flag + 1;
			} else {
				flag = flag + 0;
			}
		}

		if (flag == 0) {
			result = "The User Does Not Exist";
			logger.info(String.format("Operation:Delete User;User Info:[%s,%s];State:Fail;Reason:The User Does Not Exist.", "N/A","N/A"));

		} else {
			boolean fee = FeeTable.getInstance().lookup(i);
			String string=userList.get(index).getUsername();
			String string2=userList.get(index).getPassword();
			if (fee && loan) {
				userList.get(index).setUserid(i);
				userList.get(index).setPassword("N/A");
				userList.get(index).setUsername("N/A");
				userList.get(index).setPrivilege(false);
				result = "success";
				logger.info(String.format("Operation:Delete User;User Info:[%s,%s];State:Success", string,string2));

			} else if (fee == false) {
				result = "Outstanding Fee Exists";
				logger.info(String.format("Operation:Delete User;User Info:[%s,%s];State:Fail;Reason:Outstanding Fee Exists.", string,string2));

			} else if (loan == false) {
				result = "Active Loan Exists";
				logger.info(String.format("Operation:Delete User;User Info:[%s,%s];State:Fail;Reason:Active Loan Exists.", string,string2));

			}
		}

		return result;

	}

	public int checkUser(String string, String string2) {
		int result = 0;
		int flag = 0;
		int index = 0;
		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i).getUsername().equalsIgnoreCase(string)) {
				flag = flag + 1;
				index = i;
			} else {
				flag = flag + 0;
			}
		}
		boolean password = userList.get(index).getPassword().equalsIgnoreCase(string2);
		if (flag != 0 && password) {
			result = 0;
		} else if (flag == 0) {
			result = 2;
		} else if (password == false) {
			result = 1;
		}
		return result;
	}

	public String print() {

		String output = "";

		for (int i = 0; i < userList.size(); i++) {

			output += "User ID- ";
			output += userList.get(i).getUserid();
			output += "    ";

			output += "Username- ";
			output += userList.get(i).getUsername();
			output += "    ";

			output += "Passowrd- ";
			output += userList.get(i).getPassword();
			output += "    ";

			output += "\n";

			// Item deitem=new Item(i,ISBNList[i],cnList[i]);
			// itemList.add(deitem);
		}

		return output;

	}

	public User findUser(String string) {

		User user = null;
		int flag = 0;
		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i).getUsername().equalsIgnoreCase(string)) {
				user = userList.get(i);
			} else {
				flag = flag + 0;
			}
		}

		return user;
	}
}
