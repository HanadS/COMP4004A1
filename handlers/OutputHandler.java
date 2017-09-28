package handlers;



import logic.handler.model.Output;




public class OutputHandler {
	
	
	public static final int WAITING = 0;
	public static final int ROLEDETERMINED = 1;
	public static final int LIBRARIAN = 2;
	public static final int USER = 3;
	public static final int LIBRARIANLOGIN = 4;


	
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


public Output librarianLogin(String input) {
	
	
	Output output = new Output("",0);
	
	if(input.equalsIgnoreCase("admin")){
			
			output.setState(LIBRARIANLOGIN);
	}
	return output;
	
}



	
}
