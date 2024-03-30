package restService.Request;

public class RegisterPlayerRequest {

	private final String username;
	private final String password;


	public RegisterPlayerRequest(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
}