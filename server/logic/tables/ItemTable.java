package server.logic.tables;

import java.util.ArrayList;
import java.util.List;


import server.logic.model.Item;

public class ItemTable {
	List<Item> itemList=new ArrayList<Item>();
    private static class ItemListHolder {
        private static final ItemTable INSTANCE = new ItemTable();
    }
    private ItemTable(){
    	//set up the default list with some instances
    	String[] ISBNList=new String[]{"9781442668584"};
    	String[] cnList=new String[]{"1"};
    	for(int i=0;i<ISBNList.length;i++){
			Item deitem=new Item(i,ISBNList[i],cnList[i]);
			itemList.add(deitem);
		}
    };
    public static final ItemTable getInstance() {
        return ItemListHolder.INSTANCE;
    }

    public Object delete(String string, String string2) {

    	//Since the itemid and copynumber in is automatically assigned to the item,upon its creation.
    			//Each item has a unique itemid and copynumber.Even it is deleted,they can not be assigned to other item.
    			//To maintain the correctness of the data,here instead delete index from the List.
    			//I choose to remove the item's information instead the whole index.
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
	
    			}else{
    				result="The Item Does Not Exist";
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
				flag=flag+1;
			}else{
				flag=flag+0;
			}
		}
		Item newitem=new Item(itemList.size(),string,String.valueOf(flag+1));
		itemList.add(newitem);
		}else{
			result=false;
		}
		return result;
	}
		
		
	public List<Item> getItemTable() {
		return itemList;
	}
}