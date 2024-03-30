package DataAccess;

import java.util.ArrayList;

import DataObject.GameDataObject;

public class GameDataAccess {

	private static ArrayList<GameDataObject> games;

	public GameDataAccess() {
		initialize();
	}

	private void initialize() {
		games = new ArrayList<GameDataObject>();
	
		//Default Data
		games.add( new GameDataObject (1, 1, 1, 1, "Complete", 1, 2, 100, 25));
        games.add( new GameDataObject (2, 1, 1, -1, "Playing", 1, 2, 0, 0));
        games.add( new GameDataObject (3, 1, 1, -1, "Playing", 1, 2, 0, 0));
        games.add( new GameDataObject (4, 1, 1, -1, "Playing", 1, 2, 0, 0));
        
	}

	private static int getNextId() {
		return games.size()+1;
	}

	public static ArrayList<GameDataObject> getAllGames() {
		return games;
	}

	public static GameDataObject getGameById(int id) {
		
		for (int i=0; i < games.size(); i++) {
			if (games.get(i).id == id) {
				return games.get(i);
			}
		}
		
		return null;
	}

	public static GameDataObject createGame(GameDataObject data) {
		//This needs to be implemented.
        return null;
	}

}