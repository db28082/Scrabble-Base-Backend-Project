package DomainObject;

import java.util.ArrayList;
import DataObject.GameDataObject;
import restService.Request.CreateGameRequest;

public class GameDomainObject {

	public int id;
	public int gameTypeId;

    public int currentTurnPlayer; 

	public int winnerPlayer;
	public String gameStatus;

	public int player1Id;
	public PlayerDomainObject player1;
	public int player1Score;

	public int player2Id;
	public PlayerDomainObject player2;
	public int player2Score;

	public BoardDomainObject board;


	public GameDomainObject(GameDataObject data) {
		this.id = data.id;
		this.gameTypeId = data.gameTypeId;
        this.currentTurnPlayer = data.currentTurnPlayer;
        this.winnerPlayer = data.winnerPlayer;
		this.gameStatus = data.gameStatus;

		this.player1Id = data.player1Id;
		this.player2Id = data.player2Id;

		this.player1Score = data.player1Score;
		this.player2Score = data.player2Score;

    }

	public static ArrayList<GameDomainObject> MapList(ArrayList<GameDataObject> dataList) {
		ArrayList<GameDomainObject> domainObjectList = new ArrayList<GameDomainObject>();
		
		for (int i=0; i<dataList.size(); i++) {
			GameDomainObject domain = new GameDomainObject(dataList.get(i));
			domainObjectList.add(domain);
		}

		return domainObjectList;
	}

	public GameDomainObject(CreateGameRequest data) {
		this.gameTypeId = data.getGameTypeId();
		this.player1Id = data.getPlayer1Id();
		this.player2Id = data.getPlayer2Id();
	}
	

}
