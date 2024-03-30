package DataAccess;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryDataAccess {

	private static ArrayList<String> words;

	public DictionaryDataAccess() {
		initialize();
	}

	private void initialize() {
		words = new ArrayList<String>();
		

		try {
			File myObj = new File("c:/temp/scrabble/twl06.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
			  String data = myReader.nextLine();
			  words.add(data);
			}
			myReader.close();
		  } catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		  }


	}

 
	public static boolean validateWordInDictionary(String word) {
        //This needs to be implemented
        return false;
	}

}