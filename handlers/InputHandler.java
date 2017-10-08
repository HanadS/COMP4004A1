package handlers;
import handlers.OutputHandler;
import logic.handler.model.Output;
import logic.handler.model.ServerOutput;

public class InputHandler {

	public static final int WAITING = 0;
	public static final int ROLEDETERMINED=1;
	public static final int LIBRARIAN = 2;
	public static final int USER = 3;
	public static final int LIBRARIANLOGIN = 4;
	
	
	public static final int CREATEUSER = 5;
	public static final int CREATETITLE = 6;
	public static final int CREATEITEM = 7;
	
	
	public static final int DELETEUSER = 8;
	public static final int	DELETETITLE = 9;
	public static final int	DELETEITEM = 10;
	
	
	
	

	

	
    
    OutputHandler outputHandler=new OutputHandler();


	public ServerOutput processInput(String input, int state) {
		 String output = "";
		 Output o = new Output("",0);
		 ServerOutput oo = new ServerOutput(output,o.getState());
	        if (state == WAITING) {
	        	o = outputHandler.determineRole();
	        	output = o.getOutput();
		        oo.setOutput(output);
	         }else if (state == ROLEDETERMINED) {
	        	 
	        	 if (input.equalsIgnoreCase("librarian")) {
		            	state=LIBRARIAN;
			            oo.setState(state);
			            
			            o = outputHandler.promptPassword();
			            output = o.getOutput();
			            oo.setOutput(output);
			            
		            }
	        	 else if (input.equalsIgnoreCase("user")) {
		            	state=USER;
			            oo.setState(state);

		            }
	        	 
	        	 else{
	        		 	o = outputHandler.determineRole();
	 	        		output = "Role not recognized. "+o.getOutput();
	 	        		oo.setOutput(output);
		            }
	        	 
	        }else if(state==LIBRARIAN){
		        o=outputHandler.librarianLogin(input);
		        state=o.getState();
		        oo.setState(state);
		            
		        output = o.getOutput();
		        oo.setOutput(output);
		        
		        }
	        
	        else if(state == LIBRARIANLOGIN) {
	         if (input.equalsIgnoreCase("create user") ){
	        	
		        	o = outputHandler.promptUserInfo();
		        	state = o.getState();
		        	output = o.getOutput();
		        	
	            	oo.setState(state);
	            	oo.setOutput(output);
		        }
	         
	         else if (input.equalsIgnoreCase("create title")) {
	        		
		        	o = outputHandler.promptTitleInfo();
		        	output = o.getOutput();
		        	
		     
		        	
	            	oo.setState(state);
	            	oo.setOutput(output);
		        
		        }
	         
	         else if (input.equalsIgnoreCase("create item")) {
	        		
		        	o = outputHandler.promptItemInfo();
		        	output = o.getOutput();
		        		        	
	            	oo.setState(state);
	            	oo.setOutput(output);
		        
		        }
	         
	         else if (input.equalsIgnoreCase("delete user") ){
		        	o = outputHandler.promptUserInfo();
		    
		        	output = o.getOutput();
		        	output = "TO DELETE -> " + output;
		        	
		        	state=DELETEUSER;
	            	oo.setState(state);
	            	oo.setOutput(output);
		        }
	         else if (input.equalsIgnoreCase("delete title") ){
		        	o = outputHandler.promptTitleInfo();
	
		        	output = o.getOutput();
					state=DELETETITLE;
					output = "TO DELETE -> " + output;
					
					oo.setState(state);
					oo.setOutput(output);
		        }
	         
	         else if (input.equalsIgnoreCase("delete item") ){
		        	o = outputHandler.promptItemInfo();
	
		        	output = o.getOutput();
					state=DELETEITEM;
					output = "TO DELETE -> " + output;
					
					oo.setState(state);
					oo.setOutput(output);
		        }	
	        }
	        else if(state == CREATEUSER) {
	        	
	        	
	        	o=outputHandler.createUser(input);
        		output=o.getOutput();
        		state=o.getState();
        		oo.setOutput(output);
	            oo.setState(state);
	        	
	        	
	        	
	        }
	        
	        else if(state==DELETEUSER){
	        	
	        		o=outputHandler.deleteUser(input);
	        		output=o.getOutput();
	        		state=o.getState();
	        		oo.setOutput(output);
		            oo.setState(state);
	        	}
	        
	        
	        
	        return oo;
	}
	 



	
	   
}

