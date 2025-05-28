import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class CardModel {

	public static final int DIMENSION = 6;
	// for file folder
	// https://stackoverflow.com/questions/3634853/how-to-create-a-directory-in-java

	private File[][] cardImages = new File[DIMENSION][DIMENSION];
	private String[][] keys = new String[DIMENSION][DIMENSION];
	private HashMap<String, File> imageMap = new HashMap<>();

	/**
	 * Purpose: Sets each card in the deck to have an image and a key
	 */
	public CardModel() {

		System.out.println("CardModel created");
		File imageFolder = new File("images/cards");
		File[] images = imageFolder.listFiles();

		ArrayList<String> allKeys = new ArrayList<>();
		for (int i = 0; i < 18; i++) {
			String key = "card-" + i;
			File imageFile = images[i];
			imageMap.put(key, imageFile);
			allKeys.add(key);
			allKeys.add(key);

		}

		// https://stackoverflow.com/questions/16112515/how-to-shuffle-an-arraylist
		Collections.shuffle(allKeys); // shuffle cards

		int index = 0;
		for (int i = 0; i < DIMENSION; i++) {
			for (int j = 0; j < DIMENSION; j++) {
				String keyIndex = allKeys.get(index++);
				keys[i][j] = keyIndex;
				cardImages[i][j] = imageMap.get(keyIndex);
			}
		}

	}

	/**
	 * Purpose: gets the image assigned to the card
	 * 
	 * @param row that the card is at 
	 * @param col that the card is at 
	 * @return image
	 */
	public File getImage(int row, int col) {
		return cardImages[row][col];
	}

	/**
	 * Purpose: gets the key assigned to the card
	 * 
	 * @param row that the card is at 
	 * @param col that the card is at 
	 * @return key string
	 */
	public String getKey(int row, int col) {
		return keys[row][col];
	}

}
