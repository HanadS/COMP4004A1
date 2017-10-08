package handlers;



import logic.handler.model.Output;
import server.logic.tables.UserTable;




public class OutputHandler {
	
	
	public static final int WAITING = 0;
	public static final int ROLEDETERMINED = 1;
	public static final int LIBRARIAN = 2;
	public static final int USER = 3;
	public static final int LIBRARIANLOGIN = 4;
	
	public static final int CREATEUSER = 5;
	public static final int CREATETITLE=6;
	public static final int CREATEITEM=7;
	public static final int DELETEUSER = 8;
	public static final int	DELETETITLE = 9;
	public static final int	DELETEITEM = 10;


	
	public Output determineRole() {
		
		Output output=new Output("",0);
		output.setOutput("Are you a librarian or a User?");
		return output;
	}
	
	
public Output promptPassword() {
		
		Output output=new Output("",0);
		output.setOutput("Please Input The Password.");
		return output;
	}

public Output promptUserInfo() {
	
	Output output=new Output("",0);
	output.setOutput("Please Input User Info: username,password:");
	return output;
}
public Output promptTitleInfo() {

	Output output=new Output("",0);
	output.setOutput("Please Input Title Info:'ISBN,title'");
	return output;

}

public Output promptItemInfo() {

	Output output=new Output("",0);
	output.setOutput("Please Input Item Info:'ISBN':");
	return output;

}




public Output librarianLogin(String input) {
	Output output = new Output("",0);
	if(input.equalsIgnoreCase("admin")){
			output.setState(LIBRARIANLOGIN);
			output.setOutput("What can I do for you?"
					+ " Menu:"
					+ "Create User"
					+ "Create Title"
					+ "Create Item"
					+ "Delete User"
					+ "Delete Title"
					+ "Delete Item.");
	}
	
	else{
			output.setState(LIBRARIAN);
			output.setOutput("Wrong password");
		}
	
	return output;
}

public Output createUser(String input) {
	Output output=new Output("",0);
	String[] strArray = null;   
    strArray = input.split(",");
    boolean email=strArray[0].contains("@");
    boolean dot  = strArray[0].contains(".");
    
    if(strArray.length!=2 || email!=true || dot != true){
    		output.setOutput("Your input should in this format:'username,password'");
    		output.setState(CREATEUSER);
    }
	return output;
}







}
