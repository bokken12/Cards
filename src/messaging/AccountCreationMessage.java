package messaging;

public class AccountCreationMessage extends Message {

	public AccountCreationMessage() {
		
	}
	
	public AccountCreationMessage(String name, String pass, String mail){
		
		StringableString username = new StringableString(name);
		StringableString password = new StringableString(pass);
		StringableString email = new StringableString(mail);
		
		data.add("username", username);
		data.add("password", password);
		data.add("email", email);
	}
	
	public String getUsername() {
		return data.get("username").toString();
	}
	
	public String getPassword() {
		return data.get("password").toString();
	}
	
	public String getEmail() {
		return data.get("email").toString();
	}
}
