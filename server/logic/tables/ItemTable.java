package server.logic.tables;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import server.logic.model.Item;
import utilities.Trace;

public class ItemTable {
	private Logger logger = Trace.getInstance().getLogger("opreation_file");

	List<Item> itemList=new ArrayList<Item>();
    private static class ItemListHolder {
        private static final ItemTable INSTANCE = new ItemTable();
    }
    private ItemTable(){
    	//set up the default list with some instances
    	String[] ISBNList=new String[]{"9781442668584","1234567891234"};
    	String[] cnList=new String[]{"1","1"};
    	for(int i=0;i<ISBNList.length;i++){
			Item deitem=new Item(i,ISBNList[i],cnList[i],false);
			itemList.add(deitem);
		}
    	logger.info(String.format("Operation:Initialize ItemTable;ItemTable: %s", itemList));

    };
    public static final ItemTable getInstance() {
        return ItemListHolder.INSTANCE;
    }

    public Object delete(String string, String string2) {

    			String result="";
    			int index=0;
    			int flag=0;
    			for(int i=0;i<itemList.size();i++){
    				String ISBN=(itemList.get(i)).getISBN();
    				String copynumber=(itemList.get(i)).getCopynumber();
    				if(ISBN.equalsIgnoreCase(string) && copynumber.equalsIgnoreCase(string2)){
    					index=i;
    					flag=flag+1;
    				}else{
    					flag=flag+0;
    				}
    			}
    			if(flag!=0){    			
    				itemList.get(index).setCopynumber("N/A");
    				result="success";
    				logger.info(String.format("Operation:Delete Item;Item Info:[%s,%s];State:Success", string,"N/A"));

	
    			}else{
    				result="The Item Does Not Exist";
    				logger.info(String.format("Operation:Delete Item;Item Info:[%s,%s];State:Fail;Reason:The item is currently on loan.", string,string2));

    			}
    			return result;
	}
    
	public Object createitem(String string) {
		boolean result=true;
		result=TitleTable.getInstance().lookup(string);
		if(result){
		int flag=0;
		for(int i=0;i<itemList.size();i++){
			if(itemList.get(i).getISBN().equalsIgnoreCase(string)){
				flag = flag + 1;
			}else{
				flag=flag+0;
			}
		}
		Item newitem=new Item(itemList.size(),string,String.valueOf(flag+1),false);
		itemList.add(newitem);
		logger.info(String.format("Operation:Create New Item;Item Info:[%s,%s];State:Success", string,String.valueOf(flag+1)));

		}else{
			result=false;
		}
		return result;
	}
	public boolean lookup(String string, String string2) {
		boolean result=true;
		int flag=0;
		for(int i=0;i<itemList.size();i++){
			String ISBN=(itemList.get(i)).getISBN();
			String copynumber=(itemList.get(i)).getCopynumber();
			if(ISBN.equalsIgnoreCase(string) && copynumber.equalsIgnoreCase(string2)){
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
		
	
	
	public Item findItem(String string, String string2) {
		Item item = null;
		for(int i=0;i<itemList.size();i++){
			String ISBN=(itemList.get(i)).getISBN();
			String copynumber=(itemList.get(i)).getCopynumber();
			if(ISBN.equalsIgnoreCase(string) && copynumber.equalsIgnoreCase(string2)){
				item = itemList.get(i);
			}
		}
		return item;
	}	
	
	
	public boolean isReserved(String string, String string2) {
		boolean result=true;
		int flag=0;
		for(int i=0;i<itemList.size();i++){
			String ISBN=(itemList.get(i)).getISBN();
			String copynumber=(itemList.get(i)).getCopynumber();
			Boolean reserved  = (itemList.get(i)).getReservation();
			if(ISBN.equalsIgnoreCase(string) && copynumber.equalsIgnoreCase(string2) && reserved){
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
		
		
		for(int i=0;i<itemList.size();i++){
		
			
			output += "Item ID- ";
			output += itemList.get(i).getItemid();
			output += "    ";
			
			output += "ISBN- ";
			output += itemList.get(i).getISBN();
			output += "    ";
			
			output += "CopyNumber- ";
			output += itemList.get(i).getCopynumber();	
			output += "    ";
			
			output += "\n";


			
			
		//	Item deitem=new Item(i,ISBNList[i],cnList[i]);
		//	itemList.add(deitem);
		}
		
		return output;
		
		
		
	}	
	
	
	
	
	public List<Item> getItemTable() {
		return itemList;
	}
}