package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import DataAccess.BoardDataAccess;
import DataObject.BoardDataObject;
import DomainObject.BoardDomainObject;
import restService.Message;

public class BoardModel {
	
	public static BoardDomainObject GetBoardDetailsById(Message message, int boardId) {
		BoardDataObject boardData = BoardDataAccess.getBoardById(boardId);
		BoardDomainObject boardDomain = new BoardDomainObject(boardData);

		return boardDomain;
	}

	public static BoardDomainObject GetBoardDetailsByGameId(Message message, int gameId) {
		BoardDataObject boardData = BoardDataAccess.getBoardByGameId(gameId);
        if (boardData == null) 
          return null;

		BoardDomainObject boardDomain = new BoardDomainObject(boardData);

		return boardDomain;
	}

	public static BoardDomainObject CreateBoard(Message message, int gameId, int gameTypeId) {

        // Generate random tiles for players
        String letterbag = "AAAAAAAAABBCCDDDDEEEEEEEEEEEEFFGGGHHIIIIIIIIIJKLLLLMMNNNNNNOOOOOOOOPPQRRRRRRSSSSTTTTTTUUUUVVWWXYYZ";
        String tiles = shuffleString(letterbag);
        String p1Tiles = tiles.substring(0, 7);
        String p2Tiles = tiles.substring(7, 14);

        // Initialize the main game board with empty tiles
        String[][] board = new String[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                board[i][j] = "";
            }
        }

        // Create a new BoardDataObject
        BoardDataObject boardData = new BoardDataObject(0, gameId, p1Tiles, p2Tiles, convertBoardToString(board), "");

        BoardDataObject board1 = DataAccess.BoardDataAccess.createBoard(boardData);
        return new BoardDomainObject(boardData);
    }

    // Helper method to generate random tiles
    public static String shuffleString(String input) {

        List<Character> characters = new ArrayList<>();

        // Convert String to list of Characters using for loop
        for (int i = 0; i < input.length(); i++) {
            characters.add(input.charAt(i));
        }

        // Shuffle the list
        Collections.shuffle(characters);

        // Convert the list back to String
        StringBuilder shuffledString = new StringBuilder();
        for (int i = 0; i < characters.size(); i++) {
            shuffledString.append(characters.get(i));
        }

        return shuffledString.toString();
    }

    public static String convertBoardToString(String[][] array) {
        StringBuilder stringBuilder = new StringBuilder();
        int rows = array.length;
        int columns = array[0].length;
        for (int i = 0; i < rows; i++) {
            String[] row = array[i];
            for (int j = 0; j < columns; j++) {
                stringBuilder.append(row[j]).append(" ");
            }
            // Remove the last space if it's not the last row
            if (i < rows - 1) {
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            }
            // Append newline character if it's not the last row
            if (i < rows - 1) {
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }


	public static Boolean ValidateWordForBoard(Message message, int gameId, String word) {
		//This needs to be implemented.
		return false;
	}

}
