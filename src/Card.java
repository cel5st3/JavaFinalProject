/**
* Lead Author(s):
* @author Celeste Rodriguez
* @author Mariana Aguilar
*
* References:
* 
* 
* Version: 2025-05-30
* 
* Responsibilities of class: Create a hint card, card that gives the player a hint with cardReveal
*/
import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


import javax.swing.Icon;

public class Card extends JButton {
	// fields
	boolean matched;
	boolean faceUp;
	boolean selected; 
	int row, col;
	//private static final Icon faceDownImage = new ImageIcon(Card.class.getResource("images/minecraft.png"));
	private Image ogDownImage;
	private Image ogFaceImage;
	private Image faceUpImage;
	protected Image faceDownImage;
	private double aspectRatio;
	protected String key;

	/**
	 * Purpose: Create card
	 * @param row
	 * @param col
	 * @param faceImageFile
	 * @param key
	 */
	public Card(int row, int col, File faceImageFile, String key) {
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
	
	public Card() {
	}
	
//	@Override
//	public void paintComponent(Graphics g) {
//		super.paintComponent(g);
//		
//		int buttonWidth = getSize().width;
//		int buttonHeight = getSize().height;
//		double targetWidth = buttonWidth * 0.7, targetHeight = buttonHeight * 0.7;
//		
//		if(buttonWidth < buttonHeight) {
//			targetHeight = targetWidth / aspectRatio;
//		}else {
//			targetWidth = targetHeight * aspectRatio;
//		}
//		if(faceUp) {
//			faceUpImage = ogFaceImage.getScaledInstance((int)targetWidth, (int)targetHeight, Image.SCALE_DEFAULT);
//			setIcon(new ImageIcon(faceUpImage));
//		}else {
//			faceDownImage =ogDownImage.getScaledInstance((int)targetWidth, (int)targetHeight, Image.SCALE_DEFAULT);
//			setIcon(new ImageIcon(faceDownImage));
//		}						
//	}
	
	/**
	 * Purpose: Get row
	 * @return row
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * Purpose: Set row
	 * @param row
	 */
	public void setRow(int row) {
		this.row = row;
	}
	
	/**
	 * Purpose: Get col
	 * @return col
	 */
	public int getCol() {
		return col;
	}
	
	/**
	 * Purpose: Set col
	 * @param col
	 */
	public void setCol(int col) {
		this.col = col;
	}
	
	/**
	 * Purpose: Get key
	 * @return key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Purpose: Define when a card is face up
	 */
	public void faceUp() {
		System.out.println("Flipping face up: " + key);
	    System.out.println("Image null? " + (faceUpImage == null));
		faceUp = true;
		setIcon(new ImageIcon(faceUpImage));
		repaint();
	}
	
	/**
	 * Purpose: Define when card is face down
	 */
	public void faceDown() {
		if (!matched) {
		faceUp = false;
		setIcon(new ImageIcon(faceDownImage));
		repaint();
		}
	}
	
	/**
	 * Purpose: Set face down image
	 * @param file
	 */
	public void setFaceDownImage(File file)
	{
		try {
			ogDownImage = ImageIO.read(file)
					.getScaledInstance(
							(int)(getPreferredSize().width * 0.7),
							(int)(getPreferredSize().height * 0.7),
							Image.SCALE_DEFAULT);
			faceDownImage = ogDownImage;
			aspectRatio = faceDownImage.getWidth(null) / faceDownImage.getHeight(null);
		}catch(IOException e ) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Purpose: Set face up image
	 * @param file
	 */
	public void setFaceUpImage(File file) {
		try {
			ogFaceImage = ImageIO.read(file)
					.getScaledInstance(
							(int)(getPreferredSize().width * 0.7),
							(int)(getPreferredSize().height * 0.7),
							Image.SCALE_DEFAULT);
			faceUpImage = ogFaceImage;
							
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Purpose: Check if a card is face up
	 * @return
	 */
	public boolean isFaceUp() {
		return faceUp;
	}
	
	/**
	 * Purpose: Check if card is matched
	 * @return
	 */
	public boolean isMatched() {
		return matched;
	}
	
	/**
	 * Purpose: Set matched
	 * @param matched
	 */
	public void setMatched(boolean matched) {
		this.matched = matched;
		}
	}
