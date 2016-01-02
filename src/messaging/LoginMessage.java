package messaging;

public class LoginMessage extends Message {

	public LoginMessage() {
		
	}
	
	public LoginMessage(String name, String pass){
		super();
		StringableString username = new StringableString(name);
		StringableString password = new StringableString(pass);
		
		data.add("username", username);
		data.add("password", password);
	}
	
	public String getUsername() {
		return data.get("username").toString();
	}
	
	public String getPassword() {
		return data.get("password").toString();
	}
}
