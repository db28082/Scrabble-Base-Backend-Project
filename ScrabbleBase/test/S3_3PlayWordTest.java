import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import DataAccess.BoardDataAccess;
import DataAccess.DictionaryDataAccess;
import DataAccess.GameDataAccess;
import DataAccess.GameTypeDataAccess;
import DataAccess.PlayerDataAccess;
import DomainObject.SpaceDomainObject;
import Model.GameModel;
import restService.Message;
import restService.Request.PlayWordRequest;
import restService.Response.GameResponse;

public class S3_3PlayWordTest {
 
    @Before 
    public void Setup() {

    	GameTypeDataAccess GameTypeDataAccess = new GameTypeDataAccess();
		PlayerDataAccess playerDataAccess = new PlayerDataAccess();
		BoardDataAccess boardDataAccess = new BoardDataAccess();
		GameDataAccess gameDataAccess = new GameDataAccess();
        DictionaryDataAccess dictionaryDataAccess = new DictionaryDataAccess();

    }	

  
	/*
     * "ValidateWordPlacement - Validate the first play coveres the center square."
     * "Call ValidateWordPlacement with a valid word.  Validate the word covers the center square"
	 */
	@Test
	public void Test_ValidateWordPlacement_ValidLocation_SameRow_FirstPlay() {
		PlayWordRequest request;
		GameResponse game;

		//Setup Part 1 of the test.
        //Player 1 starts the game with the word "LEAST"
        ArrayList<SpaceDomainObject> spaces = new ArrayList<SpaceDomainObject>();
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(4, 8, "L", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(5, 8, "E", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(6, 8, "A", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(7, 8, "S", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(8, 8, "T", true)));

		Message message = new Message();
	
        boolean isValid = GameModel.ValidateWordPlacement(message, 3, spaces);

		assertTrue("ValidateWordPlacement should be successful", message.getErrorMessage().size() == 0 );
        assertTrue("ValidateWordPlacement should have returned true", isValid == true);

	}

	/*
     * "ValidateWordPlacement - Validate the first play coveres the center square."
     * "Call ValidateWordPlacement with a valid word.  Validate the word covers the center square"
	 */
	@Test
	public void Test_ValidateWordPlacement_ValidLocation_SameColumn_FirstPlay() {
		PlayWordRequest request;
		GameResponse game;

		//Setup Part 1 of the test.
        //Player 1 starts the game with the word "LEAST"
        ArrayList<SpaceDomainObject> spaces = new ArrayList<SpaceDomainObject>();
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(8, 8, "L", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(8, 9, "E", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(8, 10, "A", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(8, 11, "S", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(8, 12, "T", true)));

		Message message = new Message();
	
        boolean isValid = GameModel.ValidateWordPlacement(message, 3, spaces);

		assertTrue("ValidateWordPlacement should be successful", message.getErrorMessage().size() == 0 );
        assertTrue("ValidateWordPlacement should have returned true", isValid == true);

	}

    	/*
     * "ValidateWordPlacement - Validate the first play covers the center square. - Invalid"
     * "Call ValidateWordPlacement with a valid word.  Validate the word covers the center square - Invalid"
	 */
	@Test
	public void Test_ValidateWordPlacement_InValidLocation_FirstPlay() {
		PlayWordRequest request;
		GameResponse game;

		//Setup Part 1 of the test.
        //Player 1 starts the game with the word "LEAST"
        ArrayList<SpaceDomainObject> spaces = new ArrayList<SpaceDomainObject>();
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(1, 1, "L", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(2, 1, "E", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(3, 1, "A", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(4, 1, "S", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(5, 1, "T", true)));

		Message message = new Message();
	
        boolean isValid = GameModel.ValidateWordPlacement(message, 3, spaces);

		assertTrue("ValidateWordPlacement should not be successful", message.getErrorMessage().size() > 0 );
        assertTrue("ValidateWordPlacement should have returned false", !isValid );
		assertTrue("Incorrect Error Message provided.", message.getErrorMessage().contains("The first word must cover the center square.") );
        
	}


    /*
     * "ValidateWordPlacement - Validate the word is placed properly in the same Column or Row. - Valid"
     * "Call ValidateWordPlacement with a valid word played in the same column"
	 */
	@Test
	public void Test_ValidateWordPlacement_SameColumn_Valid() {
		PlayWordRequest request;
		GameResponse game;

		//Setup Part 1 of the test.
        //Player 1 starts the game with the word "LEAST"
        ArrayList<SpaceDomainObject> spaces = new ArrayList<SpaceDomainObject>();
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(1, 1, "L", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(1, 2, "E", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(1, 3, "A", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(1, 4, "S", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(1, 5, "T", true)));

		Message message = new Message();
	
        boolean isValid = GameModel.ValidateWordPlacement(message, 2, spaces);

		assertTrue("ValidateWordPlacement should be successful", message.getErrorMessage().size() == 0 );
        assertTrue("ValidateWordPlacement should have returned true", isValid );
        
	}



    /*
     * "ValidateWordPlacement - Validate the word is placed properly in the same Column or Row. - Valid"
     * "Call ValidateWordPlacement with a valid word played in the same row"
	 */
	@Test
	public void Test_ValidateWordPlacement_SameRow_Valid() {
		PlayWordRequest request;
		GameResponse game;

		//Setup Part 1 of the test.
        //Player 1 starts the game with the word "LEAST"
        ArrayList<SpaceDomainObject> spaces = new ArrayList<SpaceDomainObject>();
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(1, 1, "L", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(2, 1, "E", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(3, 1, "A", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(4, 1, "S", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(5, 1, "T", true)));

		Message message = new Message();
	
        boolean isValid = GameModel.ValidateWordPlacement(message, 2, spaces);

		assertTrue("ValidateWordPlacement should be successful", message.getErrorMessage().size() == 0 );
        assertTrue("ValidateWordPlacement should have returned true", isValid );
        
	}



    /*
     * "ValidateWordPlacement - Validate the word is placed properly in the same Column or Row. - InValid"
     * "Call ValidateWordPlacement with an invalid word played in multiple columns and rows."
	 */
	@Test
	public void Test_ValidateWordPlacement_Invalid_OneInanotherRow() {
		PlayWordRequest request;
		GameResponse game;

		//Setup Part 1 of the test.
        //Player 1 starts the game with the word "LEAST"
        ArrayList<SpaceDomainObject> spaces = new ArrayList<SpaceDomainObject>();
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(1, 1, "L", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(2, 1, "E", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(3, 1, "A", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(4, 2, "S", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(5, 1, "T", true)));

		Message message = new Message();
	
        boolean isValid = GameModel.ValidateWordPlacement(message, 2, spaces);

		assertTrue("ValidateWordPlacement should not be successful", message.getErrorMessage().size() > 0 );
        assertTrue("ValidateWordPlacement should have returned false", !isValid );
		assertTrue("Incorrect Error Message provided.", message.getErrorMessage().contains("All letters played must be in the same row or column.") );
        
	}

    /*
     * "ValidateWordPlacement - Validate the word is placed properly in the same Column or Row. - InValid"
     * "Call ValidateWordPlacement with an invalid word played in multiple columns and rows."
	 */
	@Test
	public void Test_ValidateWordPlacement_Invalid_OneInanotherColumn() {
		PlayWordRequest request;
		GameResponse game;

		//Setup Part 1 of the test.
        //Player 1 starts the game with the word "LEAST"
        ArrayList<SpaceDomainObject> spaces = new ArrayList<SpaceDomainObject>();
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(1, 1, "L", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(1, 2, "E", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(1,3, "A", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(2, 4, "S", true)));
        spaces.add(new SpaceDomainObject(new PlayWordRequest.Space(1, 5, "T", true)));

		Message message = new Message();
	
        boolean isValid = GameModel.ValidateWordPlacement(message, 2, spaces);

		assertTrue("ValidateWordPlacement should not be successful", message.getErrorMessage().size() > 0 );
        assertTrue("ValidateWordPlacement should have returned false", !isValid );
		assertTrue("Incorrect Error Message provided.", message.getErrorMessage().contains("All letters played must be in the same row or column.") );
        
	}

}