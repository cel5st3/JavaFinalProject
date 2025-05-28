
public class Game {
	Card card;
	int matchCount;
	int cardsRemaining;
	long time;
	
	public Game()
	{
		this.card = card;
		this.cardsRemaining = cardsRemaining;
		this.matchCount = matchCount;
	}
	
//	private void updateUI() {
//		
//	}
	/*
	 * 
	 */
	public int getCount()
	{
		return matchCount;
	}
	
	/*
	 * 
	 */
	public void setCount()
	{
		this.matchCount = matchCount;
	}
	
	/*
	 * 
	 */
	public int getCardsRemaining()
	{
		// if card is face up, it should be removed from cards remaining
		return cardsRemaining;
	}
	
	/*
	 * 
	 */
	public void setCardsRemaining()
	{
		this.cardsRemaining = cardsRemaining;
	}
	
	/*
	 * 
	 */
	public long getTime() {
		return time;
	}
	
	/*
	 * 
	 */
	public void setTime() {
		this.time = time;
	}
	
//	private boolean gameWon() { 
//		if (getCardsRemaining() == 0) {
//			return true;
//		}
//		return false;
//	}
	/*
	 * Purpose: calls 
	 */
	public void main() {
		new BoardView(new CardModel());
	}

}
