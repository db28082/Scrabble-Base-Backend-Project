import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import restService.Response.GameResponse.Board;

public class S3_1PlayWordTest {
 
    @Before 
    public void Setup() {

    	GameTypeDataAccess GameTypeDataAccess = new GameTypeDataAccess();
		PlayerDataAccess playerDataAccess = new PlayerDataAccess();
		BoardDataAccess boardDataAccess = new BoardDataAccess();
		GameDataAccess gameDataAccess = new GameDataAccess();
        DictionaryDataAccess dictionaryDataAccess = new DictionaryDataAccess();
    }	
   
	/*
     * "Play Word - Validate the board is updated"
     * "Call PlayWord game, player, and word.  - Validate the board is returned properly for Player 1 and Player 2's turn"
	 */
	@Test
	public void Test_PlayWord_Valid() {
		PlayWordRequest request;
		GameResponse game;

		//Setup Part 1 of the test.
        //Player 1 starts the game with the word "LEAST"
        ArrayList<PlayWordRequest.Space> spaces = new ArrayList<PlayWordRequest.Space>();
        spaces.add(new PlayWordRequest.Space(4, 8, "L", true));
        spaces.add(new PlayWordRequest.Space(5, 8, "E", true));
        spaces.add(new PlayWordRequest.Space(6, 8, "A", true));
        spaces.add(new PlayWordRequest.Space(7, 8, "S", true));
        spaces.add(new PlayWordRequest.Space(8, 8, "T", true));

		request = new PlayWordRequest(1, 3, spaces);
		Message message = new Message();
	
		game = GameController.playWord(message, request);

		assertTrue("Play Word should be successful", message.getErrorMessage().size() == 0 );
        assertTrue("The game should be returned", game != null);
        assertTrue("CurrentTurnPlayer should change from 1 to 2", game.getCurrentTurnPlayer() == 2);

        Board board = game.getBoard();

        assertTrue("The board should be returned", game.getBoard() != null);
        assertTrue("The board should be returned with a non-null board", game.getBoard().getBoard() != null);


        List<String> boardTiles = Arrays.asList(game.getBoard().getBoard().split(""));
        
        assertTrue("The board does not contain the letter L in the correct place", boardTiles.get((8-1)*15 + (4-1)).equals("L"));
        assertTrue("The board does not contain the letter E in the correct place", boardTiles.get((8-1)*15 + (5-1)).equals("E"));
        assertTrue("The board does not contain the letter A in the correct place", boardTiles.get((8-1)*15 + (6-1)).equals("A"));
        assertTrue("The board does not contain the letter S in the correct place", boardTiles.get((8-1)*15 + (7-1)).equals("S"));
        assertTrue("The board does not contain the letter T in the correct place", boardTiles.get((8-1)*15 + (8-1)).equals("T"));


		//Setup Part 2 of the test.
        //Player 2 then plays the word "TEAM"

        spaces = new ArrayList<PlayWordRequest.Space>();
        spaces.add(new PlayWordRequest.Space(5, 7, "T", true));
        spaces.add(new PlayWordRequest.Space(5, 8, "E", false));
        spaces.add(new PlayWordRequest.Space(5, 9, "A", true));
        spaces.add(new PlayWordRequest.Space(5, 10, "M", true));

		request = new PlayWordRequest(2, 3, spaces);
		message = new Message();
	
		game = GameController.playWord(message, request);

		assertTrue("Play Word should be successful", message.getErrorMessage().size() == 0 );
        assertTrue("The game should be returned", game != null);
        assertTrue("CurrentTurnPlayer should change from 2 to 1", game.getCurrentTurnPlayer() == 1);

        board = game.getBoard();

        assertTrue("The board should be returned", game.getBoard() != null);
        assertTrue("The board should be returned with a non-null board", game.getBoard().getBoard() != null);


        boardTiles = Arrays.asList(game.getBoard().getBoard().split(""));
        
        assertTrue("The board should still contain the letter L where Player 1 places it", boardTiles.get((8-1)*15 + (4-1)).equals("L"));
        assertTrue("The board should still contain the letter E where Player 1 placed it", boardTiles.get((8-1)*15 + (5-1)).equals("E"));
        assertTrue("The board should still contain the letter A where Player 1 placed it", boardTiles.get((8-1)*15 + (6-1)).equals("A"));
        assertTrue("The board should still contain the letter S where Player 1 placed it", boardTiles.get((8-1)*15 + (7-1)).equals("S"));
        assertTrue("The board should still contain the letter T where Player 1 placed it", boardTiles.get((8-1)*15 + (8-1)).equals("T"));

        assertTrue("The board does not contain the letter T in the correct place", boardTiles.get((7-1)*15 + (5-1)).equals("T"));
        assertTrue("The board does not contain the letter A in the correct place", boardTiles.get((9-1)*15 + (5-1)).equals("A"));
        assertTrue("The board does not contain the letter M in the correct place", boardTiles.get((10-1)*15 + (5-1)).equals("M"));
	}

	/*
     * "Play Word - Validate the invalid tiles"
     * "Call PlayWord game, player, and word.  - The played tile F is not in the users list."
	 */
	@Test
	public void Test_PlayWord_InvalidTiles_1() {
		PlayWordRequest request;
		GameResponse game;

		//Setup Part 1 of the test.
        //Player 1 starts the game with the word "FEAST"
        ArrayList<PlayWordRequest.Space> spaces = new ArrayList<PlayWordRequest.Space>();
        spaces.add(new PlayWordRequest.Space(4, 8, "F", true));
        spaces.add(new PlayWordRequest.Space(5, 8, "E", true));
        spaces.add(new PlayWordRequest.Space(6, 8, "A", true));
        spaces.add(new PlayWordRequest.Space(7, 8, "S", true));
        spaces.add(new PlayWordRequest.Space(8, 8, "T", true));

		request = new PlayWordRequest(1, 3, spaces);
		Message message = new Message();
	
		game = GameController.playWord(message, request);

        assertTrue("Service should have failed, F is invalid letter", message.getErrorMessage().size() > 0 );
        assertTrue("Incorrect Error Message provided.", message.getErrorMessage().contains("Player played an invalid tile.") );

        GameResponse response = GameController.getGameByGameIdAndPlayerId(message, 2, 1);
        Board board = response.getBoard();

        List<String> boardTiles = Arrays.asList(game.getBoard().getBoard().split(""));
        
        assertTrue("The board should not have been updated with the letters played.", boardTiles.get((8-1)*15 + (4-1)).equals(" "));
        assertTrue("The board should not have been updated with the letters played.", boardTiles.get((8-1)*15 + (5-1)).equals(" "));
        assertTrue("The board should not have been updated with the letters played.", boardTiles.get((8-1)*15 + (6-1)).equals(" "));
        assertTrue("The board should not have been updated with the letters played.", boardTiles.get((8-1)*15 + (7-1)).equals(" "));
        assertTrue("The board should not have been updated with the letters played.", boardTiles.get((8-1)*15 + (8-1)).equals(" "));
	}

	/*
     * "Play Word - Validate the invalid tiles"
     * "Call PlayWord game, player, and word.  - The player has only one letter T."
	 */
	@Test
	public void Test_PlayWord_InvalidTiles_2() {
		PlayWordRequest request;
		GameResponse game;

		//Setup Part 1 of the test.
        //Player 1 starts the game with the word "FEAST"
        ArrayList<PlayWordRequest.Space> spaces = new ArrayList<PlayWordRequest.Space>();
        spaces.add(new PlayWordRequest.Space(4, 8, "T", true));
        spaces.add(new PlayWordRequest.Space(5, 8, "A", true));
        spaces.add(new PlayWordRequest.Space(6, 8, "S", true));
        spaces.add(new PlayWordRequest.Space(7, 8, "T", true));
        spaces.add(new PlayWordRequest.Space(8, 8, "E", true));

		request = new PlayWordRequest(1, 3, spaces);
		Message message = new Message();
	
		game = GameController.playWord(message, request);

        assertTrue("Service should have failed, The second T is an invalid letter", message.getErrorMessage().size() > 0 );
        assertTrue("Incorrect Error Message provided.", message.getErrorMessage().contains("Player played an invalid tile.") );

        GameResponse response = GameController.getGameByGameIdAndPlayerId(message, 2, 1);
        Board board = response.getBoard();

        List<String> boardTiles = Arrays.asList(game.getBoard().getBoard().split(""));
        
        assertTrue("The board should not have been updated with the letters played.", boardTiles.get((8-1)*15 + (4-1)).equals(" "));
        assertTrue("The board should not have been updated with the letters played.", boardTiles.get((8-1)*15 + (5-1)).equals(" "));
        assertTrue("The board should not have been updated with the letters played.", boardTiles.get((8-1)*15 + (6-1)).equals(" "));
        assertTrue("The board should not have been updated with the letters played.", boardTiles.get((8-1)*15 + (7-1)).equals(" "));
        assertTrue("The board should not have been updated with the letters played.", boardTiles.get((8-1)*15 + (8-1)).equals(" "));
	}     
}
        
