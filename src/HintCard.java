import java.io.File;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class HintCard extends Card implements Hintable {
	Card card;
	Card[] cardsRemaining;
	
	public HintCard(int row, int col, File imageFile, String key, Card[] cardsRemaining) 
	{
		super(row, col, imageFile, key);
		//this.card = card;
		this.cardsRemaining = cardsRemaining;	
	}
	
	/**
	 * Purpose: Reveal two random cards once a hintable card is matched
	 */
	@Override
	public void cardReveal()
	{
		System.out.println("Revealing hint cards");
		Card[] cardsRemaining = getCardsRemaining();
		
		// if less than two cards remaining, return
		if (cardsRemaining.length < 2) {
			return;
		}
		
		// get two random cards from cards remaining
		Random random = new Random();
		int firstIndex = random.nextInt(cardsRemaining.length - 1);
		//int secondIndex = random.nextInt(cardsRemaining.length - 1);
		int secondIndex;
		do {
			secondIndex = random.nextInt(cardsRemaining.length - 1);
		}while(secondIndex == firstIndex);
		
		Card firstCard = cardsRemaining[firstIndex];
		Card secondCard = cardsRemaining[secondIndex];
		
		firstCard.faceUp();
		secondCard.faceUp();
		
//		

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				System.out.println("Flipping hint cards back down");
				firstCard.faceDown();
				secondCard.faceDown();
				timer.cancel();
			}
		}, 2000);
	}

	/**
	 * Purpose: Get cards remaining
	 * @return cardsRemaining
	 */
	private Card[] getCardsRemaining()
	{
		return cardsRemaining;
	}
}
