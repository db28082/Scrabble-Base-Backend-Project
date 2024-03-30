package restService.Response;


import java.util.ArrayList;

import DomainObject.PlayerDomainObject;

public class PlayerResponse {

	private final int playerId;
	private final String username;

	public PlayerResponse(int playerId, String username) {
		this.playerId = playerId;
		this.username = username;
	}

	public PlayerResponse(PlayerDomainObject domainObject) {
		this.playerId = domainObject.id;
		this.username = domainObject.userName;
	}

	public static ArrayList<PlayerResponse> MapList(ArrayList<PlayerDomainObject> domainList) {
		ArrayList<PlayerResponse> responseList = new ArrayList<PlayerResponse>();
		
		for (int i=0; i<domainList.size(); i++) {
			PlayerResponse response = new PlayerResponse(domainList.get(i));
			responseList.add(response);
		}

		return responseList;
	}

	public int getPlayerId() {
		return playerId;
	}

	public String getUsername() {
		return username;
	}
}
