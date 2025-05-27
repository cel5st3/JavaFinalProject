
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.File;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BoardView extends JFrame{

	private CardModel model;
	private JLabel movesMade;
	
	
	public BoardView(CardModel model) {
		
		this.model = model;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		//deck
		JPanel deck = new JPanel();
		GridLayout cards = new GridLayout(CardModel.DIMENSION, CardModel.DIMENSION);
		deck.setLayout(cards);

		for(int row = 0; row < CardModel.DIMENSION; row++) {
			for(int col = 0; col < CardModel.DIMENSION; col++) {
				Card card = new Card(row, col, model.getImage(row,col), model.getKey(row, col));
				deck.add(card);
				card.addActionListener(new CardListener(model, this,card));
			}
		}
		
		
		
		
		
		
		
		
		this.add(deck,BorderLayout.CENTER);
		//instructions 
		JPanel instructions = new JPanel();
		JLabel instructionsLabel = new JLabel("Click to reveal a card. Match the cards");
		instructions.add(instructionsLabel);
		this.add(instructions, BorderLayout.WEST);
		JButton retry = new JButton("Retry");
		instructions.add(retry);
		JPanel top = new JPanel();
		JLabel nameCard = new JLabel("Card Matching Game");
		top.add(nameCard);
		this.add(top, BorderLayout.NORTH);
		
		//side information 
		JPanel side = new JPanel();
		movesMade = new JLabel("Moves Made : 0");
		side.add(movesMade);
		this.add(side, BorderLayout.EAST);
		
		
		pack();
		setVisible(true);
		
		
	}
	
	public void updateUI(int moves) {
		movesMade.setText("Moves Made : " + moves);
		
	}
	
	
	public static void main(String[] args) {
		new BoardView(new CardModel());
		
	}
	
	
}
