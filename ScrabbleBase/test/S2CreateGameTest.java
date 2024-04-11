import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import Controller.GameController;
import DataAccess.BoardDataAccess;
import DataAccess.GameDataAccess;
import DataAccess.GameTypeDataAccess;
import DataAccess.PlayerDataAccess;
import restService.Message;
import restService.Request.CreateGameRequest;
import restService.Response.GameResponse;

public class S2CreateGameTest {
  
    @Before
    public void Setup() {
		GameTypeDataAccess GameTypeDataAccess = new GameTypeDataAccess();
		PlayerDataAccess playerDataAccess = new PlayerDataAccess();
		BoardDataAccess boardDataAccess = new BoardDataAccess();
		GameDataAccess gameDataAccess = new GameDataAccess();
    }	


    /*
        * "Create Game - Valid", 
        * "Call createGame with a valid player1, player2, and gametypeId.  Verify the return id is correct.", 
        */
    @Test
    public void Test_CreateGame_Valid() {

        CreateGameRequest request;
        int player1id, player2id, gametypeid;
        GameResponse game;

        //Setup Part 1 of the test.
        player1id = 1;
        player2id = 2;
        gametypeid = 1;
        request = new CreateGameRequest(player1id, player2id, gametypeid);
        Message message = new Message();
    
        game = GameController.createGame(message, request);

        assertTrue("Create Game should have been successful", message.getErrorMessage().size() == 0);
        int nextId = game.getGameId() + 1;

        //Setup Part 2 of the test.		
        player1id = 1;
        player2id = 2;
        gametypeid = 1;
        request = new CreateGameRequest(player1id, player2id, gametypeid);
        message = new Message();
        
        game = GameController.createGame(message, request);
        assertTrue("Create Game should have been successful", message.getErrorMessage().size() == 0 );
        assertTrue("Created game id is not sequential.", game.getGameId() == nextId );
        assertTrue("Game status should be returned as 'Playing'", "playing".equals(game.getStatus()));
        assertTrue("Winner Id should be returned as -1", game.getWinner() == -1);

        assertTrue("Player 1 Score should be returned as 0", game.getPlayer1Score() == 0);
        assertTrue("Player 2 Score should be returned as 0", game.getPlayer2Score() == 0);

        assertTrue(String.format("Response player1id should be %d", player1id), game.getPlayer1Id() == player1id);
        assertTrue(String.format("Response player2id should be %d", player2id), game.getPlayer2Id() == player2id);
        assertTrue(String.format("Response gametypeid should be %d", gametypeid), game.getGameTypeId() == gametypeid);

    }

    /*
        * "Create Game - Invalid fields", 
        * "Call createGame with an invalid player1, player2, or gametypeId.  Verify the proper error is returned.", 
        */
    @Test
    public void Test_CreateGame_InvalidFields() {
        //Setup();

        CreateGameRequest request;
        int player1id, player2id, gametypeid;
        GameResponse game;

        //Setup Part 1 of the test.
        player1id = 1645;
        player2id = 2;
        gametypeid = 1;
        request = new CreateGameRequest(player1id, player2id, gametypeid);
        Message message = new Message();
    
        game = GameController.createGame(message, request);

        assertTrue("Service should have failed, player 1 is invalid", message.getErrorMessage().size() > 0 );
        assertTrue("Incorrect Error Message provided.", message.getErrorMessage().contains("Player1Id does not exist.") );

        //Setup Part 2 of the test.		
        player1id = 1;
        player2id = 1645;
        gametypeid = 1;
        request = new CreateGameRequest(player1id, player2id, gametypeid);
        message = new Message();
        
        game = GameController.createGame(message, request);

        assertTrue("Service should have failed, player 2 is invalid", message.getErrorMessage().size() > 0 );
        assertTrue("Incorrect Error Message provided.", message.getErrorMessage().contains("Player2Id does not exist.") );

        //Setup Part 3 of the test.		
        player1id = 1;
        player2id = 2;
        gametypeid = 1645;
        request = new CreateGameRequest(player1id, player2id, gametypeid);
        message = new Message();
        
        game = GameController.createGame(message, request);

        assertTrue("Service should have failed, game type is invalid", message.getErrorMessage().size() > 0 );
        assertTrue("Incorrect Error Message provided.", message.getErrorMessage().contains("GameTypeId does not exist.") );
        
    }

        

    
    
}
        