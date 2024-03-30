package Controller;

import java.util.ArrayList;


import DomainObject.GameTypeDomainObject;
import Model.GameTypeModel;
import restService.Message;
import restService.Response.GameTypeResponse;




public class GameTypeController {
	//The GameTypeController will convert the request from the REST Request and the Domain Model.
	//Then it will call the Model or Service, and convert the response from the Domain model
	//to the REST Response.
	
	public static ArrayList<GameTypeResponse> getAllGameTypes(Message message) {
		try {
			ArrayList<GameTypeDomainObject> domain = GameTypeModel.GetAllGameTypes(message);
			ArrayList<GameTypeResponse> response = GameTypeResponse.MapList(domain);
			return response;
		} catch (Exception ex) {
			message.addErrorMessage(ex.getMessage());
			return null;
		}

	}
	public static GameTypeResponse getGameTypeById(Message message, int gameTypeId) {
		try {
			GameTypeDomainObject domain = GameTypeModel.GetGameTypeById(message, gameTypeId);
			GameTypeResponse response = new GameTypeResponse(domain);
			return response;
		} catch (Exception ex) {
			message.addErrorMessage(ex.getMessage());
			return null;
		}

	}

}
