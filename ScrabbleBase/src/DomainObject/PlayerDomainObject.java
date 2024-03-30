package DomainObject;

import java.util.ArrayList;
import DataObject.PlayerDataObject;
import restService.Request.RegisterPlayerRequest;

public class PlayerDomainObject {

	public int id;
	public String userName;
	public String password;


	public PlayerDomainObject(PlayerDataObject data) {
		this.id = data.id;
		this.userName = data.userName;
		this.password = data.password;
	}
	
	public static ArrayList<PlayerDomainObject> MapList(ArrayList<PlayerDataObject> dataList) {
		ArrayList<PlayerDomainObject> domainObjectList = new ArrayList<PlayerDomainObject>();
		
		for (int i=0; i<dataList.size(); i++) {
			PlayerDomainObject domain = new PlayerDomainObject(dataList.get(i));
			domainObjectList.add(domain);
		}

		return domainObjectList;
	}

	public PlayerDomainObject(RegisterPlayerRequest request) {
		this.userName = request.getUsername();
		this.password = request.getPassword();
	}

}
