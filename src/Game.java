public class Game {
	Card card; // fields
	int matchCount;
//	int cardsRemaining;
	long time;
	
	public Game() // constructor
	{
		this.card = card;
//		this.cardsRemaining = cardsRemaining;
		this.matchCount = matchCount;
	}
	
	/**
	 * Purpose: Get match count
	 * @return matchCount
	 */
	public int getCount()
	{
		return matchCount;
	}
	
	/**
	 * Purpose: Set match count
	 */
	public void setCount()
	{
		this.matchCount = matchCount;
	}
	
	/**
	 * Purpose: Get cards remaining
	 * @return cardsRemaining
	 */
//	public int getCardsRemaining()
//	{
//		// if card is face up, it should be removed from cards remaining
//		return cardsRemaining;
//	}
//	
//	/**
//	 * Purpose: Set cards remaining
//	 */
//	public void setCardsRemaining()
//	{
//		this.cardsRemaining = cardsRemaining;
//	}
	
	/**
	 * Purpose: Get time
	 * @return time
	 */
	public long getTime() {
		return time;
	}
	
	/**
	 * Purpose: Set time
	 */
	public void setTime() {
		this.time = time;
	}
	
	public void main() {
		new BoardView(new CardModel());
	}

}
