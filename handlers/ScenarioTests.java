package handlers;

import static org.junit.Assert.*;


import org.junit.Test;



public class ScenarioTests {

	
	
	
	InputHandler handler =new InputHandler();

	@Test
	public void test() {

	
		assertEquals("Scenario 1",handler.processInput("hanad@carleton.ca,hanad",InputHandler.CREATEUSER).getOutput(),"Success!");		
		
		assertEquals("Scenario 2",handler.processInput("hanad@carleton.ca,hanad",InputHandler.CREATEUSER).getOutput(),"The User Already Exists!");	

		assertEquals("Scenario 3",handler.processInput("hanad@carleton.ca,jim",InputHandler.CREATEUSER).getOutput(),"The User Already Exists!");	
		
		assertEquals("Scenario 4",handler.processInput("9781442668584",InputHandler.CREATEITEM).getOutput(),"Success!");	
		
		assertEquals("Scenario 5",handler.processInput("9781452668584",InputHandler.CREATEITEM).getOutput(),"The title does not exist. Would you like to add it? Yes/No?");		
		
		assertEquals("Scenario 6",handler.processInput("2978133181221,TestBook",InputHandler.CREATETITLE).getOutput(),"Success!");		
		assertEquals("Scenario 7",handler.processInput("9781442668584,TestBook",InputHandler.CREATETITLE).getOutput(),"The Title Already Exists!");
		
		
		assertEquals("Scenario 8",handler.processInput("hanad@carleton.ca",InputHandler.DELETEUSER).getOutput(),"Success!");
		assertEquals("Scenario 9",handler.processInput("timmy@carleton.ca",InputHandler.DELETEUSER).getOutput(),"The User Does Not Exist!");

		
		assertEquals("Scenario 10",handler.processInput("9781442668584",InputHandler.DELETETITLE).getOutput(),"Success!");		
		assertEquals("Scenario 11",handler.processInput("1111111111111",InputHandler.DELETETITLE).getOutput(),"The Title Does Not Exist!");
		
		assertEquals("Scenario 12",handler.processInput("9781442668584,1",InputHandler.DELETEITEM).getOutput(),"Success!");		
		assertEquals("Scenario 13",handler.processInput("9781452668584,1",InputHandler.DELETEITEM).getOutput(),"The Item Does Not Exist!");
		
		
		handler.processInput("jim@carleton.ca,jim",InputHandler.CREATEUSER);
		handler.processInput("9781442668584,TestBook",InputHandler.CREATETITLE);	
		handler.processInput("9781442668581,TestBook2",InputHandler.CREATETITLE);	

		handler.processInput("9781442668584",InputHandler.CREATEITEM);	
		handler.processInput("9781442668581",InputHandler.CREATEITEM);	
		
		
		assertEquals("Scenario 14",handler.processInput("jim@carleton.ca,9781442668584,2",InputHandler.BORROW).getOutput(),"Success!");
		assertEquals("Scenario 15",handler.processInput("kjnknk@carleton.ca,9781442668584,1",InputHandler.BORROW).getOutput(), "The User Does Not Exist!");	
		assertEquals("Scenario 16",handler.processInput("jim@carleton.ca,9781234234234584,1",InputHandler.BORROW).getOutput(), "Your input should in this format:'useremail,ISBN,copynumber'");	
		
		
		assertEquals("Scenario 17",handler.processInput("jim@carleton.ca,9781442668584,1",InputHandler.RENEW).getOutput(),"Success!");	
		assertEquals("Scenario 18",handler.processInput("tim@carleton.ca,9781442668584,1",InputHandler.RENEW).getOutput(),"The User Does Not Exist!");	
		assertEquals("Scenario 19",handler.processInput("jim@carleton.ca,9781234234234584,1",InputHandler.RENEW).getOutput(), "Your input should in this format:'useremail,ISBN,copynumber'");	
		
		
		

		assertTrue("Scenario 20",handler.processInput("jim@carleton.ca,9781442668584,2",InputHandler.RETURN).getOutput().contains("success!"));	
		assertTrue("Scenario 21",handler.processInput("tim@carleton.ca,9781442668584,2",InputHandler.RETURN).getOutput().contains("The User Does Not Exist!"));

		assertEquals("Scenario 22",handler.processInput("tim@carleton.ca",InputHandler.PAYFINE).getOutput(),"The User Does Not Exist!");	
		assertEquals("Scenario 23 ",handler.processInput("tim^&arleton.ca",InputHandler.PAYFINE).getOutput(),"Your input should in this format:'useremail'");	

		
		
	}

}
