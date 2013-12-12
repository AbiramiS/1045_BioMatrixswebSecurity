package mobile.bio;

public class Circle extends Shape {
	
	private float centerX;
	private float centerY;
	private float radius;
	private Point oneEndofTheCircle;
	//private Point anotherEndOfTheCircle;
	
	public Circle(){
		super();
		centerX = 0;
		centerY = 0;
		radius = 0;
		oneEndofTheCircle = new Point();
	}
	
	public float getCenterX(){
		return centerX;
	}
	
	public float getCenterY(){
		return centerY;
	}
	
	public float getRadius(){
		return radius;
	}
	
	public void setCenterX(float x){
		centerX = x;
	}
	
	public void setCenterY(float y){
		centerY = y;
	}
	public void setReadius(float r){
		radius = r;
	}
	
	public void setOneEndOfTheCircle(Point p){
		oneEndofTheCircle = p;
	}
	/*public void setAnotherendOfTheCircle(Point p){
		anotherEndOfTheCircle = p;
	}*/
	
	public Point getOneEndOfTheCircle(){
		return oneEndofTheCircle;
	}
	
	/*public Point getAnotherendOfTheCircle(){
		return anotherEndOfTheCircle;
	}*/

}
