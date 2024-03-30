package DataAccess;

import java.util.ArrayList;

import DataObject.GameTypeDataObject;

public class GameTypeDataAccess {
	
	private static ArrayList<GameTypeDataObject> gameTypes;
	
	public GameTypeDataAccess() {
		initialize();
	}

	private void initialize() {
		gameTypes = new ArrayList<GameTypeDataObject>();
		//Default Data
		//Classic
		gameTypes.add(new GameTypeDataObject(1, "Classic"));
	}



	public static ArrayList<GameTypeDataObject> getAllGameTypes() {
		return gameTypes;
	}

	public static GameTypeDataObject getGameTypeById(int id) {
		
		for (int i=0; i < gameTypes.size(); i++) {
			if (gameTypes.get(i).id == id) {
				return gameTypes.get(i);
			}
		}
		
		return null;
	}

}