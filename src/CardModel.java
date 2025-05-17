import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CardModel {

	public static final int DIMENSION = 6 ;
	//private boolean [][] deck = new boolean [DIMENSION][DIMENSION];
	
//	private String[][] deck = new String[DIMENSION][DIMENSION];
//	private int movesMade;
//	private int cardsMatched;
//	private boolean selectedCard;
	
	//constructor 
	public CardModel() {
		
	System.out.println("CardModel created");
	ArrayList<String> all = new ArrayList<>();
	//for(int i = 0; i < cardValues.size(); i++) 
	{
		//String currVal = cardValues.get(i);
		//all.add(currVal);
		//all.add(currVal);
	}
	Random randomVal = new Random();
	int index = 0;
	for(int cards = 0; cards < 36; cards++) {
		int row, column;
		do
		{
			row = randomVal.nextInt(DIMENSION);
			column = randomVal.nextInt(DIMENSION);
				
		}
		while(deck[row][column] != null);
		deck[row][column] = all.get(index++);
			
		}
		
//		//shuffle cards

		
	}
	
	public String selectCard(int row, int col) {
		movesMade++;
		return deck[row][col];
		
	}
	
	
	
}


