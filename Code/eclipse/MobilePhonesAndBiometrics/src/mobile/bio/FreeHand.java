package mobile.bio;

import java.util.ArrayList;

import android.graphics.Path;


public class FreeHand extends Shape{
	private ArrayList<Path>_graphics;
	
	public FreeHand(){
		super();
		 _graphics = new ArrayList<Path>();
	}
	
	public ArrayList<Path> getGraphicsPath(){
		return _graphics;
	}

}
