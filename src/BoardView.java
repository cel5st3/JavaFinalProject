/**
* Lead Author(s):
* @author Celeste Rodriguez
* @author Mariana Aguilar
*
* References:
* https://stackoverflow.com/questions/8255738/is-there-a-stopwatch-in-java 
* (stopwatch timer) 
* 
* Version: 2025-05-30
* 
* Responsibilities of class: Define the board in which the game is played on
*/

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
//import java.util.Timer;
import javax.swing.Timer;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class BoardView extends JFrame {
	private CardModel model;
	private JLabel movesMade;
	private JLabel gameWon;
	public int moves;
	private Timer gameTimer;
	private long startTime;
	private JLabel timerLabel;
	private boolean timerStarted = false;

	List<Card> cardsRemaining = new ArrayList<>();

	/**
	 * Purpose: Create board view
	 * 
	 * @param model
	 */
	public BoardView(CardModel model) {
		this.model = model;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// background image
		ImageIcon imageIcon = new ImageIcon("images/Cloud.jpg");
		Image background = imageIcon.getImage();

		JPanel backgroundPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
			}
		};
		backgroundPanel.setLayout(new BorderLayout());

		Random randomHint = new Random();
		int rowHint = randomHint.nextInt(CardModel.DIMENSION);
		int colHint = randomHint.nextInt(CardModel.DIMENSION);
		String hintKey = model.getKey(rowHint, colHint);

		// deck
		JPanel deck = new JPanel(new GridLayout(CardModel.DIMENSION, CardModel.DIMENSION));

		for (int row = 0; row < CardModel.DIMENSION; row++) {
			for (int col = 0; col < CardModel.DIMENSION; col++) {

				File image = model.getImage(row, col);
				String key = model.getKey(row, col);
				Card card;
				if (key.equals(hintKey)) {
					card = new HintCard(row, col, image, key, cardsRemaining);
					System.out.println("HintCard place at (" + row + ", " + col + ")");
				} else {
					card = new Card(row, col, image, key);
				}
				cardsRemaining.add(card);
				deck.add(card);
				card.addActionListener(new CardListener(model, this, card));
			}
		}

		// top panel
		JPanel top = new JPanel();
		JLabel nameCard = new JLabel("Card Matching Game");
		top.add(nameCard);

		// instructions (west panel)
		JPanel instructions = new JPanel();
		instructions.setLayout(new BoxLayout(instructions, BoxLayout.Y_AXIS));
		instructions.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel instructionsLabel = new JLabel("Click to reveal a card. Match the cards");
		instructionsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		instructions.setAlignmentX(CENTER_ALIGNMENT);
		instructionsLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		instructions.add(instructionsLabel);//
		instructions.add(Box.createVerticalStrut(20));
		

		instructions.add(Box.createVerticalStrut(30));

		JButton retry = new JButton("Reset Game");
		retry.setAlignmentX(CENTER_ALIGNMENT);
		retry.addActionListener(e -> {
			if (gameTimer != null)
				gameTimer.stop();
			this.dispose();
			new BoardView(new CardModel());
		});
		instructions.add(retry);
		instructions.add(Box.createVerticalGlue());

		// side information (east panel)
		JPanel stats = new JPanel();
		stats.setLayout(new BoxLayout(stats, BoxLayout.Y_AXIS));
		stats.setAlignmentX(CENTER_ALIGNMENT);
		stats.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		
		movesMade = new JLabel("Moves Made : 0", JLabel.LEFT); 
		movesMade.setAlignmentX(CENTER_ALIGNMENT);
		
		timerLabel = new JLabel("Time: 00:00s", JLabel.LEFT); 
		timerLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		stats.add(movesMade);
		stats.add(Box.createVerticalStrut(5));
		stats.add(timerLabel);
		instructions.add(stats);
		instructions.add(Box.createVerticalGlue());

		// Add to background panel
		backgroundPanel.add(deck, BorderLayout.CENTER);
		backgroundPanel.add(top, BorderLayout.NORTH);
		backgroundPanel.add(instructions, BorderLayout.WEST);
		// backgroundPanel.add(side, BorderLayout.EAST);

		// Set backgroundPanel as the content pane
		setContentPane(backgroundPanel);

		gameWon = new JLabel();
		stats.add(gameWon);

		setContentPane(backgroundPanel);
		pack();
		setVisible(true);

		deck.setOpaque(false);
		top.setOpaque(false);
		instructions.setOpaque(false);
		stats.setOpaque(false);
	}

	
	/**
	 * Purpose: Return cards remaining
	 * 
	 * @return cardsRemaining.size
	 */
	public int getCardsRemaining() {
		return cardsRemaining.size();
	}

	
	/**
	 * Purpose: Check if game has been won
	 * 
	 * @return true if game won, otherwise false
	 */
	private boolean gameWon() {
		if (getCardsRemaining() == 0) {
			return true;
		}
		return false;
	}

	
	/**
	 * Purpose: Update UI
	 * 
	 * @param evt
	 */
	public void updateUI(java.awt.event.ActionEvent evt) {
		moves++;
		movesMade.setText("Moves Made : " + moves);
		ImageIcon grassBlock = new ImageIcon("images/grass.png");
		Image scaled = grassBlock.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		ImageIcon winImage = new ImageIcon(scaled);

		if (gameWon()) {
			if (gameTimer != null)
				gameTimer.stop();
			gameWon.setText("Game won!");
			long totalTime = (System.currentTimeMillis() - startTime) / 1000;
			long minutes = totalTime / 60;
			long seconds = totalTime % 60;
			String time = String.format("Time: %02d:%02d", minutes, seconds);
			JOptionPane.showMessageDialog(this,
					"You finished the game in " + moves + " moves and in " + time + "seconds!", "Game Complete",
					JOptionPane.INFORMATION_MESSAGE, winImage);
		}
	}

	
	/**
	 * purpose: checks if the timer has started
	 * 
	 * @return
	 */
	public boolean isTimerStarted() {
		return timerStarted;
	}

	
	/**
	 * purpose: starts timer
	 */
	public void startTimer() {
		timerStarted = true;
		startTime = System.currentTimeMillis();
		gameTimer = new Timer(1000, e -> {
			long elapsed = (System.currentTimeMillis() - startTime) / 1000;
			long minutes = elapsed / 60;
			long seconds = elapsed % 60;
			timerLabel.setText(String.format("Time: %02d:%02d", minutes, seconds));
		});
		gameTimer.start();
	}

	
	/**
	 * Purpose: Start game
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> songs = Arrays.asList("songs/cat.wav", "songs/dog.wav", "songs/haggstrom.wav",
				"songs/living_mice.wav", "songs/mice_on_venus.wav", "songs/minecraft.wav",
				"songs/subwoofer_lullaby.wav", "songs/sweden.wav", "songs/wet_hands.wav");

		BackgroundMusic backgroundMusic = new BackgroundMusic();
		backgroundMusic.playRandomSong(songs);

		new BoardView(new CardModel());
	}

}
