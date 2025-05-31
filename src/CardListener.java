/**
* Lead Author(s):
* @author Celeste Rodriguez
* @author Mariana Aguilar
*
* References:
* https://www.w3schools.com/java/java_type_casting.asp
* 
* Version: 2025-05-30
* 
* Responsibilities of class: Create card listener that listens to user input, and determines if cards are matchable or not
*/

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class CardListener implements ActionListener {
	// fields
	private CardModel model;
	private BoardView view;
	private Card card;
	private static Card firstCard = null;
	private static Card secondCard = null;
	private static boolean waiting = false;
	
	/**
	 * CardListener Constructor 
	 * @param model
	 * @param view
	 * @param card
	 */
	public CardListener(CardModel model, BoardView view, Card card) {
		this.model = model;
		this.view = view;
		this.card = card;
	}
	
	/**
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if(waiting) return;
		
		Card cardClicked = (Card) e.getSource();
		
		if (cardClicked == firstCard || cardClicked.isFaceUp() || cardClicked.isMatched()) return;

	    cardClicked.faceUp();
	    
		if(firstCard == null) {
			firstCard = cardClicked;
		}else if (secondCard == null && cardClicked != firstCard){
			secondCard = cardClicked;
			waiting = true;
		
		Timer delay = new Timer(2000, new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
            	if (firstCard != null && secondCard != null) {
                matchable(firstCard, secondCard);
            	} 
                firstCard = null;
                secondCard = null;
                waiting = false;
                view.updateUI(e);
            }
        });

		delay.setRepeats(false);
		delay.start();
	} }
	
	/**
	 * Purpose: Check if two cards are matchable or not
	 * @param firstCard
	 * @param secondCard
	 * @return true if matchable, false if not
	 */
	public boolean matchable(Card firstCard, Card secondCard)
	{	
		if(firstCard == null || secondCard == null) {
			System.out.println("CARD IS NULL!!!!");
			return false;
		}
		
		if (firstCard.getKey().equals(secondCard.getKey()))
		{
			System.out.println("MATCHED");
			removeCardsRemaining(firstCard, secondCard);
			firstCard.setMatched(true);
			secondCard.setMatched(true);
			
			// if first card instance of hint card, call card reveal
			if (firstCard instanceof HintCard) {
				((HintCard) firstCard).cardReveal();
			}
			
			// if second card instance of hint card, call card reveal
			else if (secondCard instanceof HintCard) {
				((HintCard) secondCard).cardReveal();
				
			} 
			// else, just keep both cards face up
			return true;
		}
		firstCard.faceDown();
		secondCard.faceDown();
		// if not matchable, keep both cards face down and tell user to try again
		System.out.println("Try again!");
		return false;
	}
	
	/**
	 * Purpose: Remove cards from cardsRemaining
	 * @param firstCard
	 * @param secondCard
	 */
	public void removeCardsRemaining(Card firstCard, Card secondCard) {
		view.cardsRemaining.remove(firstCard);
		view.cardsRemaining.remove(secondCard);
	}
}
