import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

public class HintCard extends Card implements Hintable {
	
	Card card; // fields
	BoardView view;
	private List<Card> cardsRemaining = new ArrayList<>();
	
	public HintCard() // constructor
	{
		super();
		this.card = card;
		this.cardsRemaining = cardsRemaining;	
	}
	
	public HintCard(int	row, int col, File faceImageFile, String key) {
		this.row = row;
		this.col = col;
		this.key = key;
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
	//	Card[] cardsRemaining = getCardsRemaining();
		Timer timer = new Timer();
		
		// if less than two cards remaining, return
		if (getCardsRemaining() < 2) return;
		
		// get two random cards from cards remaining
		Random random = new Random();
		int firstIndex = random.nextInt(getCardsRemaining()- 1);
		int secondIndex = random.nextInt(getCardsRemaining() - 1);
				
		Card firstCard = cardsRemaining.get(firstIndex);
		Card secondCard = cardsRemaining.get(secondIndex);
				
		TimerTask task = new TimerTask() {
			
			int count =  2;
			
			@Override
			public void run() { // leave cards up for 2 seconds, turn face down after count falls under zero
				count--;
				if (count < 0) {
					firstCard.faceDown();
					secondCard.faceDown();
					timer.cancel();
				}
			}
		};
		timer.scheduleAtFixedRate(task, 0, 1000);
	}
	
	public int getCardsRemaining() {
		return cardsRemaining.size();
	}

	/**
	 * Purpose: Get cards remaining
	 * @return cardsRemaining
	 */
//	private Card[] getCardsRemaining()
//	{
//		for (int i; i < cardsRemaining.length; i++) {
//			if (cardsRemaining[i].isFaceup()) {
//				
//			}
//			Card[] faceUpCards = card.isFaceUp();	
//		}
//	}
}
