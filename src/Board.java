import java.io.Serializable;

public class Board extends AbstractBoard implements Serializable{
	String[][] pieces;
			
	public Board() {
		items = new Item[32];
		
		items[0] = new Chariot("red","a1");
		items[1] = new Chariot("red","a9");
		items[2] = new Chariot("black","j1");
		items[3] = new Chariot("black","j9");
		
		items[4] = new Horse("red","a2");
		items[5] = new Horse("red","a8");
		items[6] = new Horse("black","j2");
		items[7] = new Horse("black","j8");
		
		items[8] = new Elephant("red","a3");
		items[9] = new Elephant("red","a7");
		items[10] = new Elephant("black","j3");
		items[11] = new Elephant("black","j7");
		
		items[12] = new Advisor("red","a4");
		items[13] = new Advisor("red","a6");
		items[14] = new Advisor("black","j4");
		items[15] = new Advisor("black","j6");
		
		items[16] = new General("red","a5");
		items[17] = new General("black","j5");
		
		items[18] = new Cannon("red","c2");
		items[19] = new Cannon("red","c8");
		items[20] = new Cannon("black","h2");
		items[21] = new Cannon("black","h8");
		
		items[22] = new Soldier("red","d1");
		items[23] = new Soldier("red","d3");
		items[24] = new Soldier("red","d5");
		items[25] = new Soldier("red","d7");
		items[26] = new Soldier("red","d9");
		items[27] = new Soldier("black","g1");
		items[28] = new Soldier("black","g3");
		items[29] = new Soldier("black","g5");
		items[30] = new Soldier("black","g7");
		items[31] = new Soldier("black","g9");
		
		fillBoard();
	}
	
	public void fillBoard() {
		pieces = new String[10][9];
		
		for(int i = 0; i < items.length; i++) {
			String pos = items[i].getPosition();
			pieces[pos.charAt(0) - 'a'][pos.charAt(1) - '1'] = items[i].toString();
		}
			
	}
	
	@Override
	public void print() {

		String row = "";
		
		for(int i = pieces.length-1; i >= 0; i--) {
			for(int j = 0; j < pieces[i].length; j++) {
				if(pieces[i][j] == null)
				    row += "---";
				else
					row += pieces[i][j] + "--";
			}
			row = row.substring(0,row.length()-2);
			System.out.println((char)('a' + i) + "\t" + row);
			row = "";
			
			if(i == 9)
				System.out.println(" \t|  |  |  |\\ | /|  |  |  |");
			else if(i == 8)
				System.out.println(" \t|  |  |  |/ | \\|  |  |  |");
			else if (i == 5)
				System.out.println(" \t|                       |");
			else if(i == 2)
				System.out.println(" \t|  |  |  |/ | \\|  |  |  |");
			else if(i == 1)
				System.out.println(" \t|  |  |  |\\ | /|  |  |  |");
			else if (i == 0) {
				System.out.println();
				System.out.println(" \t1--2--3--4--5--6--7--8--9");
			}
			else
				System.out.println(" \t|  |  |  |  |  |  |  |  |");
		}
		
	}
	
	public Item getItem(String position) {
		
		for(int i = 0; i < items.length; i++)
			if(items[i].getPosition().equals(position))
				return items[i];
		return null;
	}
	

}
