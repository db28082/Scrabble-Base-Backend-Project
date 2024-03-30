package Model;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import DataAccess.BoardDataAccess;
import DataObject.BoardDataObject;
import DomainObject.BoardDomainObject;
import restService.Message;

public class BoardModel {
	
	public static BoardDomainObject GetBoardDetailsById(Message message, int boardId) {
		BoardDataObject boardData = BoardDataAccess.getBoardById(boardId);
		BoardDomainObject boardDomain = new BoardDomainObject(boardData);

		return boardDomain;
	}

	public static BoardDomainObject GetBoardDetailsByGameId(Message message, int gameId) {
		BoardDataObject boardData = BoardDataAccess.getBoardByGameId(gameId);
        if (boardData == null) 
          return null;

		BoardDomainObject boardDomain = new BoardDomainObject(boardData);

		return boardDomain;
	}

	public static BoardDomainObject CreateBoard(Message message, int gameId, int gameTypeId) {
		//This needs to be implemented.
        return null;
	}


	public static Boolean ValidateWordForBoard(Message message, int gameId, String word) {
		//This needs to be implemented.
		return false;
	}

}
