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
		boolean user = UserTable.getInstance().lookup(i);
		boolean isbn = TitleTable.getInstance().lookup(string);
		boolean copynumber = ItemTable.getInstance().lookup(string, string2);
		boolean oloan = ItemTable.getInstance().lookup(string, string2);
		boolean limit = LoanTable.getInstance().checkLimit(i);
		boolean alreadyloaned = LoanTable.getInstance().checkLoan(string, string2);

		if (user == false) {
			result = "User Invalid";
		} else if (isbn == false) {
			result = "ISBN Invalid";
		} else if (copynumber == false) {
			result = "Copynumber Invalid";
		}

		else {
			if (alreadyloaned) {

				if (oloan) {
					if (limit) {
						Loan loan = new Loan(i, string, string2, date, "0");
						loanList.add(loan);
						result = "success";
					} else if (limit == false) {
						result = "The Maximun Number of Items is Reached";
					}
				} else {
					result = "The Item is Not Available";
				}

			} else {
				result = "Already been loaned";
			}
		}
		return result;
	}
	public boolean lookup(int j, String string, String string2) {
		boolean result=true;
		int flag=0;
		for(int i=0;i<loanList.size();i++){
			String ISBN=(loanList.get(i)).getIsbn();
			String copynumber=(loanList.get(i)).getCopynumber();
		
			if(ISBN.equalsIgnoreCase(string) && copynumber.equalsIgnoreCase(string2)){
				flag=flag+1;
			}else{
				
//				System.out.println(loanList.size());
//				System.out.println("i = "+i+"line 81 ibsn = "+string+" and copynumber is "+string2);
//				System.out.println("i = "+i+"line 81 ibsn = "+ISBN+" and copynumber is "+copynumber);


				flag=flag+0;	
			}
		}
		if(flag==0){
			result=false;
		}
		return result;
	}
	public boolean checkLimit(int j) {
		boolean result=true;
		int flag=0;
		for(int i=0;i<loanList.size();i++){
			int userid=(loanList.get(i)).getUserid();
			if(userid==j){
				flag=flag+1;
			}else{
				flag=flag+0;	
			}
		}
		if(flag>=3){
			result=false;
		}
		return result;
	}
	public Object renewal(int j, String string, String string2, Date date) {
		
		String result="";
		int flag=0;
		int index=0;
		boolean limit=LoanTable.getInstance().checkLimit(j);
		for(int i=0;i<loanList.size();i++){
			String ISBN=(loanList.get(i)).getIsbn();
			String copynumber=(loanList.get(i)).getCopynumber();
			int userid=(loanList.get(i)).getUserid();
			if((userid==j) && ISBN.equalsIgnoreCase(string) && copynumber.equalsIgnoreCase(string2)){
				flag=flag+1;
				index=i;
			}else{
				flag=flag+0;	
			}
		}
		if(limit){
			if(flag!=0){
				if(loanList.get(index).getRenewstate().equalsIgnoreCase("0")){
					loanList.get(index).setUserid(j);
					loanList.get(index).setIsbn(string);
					loanList.get(index).setCopynumber(string2);
					loanList.get(index).setDate(new Date());
					loanList.get(index).setRenewstate("1");
					result="success";
				}else{
					result="Renewed Item More Than Once for the Same Loan";
					}
			}else{
				result="The loan does not exist";
			}
			
		}else if(limit==false){
			result="The Maximun Number of Items is Reached";
		}
		return result;	
	}
	
	public Object returnItem(int j, String string, String string2, Date date) {
		
		String result="";
		int flag=0;
		int index=0;
		for(int i=0;i<loanList.size();i++){
			String ISBN=(loanList.get(i)).getIsbn();
			String copynumber=(loanList.get(i)).getCopynumber();
			int userid=(loanList.get(i)).getUserid();
			if((userid==j) && ISBN.equalsIgnoreCase(string) && copynumber.equalsIgnoreCase(string2)){
				flag=flag+1;
				index=i;
			}else{
				flag=flag+0;	
			}
		}
		if(flag!=0){
			long time = date.getTime()-loanList.get(index).getDate().getTime();
			loanList.remove(index);
			if(time> 60000){
				FeeTable.getInstance().applyfee(j,time);
			}
			result="success";
		}else{
			result="The Loan Does Not Exist";
		}
		
		return result;
	}
	
	public boolean looklimit(int j) {
		boolean result=true;
		int flag=0;
		for(int i=0;i<loanList.size();i++){
			int userid=(loanList.get(i)).getUserid();
			if(userid==j){
				flag=flag+1;
			}else{
				flag=flag+0;	
			}
		}
		if(flag!=0){
			result=false;
		}
		return result;
	}
	
	 public String print() {
			
			String output = "";
			
			
			for(int i=0;i<loanList.size();i++){

				output += "UserID- ";
				output += loanList.get(i).getUserid();
				output += "    ";
				
				output += "ISBN- ";
				output += loanList.get(i).getIsbn();
				output += "    ";
				
				output += "CopyNumber- ";
				output += loanList.get(i).getCopynumber();	
				output += "    ";
				
				output += "Date- ";
				output += loanList.get(i).getDate();	
				output += "    ";
				
				output += "Renew- ";
				output += loanList.get(i).getRenewstate();	
				output += "    ";
				
				output += "\n";
			}
		return output;	
	 }
	 
		public boolean checkLoan(String string, String string2) {
			boolean result=true;
			int flag=0;
			for(int i=0;i<loanList.size();i++){
				String ISBN=(loanList.get(i)).getIsbn();
				String copynumber=(loanList.get(i)).getCopynumber();
				if(ISBN.equalsIgnoreCase(string) && copynumber.equalsIgnoreCase(string2)){
					flag=flag+1;
				}else{
					
					flag=flag+0;	
				}
			}
			if(flag!=0){
				result=false;
			}
			return result;
		}
		public boolean checkLoan(String string) {
			boolean result=true;
			int flag=0;
			for(int i=0;i<loanList.size();i++){
				String ISBN=(loanList.get(i)).getIsbn();
				if(ISBN.equalsIgnoreCase(string)){
					flag=flag+1;
				}else{
					flag=flag+0;	
				}
			}
			if(flag!=0){
				result=false;
			}
			return result;
		}
	 
	
	
}
