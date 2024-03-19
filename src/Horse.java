
public class Horse extends Item {
	
	public Horse(String color,String position) {
		super(color,position);
		setPoint(4);
	}
	
	@Override
	public String toString() {
		if(getColor().equals("red"))
			return "a";
		return "A";
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
		
		if(row+2 < pieces.length && pieces[row+1][col] == null) {
			if(col+1 < pieces[0].length && (pieces[row+2][col+1] == null || Character.isUpperCase(piece.charAt(0)) != Character.isUpperCase(pieces[row+2][col+1].charAt(0))))
				possibleMoves += "" + (char)('a'+row+2) + (col+2) + " ";
			
			if(col-1 >= 0 && (pieces[row+2][col-1] == null || Character.isUpperCase(piece.charAt(0)) != Character.isUpperCase(pieces[row+2][col-1].charAt(0))))
				possibleMoves += "" + (char)('a'+row+2) + (col) + " ";
		}
		
		if(row-2 >= 0 && pieces[row-1][col] == null) {
		
			if(col+1 < pieces[0].length && (pieces[row-2][col+1] == null || Character.isUpperCase(piece.charAt(0)) != Character.isUpperCase(pieces[row-2][col+1].charAt(0))))
				possibleMoves += "" + (char)('a'+row-2) + (col+2) + " ";
			
			if(col-1 >= 0 && (pieces[row-2][col-1] == null || Character.isUpperCase(piece.charAt(0)) != Character.isUpperCase(pieces[row-2][col-1].charAt(0))))
				possibleMoves += "" + (char)('a'+row-2) + (col) + " ";
		}
		
		if(col+2 < pieces[0].length && pieces[row][col+1] == null) {
			
			if(row+1 < pieces[0].length && (pieces[row+1][col+2] == null || Character.isUpperCase(piece.charAt(0)) != Character.isUpperCase(pieces[row+1][col+2].charAt(0))))
				possibleMoves += "" + (char)('a'+row+1) + (col+3) + " ";
			
			if(row-1 >= 0 && (pieces[row-1][col+2] == null || Character.isUpperCase(piece.charAt(0)) != Character.isUpperCase(pieces[row-1][col+2].charAt(0))))
				possibleMoves += "" + (char)('a'+row-1) + (col+3) + " ";
		}
		
		if(col-2 >= 0 && pieces[row][col-1] == null) {
			
			if(row+1 < pieces[0].length && (pieces[row+1][col-2] == null || Character.isUpperCase(piece.charAt(0)) != Character.isUpperCase(pieces[row+1][col-2].charAt(0))))
				possibleMoves += "" + (char)('a'+row+1) + (col-1) + " ";
			
			if(row-1 >= 0 && (pieces[row-1][col-2] == null || Character.isUpperCase(piece.charAt(0)) != Character.isUpperCase(pieces[row-1][col-2].charAt(0))))
				possibleMoves += "" + (char)('a'+row-1) + (col-1) + " ";
		}
		return possibleMoves;
	}

}
