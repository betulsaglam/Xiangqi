import java.io.Serializable;

public class Player implements Serializable{
	
	float puan = 0; // her taş yedikçe oyuncunun puanı taşın puanına göre artar.
    private String name;
    
    public Player(String name) {
    	this.name = name;
    }
    
    public void setName(String name) {
    	this.name = name;
    }
    
    public String getName() {
    	return name;
    }
    
}
