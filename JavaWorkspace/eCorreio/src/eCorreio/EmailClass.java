package eCorreio;

/**
 * A email message and its own methods.
 * 
 * @author Joao Soares N57609
 *
 */
public class EmailClass implements Email {
	
	private String date, subject, text, address;

	public EmailClass(String address, String date, String subject, String text) {
		this.address = address;
		this.date = date;
		this.subject = subject;
		this.text = text;
	}

	@Override
	public String getAddress() {
		return address;
	}

	@Override
	public String getDate() {
		return date;
	}

	@Override
	public String getSubject() {
		return subject;
	}

	@Override
	public String getText() {
		return text;
	}

	
}
