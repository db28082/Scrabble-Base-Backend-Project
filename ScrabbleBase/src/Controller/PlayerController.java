package Controller;

import java.util.ArrayList;

import DomainObject.PlayerDomainObject;
import Model.PlayerModel;
import restService.Message;
import restService.Request.RegisterPlayerRequest;
import restService.Response.PlayerResponse;

public class PlayerController {
	//The PlayerController will convert the request from the REST Request and the Domain Model.
	//Then it will call the Model or Service, and convert the response from the Domain model
	//to the REST Response.
	
	public static ArrayList<PlayerResponse> getAllPlayers(Message message) {
		try{
			ArrayList<PlayerDomainObject> domain = PlayerModel.GetAllPlayers(message);
			ArrayList<PlayerResponse> response = PlayerResponse.MapList(domain);
			return response;				
		} catch (Exception ex) {
			message.addErrorMessage(ex.getMessage());
			return null;
		}

	}
	public static PlayerResponse getPlayerById(Message message, int playerId) {
		try {
			PlayerDomainObject domain = PlayerModel.GetPlayerById(message, playerId);
			PlayerResponse response = new PlayerResponse(domain);
			return response;
		} catch (Exception ex) {
			message.addErrorMessage(ex.getMessage());
			return null;
		}

	}
	
	public static PlayerResponse registerPlayer(Message message, RegisterPlayerRequest request) {
		try {
			PlayerDomainObject requestDomain = new PlayerDomainObject(request);

			PlayerDomainObject domain = PlayerModel.RegisterPlayer(message, requestDomain);
			PlayerResponse response = new PlayerResponse(domain);
			return response;
			
		} catch (Exception ex) {
			message.addErrorMessage(ex.getMessage());
			return null;
		}

	}
}
