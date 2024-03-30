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
		//This needs to be implemented.
        return null;

	}

	public static GameDomainObject PlayWord(Message message, int gameId, int playerId, ArrayList<SpaceDomainObject> spaces ) {
		//This needs to be implemented.

        UpdatePlayerTiles(message, gameId, playerId, spaces);
        UpdateBoard(message, gameId, spaces);
        ScoreWord(message, gameId, playerId, spaces);

        return null;

    
    }


    public static boolean ValidateWordPlacement(Message message, int gameId, ArrayList<SpaceDomainObject> spaces) {
		//This needs to be implemented.
        return false;

    } 


    public static void UpdateBoard(Message message, int gameId, ArrayList<SpaceDomainObject> spaces) {
		//This needs to be implemented.
    }

    public static void UpdatePlayerTiles(Message message, int gameId, int playerId, ArrayList<SpaceDomainObject> spaces) {
		//This needs to be implemented.
    }


    public static void ScoreWord (Message message, int gameId, int playerId, ArrayList<SpaceDomainObject> spaces) {
		//This needs to be implemented.
    }

}
