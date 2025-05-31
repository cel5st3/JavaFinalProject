/**
* Lead Author(s):
* @author Celeste Rodriguez
* @author Mariana Aguilar
*
* References:
* https://stackoverflow.com/questions/3634853/how-to-create-a-directory-in-java
* //https://stackoverflow.com/questions/16112515/how-to-shuffle-an-arraylist
*
* Version: 2025-05-30
* 
* Responsibilities of class: Define the model for cards on the board
*/

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class CardModel {
	// fields
	public static final int DIMENSION = 6 ;
	private File[][] cardImages = new File[DIMENSION][DIMENSION];
	private String[][] keys = new String[DIMENSION][DIMENSION];
	private HashMap<String, File> imageMap = new HashMap<>();
	
	//constructor 
	public CardModel() {
		
	System.out.println("CardModel created");
	File imageFolder = new File("images/cards");
	File [] images = imageFolder.listFiles();
	
	ArrayList<String> allKeys = new ArrayList<>();
	for(int i = 0; i < 18; i++) 
	{
		String key = "card-" + i;
		File imageFile = images[i];
		imageMap.put(key, imageFile);
		allKeys.add(key);
		allKeys.add(key);
		
	}
	
	Collections.shuffle(allKeys); //shuffle cards
	
	int index = 0;
	for(int i = 0; i < DIMENSION; i++) {
		for(int j = 0; j < DIMENSION; j++) {
			String keyIndex = allKeys.get(index++);
			keys[i][j] = keyIndex;
			cardImages[i][j] = imageMap.get(keyIndex);
		}
	}	
}
	
	/**
	 * Purpose: Get card model image
	 * @param row
	 * @param col
	 * @return cardImages[row][col]
	 */
	public File getImage(int row, int col) {
		return cardImages[row][col];
	}
	
	/**
	 * Purpose: Get key of card model
	 * @param row
	 * @param col
	 * @return keys[row][col]
	 */
	public String getKey(int row, int col) {
		return keys[row][col];
	}	
}


