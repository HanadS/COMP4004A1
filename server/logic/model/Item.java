package server.logic.model;

public class Item {
	int itemid;
	String ISBN;
	String copynumber;
	boolean reservation;
	
	public Item(int itemid,String ISBN,String copynumber, boolean reservation){
		this.itemid=itemid;
		this.ISBN=ISBN;
		this.copynumber=copynumber;
		this.reservation = reservation;
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
	
	
	public boolean getReservation() {
		return reservation;
	}
	public void setReservation(boolean reservation) {
		this.reservation = reservation;
	}

	public boolean sameItem(Item b){

	if (this.getItemid() == b.getItemid() && this.getISBN() == b.getISBN() && this.getCopynumber() == b.getCopynumber() && this.getReservation() == b.getReservation() ){
			return true;
	}

		return false;

	}


}
