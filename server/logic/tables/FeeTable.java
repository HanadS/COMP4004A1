package server.logic.tables;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import server.logic.model.Fee;
import server.logic.model.Loan;


public class FeeTable {

	
	List<Fee> feeList=new ArrayList<Fee>();
    private static class FeeListHolder {
        private static final FeeTable INSTANCE = new FeeTable();
    }
    private FeeTable(){
    		Fee fee=new Fee(100,5);
    		feeList.add(fee);
    };
    public static final FeeTable getInstance() {
        return FeeListHolder.INSTANCE;
    }

	public List<Fee> getFeeTable() {
		return feeList;
	}
	
	
	public Object payfine(int i) {
		String result="";
		boolean oloan=LoanTable.getInstance().looklimit(i);
		int fee=0;
		int index=0;
		boolean user=FeeTable.getInstance().checkuser(i);
		if(user){
			for(int m=0;m<feeList.size();m++){
				if(feeList.get(m).getUserid()==i){
					fee=feeList.get(m).getFee();
					index=m;
				}else{
					fee=0;
				}
			}
		}else{
			fee=0;
		}
		
			if(oloan==false){
				result="Borrowing Items Exist";
			}else{
				feeList.get(index).setUserid(i);
				feeList.get(index).setFee(0);
				result="success";
			}
		
		
	
		
		
		return result;
	}
	private boolean checkuser(int j) {
		boolean result=true;
		int fee = 0;
		for(int i=0;i<feeList.size();i++){
			int userid=(feeList.get(i)).getUserid();
			if(userid==j){
				fee=fee+1;
			}else{
				fee=fee+0;
			}
		}	
		if(fee==0){
			result=false;
		}
		return result;
	}
	public void applyfee(int j, long time) {
		int flag=0;
		int index=0;
		for(int i = 0;i<feeList.size();i++){
			int userid=(feeList.get(i)).getUserid();
			if(userid==j){
				flag=flag+1;
				index=i;
			}
		}
		int a=(int) ((time/(60000))-5);
		if(flag!=0){
			if(a>=0){
				feeList.get(index).setFee(a+feeList.get(index).getFee());
				feeList.get(index).setUserid(j);
			}else{
				feeList.get(index).setFee(feeList.get(index).getFee());
				feeList.get(index).setUserid(j);
			}
		}else{
			if(a>=0){
				Fee fee=new Fee(j,a);
				feeList.add(fee);
			}else{
				Fee fee=new Fee(j,0);
				feeList.add(fee);
			}
		}
		
		
	}
	public boolean lookup(int j) {
		boolean result=true;
		int fee = 0;
		boolean user=FeeTable.getInstance().checkuser(j);
		if(user){
			for(int i=0;i<feeList.size();i++){
				int userid=(feeList.get(i)).getUserid();
				if(userid==j){
					fee=fee+feeList.get(i).getFee();
				}
			}	
		}else{
			fee=0;
		}
		if(fee!=0){
			result=false;
		}
		return result;
	}
	
	 public String print() {
			
			String output = "";
			
			
			for(int i=0;i<feeList.size();i++){

				output += "UserID- ";
				output += feeList.get(i).getUserid();
				output += "    ";
				output += "Fee- ";
				output += feeList.get(i).getFee();
				output += "    ";
				output += "\n";
			}
		return output;	
	 }	
	
	

}
