package restService.Response;

import java.util.ArrayList;
import DomainObject.BoardDomainObject;
import DomainObject.GameDomainObject;

public class GameResponse {

	private final int gameId;
	private final int gameTypeId;
	private final int player1Id;
	private final int player2Id;
    private final int currentTurnPlayer;
	
	private final String status;
	private final int winner;
	private final Board board;
	
	private final int player1Score;
	private final int player2Score;

	public GameResponse(int gameId, int gameTypeId, int player1Id, int player2Id, int currentTurnPlayer, String status, int winner, Board board, int player1Score, int player2Score) {
		this.gameId = gameId;
		this.gameTypeId = gameTypeId;
		this.player1Id = player1Id;
		this.player2Id = player2Id;
        this.currentTurnPlayer = currentTurnPlayer;
		this.status = status;
		this.winner = winner;
		this.board = board;
		this.player1Score = player1Score;
		this.player2Score = player2Score;
    }
	

	public GameResponse(GameDomainObject domainObject) {
		this.gameId = domainObject.id;
		this.gameTypeId = domainObject.gameTypeId;
		this.player1Id = domainObject.player1Id;
		this.player2Id = domainObject.player2Id;
        this.currentTurnPlayer = domainObject.currentTurnPlayer;
		this.status = domainObject.gameStatus;
		this.winner = domainObject.winnerPlayer;
		this.board = BoardDomainObject.MapToResponse(domainObject.board);
		this.player1Score = domainObject.player1Score;
		this.player2Score = domainObject.player2Score;
	
	}

	public static ArrayList<GameResponse> MapList(ArrayList<GameDomainObject> domainList) {
		ArrayList<GameResponse> responseList = new ArrayList<GameResponse>();
		
		for (int i=0; i<domainList.size(); i++) {
			GameResponse response = new GameResponse(domainList.get(i));
			responseList.add(response);
		}

		return responseList;
	}

	public int getGameId() {
		return gameId;
	}

	public int getPlayer1Id() {
		return player1Id;
	}

	public int getPlayer2Id() {
		return player2Id;
	}

	public int getCurrentTurnPlayer() {
		return currentTurnPlayer;
	}

	public int getGameTypeId() {
		return gameTypeId;
	}

	public String getStatus() {
		return status;
	}

	public int getWinner() {
		return winner;
	}

	public Board getBoard() {
		return board;
	}

	public int getPlayer1Score() {
		return player1Score;
	}

	public int getPlayer2Score() {
		return player2Score;
	}
	

	public static class Board {

        private final String p1Tiles;
		private final String p2Tiles;

        private final String board;

		public Board(String p1Tiles, String p2Tiles, String board) {
			this.p1Tiles = p1Tiles;
			this.p2Tiles = p2Tiles;
            this.board = board;
		}

		public String getP1Tiles() {
			return p1Tiles;
		}

		public String getP2Tiles() {
			return p2Tiles;
		}

		public String getBoard() {
			return board;
		}
	}

}


