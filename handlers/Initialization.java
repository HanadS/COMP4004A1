package handlers;
import static org.junit.Assert.*;
import org.junit.Test;

public class Initialization {

	@Test
	public void determineRole() {
	
		InputHandler handler =new InputHandler();
		assertEquals("Ask if librarian or user.",handler.processInput(" ",InputHandler.WAITING).getOutput(), "Are you a librarian or a User?" );
		
		
		
		assertEquals("Check if librarian.",handler.processInput("librarian",InputHandler.ROLEDETERMINED).getState(), InputHandler.LIBRARIAN  );

		
	//	assertEquals("Take user input",handler.processInput(" ",));
		
		
		
		
		
	}
	
	

}
