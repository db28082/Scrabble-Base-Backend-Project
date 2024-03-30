package DomainObject;

import java.util.ArrayList;
import DataObject.GameTypeDataObject;

public class GameTypeDomainObject {

	
	public int id;
	public String name;

	public GameTypeDomainObject(GameTypeDataObject data) {
		this.id = data.id;
		this.name = data.name;
	}
	
	public static ArrayList<GameTypeDomainObject> MapList(ArrayList<GameTypeDataObject> dataList) {
		ArrayList<GameTypeDomainObject> domainObjectList = new ArrayList<GameTypeDomainObject>();
		
		for (int i=0; i<dataList.size(); i++) {
			GameTypeDomainObject domain = new GameTypeDomainObject(dataList.get(i));
			domainObjectList.add(domain);
		}

		return domainObjectList;
	}
}
