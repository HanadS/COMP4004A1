package server.logic.model;

public class Item {
	int itemid;
	String ISBN;
	String copynumber;
	
	public Item(int itemid,String ISBN,String copynumber){
		this.itemid=itemid;
		this.ISBN=ISBN;
		this.copynumber=copynumber;
	}
	
	public String toString(){
		return "["+this.itemid+","+this.ISBN+","+this.copynumber+"]";
	}
	
	public int getItemid() {
		return itemid;
	}
	public void setItemid(int itemid) {
		this.itemid = itemid;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getCopynumber() {
		return copynumber;
	}
	public void setCopynumber(String copynumber) {
		this.copynumber = copynumber;
	}
	

	public boolean sameItem(Item b){

	if (this.getItemid() == b.getItemid() && this.getISBN() == b.getISBN() && this.getCopynumber() == b.getCopynumber() ){
			return true;
	}

		return false;

	}


}
