import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import Controller.GameController;
import Controller.PlayerController;
import DataAccess.BoardDataAccess;
import DataAccess.DictionaryDataAccess;
import DataAccess.GameDataAccess;
import DataAccess.GameTypeDataAccess;
import DataAccess.PlayerDataAccess;
import restService.Message;
import restService.Request.CreateGameRequest;
import restService.Request.PlayWordRequest;
import restService.Request.RegisterPlayerRequest;
import restService.Response.GameResponse;
import restService.Response.PlayerResponse;

public class S3PlayWordTest {
 
    @Before 
    public void Setup() {

    	GameTypeDataAccess GameTypeDataAccess = new GameTypeDataAccess();
		PlayerDataAccess playerDataAccess = new PlayerDataAccess();
		BoardDataAccess boardDataAccess = new BoardDataAccess();
		GameDataAccess gameDataAccess = new GameDataAccess();
        DictionaryDataAccess dictionaryDataAccess = new DictionaryDataAccess();

    }	

	/*
     * "Play Word - Valid"
     * "Call PlayWord game, player, and word.  - The word is not validated in this sprint."
	 */
	@Test
	public void Test_PlayWord_Valid() {

		PlayWordRequest request;
		GameResponse game;

		//Setup Part 1 of the test.
		//username = CreateRandomUsername();
		//password = "password";

        ArrayList<PlayWordRequest.Space> spaces = new ArrayList<PlayWordRequest.Space>();
        spaces.add(new PlayWordRequest.Space(3, 7, "A", true));
        spaces.add(new PlayWordRequest.Space(4, 7, "P", true));
        spaces.add(new PlayWordRequest.Space(5, 7, "P", true));
        spaces.add(new PlayWordRequest.Space(6, 7, "L", true));
        spaces.add(new PlayWordRequest.Space(7, 7, "E", true));

		request = new PlayWordRequest(1, 2, spaces);
		Message message = new Message();
	
		game = GameController.playWord(message, request);

		assertTrue("Play Word should be successful", message.getErrorMessage().size() == 0 );
        assertTrue("The game should be returned", game != null);
        assertTrue("CurrentTurnPlayer should change from 1 to 2", game.getCurrentTurnPlayer() == 2);

	}

	/*
     * "Play Word - Verify the current turn alternates"
     * "Call PlayWord game, player, and word.  - The word is not validated in this sprint."
	 */
	@Test
	public void Test_PlayWord_Alternates_Valid() {

		PlayWordRequest request;
		GameResponse game;

        ArrayList<PlayWordRequest.Space> spaces = new ArrayList<PlayWordRequest.Space>();
        spaces.add(new PlayWordRequest.Space(3, 7, "A", true));
        spaces.add(new PlayWordRequest.Space(4, 7, "P", true));
        spaces.add(new PlayWordRequest.Space(5, 7, "P", true));
        spaces.add(new PlayWordRequest.Space(6, 7, "L", true));
        spaces.add(new PlayWordRequest.Space(7, 7, "E", true));

        //Part 1 - Player 1 plays
        request = new PlayWordRequest(1, 2, spaces);
		Message message = new Message();
	
		game = GameController.playWord(message, request);

		assertTrue("Register Player should be successful", message.getErrorMessage().size() == 0 );
        assertTrue("CurrentTurnPlayer should change from 1 to 2", game.getCurrentTurnPlayer() == 2);

        //Part 2 - Player 2 plays
		request = new PlayWordRequest(2, 2, spaces);
		message = new Message();
	
		game = GameController.playWord(message, request);

		assertTrue("Register Player should be successful", message.getErrorMessage().size() == 0 );
        assertTrue("CurrentTurnPlayer should change from 2 to 1", game.getCurrentTurnPlayer() == 1);

        //Part 3 - Player 1 plays
        request = new PlayWordRequest(1, 2, spaces);
        message = new Message();
    
        game = GameController.playWord(message, request);

        assertTrue("Register Player should be successful", message.getErrorMessage().size() == 0 );
        assertTrue("CurrentTurnPlayer should change from 1 to 2", game.getCurrentTurnPlayer() == 2);

        //Part 4 - Player 2 plays
        request = new PlayWordRequest(2, 2, spaces);
        message = new Message();
    
        game = GameController.playWord(message, request);

        assertTrue("Register Player should be successful", message.getErrorMessage().size() == 0 );
        assertTrue("CurrentTurnPlayer should change from 2 to 1", game.getCurrentTurnPlayer() == 1);
	}

    	/*
     * "Play Word - Invalid Fields"
     * "Call PlayWord invalid game, player, and word.  - The word is not validated in this sprint."
	 */
	@Test
	public void Test_PlayWord_Invalid() {

		PlayWordRequest request;
		GameResponse game;

		//Setup Part 1 of the test.

        ArrayList<PlayWordRequest.Space> spaces = new ArrayList<PlayWordRequest.Space>();
        spaces.add(new PlayWordRequest.Space(3, 7, "A", true));
        spaces.add(new PlayWordRequest.Space(4, 7, "P", true));
        spaces.add(new PlayWordRequest.Space(5, 7, "P", true));
        spaces.add(new PlayWordRequest.Space(6, 7, "L", true));
        spaces.add(new PlayWordRequest.Space(7, 7, "E", true));

        //Part 1 - Invalid GameId
        request = new PlayWordRequest(1, 100, spaces);
		Message message = new Message();
			       
        game = GameController.playWord(message, request);

        assertTrue("Service should have failed, gameId is invalid", message.getErrorMessage().size() > 0 );
        assertTrue("Incorrect Error Message provided.", message.getErrorMessage().contains("The gameId does not exist.") );
  
        //Part 2 - Game is not in playing status
        request = new PlayWordRequest(1, 1, spaces);
		message = new Message();
			       
        game = GameController.playWord(message, request);

        assertTrue("Service should have failed, game is not in Playing status", message.getErrorMessage().size() > 0 );
        assertTrue("Incorrect Error Message provided.", message.getErrorMessage().contains("The game is not in the Playing status.") );
     
        //Part 3 - Invalid PlayerId
        request = new PlayWordRequest(100, 2, spaces);
		message = new Message();
			       
        game = GameController.playWord(message, request);

        assertTrue("Service should have failed, playerId is not valid", message.getErrorMessage().size() > 0 );
        assertTrue("Incorrect Error Message provided.", message.getErrorMessage().contains("The playerId does not exist.") );

       //Part 4 - Invalid player for the game
        request = new PlayWordRequest(3, 2, spaces);
        message = new Message();
                   
        game = GameController.playWord(message, request);

        assertTrue("Service should have failed, playerId is not valid", message.getErrorMessage().size() > 0 );
        assertTrue("Incorrect Error Message provided.", message.getErrorMessage().contains("The playerId is invalid for the game.") );
        
        //Part 5 - Not the players turn
        request = new PlayWordRequest(2, 2, spaces);
        message = new Message();
                    
        game = GameController.playWord(message, request);

        assertTrue("Service should have failed, it is not this players turn", message.getErrorMessage().size() > 0 );
        assertTrue("Incorrect Error Message provided.", message.getErrorMessage().contains("It is not this players turn.") );
	}      
}
        
