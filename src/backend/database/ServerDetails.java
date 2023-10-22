package backend.database;

public class ServerDetails {
		private String server;
		private String user;
		private String pass;
		
		public void setServer(String pserver) {
			this.server = pserver;
		}
		
		public void setUser(String user) {
			this.user = user;
		}
		
		public void setPass(String pass) {
			this.pass = pass;
		}
		
		public String getServer() {
			return server;
		}
		
		public String getUser() {
			return user;
		}
		
		public String getPass() {
			return pass;
		}

}
