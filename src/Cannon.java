
public class Cannon extends Item {
	
	public Cannon(String color,String position) {
		super(color,position);
		setPoint(4.5f);
	}
	
	@Override
	public String toString() {
		if(getColor().equals("red"))
			return "t";
		return "T";
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
		
		for(int i = row+1; i < pieces.length; i++) {  //going up
			if(pieces[i][col] == null)
				possibleMoves += "" + (char)('a'+i) + (col+1) + " ";
			else {
				for(int j = i+1; j < pieces.length; j++) {
					if(pieces[j][col] != null && Character.isUpperCase(piece.charAt(0)) != Character.isUpperCase(pieces[j][col].charAt(0))) {
						possibleMoves += "" + (char)('a'+j) + (col+1) + " ";
						break;
					}
				}
			break;
			}
		}
		
		for(int i = row-1; i >= 0; i--) {  //going down
			if(pieces[i][col] == null)
				possibleMoves += "" + (char)('a'+i) + (col+1) + " ";
			else {
				for(int j = i-1; j >= 0; j--) {
					if(pieces[j][col] != null && Character.isUpperCase(piece.charAt(0)) != Character.isUpperCase(pieces[j][col].charAt(0))) {
						possibleMoves += "" + (char)('a'+j) + (col+1) + " ";
						break;
					}
				}
			break;
			}
		}
		
		for(int i = col+1; i < pieces[row].length; i++) {  //going right
			if(pieces[row][i] == null)
				possibleMoves += "" + (char)('a'+row) + (i+1) + " ";
			else {
				for(int j = i+1; j < pieces[row].length; j++) {
					if(pieces[row][j] != null && Character.isUpperCase(piece.charAt(0)) != Character.isUpperCase(pieces[row][j].charAt(0))) {
						possibleMoves += "" + (char)('a'+row) + (j+1) + " ";
						break;
					}
				}
			break;
			}
		}
		
		for(int i = col-1; i >= 0; i--) {  //going left
			if(pieces[row][i] == null)
				possibleMoves += "" + (char)('a'+row) + (i+1) + " ";
			else {
				for(int j = i-1; j >= 0; j--) {
					if(pieces[row][j] != null && Character.isUpperCase(piece.charAt(0)) != Character.isUpperCase(pieces[row][j].charAt(0))) {
						possibleMoves += "" + (char)('a'+row) + (j+1) + " ";
						break;
					}
				}
			break;
			}
		}
		return possibleMoves;
	}

}
