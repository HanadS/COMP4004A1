package handlers;
import handlers.OutputHandler;
import logic.handler.model.Output;
import logic.handler.model.ServerOutput;

public class InputHandler {

	public static final int WAITING = 0;
    
    OutputHandler outputHandler=new OutputHandler();


	public String processInput(String input, int state) {
		 String output = "";
		 Output o = new Output("",0);
		 ServerOutput oo = new ServerOutput(output,o.getState());
	        if (state == WAITING) {
	        	o = outputHandler.determineRole();
	        	output = o.getOutput();
		         oo.setOutput(output);
	         }
	        
	        return oo.getOutput();
	}
	 



	
	   
}

