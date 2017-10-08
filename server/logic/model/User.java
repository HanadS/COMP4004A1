package server.logic.model;



public class User {

int userid;

String username;

String password;


		public User(int userid,String username,String password){
			this.userid=userid;
			this.password=password;
			this.username=username;
		}

		public String toString(){
			return "["+this.userid+","+this.username+","+this.password+"]";
		}

		public int getUserid() {
			return userid;
		}

		public void setUserid(int userid) {
			this.userid = userid;
		}










}