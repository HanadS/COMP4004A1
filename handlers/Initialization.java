package handlers;
import static org.junit.Assert.*;
import org.junit.Test;

public class Initialization {

	@Test
	public void determineRole() {
	
		InputHandler handler =new InputHandler();
		assertEquals("Ask User if librarian or user.",handler.processInput(" ",InputHandler.WAITING).getOutput(), "Are you a librarian or a User?" );
		
		
		
		
	}
	
	

}
