/**
* Lead Author(s):
* @author Celeste Rodriguez
* @author Mariana Aguilar
*
* References:
* ChatGPT
* https://stackoverflow.com/questions/8065532/how-to-randomly-pick-an-element-from-an-array
* 
* Version: 2025-05-30
* 
* Responsibilities of class: Create a hint card, card that gives the player a hint with cardReveal
*/

import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

public class HintCard extends Card implements Hintable {
	// fields
	Card card; 
	BoardView view;
    private List<Card> cardsRemaining = new ArrayList<>();
	
    /**
     * Purpose: Create hint card
     */
	public HintCard() 
	{
		super();
		this.card = card;
		this.cardsRemaining = cardsRemaining;	
	}
	
	/**
	 * Purpose: Create HintCard
	 * @param row
	 * @param col
	 * @param faceImageFile
	 * @param key
	 * @param cardsRemaining
	 */
	public HintCard(int	row, int col, File faceImageFile, String key, List<Card> cardsRemaining) {
		this.row = row;
		this.col = col;
		this.key = key;
		this.cardsRemaining = cardsRemaining;
		matched = false;
		faceUp = false;
		selected = false;
		
		setPreferredSize(new Dimension(80,80));
		
		setFaceDownImage(new File("images/grass.jpg"));
		setFaceUpImage(faceImageFile);
		setIcon(new ImageIcon(faceDownImage));
	}
	
	/**
	 * Purpose: Reveal two random cards once a hintable card is matched
	 */
	@Override
	public void cardReveal()
	{	
		System.out.println("HintCard: cardReveal triggered");
		
		List<Card> cards = new ArrayList<>(); // get cards from board
		for (Card c : cardsRemaining) {
			if (!c.isMatched() && !c.isFaceUp()) {
				cards.add(c);
			}
		}
		
		// if less than two cards remaining, return
		if (cardsRemaining.size() < 2) return;
		
		// get two random cards from cards remaining
		Random random = new Random();
		int firstIndex = random.nextInt(cardsRemaining.size());
		int secondIndex = random.nextInt(cardsRemaining.size());
	    do {
	    	secondIndex = random.nextInt(cardsRemaining.size()); // do while to ensure it gets two separate cards
	    } while (secondIndex == firstIndex);
				
		Card firstCard = cardsRemaining.get(firstIndex);
		Card secondCard = cardsRemaining.get(secondIndex);
				
		firstCard.faceUp();
		secondCard.faceUp();
		
		Timer task = new Timer(3000, new ActionListener() {
			public void actionPerformed(ActionEvent e) { // leave cards up for 3 seconds, turn face down after timer ends
					firstCard.faceDown();
					secondCard.faceDown();
				}
		});
		task.setRepeats(false);
		task.start();
	}
}
