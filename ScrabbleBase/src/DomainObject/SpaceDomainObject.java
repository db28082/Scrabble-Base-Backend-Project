package DomainObject;

import java.util.ArrayList;
import restService.Request.PlayWordRequest;

public class SpaceDomainObject {

    public int column;
    public int row;
    public String letter;
    public boolean isMyLetter;

	
	public SpaceDomainObject(PlayWordRequest.Space data) {
		this.column = data.getColumn();
		this.row = data.getRow();
		this.letter = data.getLetter();
		this.isMyLetter = data.getIsMyLetter();
	}
	

	public static ArrayList<SpaceDomainObject> MapFromRequestList(ArrayList<PlayWordRequest.Space> requestList) {
		ArrayList<SpaceDomainObject> domainObjectList = new ArrayList<SpaceDomainObject>();
		
		for (int i=0; i<requestList.size(); i++) {
			SpaceDomainObject domain = new SpaceDomainObject(requestList.get(i));
			domainObjectList.add(domain);
		}

		return domainObjectList;
	}


}
