package server.logic.tables;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import java.util.List;

import org.apache.log4j.Logger;

import server.logic.model.Loan;
import utilities.Config;
import utilities.Trace;


import server.logic.model.Item;


public class LoanTable {
	List<Loan> loanList=new ArrayList<Loan>();
	private Logger logger = Trace.getInstance().getLogger("opreation_file");

    private static class LoanListHolder {
        private static final LoanTable INSTANCE = new LoanTable();
    }
    private LoanTable(){
    	//set up the default list with some instances
    	
    	
    	
	    	Loan loan=new Loan(0,"9781442668584","1",new Date(100),0);
	    	
	    	loanList.add(loan);
	    	logger.info(String.format("Operation:Initialize LoanTable;LoanTable: %s", loanList));

	    	
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
			logger.info(String.format("Operation:Borrow Item;Loan Info:[%d,%s,%s,%s];State:Fail;Reason:Invalid User.", i,string,string2,dateformat(date)));

		} else if (isbn == false) {
			result = "ISBN Invalid";
			logger.info(String.format("Operation:Borrow Item;Loan Info:[%d,%s,%s,%s];State:Fail;Reason:Invalid ISBN.", i,string,string2,dateformat(date)));

		} else if (copynumber == false) {
			result = "Copynumber Invalid";
			logger.info(String.format("Operation:Borrow Item;Loan Info:[%d,%s,%s,%s];State:Fail;Reason:Invalid Copynumber.", i,string,string2,dateformat(date)));
		}

		else {
			

			
			if (alreadyloaned) {

				if (oloan) {
					Item item  = ItemTable.getInstance().findItem( string, string2);

					if (limit) {
						
						item.setReservation(false);
						Loan loan = new Loan(i, string, string2, date, 0);
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
	public boolean lookup(String string, String string2) {
		

		
		boolean result=true;
		int flag=0;
		for(int i=0;i<loanList.size();i++){
			String ISBN=(loanList.get(i)).getIsbn();
			String copynumber=(loanList.get(i)).getCopynumber();
		
			System.out.println("copynumber = "+copynumber+"string = "+string);

			
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
		if(flag>=10){
			result=false;
		}
		return result;
	}
	public Object renewal(int j, String string, String string2, Date date) {
		
		String result="";
		int flag=0;
		int index=0;
		boolean limit=LoanTable.getInstance().checkLimit(j);
		
		boolean reserved = ItemTable.getInstance().isReserved(string,string2);
		
		//boolean fee=FeeTable.getInstance().lookup(j);
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
			boolean hasPrivilege = UserTable.getInstance().getUserTable().get(j).getPrivilege();

			
		if (hasPrivilege == true){
			if(limit == true){
			if(!reserved){
				if(loanList.get(index).getRenewstate() < 2){
					loanList.get(index).setUserid(j);
					loanList.get(index).setIsbn(string);
					loanList.get(index).setCopynumber(string2);
					loanList.get(index).setDate(new Date());
					loanList.get(index).setRenewstate(loanList.get(index).getRenewstate()+1);
					result="success";
				}
				else{
					result="Renewed Item More Than Once for the Same Loan";
				
					
					}
			}else{
				result="This item is reserved";

			}
				
				
				
			}else{
				result="The Maximun Number of Items is Reached";
			} }else{
				result= "privilege revoked";
				
		
		}} else{
			
			result= "The loan does not exist";

		}
		
		
		return result;	
	}
	
	public Object returnItem(int j, String string, String string2, Date date) {

		// System.out.println("in here");
		boolean user = UserTable.getInstance().lookup(j);

		String result = "";
		int flag = 0;
		int index = 0;

		for (int i = 0; i < loanList.size(); i++) {
			String ISBN = (loanList.get(i)).getIsbn();
			String copynumber = (loanList.get(i)).getCopynumber();
			int userid = (loanList.get(i)).getUserid();
			if ((userid == j) && ISBN.equalsIgnoreCase(string) && copynumber.equalsIgnoreCase(string2)) {
				flag = flag + 1;
				index = i;
			} else {
				flag = flag + 0;
			}
		}
		boolean hasPrivilege = checkPrivilege(index);

		
		if (flag != 0) {
			
			
			result += "UserID- ";
			result += loanList.get(index).getUserid();
			result += "    ";
			
			result += "ISBN- ";
			result += loanList.get(index).getIsbn();
			result += "    ";
			
			result += "CopyNumber- ";
			result += loanList.get(index).getCopynumber();	
			result += "    ";
			
			result += "Date- ";
			result += loanList.get(index).getDate();	
			result += "    ";
			
			result += "Renew- ";
			result += loanList.get(index).getRenewstate();	
			result += "    ";
			

			long time2 = date.getTime() - loanList.get(index).getDate().getTime();

			int time = (int) time2;
			

			if (!hasPrivilege && user) {
				
				UserTable.getInstance().getUserTable().get(j).setPrivilege(false);
				
				result += "privilege revoked";

			} else {
		
			
			

			if (time > Config.OVERDUE * Config.STIMULATED_DAY) {
				//System.out.println("overdue");

				FeeTable.getInstance().applyfee(j, time);

				result += "\nThis item is overdue";

			} else {
				
				loanList.remove(index);
					result += "\nsuccess";
					
					logger.info(String.format("Operation:Return Item;Loan Info:[%d,%s,%s,%s];State:Success", j,string,string2,dateformat(date)));

				}
			}
		}

		else {
			result = "The Loan Does Not Exist";
			logger.info(String.format("Operation:Return Item;Loan Info:[%d,%s,%s,%s];State:Fail;Reason:The Loan Does Not Exist.", j,string,string2,dateformat(date)));

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
		
		
		public boolean checkPrivilege(int i) {
			boolean result=true;
			
			Date date  = new Date();
			long time2 = date.getTime() - loanList.get(i).getDate().getTime();

			int time = (int) time2;
			
			
			if (time > 8 * Config.STIMULATED_DAY) {
				//System.out.println("overdue");
				result = false;
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
	 
		public boolean checkUser(int j) {
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
		private String dateformat(Date date){
			DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String datestr=format1.format(date);
			return datestr;
		}
	
}
