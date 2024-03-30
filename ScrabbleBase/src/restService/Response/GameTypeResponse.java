package restService.Response;
import java.util.ArrayList;
import DomainObject.GameTypeDomainObject;

public class GameTypeResponse {

	private final int gameTypeId;
	private final String name;

	public GameTypeResponse(int gameTypeId, String name) {
		this.gameTypeId = gameTypeId;
		this.name = name;
	}

	public GameTypeResponse(GameTypeDomainObject domainObject) {
		this.gameTypeId = domainObject.id;
		this.name = domainObject.name;
	}

	public static ArrayList<GameTypeResponse> MapList(ArrayList<GameTypeDomainObject> domainList) {
		ArrayList<GameTypeResponse> responseList = new ArrayList<GameTypeResponse>();
		
		for (int i=0; i<domainList.size(); i++) {
			GameTypeResponse response = new GameTypeResponse(domainList.get(i));
			responseList.add(response);
		}

		return responseList;
	}

	public int getGameTypeId() {
		return gameTypeId;
	}

	public String getName() {
		return name;
	}


	
	public static class LetterCube {

		private final int diceNumber;
		private final String side1;
		private final String side2;
		private final String side3;
		private final String side4;
		private final String side5;
		private final String side6;

		public LetterCube(int diceNumber, String side1, String side2, String side3, String side4, String side5, String side6) {
			this.diceNumber = diceNumber;
			this.side1 = side1;
			this.side2 = side2;
			this.side3 = side3;
			this.side4 = side4;
			this.side5 = side5;
			this.side6 = side6;
		}

		public int getDiceNumber() {
			return diceNumber;
		}

		public String getSide1() {
			return side1;
		}

		public String getSide2() {
			return side2;
		}

		public String getSide3() {
			return side3;
		}

		public String getSide4() {
			return side4;
		}

		public String getSide5() {
			return side5;
		}

		public String getSide6() {
			return side6;
		}

	}

}
