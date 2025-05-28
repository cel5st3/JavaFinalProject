
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BoardView extends JFrame{

	private CardModel model;
	private JLabel movesMade;
	private JLabel cardsLeft;
	public int moves;
	List<Card> cardsRemaining = new ArrayList<>();
	
	public BoardView(CardModel model) {
		
		// ImageIcon imageIcon = new ImageIcon("images/Cloud.jpg");
		this.model = model;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		//deck
		JPanel deck = new JPanel();
//		JLabel cloud = new JLabel(imageIcon);
//		add(cloud);
		JFrame frame = new JFrame();
		frame.add(new JLabel("images/Cloud.jpg"));
//		JComponent cloud = new JComponent("images/Cloud.jpg");
		GridLayout cards = new GridLayout(CardModel.DIMENSION, CardModel.DIMENSION);
		deck.setLayout(cards);
		
		// find random col and row to add hint cards to board
		
		for(int row = 0; row < CardModel.DIMENSION; row++) {
			for(int col = 0; col < CardModel.DIMENSION; col++) {
				Card card;
				if (Math.random() < 0.25) {
					card = new HintCard(row, col, model.getImage(row, col), model.getKey(row, col));
				} else {
					card = new Card(row, col, model.getImage(row,col), model.getKey(row, col));
				}
				deck.add(card);
				card.addActionListener(new CardListener(model, this,card));
				cardsRemaining.add(card);
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
		
//		cardsLeft = new JLabel("Cards Left : 0");
//		side.add(cardsLeft);
		
		pack();
		setVisible(true);
		
		
	}
	
	public int getCardsRemaining() {
		return cardsRemaining.size();
	}
	
	public void updateUI(java.awt.event.ActionEvent evt) {
		moves++;
		movesMade.setText("Moves Made : " + moves);
	}
	
	
	public static void main(String[] args) {
		new BoardView(new CardModel());
		
	}
	
	
}
