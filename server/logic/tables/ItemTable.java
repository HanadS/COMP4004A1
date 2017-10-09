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

	
	
	public List<Item> getItemTable() {
		return itemList;
	}
}