import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;


public class Game extends AbstractGame {
	boolean redTurn;
	General redGeneral = null, blackGeneral = null;
	
	public Game(String player1, String player2) {
		red = new Player(player1);
		black = new Player(player2);
		board = new Board();
		redTurn = true;
		
		for(int i = 0; i < board.items.length; i++) {
			if(board.items[i] instanceof General && board.items[i].getColor().equals("red"))
				redGeneral = (General)board.items[i];
			if(board.items[i] instanceof General && board.items[i].getColor().equals("black"))
				blackGeneral = (General)board.items[i];
		}
	}
	
	public Board getBoard() {
		return board;
	}

	@Override
	public void play(String from, String to) {
		try{
			Item temp = board.getItem(from);
			if(((temp.getColor().equals("red") && redTurn) || (temp.getColor().equals("black")) && !redTurn) && temp.canMove(board.pieces,to) && !flyingGeneral(from,to)) {
				
				board.pieces[to.charAt(0)-'a'][to.charAt(1)-'1'] = temp.toString();
				board.pieces[from.charAt(0)-'a'][from.charAt(1)-'1'] = null;
				
				int index = -1;
				if(board.getItem(to) != null) { 
					
					for(int i = 0; i < board.items.length; i++)
						if(board.items[i].getPosition().equals(to))
							index = i;
					
					board.items[index].setPosition("xx");  //If there was a piece on the destination, set its position to "xx" temporarily
					
					if(redTurn)
						red.puan += board.items[index].getPoint();
					else
						black.puan += board.items[index].getPoint();
				}
				temp.setPosition(to);  //Move the piece temporarily
			
				
				if((redTurn && checkmate(blackGeneral.getPosition(),"black")) || (!redTurn && checkmate(redGeneral.getPosition(),"red"))) {  //If the current player checkmates the other player, writes an error message on the screen and exits the method
					String winner = "";
					
					if(redTurn) 
						System.out.println("ŞAH MAT! " + red.getName() + " oyunu kazandı. " + red.getName() + "'in puanı: " + red.puan + ", " + black.getName() + "'nin puanı: " + black.puan);
					else
						System.out.println("ŞAH MAT! " + black.getName() + " oyunu kazandı. " + red.getName() + "'in puanı: " + red.puan + ", " + black.getName() + "'nin puanı: " + black.puan);
					
					return;
				}
				
			if((redTurn && check(redGeneral.getPosition(),"red")) || (!redTurn && check(blackGeneral.getPosition(),"black"))) {  
				board.pieces[to.charAt(0)-'a'][to.charAt(1)-'1'] = null;
				board.pieces[from.charAt(0)-'a'][from.charAt(1)-'1'] = temp.toString();
				temp.setPosition(from);
			
				if(index != -1)
					board.items[index].setPosition(to);             //If the other player can check the current player, moves the pieces
																	//back to their original positions and exits the method after writing
				if(redTurn)											//an error message on the screen
					red.puan -= board.items[index].getPoint();
				else
					black.puan -= board.items[index].getPoint();
				
				System.out.println("hatali hareket");
				return;
			}
				
				
				redTurn = !redTurn;
			}
			else {
				System.out.println("hatali hareket");
				
			}
		}
		catch(Exception e) {
			System.out.println("hatali hareket");
		}
		
		
	}
	
	public boolean flyingGeneral(String from, String to) {	
		String redPosition = redGeneral.getPosition();
		String blackPosition = blackGeneral.getPosition();
		int col = redPosition.charAt(1) - '1';
		int count = 0;
		
		if(redPosition.charAt(1) == blackPosition.charAt(1)) {                                //if both of the generals are on the same column  
			for(int i = redPosition.charAt(0)-'a'+1; i < blackPosition.charAt(0)-'a'; i++) {  //and we are trying to move the only piece which is
				if(board.pieces[i][col] != null)                                              //preventing them from directly facing each other
					count++;
			}
			if(count == 1 && (from.charAt(1) == redPosition.charAt(1) && !(board.getItem(from) instanceof General)))
				return true;
		}
		else if(from.equals(redPosition) && to.charAt(1) == blackPosition.charAt(1)) {       //if we are trying to move a general to the same 
			count = 0;																		 //column as the other general and there isn't any
																							 //pieces in between them
			for(int i = to.charAt(0)-'a'+1; i < blackPosition.charAt(0)-'a'; i++) {
				if(board.pieces[i][blackPosition.charAt(1)-'1'] != null)
					count++;
			}
			if(count == 0)
				return true;
		}
		else if(from.equals(blackPosition) && to.charAt(1) == redPosition.charAt(1)) {
			count = 0;
			
			for(int i = redPosition.charAt(0)-'a'+1; i < to.charAt(0)-'a'; i++) {
				if(board.pieces[i][redPosition.charAt(1)-'1'] != null)
					count++;
			}
			if(count == 0)
				return true;
		}
		
		return false;
	}
	
	public boolean check(String position, String color) {
		String allMoves = "";
	
		for(int i = 0; i < board.items.length; i++) {
			if(!board.items[i].getColor().equals(color) && !board.items[i].getPosition().equals("xx")) {
				allMoves += board.items[i].getPossibleMoves(board.pieces);
			}
		}
	
		if(allMoves.contains(position))  //if a piece from the other color can move to the position of the general it's a check
			return true;
		return false;	
	}
	
	public boolean checkmate(String position, String color) {
		String allMoves = "";
		
		if(check(position,color)) {  //If it's not a check, it cannot be a checkmate
			for(int i = 0; i < board.items.length; i++) {
				if(!board.items[i].getPosition().equals("xx") && board.items[i].getColor().equals(color)) {
					allMoves = board.items[i].getPossibleMoves(board.pieces).replaceAll(" ", "");
				
					for(int j = 0; j < allMoves.length()-1; j += 2) {     //Tries all of the possible moves for every piece of the same color
						String tempPosition = allMoves.substring(j,j+2);  //to see if it is a checkmate
						String oldPosition = board.items[i].getPosition();
						board.pieces[oldPosition.charAt(0) - 'a'][oldPosition.charAt(1) - '1'] = null;
						board.pieces[tempPosition.charAt(0) - 'a'][tempPosition.charAt(1) - '1'] = board.items[i].toString();
						
						int index = -1;
						if(board.getItem(tempPosition) != null) { 
							
							for(int k = 0; k < board.items.length; k++)
								if(board.items[k].getPosition().equals(tempPosition))
									index = k;
							
							board.items[index].setPosition("xx");
						}
						
						
						if(board.items[i] instanceof General && !flyingGeneral(position,tempPosition)) 
							position = tempPosition; 
					
						board.items[i].setPosition(tempPosition);
						
						if(!check(position,color)) {  //If it is no longer a check, then it is not a checkmate
							board.pieces[oldPosition.charAt(0) - 'a'][oldPosition.charAt(1) - '1'] = board.items[i].toString();
							
							if(index != -1) {
								board.items[index].setPosition(tempPosition);
								board.pieces[tempPosition.charAt(0) - 'a'][tempPosition.charAt(1) - '1'] = board.items[index].toString();
							}
							else
								board.pieces[tempPosition.charAt(0) - 'a'][tempPosition.charAt(1) - '1'] = null;
							
							board.items[i].setPosition(oldPosition);
							
							if(board.items[i] instanceof General)
								position = oldPosition;
							return false;
						}
						board.pieces[oldPosition.charAt(0) - 'a'][oldPosition.charAt(1) - '1'] = board.items[i].toString();
						
						if(index != -1) {
							board.items[index].setPosition(tempPosition);
							board.pieces[tempPosition.charAt(0) - 'a'][tempPosition.charAt(1) - '1'] = board.items[index].toString();
						}
						else
							board.pieces[tempPosition.charAt(0) - 'a'][tempPosition.charAt(1) - '1'] = null;
						
						board.items[i].setPosition(oldPosition);
						
						if(board.items[i] instanceof General)
							position = oldPosition;
					}
				}
			}
			
			return true;
		}
	
		return false;
	}

	@Override
	public void save_binary(String address) {
		ObjectOutputStream out = null;
		
		try {
			out = new ObjectOutputStream(new FileOutputStream(address));
			out.writeObject(this.red);
			out.writeObject(this.black);
			out.writeObject(this.board);
			out.writeObject(this.board.items);
			out.writeBoolean(this.redTurn);
			out.writeObject(this.redGeneral);
			out.writeObject(this.blackGeneral);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void save_text(String address) {
		PrintWriter out = null;
		
		try {
			out = new PrintWriter(new FileOutputStream(address));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i = 0; i < board.pieces.length; i++) 
			for(int j = 0; j < board.pieces[0].length; j++)
				out.print(board.pieces[i][j] + " ");
		out.println();
		
		for(int i = 0; i < board.items.length; i++)
			out.print(board.items[i].getColor() + " " + board.items[i].getPosition() + " ");
		out.println();
		
		out.print(red.getName() + " " + red.puan + " " + black.getName() + " " + black.puan);
		out.println();
		
		out.print(redTurn);
		
		out.close();
		
		
	}

	@Override
	public void load_text(String address) {
		Scanner in = null;
		
		try {
			in = new Scanner(new FileInputStream(address));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[][] newPieces = new String[10][9];
		Scanner sc = new Scanner(in.nextLine());
		
		for(int i = 0; i < board.pieces.length; i++)
			for(int j = 0; j < board.pieces[0].length; j++) {
				String piece = sc.next();
				if(piece.equals("null"))
					newPieces[i][j] = null;
				else
					newPieces[i][j] = piece;
			}
				
		
		Item[] newItems = new Item[32];
		sc = new Scanner(in.nextLine());
		for(int i = 0; i < board.items.length; i++) {
			String color = sc.next();
			String position = sc.next();
			
			if(i <= 3)
				newItems[i] = new Chariot(color,position);
			else if(i >= 4 && i <= 7)
				newItems[i] = new Horse(color,position);
			else if(i >= 8 && i <= 11)
				newItems[i] = new Elephant(color,position);
			else if(i >= 12 && i <= 15)
				newItems[i] = new Advisor(color,position);
			else if(i >= 16 && i <= 17)
				newItems[i] = new General(color,position);
			else if(i >= 18 && i <= 21)
				newItems[i] = new Cannon(color,position);
			else
				newItems[i] = new Soldier(color,position);
		}
		
		sc = new Scanner(in.nextLine());
		String name = sc.next();
		Player newRed = new Player(name);
		newRed.puan = Float.parseFloat(sc.next());
		name = sc.next();
		Player newBlack = new Player(name);
		newBlack.puan = Float.parseFloat(sc.next());
		
		sc = new Scanner(in.nextLine());
		boolean newRedTurn = sc.nextBoolean();
			
		in.close();
		sc.close();
				
		this.board.pieces = newPieces;
		this.board.items = newItems;
		this.red = newRed;
		this.black = newBlack;
		this.redTurn = newRedTurn;
		this.redGeneral = (General)newItems[16];
		this.blackGeneral = (General)newItems[17];
			
	}

	@Override
	public void load_binary(String address) {
		ObjectInputStream in = null;
		
		try {
			in = new ObjectInputStream(new FileInputStream(address));
			Player newRed = (Player) in.readObject();
			Player newBlack = (Player) in.readObject();
			Board newBoard = (Board)in.readObject();
			Item[] newItems = (Item[])in.readObject();
			boolean newRedTurn = in.readBoolean();
			General newRedGeneral = (General) in.readObject();
			General newBlackGeneral = (General) in.readObject();
			in.close();
			
			this.red = newRed;
			this.black = newBlack;
			this.board = newBoard;
			this.board.items = newItems;
			this.redTurn = newRedTurn;
			this.redGeneral = newRedGeneral;
			this.blackGeneral = newBlackGeneral;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
