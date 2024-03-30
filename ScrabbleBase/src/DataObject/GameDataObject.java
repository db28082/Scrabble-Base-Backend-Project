package DataObject;

public class GameDataObject {

	public int id;
	public int gameTypeId;

    public int currentTurnPlayer;
    public int winnerPlayer;
	public String gameStatus;


    public int player1Id;
	public int player2Id;

    public int player1Score;
    public int player2Score;

	public GameDataObject (int id, int gameTypeId, int currentTurnPlayer, int winnerPlayer, String gameStatus, int player1Id, int player2Id, int player1Score, int player2Score) {
		this.id = id;
		this.gameTypeId = gameTypeId;
        this.currentTurnPlayer = currentTurnPlayer;
		this.winnerPlayer = winnerPlayer;
		this.gameStatus = gameStatus;
		this.player1Id = player1Id;
		this.player2Id = player2Id;
        this.player1Score = player1Score;
        this.player2Score = player2Score;
	}
}