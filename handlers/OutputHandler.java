package handlers;



import logic.handler.model.Output;
import server.logic.tables.ItemTable;
import server.logic.tables.TitleTable;
import server.logic.tables.UserTable;




public class OutputHandler {
	
	
	public static final int WAITING = 0;
	public static final int ROLEDETERMINED = 1;
	public static final int LIBRARIAN = 2;
	public static final int USER = 3;
	public static final int LIBRARIANLOGIN = 4;
	
	public static final int CREATEUSER = 5;
	public static final int CREATETITLE = 6;
	public static final int CREATEITEM = 7;
	public static final int  DELETEUSER = 8;
	public static final int  DELETETITLE = 8;
	public static final int  DELETEITEM = 9;

	

	
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
		output.setState(CREATEUSER);
		return output;
	}
	
	public Output promptTitleInfo() {
	
		Output output=new Output("",0);
		output.setOutput("Please Input Title Info:'ISBN,title'");
		output.setState(CREATETITLE);
		return output;
	
	}

	public Output promptItemInfo() {

		Output output=new Output("",0);
		output.setOutput("Please Input Item Info:'ISBN,copynumber'");
		output.setState(CREATEITEM);
		return output;

	}

	
	public Output createUser(String input) {
		Output output=new Output("",0);
		String[] strArray = null;   
	    strArray = input.split(",");
	    
        Object result="";

	    
	    boolean email=strArray[0].contains("@");
	    boolean dot  = strArray[0].contains(".");
	    
		  
	    
	    if(strArray.length!=2 || email!=true || dot != true ){
        	output.setOutput("Your input should in this format:'username,password'");
        	output.setState(CREATEUSER);
        }else{
	    
		result=UserTable.getInstance().createuser(strArray[0], strArray[1]);
		    
		if(result.equals(true)){
		   output.setOutput("Success!");
		 }else{
     		output.setOutput("The User Already Exists!");
     	}
		 output.setState(LIBRARIANLOGIN);
        }
		return output;
	}

	public Output deleteUser(String input) {
		Output output=new Output("",0);
		String[] strArray = null;   
        strArray = input.split(",");
        int userid=UserTable.getInstance().lookup(strArray[0]);
        boolean email=strArray[0].contains("@");

        Object result="";
       
        if(strArray.length!=1 || email!=true){
        		output.setOutput("Your input should in this format:'useremail'");
        		output.setState(DELETEUSER);
        }else if(userid==-1){
        	output.setOutput("The User Does Not Exist!");
        	output.setState(DELETEUSER);
        }
        
        else{
        		result=UserTable.getInstance().delete(userid);
        		if(result.equals("success")){
        			output.setOutput("Success!");
        		}else{
        			output.setOutput(result+"!");
        		}
        		output.setState(LIBRARIANLOGIN);
        		
		
        }
        
        return output;
	}

	
		
	public Output createTitle(String input) {
		Output output=new Output("",0);
		String[] strArray = null;   
        strArray = input.split(",");
        boolean number=isInteger(strArray[0]);
        Object result="";
        if(strArray.length!=2 || number!=true){
        	output.setOutput("Your input should in this format:'ISBN,title',ISBN should be a 13-digit number");
        	output.setState(CREATETITLE);
        }else{
        	result=TitleTable.getInstance().createtitle(strArray[0], strArray[1]);
        	if(result.equals(true)){
        		output.setOutput("Success!");
        	}else{
        		output.setOutput("The Title Already Exists!");
        	}
        	
        	output.setState(LIBRARIANLOGIN);
        }
        
        
		return output;
	}
	

	public Output deleteTitle(String input) {

		Output output=new Output("",0);
		String[] strArray = null;   
		strArray = input.split(",");
		boolean number=isInteger(strArray[0]);
		Object result="";
		if(strArray.length!=1 || number!=true){
			output.setOutput("Your input should in this format:'ISBN',ISBN should be a 13-digit number");
			output.setState(DELETETITLE);
		}else{

       result=TitleTable.getInstance().delete(strArray[0]);

       if(result.equals("success")){
        	output.setOutput("Success!");
        }else{
    		output.setOutput(result+"!");
    	}
            output.setState(LIBRARIANLOGIN);
		 }

			return output;

	}

	public Output createItem(String input) {
		Output output=new Output("",0);
		String[] strArray = null;   
        strArray = input.split(",");
        boolean number=isInteger(strArray[0]);
        Object result="";
        if(strArray.length!=1 || number!=true){
        	output.setOutput("Your input should in this format:'ISBN',ISBN should be a 13-digit number");
        	output.setState(CREATEITEM);
        }else{
        	result=ItemTable.getInstance().createitem(strArray[0]);
        	if(result.equals(true)){
        		output.setOutput("Success!");
        	}else{
        		output.setOutput("The Title Does Not Exists!");
        	}
        	output.setState(LIBRARIANLOGIN);
        }
		return output;
	}
	
	public Output deleteItem(String input) {
		Output output=new Output("",0);
		String[] strArray = null;   
        strArray = input.split(",");
        boolean number=isInteger(strArray[0]);
        Object result="";
        if(strArray.length!=2 || number!=true){
        	output.setOutput("Your input should in this format:'ISBN,copynumber',ISBN should be a 13-digit number");
        	output.setState(DELETEITEM);
        }else{
        		result=ItemTable.getInstance().delete(strArray[0], strArray[1]);
            	if(result.equals("success")){
            		output.setOutput("Success!");
            	}
            	output.setState(LIBRARIANLOGIN);
        	
        }
        
       
        
        
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
public static boolean isInteger(String value) {
	char[] ch = value.toCharArray();
	boolean isNumber=true;
	if(value.length()==13){
		for (int i = 0; i < ch.length; i++) {
			isNumber = Character.isDigit(ch[i]);
		}
	}else{
		isNumber=false;
	}
	return isNumber;
	 }

public boolean isNumber(String value) {
	char[] ch = value.toCharArray();
	boolean isNumber=true;
		for (int i = 0; i < ch.length; i++) {
			isNumber = Character.isDigit(ch[i]);
		}
	return isNumber;
}


	
}
