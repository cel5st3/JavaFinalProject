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
	boolean matched;
	boolean faceUp;
	boolean selected;
	int row, col;
	private Image ogDownImage;
	private Image ogFaceImage;
	private Image faceUpImage;
	private Image faceDownImage;
	private double aspectRatio;
	private String key;

	/*
	 * Purpose: 
	 */
	public Card(int row, int col, File faceImageFile, String key) {
		this.row = row;
		this.col = col;
		this.key = key;
		matched = false;
		faceUp = false;
		selected = false;

		setPreferredSize(new Dimension(80, 80));

		setFaceDownImage(new File("images/minecraft.png"));
		setFaceUpImage(faceImageFile);
		setIcon(new ImageIcon(faceDownImage));
	}
	
	/*
	 * Purpose: Empty card constructor for simple card object 
	 */
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
//		
//				
//						
//	}

	/*
	 * Purpose: Returns the row index of the card 
	 * @return the row number of the card
	 */
	public int getRow() {
		return row;
	}

	/*
	 * Purpose: sets the row index of the card
	 * @param the row index of the card 
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/*
	 * Purpose: returns the column index of the card
	 * @return the column number of the card
	 */
	public int getCol() {
		return col;
	}

	/*
	 * Purpose: sets the column index of the card
	 * @param the column position to assign to this card 
	 */
	public void setCol(int col) {
		this.col = col;
	}

	/*
	 * Purpose: returns the key associated with this card which will be used to identify matching pairs !!!
	 * @return the key that represents this card
	 */
	public String getKey() {
		return key;
	}

	/*
	 * Purpose: 
	 */
	public void faceUp() {
		System.out.println("Flipping face up: " + key);
		System.out.println("Image null? " + (faceUpImage == null));
		faceUp = true;
		setIcon(new ImageIcon(faceUpImage));
		repaint();
	}

	/*
	 * Purpose: 
	 */
	public void faceDown() {
		faceUp = false;
		setIcon(new ImageIcon(faceDownImage));
		repaint();
	}

	/*
	 * Purpose: 
	 * @param file the image file to be used as the face-down image
	 */
	public void setFaceDownImage(File file) {
		try {
			ogDownImage = ImageIO.read(file).getScaledInstance((int) (getPreferredSize().width * 0.7),
					(int) (getPreferredSize().height * 0.7), Image.SCALE_DEFAULT);
			faceDownImage = ogDownImage;
			aspectRatio = faceDownImage.getWidth(null) / faceDownImage.getHeight(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Purpose:
	 * @param file the image file to be used as the face-up image
	 */
	public void setFaceUpImage(File file) {
		try {
			ogFaceImage = ImageIO.read(file).getScaledInstance((int) (getPreferredSize().width * 0.7),
					(int) (getPreferredSize().height * 0.7), Image.SCALE_DEFAULT);
			faceUpImage = ogFaceImage;

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Purpose: Checks whether the card is currently face up / revealed
	 * @return true if the card is face up 
	 */
	public boolean isFaceUp() {
		return faceUp;
	}

	/*
	 * Purpose: Checks whether the card has been matched with another
	 * @return if the card is matched
	 */
	public boolean isMatched() {
		return matched;
	}

	/*
	 * Purpose: Sets whether the card has been matched
	 * @param matched to mark the card as matched
	 */
	public void setMatched(boolean matched) {
		this.matched = matched;
	}
}
