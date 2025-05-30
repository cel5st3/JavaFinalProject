
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BoardView extends JFrame{

	private CardModel model;
	private JLabel movesMade;
//	private JLabel cardsLeft;
	private JLabel gameWon;
	public int moves;
	List<Card> cardsRemaining = new ArrayList<>();
	
	public BoardView(CardModel model) {	
		this.model = model;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// background image
		ImageIcon imageIcon = new ImageIcon("images/Cloud.jpg");
		Image background = imageIcon.getImage();	
		
		JPanel backgroundPanel = new JPanel(){
			@Override
	        protected void paintComponent(Graphics g) {
				super.paintComponent(g);
	            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
	        }
	    };
	    backgroundPanel.setLayout(new BorderLayout());
	    
	    Random randomHint = new Random(); //-- added
	    int rowHint = randomHint.nextInt(CardModel.DIMENSION);
	    int colHint = randomHint.nextInt(CardModel.DIMENSION);
	    String hintKey = model.getKey(rowHint, colHint);
	    
	    
	    
		//deck
		JPanel deck = new JPanel(new GridLayout(CardModel.DIMENSION, CardModel.DIMENSION));
		//GridLayout cards = new GridLayout(CardModel.DIMENSION, CardModel.DIMENSION);
		//deck.setLayout(cards);
		
		//List<Card> allCards = new ArrayList<>();
		for(int row = 0; row < CardModel.DIMENSION; row++) {
			for(int col = 0; col < CardModel.DIMENSION; col++) {
			
				File image = model.getImage(row,col);
				String key = model.getKey(row, col);
				Card card;
				if(key.equals(hintKey)) {
					card = new HintCard(row, col, image, key);
					System.out.println("HintCard place at (" + row + ", " + col+ ")");
				}
				else
				{
					card = new Card(row, col, image, key);
				}
//				Card card = new Card(row, col, model.getImage(row, col), model.getKey(row, col));
//				allCards.add(card);
//				cardsRemaining.add(card);
				deck.add(card);
				card.addActionListener(new CardListener(model, this, card));
			}
		}
		
//		for (Card card : allCards) {
//			if (card.getKey() == findFirstHintCard().getKey() || card.getKey() == findSecondHintCard().getKey()) {
//				card = new HintCard(card.getRow(), card.getCol(), model.getImage(card.getRow(), card.getCol()), model.getKey(card.getRow(), card.getCol()));
//			}
//			deck.add(card);
//			card.addActionListener(new CardListener(model, this,card));
//			cardsRemaining.add(card);
//		}
//			
//		if (cardsRemaining == null || cardsRemaining.isEmpty()) {
//			return;
//		}
		
		// top panel
		JPanel top = new JPanel();
		JLabel nameCard = new JLabel("Card Matching Game");
		top.add(nameCard);
		
		//instructions (west panel)
		JPanel instructions = new JPanel();
		JLabel instructionsLabel = new JLabel("Click to reveal a card. Match the cards");
		instructions.add(instructionsLabel);
		JButton retry = new JButton("Retry");
		instructions.add(retry);
		
		//side information  (east panel)
		JPanel side = new JPanel();
		movesMade = new JLabel("Moves Made : 0");
		side.add(movesMade);
		
	    // Add to background panel
	    backgroundPanel.add(deck, BorderLayout.CENTER);
	    backgroundPanel.add(top, BorderLayout.NORTH);
	    backgroundPanel.add(instructions, BorderLayout.WEST);
	    backgroundPanel.add(side, BorderLayout.EAST);

	    // Set backgroundPanel as the content pane
	    setContentPane(backgroundPanel);
		
		
//		cardsLeft = new JLabel("Cards Left : 0");
//		side.add(cardsLeft);
		
	    gameWon = new JLabel();
		side.add(gameWon);
		
		setContentPane(backgroundPanel);
		pack();
		setVisible(true);
		
	    deck.setOpaque(false);
	    top.setOpaque(false);
	    instructions.setOpaque(false);
	    side.setOpaque(false);
	}
	
	public int getCardsRemaining() {
		return cardsRemaining.size();
	}
	
	public Card findFirstHintCard() {
		// find random col and row to add hint cards to board
		Random random = new Random();
		int randomKey = random.nextInt(cardsRemaining.size());
		Card randomCard = cardsRemaining.get(randomKey);
				
		return randomCard;
	}
	
	public Card findSecondHintCard() {
		// find random col and row to add hint cards to board
		Random random = new Random();
		int secondRandomKey = random.nextInt(cardsRemaining.size());
		Card secondRandomCard = cardsRemaining.get(secondRandomKey);
		
		return secondRandomCard;
	}
	
	/**
	 * Purpose: Check if game has been won
	 * @return true if game won, otherwise false
	 */
	private boolean gameWon() { 
		if (getCardsRemaining() == 0) {
			return true;
		}
		return false;
	}
	
	public void updateUI(java.awt.event.ActionEvent evt) {
		moves++;
		movesMade.setText("Moves Made : " + moves);
		
		if (gameWon() == true) {
			gameWon.setText("Game won!");
		}
	}
	
	public static void main(String[] args) {
		new BoardView(new CardModel());
	}
}
