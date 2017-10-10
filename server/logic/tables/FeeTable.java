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
	
	
	

}
