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
    	
	    	Loan loan=new Loan(0,"9781442668584","1",new Date(),"0");
	    	loanList.add(loan);
	    	
    };
    public static final LoanTable getInstance() {
        return LoanListHolder.INSTANCE;
    }
	
    
	public List<Loan> getLoanTable() {
		return loanList;
	}
	public Object createloan(int i, String string, String string2, Date date) {
		String result="";
		
		boolean user=UserTable.getInstance().lookup(i);

		//System.out.println(user);
		
		if(user==false){
			result="User Invalid";
		}
		else{		
			Loan loan=new Loan(i,string,string2,date,"0");
			loanList.add(loan);
			result="success";
		}
		
		
    	return result;
	}
	
	
	
}
