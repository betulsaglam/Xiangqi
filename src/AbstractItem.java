import java.io.Serializable;

public abstract class AbstractItem implements ItemInterface,Serializable {
	
	private String position;  // tahtadaki konumu gösterir. Örneğin, a1
	private String color;
	private float point;
	
	public String getPosition() {
		return position;
	}
	
	public void setPosition(String position) {
		this.position = position;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public float getPoint() {
		return point;
	}
	
	public void setPoint(float point) {
		this.point = point;
	}
	
	
}
