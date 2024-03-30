package DataAccess;

import java.util.ArrayList;

import DataObject.BoardDataObject;

public class BoardDataAccess {

	private static ArrayList<BoardDataObject> boards;

	public BoardDataAccess() {
		initialize();
	}

	private void initialize() {
		boards = new ArrayList<BoardDataObject>();
	
		//Default Data
		boards.add( new BoardDataObject(1, 1, 
                                "QIHRYAB", "FORTUCE",
                                "               " +
                                "               " +
                                "               " +
                                "               " +
                                "     C         " +
                                "     O         " +
                                "     M         " +
                                "   APPLE       " +
                                "     U         " +
                                "     T   D     " +
                                "    SERVER     " +
                                "         I     " +
                                "         V     " +
                                "         E     " +
                                "     DISKS     ", 
                                "AAAAAAABDDEEEEEEFGGGHIIIIIIJLLLMNNNNNNOOOOOORRSTTTTUUWWXYZ"));




        boards.add( new BoardDataObject(2, 2, 
                                "AAAAAAA", "BBBBBBB",
                                "               " +
                                "               " +
                                "               " +
                                "               " +
                                "     C         " +
                                "     O         " +
                                "     M         " +
                                "   APPLE       " +
                                "     U         " +
                                "     T   D     " +
                                "    SERVER     " +
                                "         I     " +
                                "         V     " +
                                "         E     " +
                                "     DISKS     ", 
                                "AAAAAAAAABBCCDDDDEEEEEEEEEEEEFFGGGHHIIIIIIIIIJKLLLLMMNNNNNNOOOOOOOOPPQRRRRRRSSSSTTTTTTUUUUVVWWXYYZ"));


        boards.add( new BoardDataObject(3, 3, 
                                "AEILSTU", "AEMNOTW",
                                "               " +
                                "               " +
                                "               " +
                                "               " +
                                "               " +
                                "               " +
                                "               " +
                                "               " +
                                "               " +
                                "               " +
                                "               " +
                                "               " +
                                "               " +
                                "               " +
                                "               ", 
                                "NIAHAILAEDBEADIIIGJDCABIEGFDFIECEAAHEGIEEKRRERVOLNOOLSYWMZTOTPRRNORNSPSTOUOQNVUUYXT"));

        boards.add( new BoardDataObject(4, 4, 
                                "WAITERS", "SMELLXQ",
                                "               " +
                                "               " +
                                "               " +
                                "               " +
                                "               " +
                                "               " +
                                "               " +
                                "               " +
                                "               " +
                                "               " +
                                "               " +
                                "               " +
                                "               " +
                                "               " +
                                "               ", 
                                "ABC"));


	}

    


	private static int getNextId() {
		return boards.size()+1;
	}


	public static BoardDataObject getBoardById(int id) {
		
		for (int i=0; i < boards.size(); i++) {
			if (boards.get(i).id == id) {
				return boards.get(i);
			}
		}
		
		return null;
	}

	public static BoardDataObject getBoardByGameId(int gameId) {
		
		for (int i=0; i < boards.size(); i++) {
			if (boards.get(i).gameId == gameId) {
				return boards.get(i);
			}
		}
		
		return null;
	}

	public static BoardDataObject createBoard(BoardDataObject data) {
		//This needs to be implemented.
        return null;

	}




}