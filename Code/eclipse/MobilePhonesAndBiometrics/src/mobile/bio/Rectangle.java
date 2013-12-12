package mobile.bio;

import android.graphics.Path;

public class Rectangle extends Shape {
	private Point temppointOfOneEndRectangle;
	//private Point temppointOfAnotherEndRectangle;
	private float left;
	private float top;
	private float right;
	private float bottom;
	
	public Rectangle(){
		super();
		left = 0;
		top = 0;
		right = 0;
		bottom = 0;
		temppointOfOneEndRectangle = new Point();
		//temppointOfAnotherEndRectangle = new Point();
	}
	
	public void setPath(Path path){
		path_of_shape = path;
	}
	
	
	
	public Point gettemppointOfOneEndRectangle(){
		return temppointOfOneEndRectangle;
	}
	
	/*public Point gettemppointOfAnotherEndRectangle(){
		return temppointOfAnotherEndRectangle;
	}*/
	
	public void settemppointOfOneEndRectangle(Point p){
		temppointOfOneEndRectangle = p;
	}
	
	/*public void settemppointOfAnotherEndRectangle(Point p){
		temppointOfAnotherEndRectangle = p;
	}*/
	
	public float getLeft(){
		return left;
	}
	
	public float getTop(){
		return top;
	}
	
	public float getRight(){
		return right;
	}
	public float getBottom(){
		return bottom;
	}
	
	public void setLeft(float l){
		left = l;
	}
	
	public void setTop(float t){
		left = t;
	}
	
	public void setRight(float r){
		right = r;
	}
	public void setBottom(float b){
		bottom = b;
	}
	
}
