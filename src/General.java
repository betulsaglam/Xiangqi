
public class General extends Item {
	
	public General(String color,String position) {
		super(color,position);
	}
	
	@Override
	public String toString() {
		if(getColor().equals("red"))
			return "ş";
		return "Ş";
	}
	
	@Override
	public boolean canMove(String[][] pieces, String destination) {

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
			if(row + 1 <= 2 && (pieces[row+1][col] == null || Character.isUpperCase(piece.charAt(0)) != Character.isUpperCase(pieces[row+1][col].charAt(0))))
				possibleMoves += "" + (char)('a'+row+1) + (col+1) + " ";  //going up
			
			if(row - 1 >= 0 && (pieces[row-1][col] == null || Character.isUpperCase(piece.charAt(0)) != Character.isUpperCase(pieces[row-1][col].charAt(0))))
				possibleMoves += "" + (char)('a'+row-1) + (col+1) + " ";  //going down
			
			if(col + 1 <= 5 && (pieces[row][col+1] == null || Character.isUpperCase(piece.charAt(0)) != Character.isUpperCase(pieces[row][col+1].charAt(0))))
				possibleMoves += "" + (char)('a'+row) + (col+2) + " ";  //going right
			
			if(col - 1 >= 3 && (pieces[row][col-1] == null || Character.isUpperCase(piece.charAt(0)) != Character.isUpperCase(pieces[row][col-1].charAt(0))))
				possibleMoves += "" + (char)('a'+row) + col + " ";  //going left
		}
		else if(getColor().equals("black")) {
			if(row + 1 <= 9 && (pieces[row+1][col] == null || Character.isUpperCase(piece.charAt(0)) != Character.isUpperCase(pieces[row+1][col].charAt(0))))
				possibleMoves += "" + (char)('a'+row+1) + (col+1) + " ";  //going up
			
			if(row - 1 >= 7 && (pieces[row-1][col] == null || Character.isUpperCase(piece.charAt(0)) != Character.isUpperCase(pieces[row-1][col].charAt(0))))
				possibleMoves += "" + (char)('a'+row-1) + (col+1) + " ";  //going down
			
			if(col + 1 <= 5 && (pieces[row][col+1] == null || Character.isUpperCase(piece.charAt(0)) != Character.isUpperCase(pieces[row][col+1].charAt(0))))
				possibleMoves += "" + (char)('a'+row) + (col+2) + " ";  //going right
			
			if(col - 1 >= 3 && (pieces[row][col-1] == null || Character.isUpperCase(piece.charAt(0)) != Character.isUpperCase(pieces[row][col-1].charAt(0))))
				possibleMoves += "" + (char)('a'+row) + col + " ";  //going left
		}
		return possibleMoves;
	}

}
