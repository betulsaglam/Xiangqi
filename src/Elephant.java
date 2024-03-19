
public class Elephant extends Item {
	
	public Elephant(String color,String position) {
		super(color,position);
		setPoint(2);
	}
	
	@Override
	public String toString() {
		if(getColor().equals("red"))
			return "f";
		return "F";
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
			if(row+2 <= 4) {
				if(col+2 < pieces[0].length && pieces[row+1][col+1] == null && (pieces[row+2][col+2] ==  null || Character.isUpperCase(piece.charAt(0)) != Character.isUpperCase(pieces[row+2][col+2].charAt(0))))
					possibleMoves += "" + (char)('a' + row+2) + (col+3) + " "; 
				
				if(col-2 >= 0 && pieces[row+1][col-1] == null && (pieces[row+2][col-2] ==  null || Character.isUpperCase(piece.charAt(0)) != Character.isUpperCase(pieces[row+2][col-2].charAt(0))))
					possibleMoves += "" + (char)('a' + row+2) + (col-1) + " "; 
			}
			
			if(row-2 >= 0) {
				if(col+2 < pieces[0].length && pieces[row-1][col+1] == null && (pieces[row-2][col+2] ==  null || Character.isUpperCase(piece.charAt(0)) != Character.isUpperCase(pieces[row-2][col+2].charAt(0))))
					possibleMoves += "" + (char)('a' + row-2) + (col+3) + " "; 
				
				if(col-2 >= 0 && pieces[row-1][col-1] == null && (pieces[row-2][col-2] ==  null || Character.isUpperCase(piece.charAt(0)) != Character.isUpperCase(pieces[row-2][col-2].charAt(0))))
					possibleMoves += "" + (char)('a' + row-2) + (col-1) + " "; 
			}
				
		}
		
		else if(getColor().equals("black")) {
			if(row+2 < pieces.length) {
				if(col+2 < pieces[0].length && pieces[row+1][col+1] == null && (pieces[row+2][col+2] ==  null || Character.isUpperCase(piece.charAt(0)) != Character.isUpperCase(pieces[row+2][col+2].charAt(0))))
					possibleMoves += "" + (char)('a' + row+2) + (col+3) + " "; 
				
				if(col-2 >= 0 && pieces[row+1][col-1] == null && (pieces[row+2][col-2] ==  null || Character.isUpperCase(piece.charAt(0)) != Character.isUpperCase(pieces[row+2][col-2].charAt(0))))
					possibleMoves += "" + (char)('a' + row+2) + (col-1) + " "; 
			}
			
			if(row-2 >= 5 && col-2 >= 0 && col+2 < pieces[0].length) {
				if(col+2 < pieces[0].length && pieces[row-1][col+1] == null && (pieces[row-2][col+2] ==  null || Character.isUpperCase(piece.charAt(0)) != Character.isUpperCase(pieces[row-2][col+2].charAt(0))))
					possibleMoves += "" + (char)('a' + row-2) + (col+3) + " "; 
				
				if(col-2 >= 0 && pieces[row-1][col-1] == null && (pieces[row-2][col-2] ==  null || Character.isUpperCase(piece.charAt(0)) != Character.isUpperCase(pieces[row-2][col-2].charAt(0))))
					possibleMoves += "" + (char)('a' + row-2) + (col-1) + " "; 
			}
				
		}
		return possibleMoves;
	}

}
