import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.Dimension;

import javax.swing.Icon;

public class Card extends JButton {
	boolean matched;
	boolean faceUp;
	boolean selected; 
	int row, col;
	//private static final Icon faceDownImage = new ImageIcon(Card.class.getResource("images/minecraft.png"));
	

	
	public Card(int row, int col) {
		this.row = row;
		this.col = col;
		matched = false;
		faceUp = false;
		selected = false;
		
		//setIcon(faceDownImage);
		setPreferredSize(new Dimension(80,80));
		setText("ʕ•ᴥ•ʔ");
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
	public void faceUp() {
		setText(value);
	}
	public void faceDown() {
		setText("ʕ•ᴥ•ʔ");
	}
	
	
}
