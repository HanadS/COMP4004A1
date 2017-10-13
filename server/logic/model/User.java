package server.logic.model;



public class User {

		int userid;
		String username;
		String password;
		Boolean hasPrivilege;
		
		public User(int userid,String username,String password, Boolean hasPrivilege){
			this.userid=userid;
			this.password=password;
			this.username=username;
			this.hasPrivilege = hasPrivilege;
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


		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
		public Boolean getPrivilege() {
			return hasPrivilege;
		}

		public void setPrivilege(Boolean hasPrivilege) {
			this.hasPrivilege = hasPrivilege;
		}

	
		public boolean sameUser(User b){
			if (this.getUserid() == b.getUserid() && this.getUsername() == b.getUsername() && this.getPassword() == b.getPassword() ){
				return true;
				}
				return false;
			}




}