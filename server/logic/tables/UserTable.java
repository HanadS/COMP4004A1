package server.logic.tables;


import java.util.ArrayList;
import java.util.List;

import server.logic.model.User;





public class UserTable {

	List<User> userList=new ArrayList<User>();

	
	 private static class UserListHolder {
	        private static final UserTable INSTANCE = new UserTable();
	    }
	
	

	  private UserTable(){
			String[] passwordList=new String[]{"jim"};
	    	String[] usernameList=new String[]{"jim@carleton.ca"};
	    	
	    	for(int i=0;i<usernameList.length;i++){
				User theUser=new User(i,usernameList[i],passwordList[i]);
				userList.add(theUser);
	    	} 
	  };
	  
	  
		public Object createuser(String string, String string2) {		
			boolean result=true;
			User newuser=new User(userList.size(),string,string2);
			result=userList.add(newuser);
			return result;
		}
	  
	  
	  
	  public static final UserTable getInstance() {
	        return UserListHolder.INSTANCE;
	    }
	  
	
	  public List<User> getUserTable() {
			return userList;
		}
	
	
}
