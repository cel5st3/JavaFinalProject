import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CardModel {

	public static final int DIMENSION = 6 ;
	private boolean [][] deck = new boolean [DIMENSION][DIMENSION];
	private ArrayList<String> cardValues = new ArrayList<>(List.of("@('_')@", "@( * O * )@", "--{,_,\\\">", "><(((('>", "=^..^=", "(\\_/)"));
	private int movesMade;
	private int cardsMatched;
	
	
	public CardModel() {
		
	System.out.println("CardModel created"); 
		Random randomNumberGenerator = new Random();
		
		for(int cards = 0; cards < 36; cards++) {
			int row, column;
			do
			{
				row = randomNumberGenerator.nextInt(DIMENSION);
				column = randomNumberGenerator.nextInt(DIMENSION);
				
			}while(deck[row][column]);
			deck[row][column] = true;
		}
	}
	public boolean selectCard(int row, int col) {
		movesMade++;
		boolean card1 = deck[row][col];
		return card1;
	}
	public boolean matchCard(int row, int col) {
		movesMade++;
		boolean card2 = deck[row][col];
		return card2;
	}
	
	
}


