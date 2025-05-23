
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
	
	public int getCount()
	{
		return matchCount;
	}
	
	public void setCount()
	{
		this.matchCount = matchCount;
	}
	
	public int getCardsRemaining()
	{
		// if card is face up, it should be removed from cards remaining
		return cardsRemaining;
	}
	
	public void setCardsRemaining()
	{
		this.cardsRemaining = cardsRemaining;
	}
	
	public long getTime() {
		return time;
	}
	
	public void setTime() {
		this.time = time;
	}
	
//	private boolean gameWon() { 
//		if (getCardsRemaining() == 0) {
//			return true;
//		}
//		return false;
//	}
	
	/**
	 * Purpose: See if two cards are matchable or not
	 * @param firstCard
	 * @param secondCard
	 * @return true if matchable, false if not
	 */
//	public boolean matchable(Card firstCard, Card secondCard)
//	{
//		if (firstCard == secondCard)
//		{
//			// if first card instance of hint card, put both cards face up and call card reveal
//			if (firstCard instanceof HintCard) {
//				firstCard.faceUp();
//				secondCard.faceUp();
//				((HintCard) firstCard).cardReveal();
//				return true;
//			}
//			
//			// if second card instance of hint card, put both cards face up and call card reveal
//			if (secondCard instanceof HintCard) {
//				firstCard.faceUp();
//				secondCard.faceUp();
//				((HintCard) secondCard).cardReveal();
//				return true;
//			} 
//			else {
//				// else, just put both cards face up
//				firstCard.faceUp();
//				secondCard.faceUp();
//				return true;
//			}
//		}
//		// if not matchable, keep both cards face down and tell user to try again
//		System.out.print("Try again!");
//		return false;
//	}
	
	public void main() {
		new BoardView(new CardModel());
	}

}
