package DataObject;

public class BoardDataObject {

	public int id;
	public int gameId;
	public String p1Tiles;
    public String p2Tiles;
    public String board;
    public String letterBag;

	public BoardDataObject (int id, int gameId, String p1Tiles, String p2Tiles, String board, String letterBag) {
		this.id = id;
		this.gameId = gameId;
		this.p1Tiles = p1Tiles;
		this.p2Tiles = p2Tiles;
		this.board = board;
        this.letterBag = letterBag;
    }	
}