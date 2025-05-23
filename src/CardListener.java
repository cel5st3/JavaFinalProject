import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import javax.swing.Timer;
public class CardListener implements ActionListener{
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
		// TODO Auto-generated method stub
		if(waiting) {
			return;
		}
		Card cardClicked = (Card) e.getSource();
		
		if (cardClicked == firstCard || cardClicked.isFaceUp() || cardClicked.isMatched()) return;

	    cardClicked.faceUp();
	    
		if(firstCard == null) {
			firstCard = cardClicked;
		}else if (secondCard == null && cardClicked != firstCard){
			secondCard = cardClicked;
			waiting = true;
		}
		
		Timer delay = new Timer(2000, new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                matchable(firstCard, secondCard);
                firstCard = null;
                secondCard = null;
                waiting = false;
            }
        });

		delay.setRepeats(false);
		delay.start();
	}
	/**
	 * 
	 * @param firstCard
	 * @param secondCard
	 * @return
	 */
	public boolean matchable(Card firstCard, Card secondCard)
	{
		if(firstCard == null || secondCard == null) {
			System.out.println("CARD IS NULL!!!!");
			return false;
		}
		if (firstCard.getKey().equals(secondCard.getKey()))
		{
			// if first card instance of hint card, put both cards face up and call card reveal
			if (firstCard instanceof HintCard) {
				firstCard.faceUp();
				secondCard.faceUp();
				((HintCard) firstCard).cardReveal();
			}
			
			// if second card instance of hint card, put both cards face up and call card reveal
			else if (secondCard instanceof HintCard) {
				firstCard.faceUp();
				secondCard.faceUp();
				((HintCard) secondCard).cardReveal();
				
			} 
			else {
				// else, just put both cards face up
				firstCard.setMatched(true);
				secondCard.setMatched(true);
				System.out.println("MATCHED");
				
			}
			return true;
			
		}
		firstCard.faceDown();
		secondCard.faceDown();
		// if not matchable, keep both cards face down and tell user to try again
		System.out.println("Try again!");
		return false;
	}
	
}
