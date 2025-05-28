import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import javax.swing.Timer;

public class CardListener implements ActionListener {
	private CardModel model;
	private BoardView view;
	private Card card;
	private static Card firstCard = null;
	private static Card secondCard = null;
	private static boolean waiting = false;

	/**
	 * Purpose: CardListener Constructor, grabs information from Card, BoardView and
	 * CardModel
	 * 
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
	 * Purpose: Handles logic when a card is clicked, gets the information from the card,
	 * checks if it a card that has already been selected and calls call revel method if card
	 * keys match. Sets time when two cards are face up to face back down after two seconds
	 * @param action event 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (waiting) {
			return;
		}
		Card cardClicked = (Card) e.getSource();

		if (cardClicked == firstCard || cardClicked.isFaceUp() || cardClicked.isMatched())
			return;

		cardClicked.faceUp();

		if (firstCard == null) {
			firstCard = cardClicked;
			return;
		}
		if (secondCard == null && cardClicked != firstCard) {
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
	 * Purpose: Checks if the cards are null returns true if two cards have a same key
	 * @param firstCard  the first card to check 
	 * @param secondCard the second card to check 
	 * @return if the both cards keys match 
	 */
	public boolean matchable(Card firstCard, Card secondCard) {
		if (firstCard == null || secondCard == null) {
			System.out.println("CARD IS NULL!!!!");
			return false;
		}
		System.out.println("Try again!");
		return firstCard.getKey().equals(secondCard.getKey());
	}
	
	/**
	 * Purpose: Sets the cards to face up and checks if they are hint cards, will call hint card reveal method 
	 * @param firstCard  the first card to reveal and compare
	 * @param secondCard  the second card to reveal and compare 
	 */
	private void revealCards(Card firstCard, Card secondCard) {
		firstCard.faceUp();
		secondCard.faceUp();

		boolean firstHintCard = firstCard instanceof HintCard;
		boolean secondHintCard = secondCard instanceof HintCard;

		if (firstCard.getKey().equals(secondCard.getKey())) {
			if (firstHintCard && secondHintCard) {
				System.out.println("hintcard matched - calling cardReveal");
				((HintCard) firstCard).cardReveal();
			} else {
				System.out.println("regular cards match");
			}
			firstCard.setMatched(true);
			secondCard.setMatched(true);
		} else {
			System.out.println("not a match");
		}

	}
	
	/*
	 * Purpose: Resets the cards to null and sets waiting to false, allowed new cards to be used 
	 */
	private void reset() {
		firstCard = null;
		secondCard = null;
		waiting = false;
	}
}
