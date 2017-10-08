package server.logic.tables;

import java.util.ArrayList;
import java.util.List;
import server.logic.model.Title;


public class TitleTable {
	List<Title> titleList=new ArrayList<Title>();
    private static class TitleListHolder {
        private static final TitleTable INSTANCE = new TitleTable();
    }
    private TitleTable(){
    	//set up the default list with some instances
    	String[] ISBNList=new String[]{"9781442668584"};
    	String[] titlenameList=new String[]{"TestBook"};
    	for(int i=0;i<ISBNList.length;i++){
    		Title detitle=new Title(ISBNList[i],titlenameList[i]);
    		titleList.add(detitle);
		}
    };
    public static final TitleTable getInstance() {
        return TitleListHolder.INSTANCE;
    }


	public List<Title> getTitleTable() {
		return titleList;
	}
	public Object createtitle(String string, String string2) {		
			boolean result=true;
			Title newtitle=new Title(string,string2);
			result=titleList.add(newtitle);
			return result;	
	}
	


}
