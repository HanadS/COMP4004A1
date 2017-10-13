package server.logic.tables;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import server.logic.model.Title;
import utilities.Trace;



public class TitleTable {
	List<Title> titleList=new ArrayList<Title>();
	private Logger logger = Trace.getInstance().getLogger("opreation_file");

    private static class TitleListHolder {
        private static final TitleTable INSTANCE = new TitleTable();
    }
    private TitleTable(){
    	


    	//set up the default list with some instances
    	String[] ISBNList=new String[]{"9781442668584","1234567891234"};
    	String[] titlenameList=new String[]{"TestBook","testbook2"};
    	for(int i=0;i<ISBNList.length;i++){
    		Title detitle=new Title(ISBNList[i],titlenameList[i]);
    		titleList.add(detitle);
		}
    	logger.info(String.format("Operation:Initialize TitleTable;TitleTable: %s", titleList));

    };
    public static final TitleTable getInstance() {
        return TitleListHolder.INSTANCE;
    }


	public List<Title> getTitleTable() {
		return titleList;
	}
	public Object createtitle(String string, String string2) {		
		boolean result=true;
		int flag=0;
		for(int i=0;i<titleList.size();i++){
			String ISBN=(titleList.get(i)).getISBN();
			if(ISBN.equalsIgnoreCase(string)){
				flag=flag+1;
			}else{
				flag=flag+0;	
			}
		}
		if(flag==0){
			logger.info(String.format("Operation:Create New Title;Title Info:[%s,%s];State:Success", string,string2));

			Title newtitle=new Title(string,string2);
			result=titleList.add(newtitle);
		}else{
			result=false;
			logger.info(String.format("Operation:Create New Title;Title Info:[%s,%s];State:Fail;Reason:The ISBN already existed.", string,string2));

		}
		return result;	
	}
	
	public Object delete(String string) {
		String result="";
		int index=0;
		int flag=0;
		for(int i=0;i<titleList.size();i++){
			if(titleList.get(i).getISBN().equalsIgnoreCase(string)){
				flag=flag+1;
				index=i;
			}else{
				flag=flag+0;
			}
		}
		if(flag!=0){
			String string2=titleList.get(index).getBooktitle();

			titleList.remove(index);
			result="success";
			logger.info(String.format("Operation:Delete Title;Title Info:[%s,%s];State:Success", string,string2));

		}else{
			result="The Title Does Not Exist";
			logger.info(String.format("Operation:Delete Title;ISBN Info:[%s];State:Fail;Reason:Active Loan Exists.", string));

		}

			return result;

	}
	public boolean lookup(String string) {
		boolean result=true;
		int flag=0;
		for(int i=0;i<titleList.size();i++){
			String ISBN=(titleList.get(i)).getISBN();
			if(ISBN.equalsIgnoreCase(string)){
				flag=flag+1;
			}else{
				flag=flag+0;	
			}
		}
		if(flag==0){
			result=false;
		}
		return result;
	}
	
	 public String print() {
			
			String output = "";
			
			
			for(int i=0;i<titleList.size();i++){

				output += "ISBN- ";
				output += titleList.get(i).getISBN();
				output += "    ";
				output += "Title- ";
				output += titleList.get(i).getBooktitle();
				output += "    ";
				output += "\n";
			}
		return output;	
	 }	
			

}
