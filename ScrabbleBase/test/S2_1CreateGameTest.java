import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

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
import restService.Response.GameResponse.Board;

public class S2_1CreateGameTest {
  
    @Before
    public void Setup() {
		GameTypeDataAccess GameTypeDataAccess = new GameTypeDataAccess();
		PlayerDataAccess playerDataAccess = new PlayerDataAccess();
		BoardDataAccess boardDataAccess = new BoardDataAccess();
		GameDataAccess gameDataAccess = new GameDataAccess();
    }	


    /*
        * "Create Game - Valid", 
        * "Call createGame with a valid player1, player2, and gametypeId.  Verify a board is returned properly.", 
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
        assertTrue("Game status should be returned as 'Playing'", game.getStatus() == "Playing");
        assertTrue("Winner Id should be returned as -1", game.getWinner() == -1);

        assertTrue("Player 1 Score should be returned as 0", game.getPlayer1Score() == 0);
        assertTrue("Player 2 Score should be returned as 0", game.getPlayer2Score() == 0);

        assertTrue(String.format("Response player1id should be %d", player1id), game.getPlayer1Id() == player1id);
        assertTrue(String.format("Response player2id should be %d", player2id), game.getPlayer2Id() == player2id);
        assertTrue(String.format("Response gametypeid should be %d", gametypeid), game.getGameTypeId() == gametypeid);


        Board board = game.getBoard();
        assertTrue("The board should have been created", board != null);
        assertTrue("The board should have been created with p1Tiles not null", board.getP1Tiles() != null);
        assertTrue("The board should have been created with p2Tiles not null", board.getP2Tiles() != null);
        assertTrue("The board should have been created with board not null", board.getBoard() != null);
        assertTrue("The board should have been created with 7 p1Tiles", board.getP1Tiles().length() == 7);
        assertTrue("The board should have been created with 7 p1Tiles", board.getP1Tiles().length() == 7);
        assertTrue("The board should have been created with board populated", board.getBoard().length() == 225);

    }

    /*
        * Create Game - Verify Created Tiles are Unique. 
        * Call createGame with a valid player1, player2, and gametypeId.  Call this 10000 times - all p1 and p2 tiles created
        *  must be unique. 
        */
        @Test
        public void Test_CreateGame_VerifyCreatedTilesAreUnique() {
    
            CreateGameRequest request;
            int player1id, player2id, gametypeid;
            GameResponse game;
    
            //Setup Part 1 of the test.
            player1id = 1;
            player2id = 2;
            gametypeid = 1;
            request = new CreateGameRequest(player1id, player2id, gametypeid);
            Message message = new Message();
        
            
            HashSet<String> tileSet = new HashSet<String>();

            for (int i = 0; i< 10000; i++) {
                game = GameController.createGame(message, request);
                assertTrue("Create Game should have been successful", message.getErrorMessage().size() == 0);

                String tiles = game.getBoard().getP1Tiles() + game.getBoard().getP2Tiles();
                tileSet.add(tiles);
            }
            
            assertTrue("All tile sets created were not unique.", tileSet.size() == 10000);
    
        }
    

        
    /*
        * Create Game - Verify Created Tiles are Valid, 
        * Call createGame with a valid player1, player2, and gametypeId.  Call this 10000 times - all p1 and p2 tiles created
        *  must be valid for the tile distributions, 
        */
        @Test
        public void Test_CreateGame_VerifyCreatedTilesAreValid() {
    
            CreateGameRequest request;
            int player1id, player2id, gametypeid;
            GameResponse game;
    
            //Setup Part 1 of the test.
            player1id = 1;
            player2id = 2;
            gametypeid = 1;
            request = new CreateGameRequest(player1id, player2id, gametypeid);
            Message message = new Message();
        
            
            for (int i = 0; i< 10000; i++) {
                game = GameController.createGame(message, request);
                assertTrue("Create Game should have been successful", message.getErrorMessage().size() == 0);

                String letterbag = "AAAAAAAAABBCCDDDDEEEEEEEEEEEEFFGGGHHIIIIIIIIIJKLLLLMMNNNNNNOOOOOOOOPPQRRRRRRSSSSTTTTTTUUUUVVWWXYYZ";

                String tiles = orderString(game.getBoard().getP1Tiles() + game.getBoard().getP2Tiles());
                
                int lastIndex = -1;
                for( int j = 0; j< 14; j++) {
                    lastIndex = letterbag.indexOf(tiles.charAt(j), lastIndex +1);

                    assertTrue("The created letter distribution is invalid.", lastIndex != -1);

                }
                
            }
            
        }

        private static String orderString(String unorderedString) {

              List<String> letters = Arrays.asList(unorderedString.split(""));
              Collections.sort(letters);
              String ordered = "";
              for (String letter : letters) {
                ordered += letter;
              }
              return ordered;
        }
    





}
        