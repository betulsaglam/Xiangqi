
public class Advisor extends Item {
	
	public Advisor(String color,String position) {
		super(color,position);
		setPoint(2);
	}
	
	@Override
	public String toString() {
		if(getColor().equals("red"))
			return "v";
		return "V";
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
			if(pos.equals("b5")) {
				
				for(int i = 0; i <= 2; i = i+2)
					for(int j = 3; j <= 5; j = j+2)
						if(pieces[i][j] == null || (Character.isUpperCase(piece.charAt(0)) != Character.isUpperCase(pieces[i][j].charAt(0))))
							possibleMoves += "" + (char)('a'+i) + (j+1) + " ";
			}
			else {
				if(pieces[1][4] == null || (Character.isUpperCase(piece.charAt(0)) != Character.isUpperCase(pieces[1][4].charAt(0))))
				    possibleMoves += "b5 ";
			}
		}
		else if(getColor().equals("black")) {
			if(pos.equals("i5")) {
				
				for(int i = 7; i <= 9; i = i+2)
					for(int j = 3; j <= 5; j = j+2)
						if(pieces[i][j] == null || (Character.isUpperCase(piece.charAt(0)) != Character.isUpperCase(pieces[i][j].charAt(0))))
							possibleMoves += "" + (char)('a'+i) + (j+1) + " ";
			}
			else {
				if(pieces[8][4] == null || (Character.isUpperCase(piece.charAt(0)) != Character.isUpperCase(pieces[8][4].charAt(0))))
				    possibleMoves += "i5 ";
			}
		}
		return possibleMoves;
	}

}
