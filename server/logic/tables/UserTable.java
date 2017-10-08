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
			int flag=0;
			for(int i=0;i<userList.size();i++){
				String email=(userList.get(i)).getUsername();
				if(email.equalsIgnoreCase(string)){
					flag=flag+1;
				}else{
					flag=flag+0;	
				}
			}
			if(flag==0){
				User newuser=new User(userList.size(),string,string2);
				result=userList.add(newuser);
			}else{
				result=false;
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
			int userid=-1;
			for(int i=0;i<userList.size();i++){
				if(userList.get(i).getUsername().equalsIgnoreCase(string)){
					userid=i;
				}
			}
			return userid;
		}
	  
	  public Object delete(int i) {
			String result="";
			int flag=0;
			int index=0;
			for(int j=0;j<userList.size();j++){
				if(userList.get(j).getUserid()==i){
					index=j;
					flag=flag+1;
				}
			}
			
			
			
				if(flag ==  1){
					userList.get(index).setUserid(i);
					userList.get(index).setPassword("N/A");
					userList.get(index).setUsername("N/A");
					result="success";
				}
			
				
			return result;

		}
	
	
}
