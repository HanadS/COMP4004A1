package server.logic.tables;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import server.logic.model.Loan;


public class LoanTable {
	List<Loan> loanList=new ArrayList<Loan>();
    private static class LoanListHolder {
        private static final LoanTable INSTANCE = new LoanTable();
    }
    private LoanTable(){
    	//set up the default list with some instances
    	
	    	Date testDate = new Date();	
	    	Loan loan=new Loan(0,"9781442668584","1",new Date(),"0");
	    	loanList.add(loan);
	    	
    };
    public static final LoanTable getInstance() {
        return LoanListHolder.INSTANCE;
    }
	
    
	public List<Loan> getLoanTable() {
		return loanList;
	}

	

	
}
