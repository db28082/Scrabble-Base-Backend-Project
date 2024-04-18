package Controller;

import java.util.ArrayList;

import DomainObject.BoardDomainObject;
import DomainObject.GameDomainObject;
import DomainObject.SpaceDomainObject;
import Model.BoardModel;
import Model.GameModel;
import Model.GameTypeModel;
import Model.PlayerModel;
import restService.Message;
import restService.Request.CreateGameRequest;
import restService.Request.PlayWordRequest;
import restService.Response.GameResponse;

public class GameController {
	//The GameController will convert the request from the REST Request and the Domain Model.
	//Then it will call the Model or Service, and convert the response from the Domain model
	//to the REST Response.
	public static ArrayList<GameResponse> getAllGames(Message message) {
		try {
			ArrayList<GameDomainObject> domain = GameModel.GetAllGameSummaries(message);
			ArrayList<GameResponse> response = GameResponse.MapList(domain);
			return response;
		} catch (Exception ex) {
			message.addErrorMessage(ex.getMessage());
			return null;
		}
	}
	public static GameResponse getGameByGameIdAndPlayerId(Message message, int gameId, int playerId) {
		try {
			GameDomainObject domain = GameModel.GetGameDetailsByGameIdandPlayerId(message, gameId, playerId);
			GameResponse response = new GameResponse(domain);
			return response;
		} catch (Exception ex) {
			message.addErrorMessage(ex.getMessage());
			return null;
		}
	}
	public static GameResponse createGame(Message message, CreateGameRequest request) {
		try {

			// Check if player1Id exists
			if (!PlayerModel.ValidatePlayerById(request.getPlayer1Id())) {
				message.addErrorMessage("Player1Id does not exist.");
				return null;
			}
	
			// Check if player2Id exists
			if (!PlayerModel.ValidatePlayerById(request.getPlayer2Id())) {
				message.addErrorMessage("Player2Id does not exist.");
				return null;
			}
	
			// Check if gameTypeId exists
			if (!GameTypeModel.ValidateGameTypeById(request.getGameTypeId())) {
				message.addErrorMessage("GameTypeId does not exist.");
				return null;
			}

			GameDomainObject newGame = new GameDomainObject(request);

			GameDomainObject domain = GameModel.CreateGame(message, newGame);
			BoardDomainObject board = BoardModel.CreateBoard(message, domain.id, domain.gameTypeId);
			domain.board = board;

			GameResponse response = new GameResponse(domain);
		
			
			return response;
		} catch (Exception ex) {
			message.addErrorMessage(ex.getMessage());
			return null;
		}

	}

	public static GameResponse playWord(Message message, PlayWordRequest request) {
		try {
            ArrayList<SpaceDomainObject> spaces = SpaceDomainObject.MapFromRequestList(request.getSpaces());
			GameDomainObject domain = GameModel.PlayWord(message, request.getGameId(), request.getPlayerId(), spaces);
			return getGameByGameIdAndPlayerId(message, request.getGameId(), request.getPlayerId());
		} catch (Exception ex) {
			message.addErrorMessage(ex.getMessage());
			return null;
		}
	}
}
