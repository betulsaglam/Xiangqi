
public class Soldier extends Item{
	
	public Soldier(String color,String position) {
		super(color,position);
		setPoint(1);
	}
	
	@Override
	public String toString() {
		if(getColor().equals("red"))
			return "p";
		return "P";
	}
	
	@Override
	public boolean canMove(String[][] pieces,String destination) {
		
		if(getPossibleMoves(pieces).contains(destination))
			return true;
		return false;
	}
	
	@Override
	public String getPossibleMoves(String[][] pieces) {
		String possibleMoves = "";
		String pos = getPosition();
		int row = pos.charAt(0) - 'a';
		int col = pos.charAt(1) - '1';
		String piece = pieces[row][col];
		
		if(getColor().equals("red")) {
			if(pos.charAt(0) <= 'e') {  //before crossing the river
				if(row+1 < pieces.length && (pieces[row+1][col] == null || pieces[row+1][col].charAt(0) < 'a'))  // going up
				    possibleMoves += "" + (char)('a'+row+1) + (col+1) + " ";
			}
			else {  //after crossing the river
				if(row+1 < pieces.length && (pieces[row+1][col] == null || pieces[row+1][col].charAt(0) < 'a'))  // going up
				    possibleMoves += "" + (char)('a'+row+1) + (col+1) + " ";
				
				if(col+1 < pieces[0].length && (pieces[row][col+1] == null || pieces[row][col+1].charAt(0) < 'a'))  //going right
					possibleMoves += "" + (char)('a'+row) + (col+2) + " ";
				
				if(col-1 >= 0 && (pieces[row][col-1] == null || pieces[row][col-1].charAt(0) < 'a'))  //going left
					possibleMoves += "" + (char)('a'+row) + col + " ";
			}
		}
		else if(getColor().equals("black")) {
			if(pos.charAt(0) >= 'f') {  //before crossing the river
				if(row-1 >= 0 && (pieces[row-1][col] == null || pieces[row-1][col].charAt(0) >= 'a'))  // going up
				    possibleMoves += "" + (char)('a'+row-1) + (col+1) + " ";
			}
			else {  //after crossing the river
				if(row-1 >= 0 && (pieces[row-1][col] == null || pieces[row-1][col].charAt(0) >= 'a'))  // going up
				    possibleMoves += "" + (char)('a'+row-1) + (col+1) + " ";
				
				if(col+1 < pieces[0].length && (pieces[row][col+1] == null || pieces[row][col+1].charAt(0) < 'a'))  //going right
					possibleMoves += "" + (char)('a'+row) + (col+2) + " ";
				
				if(col-1 >= 0 && (pieces[row][col-1] == null || pieces[row][col-1].charAt(0) < 'a'))  //going left
					possibleMoves += "" + (char)('a'+row) + col + " ";
			}			
		}
		return possibleMoves;
	}

}
