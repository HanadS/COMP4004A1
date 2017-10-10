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
    		Fee fee=new Fee(0,5);
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
		
			feeList.get(index).setUserid(i);
			feeList.get(index).setFee(0);
			result="success";
		
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
	
	

}
