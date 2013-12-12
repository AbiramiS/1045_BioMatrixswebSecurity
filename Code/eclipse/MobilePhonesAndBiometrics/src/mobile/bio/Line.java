package mobile.bio;

import android.graphics.Path;

public class Line extends Shape{
	private Point begin;
    private Point end;
    public Line(){
    	super();
    	begin = new Point();
    	end = new Point();	
    }
    public Line(Point begin_point, Point end_point){
    	super();
	    begin = begin_point;
	    end = end_point;
    //path_of_shape.moveTo(begin.getX(), begin.getY());
    //path_of_shape.lineTo(end.getX(),end.getY());
    }
    
    public void setPath(Path path){
    	path_of_shape = path;
    }
    
    public Point getBegin(){
    	return begin;
    }
    
    public Point getEnd(){
    	return end;
    }
    public void setBegin(Point _begin){
    begin = _begin;	
    }
    
    public void setEnd(Point _end){
    	end = _end;
    }
}
