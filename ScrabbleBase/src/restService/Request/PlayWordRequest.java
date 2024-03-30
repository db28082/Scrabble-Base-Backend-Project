package restService.Request;

import java.util.ArrayList;

public class PlayWordRequest {

	private final int playerId;
	private final int gameId;
    private final ArrayList<Space> spaces;

	public PlayWordRequest(int playerId, int gameId, ArrayList<Space> spaces) {
		this.playerId = playerId;
        this.gameId = gameId;
        this.spaces = spaces;
		
	}

	public int getPlayerId() {
		return playerId;
	}

    public int getGameId() {
		return gameId;
	}

	public ArrayList<Space> getSpaces() {
		return spaces;
	}

	public static class Space {

        private final int column;
		private final int row;
        private final String letter;
        private final boolean isMyLetter;


		public Space(int column, int row, String letter, boolean isMyLetter) {
			this.column = column;
			this.row = row;
            this.letter = letter;
            this.isMyLetter = isMyLetter;
		}

		public int getColumn() {
			return column;
		}

		public int getRow() {
			return row;
		}

		public String getLetter() {
			return letter;
		}

		public boolean getIsMyLetter() {
			return isMyLetter;
		}
	}



}