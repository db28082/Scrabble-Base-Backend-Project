package Model;
import java.util.ArrayList;

import DataAccess.GameTypeDataAccess;
import DataObject.GameTypeDataObject;
import DomainObject.GameTypeDomainObject;
import restService.Message;


public class GameTypeModel {
	
	public static GameTypeDomainObject GetGameTypeById(Message message, int id) {
		GameTypeDataObject gameTypeData = GameTypeDataAccess.getGameTypeById(id);
		GameTypeDomainObject gameTypeDomain = new GameTypeDomainObject(gameTypeData);
		
	
		return gameTypeDomain;
	}

	public static ArrayList<GameTypeDomainObject> GetAllGameTypes(Message message) {
		ArrayList<GameTypeDataObject> gameTypeDataList = GameTypeDataAccess.getAllGameTypes();
		ArrayList<GameTypeDomainObject> gameTypeDomainList = GameTypeDomainObject.MapList(gameTypeDataList);
		
		return gameTypeDomainList;
	}


	public static boolean ValidateGameTypeById(int id) {
		if (GameTypeDataAccess.getGameTypeById(id) == null)
			return false;
		return true;
	}
	

}
