package DomainObject;

import DataObject.BoardDataObject;
import restService.Response.GameResponse;

public class BoardDomainObject {

	public int id;
	public int gameId;
	public String p1Tiles;
    public String p2Tiles;
    public String board;



	public BoardDomainObject(BoardDataObject data) {
		this.id = data.id;
		this.gameId = data.gameId;
        this.p1Tiles = data.p1Tiles;
		this.p2Tiles = data.p2Tiles;
		this.board = data.board;
	}
	
	public static GameResponse.Board MapToResponse(BoardDomainObject domain){
		if (domain == null)
			return null;
	
		GameResponse.Board response = new GameResponse.Board(domain.p1Tiles, domain.p2Tiles, domain.board);
		return response;
	}


}