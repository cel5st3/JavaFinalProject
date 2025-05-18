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
	//private static final Icon faceDownImage = new ImageIcon(Card.class.getResource("images/minecraft.png"));
	private Image ogDownImage;
	private Image ogFaceImage;
	private Image faceUpImage;
	private Image faceDownImage;
	private double aspectRatio;
	private String key;

	
	public Card(int row, int col, File faceImageFile, String key) {
		this.row = row;
		this.col = col;
		this.key = key;
		matched = false;
		faceUp = false;
		selected = false;
		
		//setIcon(faceDownImage);
		setPreferredSize(new Dimension(80,80));
		//setText("ʕ•ᴥ•ʔ"); s
		
		setFaceDownImage(new File("images/minecraft.png"));
		setFaceUpImage(faceImageFile);
		setIcon(new ImageIcon(faceDownImage));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		int buttonWidth = getSize().width;
		int buttonHeight = getSize().height;
		double targetWidth = buttonWidth * 0.7, targetHeight = buttonHeight * 0.7;
		
		if(buttonWidth < buttonHeight) {
			targetHeight = targetWidth / aspectRatio;
		}else {
			targetWidth = targetHeight * aspectRatio;
		}
		if(faceUp) {
			faceUpImage = ogFaceImage.getScaledInstance((int)targetWidth, (int)targetHeight, Image.SCALE_DEFAULT);
			setIcon(new ImageIcon(faceUpImage));
		}else {
			faceDownImage =ogDownImage.getScaledInstance((int)targetWidth, (int)targetHeight, Image.SCALE_DEFAULT);
			setIcon(new ImageIcon(faceDownImage));
		}
		
				
						
	}
	
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public String getKey() {
		return key;
	}

	public void faceUp() {
		faceUp = true;
		setIcon(new ImageIcon(faceUpImage));
		repaint();
	}
	public void faceDown() {
		faceUp = false;
		setIcon(new ImageIcon(faceDownImage));
		repaint();
	}
	
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
	public boolean isFaceUp() {
		return faceUp;
	}
	public boolean isMatched() {
		return matched;
	}
	public void setMatched(boolean matched) {
		this.matched = matched;
	}
}
