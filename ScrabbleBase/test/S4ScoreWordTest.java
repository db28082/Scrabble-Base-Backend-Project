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
import DomainObject.SpaceDomainObject;
import Model.GameModel;
import restService.Message;
import restService.Request.CreateGameRequest;
import restService.Request.PlayWordRequest;
import restService.Request.RegisterPlayerRequest;
import restService.Response.GameResponse;
import restService.Response.PlayerResponse;

public class S4ScoreWordTest {
 
    @Before 
    public void Setup() {

    	GameTypeDataAccess GameTypeDataAccess = new GameTypeDataAccess();
		PlayerDataAccess playerDataAccess = new PlayerDataAccess();
		BoardDataAccess boardDataAccess = new BoardDataAccess();
		GameDataAccess gameDataAccess = new GameDataAccess();
        DictionaryDataAccess dictionaryDataAccess = new DictionaryDataAccess();

    }	


   
	/*
     * "Score Word"
     * "Call Score Work with a game, player, and word. Validate the word is scored properly - Note: the game and player are not validated.  The letters in the word are not validated.
	 */
	@Test
	public void Test_ScoreWord_One() {

        Message message = new Message();
		ArrayList<SpaceDomainObject> spaces = new ArrayList<SpaceDomainObject>();
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(3, 7, "A", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(4, 7, "P", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(5, 7, "P", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(6, 7, "L", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(7, 7, "E", true)));
        GameModel.ScoreWord(message, 2, 1, spaces);


        GameResponse response = GameController.getGameByGameIdAndPlayerId(message, 2, 1);

		assertTrue("The service should be successful", message.getErrorMessage().size() == 0 );
        assertTrue("Player1 Score should update to 9 points", response.getPlayer1Score() == 9);
        assertTrue("Player2 Score should still be 0 points", response.getPlayer2Score() == 0);

	}

	/*
     * "Score Word"
     * "Call Score Work with a game, player, and word. Validate the word is scored properly - Note: the game and player are not validated.  The letters in the word are not validated.
	 */
	@Test
	public void Test_ScoreWord_AllLetters_NonDictionary() {

        Message message = new Message();
		ArrayList<SpaceDomainObject> spaces = new ArrayList<SpaceDomainObject>();
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(0, 0, "A", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(0, 0, "B", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(0, 0, "C", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(0, 0, "D", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(0, 0, "E", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(0, 0, "F", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(0, 0, "G", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(0, 0, "H", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(0, 0, "I", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(0, 0, "J", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(0, 0, "K", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(0, 0, "L", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(0, 0, "M", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(0, 0, "N", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(0, 0, "O", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(0, 0, "P", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(0, 0, "Q", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(0, 0, "R", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(0, 0, "S", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(0, 0, "T", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(0, 0, "U", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(0, 0, "V", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(0, 0, "W", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(0, 0, "X", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(0, 0, "Y", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(0, 0, "Z", true)));
        GameModel.ScoreWord(message, 2, 1, spaces);


        GameResponse response = GameController.getGameByGameIdAndPlayerId(message, 2, 1);

		assertTrue("The service should be successful", message.getErrorMessage().size() == 0 );
        assertTrue("Player1 Score should update to 87 points", response.getPlayer1Score() == 87);
        assertTrue("Player2 Score should still be 0 points", response.getPlayer2Score() == 0);

	}

	/*
     * "Score Word"
     * "Call Score Work with a game, player, and word. Validate the word is scored properly - Note: the game and player are not validated.  The letters in the word are not validated.
	 */
	@Test
	public void Test_ScoreWord_AllLetters_SeveralSmallTests() {



        Message message = new Message();
		ArrayList<SpaceDomainObject> spaces = new ArrayList<SpaceDomainObject>();

        //Test 1: THE : Points: 6

        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(0, 0, "T", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(0, 0, "H", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(0, 0, "E", true)));
        GameModel.ScoreWord(message, 2, 1, spaces);

        GameResponse response = GameController.getGameByGameIdAndPlayerId(message, 2, 1);

		assertTrue("The service should be successful", message.getErrorMessage().size() == 0 );
        assertTrue("Player1 Score should update to 6 points", response.getPlayer1Score() == 6);
        assertTrue("Player2 Score should still be 0 points", response.getPlayer2Score() == 0);


        //Test 1: FIVE : Points: 10
        spaces = new ArrayList<SpaceDomainObject>();
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(0, 0, "F", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(0, 0, "I", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(0, 0, "V", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(0, 0, "E", true)));
        GameModel.ScoreWord(message, 2, 1, spaces);

        response = GameController.getGameByGameIdAndPlayerId(message, 2, 1);

		assertTrue("The service should be successful", message.getErrorMessage().size() == 0 );
        assertTrue("Player1 Score should update to 16 points", response.getPlayer1Score() == 16);
        assertTrue("Player2 Score should still be 0 points", response.getPlayer2Score() == 0);


        //Test 1: BOXING : Points: 16
        spaces = new ArrayList<SpaceDomainObject>();
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(0, 0, "B", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(0, 0, "O", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(0, 0, "X", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(0, 0, "I", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(0, 0, "N", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(0, 0, "G", true)));
        GameModel.ScoreWord(message, 2, 1, spaces);

        response = GameController.getGameByGameIdAndPlayerId(message, 2, 1);

        assertTrue("The service should be successful", message.getErrorMessage().size() == 0 );
        assertTrue("Player1 Score should update to 32 points", response.getPlayer1Score() == 32);
        assertTrue("Player2 Score should still be 0 points", response.getPlayer2Score() == 0);
        
        //Test 1: WIZARDS : Points: 20
        spaces = new ArrayList<SpaceDomainObject>();
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(0, 0, "W", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(0, 0, "I", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(0, 0, "Z", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(0, 0, "A", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(0, 0, "R", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(0, 0, "D", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(0, 0, "S", true)));
        GameModel.ScoreWord(message, 2, 1, spaces);

        response = GameController.getGameByGameIdAndPlayerId(message, 2, 1);

        assertTrue("The service should be successful", message.getErrorMessage().size() == 0 );
        assertTrue("Player1 Score should update to 52 points", response.getPlayer1Score() == 52);
        assertTrue("Player2 Score should still be 0 points", response.getPlayer2Score() == 0);


        //Test 1: JUMP : Points: 15
        spaces = new ArrayList<SpaceDomainObject>();
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(0, 0, "J", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(0, 0, "U", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(0, 0, "M", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(0, 0, "P", true)));
        GameModel.ScoreWord(message, 2, 1, spaces);

        response = GameController.getGameByGameIdAndPlayerId(message, 2, 1);

        assertTrue("The service should be successful", message.getErrorMessage().size() == 0 );
        assertTrue("Player1 Score should update to 77 points", response.getPlayer1Score() == 67);
        assertTrue("Player2 Score should still be 0 points", response.getPlayer2Score() == 0);


        //Test 1: QUICKLY : Points: 25
        spaces = new ArrayList<SpaceDomainObject>();
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(0, 0, "Q", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(0, 0, "U", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(0, 0, "I", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(0, 0, "C", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(0, 0, "K", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(0, 0, "L", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(0, 0, "Y", true)));
        GameModel.ScoreWord(message, 2, 1, spaces);

        response = GameController.getGameByGameIdAndPlayerId(message, 2, 1);

        assertTrue("The service should be successful", message.getErrorMessage().size() == 0 );
        assertTrue("Player1 Score should update to 92 points", response.getPlayer1Score() == 92);
        assertTrue("Player2 Score should still be 0 points", response.getPlayer2Score() == 0);

    }


        
}
        