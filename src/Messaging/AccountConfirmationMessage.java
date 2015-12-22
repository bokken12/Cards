package messaging;

public class AccountConfirmationMessage extends Message {

	public AccountConfirmationMessage() {

	}

	public AccountConfirmationMessage(boolean confirm) {
		
		StringableBoolean confirmed = new StringableBoolean(confirm);
		
		data.add("confirmed", confirmed);
	}
	
	public boolean isConfirmed() {
		return ((StringableBoolean) data.get("confirmed")).getBoolean();
	}

}
