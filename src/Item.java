
public class Item extends AbstractItem {
	
	public Item(String color,String position) {
		setColor(color);
		setPosition(position);
	}
	
	public String toString() {
		return "";
	}

	@Override
	public boolean canMove(String[][] pieces,String destination) {
		return false;
	}
	
	public String getPossibleMoves(String[][] pieces) {
		return "";
	}
	


}
