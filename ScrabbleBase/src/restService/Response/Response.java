package restService.Response;

import java.util.ArrayList;

import restService.Message;

public class Response {

	private boolean success;
	private ArrayList<String> errormessages;
	private ArrayList<String> infomessages;
	private Object data;

	public Response (Message message, Object data) {
		if (message.getErrorMessage().size() > 0) {
			this.success = false;
			this.errormessages = message.getErrorMessage();
			this.infomessages = message.getInfoMessage();
			this.data = data;
		} else {
			this.success = true;
			this.errormessages = message.getErrorMessage();
			this.infomessages = message.getInfoMessage();
			this.data = data;
		}
	}


	public Response(boolean success, ArrayList<String> errormessages, ArrayList<String> infomessages, Object data) {
		this.success = success;
		this.errormessages = errormessages;
		this.infomessages = infomessages;
		this.data = data;
	}

	public boolean getSuccess() {
		return this.success;
	}

	public ArrayList<String> getErrorMessages() {
		return this.errormessages;
	}

	public ArrayList<String> getInfoMessages() {
		return this.infomessages;
	}

	public Object getData() {
		return this.data;
	}

}
