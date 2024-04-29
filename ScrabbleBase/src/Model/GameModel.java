package Model;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;

import DataAccess.BoardDataAccess;
import DataAccess.DictionaryDataAccess;
import DataAccess.GameDataAccess;
import DataAccess.GameTypeDataAccess;
import DataAccess.PlayerDataAccess;
import DataObject.BoardDataObject;
import DataObject.GameDataObject;
import DataObject.PlayerDataObject;
import DomainObject.BoardDomainObject;
import DomainObject.GameDomainObject;
import DomainObject.PlayerDomainObject;
import DomainObject.SpaceDomainObject;
import restService.Message;


public class GameModel {
	

	public static ArrayList<GameDomainObject> GetAllGameSummaries(Message message) {
		ArrayList<GameDataObject> gameDataList = GameDataAccess.getAllGames();
		ArrayList<GameDomainObject> gameDomainList = GameDomainObject.MapList(gameDataList);
		return gameDomainList;
	}

	public static GameDomainObject GetGameDetailsByGameIdandPlayerId(Message message, int gameId, int playerId) {
		//Return game details
		// if the game is setup - return the board and the current players words, and current score
		// if the game is complete - return both words, and the scores
	

		//Get the Game Details
		GameDataObject gameData = GameDataAccess.getGameById(gameId);
		GameDomainObject gameDomain = new GameDomainObject(gameData);

		boolean isPlayer1 = false;
		boolean isPlayer2 = false;
		
		if (gameDomain.player1Id == playerId) {
			isPlayer1 = true;
		} else if (gameDomain.player2Id == playerId) {
			isPlayer2 = true;
		} else {
            message.addErrorMessage("Player " + playerId + " is not part of this game");
            return null;
		}


		BoardDomainObject boardDomain = BoardModel.GetBoardDetailsByGameId(message, gameDomain.id);
		gameDomain.board = boardDomain;
         
		if (boardDomain != null) {
            if (isPlayer1) {
                gameDomain.board.p2Tiles = "";
            } else if (isPlayer2) {
                gameDomain.board.p1Tiles = "";
            }
        }
	
    	return gameDomain;
	}


	public static GameDomainObject GetGameById(Message message, int id) {
		GameDataObject gameData = GameDataAccess.getGameById(id);
		GameDomainObject gameDomain = new GameDomainObject(gameData);
		return gameDomain;
	}


	public static boolean ValidateGameById(int id) {
		if (GameDataAccess.getGameById(id) == null)
			return false;
		return true;
	}

	public static GameDomainObject CreateGame(Message message, GameDomainObject domainGameToCreate) {
		try{
		 GameDataObject newGameData = new GameDataObject(-1,  domainGameToCreate.gameTypeId, domainGameToCreate.currentTurnPlayer, -1, "playing", domainGameToCreate.player1Id, domainGameToCreate.player2Id, 0, 0);
 
		 
		 GameDataObject data = GameDataAccess.createGame(newGameData);
		 
		 return new GameDomainObject(data);
		} catch(Exception ex){
 
		 message.addErrorMessage(ex.getMessage());
			 return null;
		}
	 }

	public static GameDomainObject PlayWord(Message message, int gameId, int playerId, ArrayList<SpaceDomainObject> spaces ) {
		//This needs to be implemented.
		//Get the Game Details
		GameDataObject gameData = GameDataAccess.getGameById(gameId);
		try {
			//Validate that the gameId is in the datastore 
			if (ValidateGameById(gameId) == true){
				if(gameData.gameStatus != "Playing"){ //game status is not in the Playing status 
					message.addErrorMessage("The game is not in the Playing status.");
				}
			} else {
				message.addErrorMessage("The gameId does not exist."); //gameId does not exists in the datastore 
			}
		} catch (NullPointerException e) {
			message.addErrorMessage("The gameId does not exist.");
		}

		//Validate playerId 
		if (PlayerModel.ValidatePlayerById(playerId) == true){
			if(gameData.player1Id == playerId || gameData.player2Id == playerId){ //playerId is either player 1 or player 2
				if(gameData.currentTurnPlayer == playerId){
					UpdatePlayerTiles(message, gameId, playerId, spaces);
					UpdateBoard(message, gameId, spaces);
					currentTurnPlayerChange(gameData, playerId);
				} else {
					message.addErrorMessage("It is not this players turn.");
				}
			} else {
				message.addErrorMessage("The playerId is invalid for the game.");
			}
		} else {
			message.addErrorMessage("The playerId does not exist.");
		}

        ScoreWord(message, gameId, playerId, spaces);

        return null;
    }


    public static boolean ValidateWordPlacement(Message message, int gameId, ArrayList<SpaceDomainObject> spaces) {
		if (spaces.isEmpty()) {
			message.addErrorMessage("The first word must cover the center square.");
			return false;
		}
	
		// Check if all spaces are in the same row or column
		int row = spaces.get(0).row;
		int column = spaces.get(0).column;
		boolean sameRow = true;
		boolean sameColumn = true;
		for (SpaceDomainObject space : spaces) {
			if (space.row != row)
				sameRow = false;
			if (space.column != column)
				sameColumn = false;
		}
	
		if (!sameRow && !sameColumn) {
			message.addErrorMessage("All letters played must be in the same row or column.");
			return false;
		}
		
		// Check if the first word covers the center square
    boolean coversCenter = false;
    for (SpaceDomainObject space : spaces) {
        if (space.row == 8 && space.column == 8) {
            coversCenter = true;
        }
    }

    if (!coversCenter && gameId == 3) {
        message.addErrorMessage("The first word must cover the center square.");
        return false;
    }
		return true;
	}

    public static void UpdateBoard(Message message, int gameId, ArrayList<SpaceDomainObject> spaces) {
		//This needs to be implemented.
		GameDataObject gameData = GameDataAccess.getGameById(gameId);
		BoardDataObject tileData = BoardDataAccess.getBoardByGameId(gameId);
		int m = 0, n = 0, indexPlacement = 0;
		String letter; 
		char[] character = new char [spaces.size()]; 
		StringBuilder ab = new StringBuilder(tileData.board);
		boolean validUpdate = true;
	
		for(int i = 0; i < spaces.size(); i++){
			if(message.getErrorMessage().size() > 0){
				validUpdate = false;
			} else {
				m = spaces.get(i).row; // row 
				n = spaces.get(i).column; //col
				indexPlacement = (m-1) * 15 + (n-1);
				letter = spaces.get(i).letter;
				character[i] = letter.charAt(0);
				ab.setCharAt(indexPlacement, character[i]);
				tileData.board = ab.toString();
			}
		}
    }

    public static void UpdatePlayerTiles(Message message, int gameId, int playerId, ArrayList<SpaceDomainObject> spaces) {
		//This needs to be implemented.
		/*Validate tiles played by the player are in their list of tiles*/
		GameDataObject gameData = GameDataAccess.getGameById(gameId);
		BoardDataObject tileData = BoardDataAccess.getBoardByGameId(gameId);
		boolean isValid; 
		String replacedTileData, word = "";

		for(SpaceDomainObject space: spaces){
			word += space.letter;
		}

		for(SpaceDomainObject space: spaces){
			if(tileData.board.contains(word)){
				isValid = true;
			} else {
				if(gameData.player1Id == playerId){
					if(tileData.p1Tiles.contains(space.letter)){
						if(space.isMyLetter == true){
							replacedTileData = tileData.p1Tiles.replaceFirst(space.letter, "*");
							tileData.p1Tiles = replacedTileData; //updates the player's tiles of the tiles that they played (replaced by *)	
						}
					} else {
						isValid = false; 
						if(space.isMyLetter != false){
							message.addErrorMessage("Player played an invalid tile.");
						}
					}
				}
				if(gameData.player2Id == playerId){
					if(tileData.p2Tiles.contains(space.letter)){
						if(space.isMyLetter == true){
							replacedTileData = tileData.p2Tiles.replaceFirst(space.letter, "*");
							tileData.p2Tiles = replacedTileData; //updates the player's tiles of the tiles that they played (replaced by *)
						}
					} else {
						isValid = false;
						if(space.isMyLetter != false){
							message.addErrorMessage("Player played an invalid tile.");
						}
					}
				}
			} 
		}
		ReassignPlayerTiles(tileData, gameId, playerId);	
    }

	public static void ReassignPlayerTiles(BoardDataObject tileData, int gameId, int playerId){
		GameDataObject gameData = GameDataAccess.getGameById(gameId);
		char[] letterBag = tileData.letterBag.toCharArray();
		StringBuilder newTiles;
		StringBuilder replacementTiles = new StringBuilder(tileData.letterBag);

		if(gameData.player1Id == playerId){
			newTiles = new StringBuilder(tileData.p1Tiles);
			for(int i = 0; i < tileData.p1Tiles.length(); i++){
				for(int j = 0; j < letterBag.length; j++){
					if(tileData.p1Tiles.charAt(i) == '*'){
						if(letterBag[j] != '*'){
							newTiles.setCharAt(i, letterBag[j]);
							replacementTiles.setCharAt(j, '*');
							letterBag[j] = '*';
							j = letterBag.length;
						}
					} else {
						j = letterBag.length;
					}
				}
			}
			if(tileData.letterBag.length() < tileData.p1Tiles.length()){
				for(int i = 0; i < newTiles.length(); i++){
					if(newTiles.charAt(i) == '*'){
						newTiles.deleteCharAt(i);
						i = 0;
					}
				}
			}
			tileData.p1Tiles = newTiles.toString();
			tileData.letterBag = replacementTiles.toString();
		}

		if(gameData.player2Id == playerId){
			newTiles = new StringBuilder(tileData.p2Tiles);
			for(int i = 0; i < tileData.p2Tiles.length(); i++){
				for(int j = 0; j < letterBag.length; j++){
					if(tileData.p2Tiles.charAt(i) == '*'){
						if(letterBag[j] != '*'){
							newTiles.setCharAt(i, letterBag[j]);
							replacementTiles.setCharAt(j, '*');
							letterBag[j] = '*';
							j = letterBag.length;
						}
					} else {
						j = letterBag.length;
					}
				}
			}
			if(tileData.letterBag.length() < tileData.p2Tiles.length()){
				for(int i = 0; i < newTiles.length(); i++){
					if(newTiles.charAt(i) == '*'){
						newTiles.deleteCharAt(i);
						i = 0;
					}
				}
			}
			tileData.p2Tiles = newTiles.toString();
			tileData.letterBag = replacementTiles.toString();
		}
	}

	public static void currentTurnPlayerChange(GameDataObject gameData, int playerId) {
		int nextPlayer = 0;
		if(gameData.player1Id == playerId){
			nextPlayer = gameData.player2Id; //if player 1 currentTurnPlayer changes to 2 
		}
		if (gameData.player2Id == playerId){
			nextPlayer = gameData.player1Id; //if player 2 currentTurnPlayer changes to 1
		}
		gameData.currentTurnPlayer = nextPlayer;
	}

    public static void ScoreWord (Message message, int gameId, int playerId, ArrayList<SpaceDomainObject> spaces) {
		//This needs to be implemented.
    }

}
