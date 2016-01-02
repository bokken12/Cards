package messaging;

public class AccountConfirmationMessage extends Message {

	public AccountConfirmationMessage() {

	}

	public AccountConfirmationMessage(boolean confirm) {
		super();
		StringableBoolean confirmed = new StringableBoolean(confirm);
		
		data.add("confirmed", confirmed);
	}
	
	public boolean isConfirmed() {
		return ((StringableBoolean) data.get("confirmed")).getBoolean();
	}

}
