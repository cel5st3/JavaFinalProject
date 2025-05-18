import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
public class CardListener implements ActionListener{

	
	private CardModel model;
	private BoardView view;
	private Card card;
	
	public CardListener(CardModel model, BoardView view, Card card) {
		this.model = model;
		this.view = view;
		this.card = card;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Card cardClicked = (Card) e.getSource();
		
		if(!cardClicked.isFaceUp()) {
			cardClicked.faceUp();
		}else {
			cardClicked.faceDown();
		}
	}

	
	
}
