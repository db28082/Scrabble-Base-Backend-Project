package DataAccess;

import java.util.ArrayList;

import DataObject.PlayerDataObject;

public class PlayerDataAccess {

	private static ArrayList<PlayerDataObject> players;

	public PlayerDataAccess() {
		initialize();
	}

	private void initialize() {
		players = new ArrayList<PlayerDataObject>();
	
		//Default Data
        //R. Florin
		players.add(new PlayerDataObject(1, "rflorin", "pass1"));
		//K. Findley
		players.add(new PlayerDataObject(2, "kfindley", "pass2"));
		//Player not used on any game.
		players.add(new PlayerDataObject(3, "unused", "pass2"));

        
	}

	private static int getNextId() {
		return players.size()+1;
	}

	public static ArrayList<PlayerDataObject> getAllPlayers() {
		return players;
	}

	public static PlayerDataObject getPlayerById(int id) {
		
		for (int i=0; i < players.size(); i++) {
			if (players.get(i).id == id) {
				return players.get(i);
			}
		}
		
		return null;
	}

	public static PlayerDataObject getPlayerByUsername(String username) {
		
		for (int i=0; i < players.size(); i++) {
			if (players.get(i).userName.equals(username)) {
				return players.get(i);
			}
		}
		
		return null;
	}

	public static PlayerDataObject createPlayer(PlayerDataObject data) {
		Message errorMessage = new Message();
    		if (!isValidUsername(data.userName)) {
        	errorMessage.addErrorMessage("The username is invalid.");
    	}

   	 if (!isUniqueUsername(data.userName)) {
     	   errorMessage.addErrorMessage("The username is already taken.");
   	 }

   	 if (!isValidPassword(data.password)) {
    	    errorMessage.addErrorMessage("The password is invalid.");
  	  }

   	 if (errorMessage.getErrorMessage().size() > 0) {
       	 // If there are error messages, return null indicating failure
        	return null;
    	}

   	 // Generate new player ID
  	  int playerId = getNextId();
  	  // Create new player object
  	  PlayerDataObject newPlayer = new PlayerDataObject(playerId, data.userName, data.password);
   	 // Add new player to the list
   	 players.add(newPlayer);
  	  // Return the newly created player object
  	  return newPlayer;
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

	private static boolean isUniqueUsername(String username) {
		for (PlayerDataObject player : players) {
			if (player.userName.equals(username)) {
				return false;
			}
		}
		return true;
	}
}
