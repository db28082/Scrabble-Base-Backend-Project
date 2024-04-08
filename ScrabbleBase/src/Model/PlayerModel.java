package Model;
import java.util.ArrayList;

import DataAccess.PlayerDataAccess;
import DataObject.PlayerDataObject;
import DomainObject.PlayerDomainObject;
import restService.Message;


public class PlayerModel {
	
	public static PlayerDomainObject GetPlayerById(Message message, int id) {
		PlayerDataObject playerData = PlayerDataAccess.getPlayerById(id);
		PlayerDomainObject playerDomain = new PlayerDomainObject(playerData);
		return playerDomain;
	}

	public static ArrayList<PlayerDomainObject> GetAllPlayers(Message messasge) {
		ArrayList<PlayerDataObject> playerDataList = PlayerDataAccess.getAllPlayers();
		ArrayList<PlayerDomainObject> playerDomainList = PlayerDomainObject.MapList(playerDataList);
		return playerDomainList;
	}



	public static boolean ValidatePlayerById(int id) {
		if (PlayerDataAccess.getPlayerById(id) == null)
			return false;
		return true;
	}
	
	public static PlayerDomainObject RegisterPlayer(Message message, PlayerDomainObject domainPlayerToCreate) {
		PlayerDataObject playerDataToCreate = new PlayerDataObject(domainPlayerToCreate.id, domainPlayerToCreate.userName, domainPlayerToCreate.password);
		if (!isValidUsername(playerDataToCreate.userName)) {
			message.addErrorMessage("The username is invalid.");
			return null;
		}
	
		if (!isUniqueUsername(playerDataToCreate.userName)) {
			message.addErrorMessage("The username is already taken.");
			return null;
		}
	
		if (!isValidPassword(playerDataToCreate.password)) {
			message.addErrorMessage("The password is invalid.");
			return null;
		}
	
		PlayerDataObject createdPlayerData = PlayerDataAccess.createPlayer(playerDataToCreate);
		if (createdPlayerData == null) {
			// Player creation failed, possibly due to duplicate username
			message.addErrorMessage("Failed to register player.");
			return null;
		}
	
		// Player creation successful, return the created player domain object
		return new PlayerDomainObject(createdPlayerData);
	}

	//Methods to test the validity and uniqueness of the username and password
	private static boolean isValidPassword(String password) {
		// Password should be between 6 and 18 characters and can only contain numbers or letters
		return password.matches("[a-zA-Z0-9]{6,18}");
	}

	private static boolean isValidUsername(String username) {
    		// Username should be between 6 and 18 characters and can only contain numbers or letters
    		return username.matches("[a-zA-Z0-9]{6,18}");
	}

	public static boolean isUniqueUsername(String username) {
		ArrayList<PlayerDataObject> allPlayers = PlayerDataAccess.getAllPlayers();
		for (PlayerDataObject player : allPlayers) {
			if (player.userName.equals(username)) {
				return false;
			}
		}
		return true;
	}


}
