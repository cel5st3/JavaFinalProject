import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class HintCard extends Card implements Hintable {
	Card card;
	Card[] cardsRemaining;
	
	public HintCard() 
	{
		super();
		this.card = card;
		this.cardsRemaining = cardsRemaining;	
	}
	
	/**
	 * Purpose: Reveal two random cards once a hintable card is matched
	 */
	@Override
	public void cardReveal()
	{
		Card[] cardsRemaining = getCardsRemaining();
		Timer timer = new Timer();
		
		// if less than two cards remaining, return
		if (cardsRemaining.length < 2) {
			return;
		}
		
		// get two random cards from cards remaining
		Random random = new Random();
		int firstIndex = random.nextInt(cardsRemaining.length - 1);
		int secondIndex = random.nextInt(cardsRemaining.length - 1);
				
		Card firstCard = cardsRemaining[firstIndex];
		Card secondCard = cardsRemaining[secondIndex];
				
		TimerTask task = new TimerTask() {
			
			int count =  2;
			
			@Override
			public void run() {
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

	/**
	 * Purpose: Get cards remaining
	 * @return cardsRemaining
	 */
	private Card[] getCardsRemaining()
	{
		return cardsRemaining;
	}
}
