
public class Chariot extends Item {
	
	public Chariot(String color,String position) {
		super(color,position);
		setPoint(9);
	}
	
	@Override
	public String toString() {
		if(getColor().equals("red"))
			return "k";
		return "K";
	}
	
	@Override
	public boolean canMove(String[][] pieces,String destination) {

		if(getPossibleMoves(pieces).contains(destination)) {
			return true;
		}
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
			else if(Character.isUpperCase(piece.charAt(0)) != Character.isUpperCase(pieces[i][col].charAt(0))) {
				possibleMoves += "" + (char)('a'+i) + (col+1) + " ";
				break;
			}
			else
				break;
		}
		
		for(int i = row-1; i >= 0; i--) {  //going down
			if(pieces[i][col] == null)
				possibleMoves += "" + (char)('a'+i) + (col+1) + " ";
			else if(Character.isUpperCase(piece.charAt(0)) != Character.isUpperCase(pieces[i][col].charAt(0))) {
				possibleMoves += "" + (char)('a'+i) + (col+1) + " ";
				break;
			}
			else
				break;
		}
		
		for(int i = col+1; i < pieces[row].length; i++) {  //going right
			if(pieces[row][i] == null)
				possibleMoves += "" + (char)('a'+row) + (i+1) + " ";
			else if(Character.isUpperCase(piece.charAt(0)) != Character.isUpperCase(pieces[row][i].charAt(0))) {
				possibleMoves += "" + (char)('a'+row) + (i+1) + " ";
				break;
			}
			else
				break;
		}
		
		for(int i = col-1; i >= 0; i--) {  //going left
			if(pieces[row][i] == null)
				possibleMoves += "" + (char)('a'+row) + (i+1) + " ";
			else if(Character.isUpperCase(piece.charAt(0)) != Character.isUpperCase(pieces[row][i].charAt(0))) {
				possibleMoves += "" + (char)('a'+row) + (i+1) + " ";
				break;
			}
			else
				break;
		}
		return possibleMoves;
	}

}
