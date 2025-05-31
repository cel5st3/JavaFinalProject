/**
* Lead Author(s):
* @author Celeste Rodriguez
* @author Mariana Aguilar
* 
* Version: 2025-05-30
* 
* Responsibilities of class: Start game and follow count and time
*/

public class Game {
	// fields
	Card card;
	int matchCount;
	long time;
	
	// constructor
	public Game() 
	{
		this.card = card;
		this.matchCount = matchCount;
	}
	
	/**
	 * Purpose: Get match count
	 * @return matchCount
	 */
	public int getCount()
	{
		return matchCount;
	}
	
	/**
	 * Purpose: Set match count
	 */
	public void setCount()
	{
		this.matchCount = matchCount;
	}
	
	/**
	 * Purpose: Get time
	 * @return time
	 */
	public long getTime() {
		return time;
	}
	
	/**
	 * Purpose: Set time
	 */
	public void setTime() {
		this.time = time;
	}
	
	/**
	 * Purpose: Start game
	 */
	public void main() {
		new BoardView(new CardModel());
	}
}
