package application;

public class Message {
	private String message;
	private final String start;
	private final String end;

	public Message(String message, String start, String end) {
		this.message = message;
		this.start = start;
		this.end = end;
	}

	public void clearMessage(){
		message = "";
	}

	public String getMessage() {
		return message;
	}

	public String getStart() {
		return start;
	}

	public String getEnd() {
		return end;
	}
}
