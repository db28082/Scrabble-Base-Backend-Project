import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import Controller.PlayerController;
import DataAccess.PlayerDataAccess;
import restService.Message;
import restService.Request.RegisterPlayerRequest;
import restService.Response.PlayerResponse;

public class S1PlayerRegistrationTest {

	private String CreateRandomUsername() {
		return "user" + (long)Math.floor((Math.random()*100000000L));
	}

	@Before
	public void Setup() {
		PlayerDataAccess playerDataAccess = new PlayerDataAccess();
	}	

    
	/*
     * "Create Player - Valid"
     * "Call createPlayer with a valid username, password.  Verify the return id is correct."
	 */
	@Test
	public void Test_CreatePlayer_Valid() {

		RegisterPlayerRequest request;
		String username, password;
		PlayerResponse player;

		//Setup Part 1 of the test.
		username = CreateRandomUsername();
		password = "password";
		request = new RegisterPlayerRequest(username, password);
		Message message = new Message();
	
		player = PlayerController.registerPlayer(message, request);

		assertTrue("Register Player should be successful", message.getErrorMessage().size() == 0 );
		int nextId = player.getPlayerId() + 1;

		//Setup Part 2 of the test.		
		username = CreateRandomUsername();
		password = "password";
		request = new RegisterPlayerRequest(username, password);
		message = new Message();
		
		player = PlayerController.registerPlayer(message, request);

		assertTrue("Register Player should be successful", message.getErrorMessage().size() == 0 );
		assertTrue("created player id is not sequential.", player.getPlayerId() == nextId );
		assertTrue(String.format("Response username should be %s", username), player.getUsername() == username);
	}


	/*
     * "Create Player - Dupe Username", 
     * "Call createPlayer with a valid username, password, Then call create player again with the same username.  Verify the proper error is returned.", 
	 */
	@Test
	public void Test_CreatePlayer_DupeUsername() {

		RegisterPlayerRequest request;
		String username, password;
		PlayerResponse player;

		//Setup Part 1 of the test.
		username = CreateRandomUsername();
		password = "password";
		request = new RegisterPlayerRequest(username, password);
		Message message = new Message();
	
		player = PlayerController.registerPlayer(message, request);

		assertTrue("Register Player should be successful", message.getErrorMessage().size() == 0 );


		//Setup Part 2 of the test.		
		//username =   Do not change the username.  
		password = "password";
		request = new RegisterPlayerRequest(username, password);
		message = new Message();
		
		player = PlayerController.registerPlayer(message, request);

		assertTrue("Register Player should not have been successful.  There is a duplicate username.", message.getErrorMessage().size() > 0);
		assertTrue("Incorrect Error Message provided.", message.getErrorMessage().contains("The username is already taken.") );
	}


	/*
     * "Create Player - Username is 6-18 characters", 
     * "Call createPlayer with a username that is too short and too long.  Verify the proper error is returned.", 
	 */
	@Test
	public void Test_CreatePlayer_UsernameSize() {

		RegisterPlayerRequest request;
		String username, password;
		PlayerResponse player;

		//Setup Part 1 of the test.
		username = "short";
		password = "password";
		request = new RegisterPlayerRequest(username, password);
		Message message = new Message();
	
		player = PlayerController.registerPlayer(message, request);

		assertTrue("Register Player should not have been successful.  The username is too short.", message.getErrorMessage().size() > 0 );
		assertTrue("Incorrect Error Message provided.", message.getErrorMessage().contains("The username is invalid.") );


		//Setup Part 2 of the test.		
		username = "UserNameMoreThanEighteen";
		password = "password";
		request = new RegisterPlayerRequest(username, password);
		message = new Message();
		
		player = PlayerController.registerPlayer(message, request);

		assertTrue("Register Player should not have been successful.  The username is too long", message.getErrorMessage().size() > 0 );
		assertTrue("Incorrect Error Message provided.", message.getErrorMessage().contains("The username is invalid.") );
	}

	/*
	 * Create Player - Username cannot contain symbols", 
     * "Call createPlayer with a username that contains symbols.  Verify the proper error is returned.", 
	 */
	@Test
	public void Test_CreatePlayer_UsernameSymbols() {

		RegisterPlayerRequest request;
		String username, password;
		PlayerResponse player;

		//Setup Part 1 of the test.
		username = "user.name";
		password = "password";
		request = new RegisterPlayerRequest(username, password);
		Message message = new Message();
	
		player = PlayerController.registerPlayer(message, request);

		assertTrue("Register Player should not have been successful.  The username contains symbols", message.getErrorMessage().size() > 0 );
		assertTrue( "Incorrect Error Message provided.", message.getErrorMessage().contains("The username is invalid.") );


		//Setup Part 2 of the test.		
		username = "user$name";
		password = "password";
		request = new RegisterPlayerRequest(username, password);
		message = new Message();
	
		player = PlayerController.registerPlayer(message, request);

		assertTrue("Register Player should not have been successful.  The username contains symbols", message.getErrorMessage().size() > 0 );
		assertTrue("Incorrect Error Message provided.", message.getErrorMessage().contains("The username is invalid.") );

	}

	/*
     * "Create Player - Password is 6-18 characters", 
     * "Call createPlayer with a password that is too short and too long.  Verify the proper error is returned.", 
	 */
	@Test
	public void Test_CreatePlayer_PasswordSize() {

		RegisterPlayerRequest request;
		String username, password;
		PlayerResponse player;

		//Setup Part 1 of the test.
		username = CreateRandomUsername();
		password = "short";
		request = new RegisterPlayerRequest(username, password);
		Message message = new Message();
	
		player = PlayerController.registerPlayer(message, request);

		assertTrue("Register Player should not have been successful.  The password is too short.", message.getErrorMessage().size() > 0 );
		assertTrue("Incorrect Error Message provided.", message.getErrorMessage().contains("The password is invalid.") );


		//Setup Part 2 of the test.		
		
		username = CreateRandomUsername();
		password = "PasswordMoreThanEighteen";
		request = new RegisterPlayerRequest(username, password);
		message = new Message();
		
		player = PlayerController.registerPlayer(message, request);

		assertTrue("Register Player should not have been successful.  The password is too long.", message.getErrorMessage().size() > 0 );
		assertTrue("Incorrect Error Message provided.", message.getErrorMessage().contains("The password is invalid.") );
	}

	/*
	 * Create Player - Password cannot contain symbols", 
     * "Call createPlayer with a password that contains symbols.  Verify the proper error is returned.", 
	 */
	@Test
	public void Test_CreatePlayer_PasswordSymbols() {

		RegisterPlayerRequest request;
		String username, password;
		PlayerResponse player;

		//Setup Part 1 of the test.
		username = CreateRandomUsername();
		password = "pass.word";
		request = new RegisterPlayerRequest(username, password);
		Message message = new Message();
	
		player = PlayerController.registerPlayer(message, request);

		assertTrue("Register Player should not have been successful.  The password contains symbols", message.getErrorMessage().size() > 0 );
		assertTrue("Incorrect Error Message provided.", message.getErrorMessage().contains("The password is invalid.") );


		//Setup Part 2 of the test.		
		username = CreateRandomUsername();
		password = "pass$word";
		request = new RegisterPlayerRequest(username, password);
		message = new Message();
	
		player = PlayerController.registerPlayer(message, request);

		assertTrue("Register Player should not have been successful.  The password contains symbols", message.getErrorMessage().size() > 0 );
		assertTrue("Incorrect Error Message provided.", message.getErrorMessage().contains("The password is invalid.") );

	}



}
