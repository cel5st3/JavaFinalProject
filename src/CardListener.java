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
			return;
		}
		if (secondCard == null && cardClicked != firstCard){
			secondCard = cardClicked;
			waiting = true;
			if (matchable(firstCard, secondCard)) {
	            revealCards(firstCard, secondCard);
	            reset();
	        } else {
	            Timer delay = new Timer(2000, new ActionListener() {
	                public void actionPerformed(ActionEvent ev) {
	                    firstCard.faceDown();
	                    secondCard.faceDown();
	                    reset();
	                }
	            });
	            delay.setRepeats(false);
	            delay.start();
	        }
	    }
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
		System.out.println("Try again!");
		return firstCard.getKey().equals(secondCard.getKey());
	}
	
	
	private void revealCards(Card firstCard, Card secondCard) {
		firstCard.faceUp();
		secondCard.faceUp();
		
		boolean firstHintCard = firstCard instanceof HintCard;
		boolean secondHintCard = secondCard instanceof HintCard;
		
		if(firstCard.getKey().equals(secondCard.getKey())) {
			if(firstHintCard && secondHintCard) {
				System.out.println("hintcard matched - calling cardReveal");
				((HintCard)firstCard).cardReveal();
			}else {
				System.out.println("regular cards match");
			}
			firstCard.setMatched(true);
			secondCard.setMatched(true);
		}else {
			System.out.println("not a match");
		}
			

	}

	private void reset() {
		firstCard = null;
		secondCard = null;
		waiting = false;
	}
}
