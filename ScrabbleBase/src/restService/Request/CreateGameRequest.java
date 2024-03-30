package restService.Request;

public class CreateGameRequest {

	private final int player1id;
	private final int player2id;
	private final int gametypeid;

	public CreateGameRequest(int player1Id, int player2Id, int gameTypeId) {
		this.player1id = player1Id;
		this.player2id = player2Id;
		this.gametypeid = gameTypeId;
	}

	public int getPlayer1Id() {
		return player1id;
	}

	public int getPlayer2Id() {
		return player2id;
	}

	public int getGameTypeId() {
		return gametypeid;
	}

}