package mobile.bio;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

import mobile.bio.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.WallpaperManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AndroidPaint extends Activity implements OnTouchListener {
    /** Called when the activity is first created. */
	static final int REQUEST_CODE = 1001;
	//This is for the menu selection
	//private Shape GraphicObject;
	Vector vc=new Vector();
	//each time we create a new graphic object, we create this Shape object called currentGraphicObject
	private Shape currentGraphicObject;
	 
	StringBuilder builder = new StringBuilder();
	TextView textView;
	
	private int ShapeObject_to_be_created;
	
	private static final int ShapeLine = 1;
	private static final int ShapeRect = 2;
	private static final int ShapeCircle = 3;
	private static final int ShapeOval = 4;
	private static final int ShapeFreehand =5;
	private static final int ShapeErase = 6;
	
	private Paint mPaint;
	
	private float BrushWidth;
	
	double[] color;
	Panel p;
	int number_of_graphicObjects;
	String finalpath="";
	boolean shapemenuclicked;
	boolean colormenuclicked;
	boolean erasemenuclicked;
	boolean brushwidthmenuclicked;
	boolean sendtoserver;
	Base64 base64;
	private ArrayList<Shape> graphicobjects;
	
	private Bitmap wallPaperBitmap;
	
	static WallpaperManager wallpaperManager;

	final Context context = this;
	private int wallpaperHeight;
	
	private int wallpaperWidth;
	String status="";
	private String mImagePath = "";
	
	File file;
	
	//FileOutputStream fos;
	
	Canvas bitmapCanvas;
	
	//test... will later add it to the Shape class
	//Path path_of_shape_for_WallPaperBitmap;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);       
        vc.clear();
        color = new double[3];        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        graphicobjects = new ArrayList<Shape>();        
        //Initialization
        currentGraphicObject = new Shape();
        mPaint = new Paint();        
        InitializePaint();        
        shapemenuclicked = false;
        colormenuclicked = false;  
        sendtoserver = false; 
        BrushWidth = 3;        
        wallpaperManager = WallpaperManager.getInstance(getApplicationContext());        
        Display display = getWindowManager().getDefaultDisplay();        
        //wallpaperHeight = wallpaperManager.getDesiredMinimumHeight();
        wallpaperHeight = display.getHeight();
        //wallpaperWidth = wallpaperManager.getDesiredMinimumWidth();
        wallpaperWidth = display.getWidth();
        //path_of_shape_for_WallPaperBitmap = new Path();        
        wallPaperBitmap = Bitmap.createBitmap(wallpaperWidth, wallpaperHeight, Bitmap.Config.ARGB_8888);        
        bitmapCanvas = new Canvas(wallPaperBitmap);        
    }
    
    //test
    public void onPause(){
    	super.onPause();
    	p.surfaceDestroyed(p.getHolder());
    	//activitypaused = true;
    }
    
    public void onStop(){
    	super.onStop();
    	p.surfaceDestroyed(p.getHolder());
    	/*graphicobjects.clear();
    	number_of_graphicObjects = 0;*/
    }
    
    public void onDestroy(){
    	super.onDestroy();
    	p.surfaceDestroyed(p.getHolder());
    	//p.getGraphicObjects().clear();
    	graphicobjects.clear();
    	number_of_graphicObjects = 0;
    }
    
   //test
   public void onResume(){
	   super.onResume();
	  // p._thread.setRunning(true);
	   //p._thread.start();
	   //p = new Panel(this);
       //setContentView(new Panel(this));
       //setContentView(p);
    }
    
    public void onStart(){
    	super.onStart();

    	//p.surfaceCreated(p.getHolder());
    	p = new Panel(this);
        setContentView(p/*new Panel(this)*/);
    	//setContentView(p);
    	//new line is being added
    	//p.getGraphicObjects() = new ArrayList<Shape>(); 
         //setContentView(p);
        // wallPaperBitmap = Bitmap.createBitmap(p.getWidth(),p.getHeight(),Bitmap.Config.ARGB_8888);
         
        /* //test
         if (getLastNonConfigurationInstance() != null)
         {
         	//GraphicObject = (Shape)getLastNonConfigurationInstance();
        	 graphicobjects.set(number_of_graphicObjects-1, (Shape)getLastNonConfigurationInstance());
         }*/
        /* int n = graphicobjects.size();
         if (getLastNonConfigurationInstance() != null)
         {
         	//GraphicObject = (Shape)getLastNonConfigurationInstance();
         	//graphicobjects.set(number_of_graphicObjects-1, (Shape)getLastNonConfigurationInstance());
         	//graphicobjects.addAll((Shape)getLastNonConfigurationInstance());
        	 Object[] temp = getLastNonConfigurationInstance();
         	for(int i = 0; i<n; i++){
         		graphicobjects.set(i,(Shape)getLastNonConfigurationInstance());
         	}
         }*/
    }
    
    public void onSaveInstanceState(Bundle savedInstanceState){
    	//savedInstanceState.p
    }
    
    public void onRestoreInstanceState(Bundle savedInstanceState){
    	
    }
   
    //test
   /* public Object onRetainNonConfigurationInstance() 
    {
     // if (GraphicObject != null) // Check that the object exists
          //return(GraphicObject);
    	//if(!graphicobjects.isEmpty())
    		return graphicobjects.toArray();//.get(number_of_graphicObjects-1);
     // return super.onRetainNonConfigurationInstance();
    }*/
    
    private void InitializePaint()
    {
    	mPaint.setDither(true);
	        
	    mPaint.setColor(Color.rgb(100, 100,100));

    	mPaint.setStyle(Paint.Style.STROKE);
 
    	mPaint.setStrokeJoin(Paint.Join.ROUND);

    	mPaint.setStrokeCap(Paint.Cap.ROUND);
    	
    	mPaint.setStrokeWidth(BrushWidth);	
	
	}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.menu, menu);
    	return true;
    	}   
public boolean onOptionsItemSelected(MenuItem item){
    	
    switch(item.getItemId()){
    case R.id.itemLine:
    	ShapeObject_to_be_created = ShapeLine;
     	//GraphicObject = new Line();
     	shapemenuclicked = true;
     	erasemenuclicked = false;
     	brushwidthmenuclicked = false;
    	return true;
    
    case R.id.itemFreehand:
    	ShapeObject_to_be_created = ShapeFreehand;
    	//GraphicObject = new FreeHand();
    	shapemenuclicked = true;
    	erasemenuclicked = false;
    	brushwidthmenuclicked = false;
    	return true;
    	
    case R.id.itemRectangle:
    	ShapeObject_to_be_created = ShapeRect;
    	//GraphicObject = new Rectangle();
    	shapemenuclicked = true;
    	erasemenuclicked = false;
    	brushwidthmenuclicked = false;
    	return true;
    	
    case R.id.itemCircle:
    	ShapeObject_to_be_created = ShapeCircle;
    	//GraphicObject = new Circle();
    	shapemenuclicked = true;
    	erasemenuclicked = false;
    	brushwidthmenuclicked = false;
    	return true;
    	
    case R.id.itemOval:
    	ShapeObject_to_be_created = ShapeOval;
    	//GraphicObject = new Oval();
    	shapemenuclicked = true;
    	erasemenuclicked = false;
    	brushwidthmenuclicked = false;
    	return true;
    	
    case R.id.itemColor:
    	LaunchColorPicker();
    	colormenuclicked = true;
    	erasemenuclicked = false;
    	brushwidthmenuclicked = false;
    	return true;
    	
    case R.id.itemErase:
    	ShapeObject_to_be_created = ShapeErase;
    	//GraphicObject = new Erase();
    	erasemenuclicked = true;
    	shapemenuclicked = false;
    	colormenuclicked = false;
    	brushwidthmenuclicked = false;
    	//BrushWidth = 8;
    	return true;
    	
    case R.id.Signature1:
    	//GraphicObject = new Erase();
    	Signature1();
    	erasemenuclicked = true;
    	shapemenuclicked = false;
    	colormenuclicked = false;
    	brushwidthmenuclicked = false;
    	//BrushWidth = 8;
    	return true;
    	
    case R.id.Signature2:
    	//GraphicObject = new Erase();
    	//Signature2();
    	erasemenuclicked = true;
    	shapemenuclicked = false;
    	colormenuclicked = false;
    	brushwidthmenuclicked = false;
    	//BrushWidth = 8;
    	return true;
    	
    case R.id.VerificationSignature:
    	//GraphicObject = new Erase();
    	LoginToServer();
    	erasemenuclicked = true;
    	shapemenuclicked = false;
    	colormenuclicked = false;
    	brushwidthmenuclicked = false;
    	//BrushWidth = 8;
    	return true;
    	
    case R.id.itemBrushWidth3:
    	BrushWidth = 3;
    	brushwidthmenuclicked = true;
    	return true;
    	
    case R.id.itemBrushWidth4:
    	BrushWidth = 4;
    	brushwidthmenuclicked = true;
    	return true;
    	
    case R.id.itemBrushWidth5:
    	BrushWidth = 5;
    	brushwidthmenuclicked = true;
    	return true;
    	
    case R.id.itemBrushWidth6:
    	BrushWidth = 6;
    	brushwidthmenuclicked = true;
    	return true;
    	
    case R.id.itemSetWallPaper:
    	//wallPaperBitmap = Bitmap.createBitmap(p.getWidth(),p.getHeight(),Bitmap.Config.ARGB_8888);
    	//Bitmap temp = p.getDrawingCache(true);
    	//wallPaperBitmap = Bitmap.createBitmap(temp);
    	//Canv
    	//Canvas canvas = p.getHolder().lockCanvas();
    	//p.getHolder().lockCanvas().drawBitmap(wallPaperBitmap, 0,0, mPaint);
    	//p.draw(canvas);
    	//try{
    		//getApplicationContext().setWallpaper(wallPaperBitmap);
    	//}
    	//catch(IOException e){
    		//e.printStackTrace();
    	//}
    	//p.saveScreenshot();
    	//wallPaperBitmap = Bitmap.createBitmap(this.p.getWidth(), this.p.getHeight(), Bitmap.Config.ARGB_8888);
    	//File file = new File(mScreenshotPath + "/" + "screeshot.jpg");
    	/*try{
    	FileInputStream fIS = new FileInputStream(file); 
    	//wallPaperBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fIS);
    	fIS.close();
    	} catch (FileNotFoundException e) {
        Log.e("Panel", "FileNotFoundException", e);
    	} catch (IOException e) {
        Log.e("Panel", "IOEception", e);
    	}*/
    	
    	//...............................
    	//wallPaperBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
    	//Display display = getWindowManager().getDefaultDisplay();
		//int width = display.getWidth();
		//int height = display.getHeight();
    	//Bitmap wallPaperBitmapTemp = Bitmap.createScaledBitmap(wallPaperBitmap,width/2, height/2, true);
    	//try{
    		//getApplicationContext().setWallpaper(wallPaperBitmapTemp);
    	//}
    	//catch(IOException e){
    		//e.printStackTrace();
    	//}
    	
    	//IBinder windowToken = p.getWindowToken();
    	//wallpaperManager.setWallpaperOffsets(windowToken, 0.5f, 0.5f);
    	//Bitmap bitmapTemp = Bitmap.createScaledBitmap(wallPaperBitmap, parent., dstHeight, filter)
    	
    	try{
    		wallpaperManager.clear();
    		wallpaperManager.setBitmap(wallPaperBitmap);	
    	}
    	catch(IOException e){
    		e.printStackTrace();
    	}
    	//wallpaperManager.suggestDesiredDimensions(minimumWidth, minimumHeight)
    	
    	return true;
    	
    case R.id.itemSaveImage:
    	
    	 Calendar currentDate = Calendar.getInstance();
    	  SimpleDateFormat formatter= new SimpleDateFormat("yyyyMMMddHmmss");
    	  String dateNow = formatter.format(currentDate.getTime());
    	  Toast.makeText(getApplicationContext(), "date-"+dateNow, Toast.LENGTH_SHORT).show();
    	  mImagePath = Environment.getExternalStorageDirectory() + "/signatureimages";
    	  file = new File(mImagePath + "/" + dateNow +".png");
    	  finalpath=mImagePath + "/" + dateNow +".png";
    	  Toast.makeText(getApplicationContext(), "-"+file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
    	  FileOutputStream fos;     
    	  try {  
            fos = new FileOutputStream(file);
            wallPaperBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
    	  } catch (FileNotFoundException e) {
            Log.e("Panel", "FileNotFoundException", e);
    	  }  
    	  catch (IOException e) {
            Log.e("Panel", "IOEception", e);
        }
    }
   
	return false;
} 

	private void LaunchColorPicker(){
		Intent launchcolorpicker = new Intent();
		launchcolorpicker.setClassName("somitsolutions.training.android.colorpicker", "somitsolutions.training.android.colorpicker.ColorPicker");
		launchcolorpicker.setAction("somitsolutions.training.android.colorpicker.android.intent.action.COLORPICKER");
		launchcolorpicker.addCategory("CATEGORY_DEFAULT");
		launchcolorpicker.setType("vnd.somitsolutions.color/vnd.somitsolutions.color-value");
		
		try {
	    	startActivityForResult(launchcolorpicker,REQUEST_CODE);
	    
	    }
	    catch(ActivityNotFoundException e){
	    	Log.e("IntentExample", "Activity could not be started...");
	    }   
	}

	private void Signature1(){	
		
		Intent intent=getIntent();
		try {
			Toast.makeText(getApplicationContext(), "sending file to server......", Toast.LENGTH_SHORT).show(); 			
			HttpClient httpClient = new DefaultHttpClient();
	        HttpPost postRequest = new HttpPost(getResources().getString(R.string.server_url)+"signatureone.do");
			FileInputStream fis = new FileInputStream(new File(finalpath));
			byte[] buffer=new byte[fis.available()];
			fis.read(buffer);
			@SuppressWarnings("static-access") 
			String Base64String = base64.encodeBytes(buffer);   
	           
	        //InputStreamBody isb = new InputStreamBody(new FileInputStream(file),file.getName());
	        MultipartEntity multipartContent = new MultipartEntity();
	        multipartContent.addPart("signaturefile", new StringBody(Base64String.toString()));
	        multipartContent.addPart("positionvalues", new StringBody(vc.toString()));
	        multipartContent.addPart("useridinfo", new StringBody(intent.getStringExtra("response")));
	        postRequest.setEntity(multipartContent);
	        HttpResponse res =httpClient.execute(postRequest);
	        InputStream is= res.getEntity().getContent();
	          
	        byte[] b=null;
	        ByteArrayOutputStream bos = new ByteArrayOutputStream();
	        int ch;
			while ((ch = is.read()) != -1){
				bos.write(ch);
			}
			b = bos.toByteArray();
			bos.close();
	        is.close();
	        
	        DetailsPage dp=new DetailsPage();
	        status=dp.homedetails1(b);
	        	        
	        	AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						context);			 
					// set title
					alertDialogBuilder.setTitle("Step 2 Successfull.");			 
					// set dialog message
					alertDialogBuilder
						.setMessage("Click Yes to Confirm Signature!")
						.setCancelable(false)
						.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int id) {
								// if this button is clicked, close
								// current activity									
								Intent inte=new Intent(getApplicationContext(),AndroidPaintNext.class);
							    inte.putExtra("useridstatus", status);
							    startActivityForResult(inte,3);						
								//Intent inte=new Intent(getApplicationContext(),AndroidPaint.class);
						       // inte.putExtra("response", dp.details(b));
						        //startActivityForResult(inte,3);	
							}
							})
						.setNegativeButton("No",new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int id) {
								// if this button is clicked, just close
								// the dialog box and do nothing
								dialog.cancel();
								
								//startActivity(new Intent(getApplicationContext(), MobilePhonesAndBiometricsActivity.class));
							}
						});			 
						// create alert dialog
						AlertDialog alertDialog = alertDialogBuilder.create();			 
						// show it
						alertDialog.show();	        
	        
	    }
	    catch(Exception e){
	    	Log.e("IntentExample", "Activity could not be started...");
	    }   
	}


	private void LoginToServer(){	
		try {
			Toast.makeText(getApplicationContext(), "sending file to server......", Toast.LENGTH_SHORT).show(); 
			Intent intent=getIntent();
			HttpClient httpClient = new DefaultHttpClient();
	        HttpPost postRequest = new HttpPost(getResources().getString(R.string.server_url)+"signaturecheck.do");       
	        Toast.makeText(getApplicationContext(), "finalpath is......"+finalpath, Toast.LENGTH_SHORT).show();
			FileInputStream fis = new FileInputStream(new File(finalpath));
			byte[] buffer=new byte[fis.available()];
			fis.read(buffer); 
			@SuppressWarnings("static-access")
			String Base64String = base64.encodeBytes(buffer);   
	           
	        //InputStreamBody isb = new InputStreamBody(new FileInputStream(file),file.getName());
	        MultipartEntity multipartContent = new MultipartEntity();
	        multipartContent.addPart("signaturefile", new StringBody(Base64String.toString()));
	        multipartContent.addPart("positionvalues", new StringBody(vc.toString()));
	        postRequest.setEntity(multipartContent);
	        HttpResponse res =httpClient.execute(postRequest);
	        InputStream is= res.getEntity().getContent();
	          
	        byte[] b=null;
	        ByteArrayOutputStream bos = new ByteArrayOutputStream();
	        int ch;
			while ((ch = is.read()) != -1){
				bos.write(ch);
			}
			b = bos.toByteArray();
			bos.close();
	        is.close();
	    }
	    catch(Exception e){
	    	Log.e("IntentExample", "Activity could not be started...");
	    }   
	}

public void onActivityResult(int requestcode, int resultcode, Intent result ) {
	
	if(requestcode == REQUEST_CODE){
    	if(resultcode == RESULT_OK){
    		color[0] = (result.getDoubleArrayExtra("somitsolutions.training.android.colorpicker.color_of_the_shape"))[0];
    		color[1] = (result.getDoubleArrayExtra("somitsolutions.training.android.colorpicker.color_of_the_shape"))[1];
    		color[2] = (result.getDoubleArrayExtra("somitsolutions.training.android.colorpicker.color_of_the_shape"))[2];
    		//mPaint.setColor(Color.rgb((int)color[0], (int)color[1],(int)color[2]));
    		//GraphicObject.setPaintColor((int)color[0], (int)color[1],(int)color[2]);
    		
    		//test
    		//onStart();
    	}
	}
}


    class Panel extends SurfaceView implements SurfaceHolder.Callback {
        //private TutorialThread _thread;
    	//test
    	public TutorialThread _thread;
    	//private ArrayList<Shape> graphicobjects;
    	//private Canvas canvas;
    	//private Canvas canvas;// = new Canvas(wallPaperBitmap);
        public Panel(Context context) {
            super(context);
            getHolder().addCallback(this);
            _thread = new TutorialThread(getHolder(), this);
            //graphicobjects = new ArrayList<Shape>();
            //canvas = new Canvas(wallPaperBitmap);
            setFocusable(true);
           // wallPaperBitmap = Bitmap.createBitmap(this.getWidth(),this.getHeight(),Bitmap.Config.ARGB_8888);
           setDrawingCacheEnabled(true);
        }
        
        
        public ArrayList<Shape> getGraphicObjects(){
        	return graphicobjects;
        }
        /*private void setGraphicObject(Shape s){
        	GraphicObject = s;
        }
        
        private Shape getGraphicObject(){
        	return GraphicObject;
        }*/
        
        /*public Canvas getWallPaperCanvas(){
        	return canvas;
        }*/
 
        @Override
        public boolean onTouchEvent(MotionEvent event) {
        	//if(shapemenuclicked == true && erasemenuclicked == false){
	        	synchronized (_thread.getSurfaceHolder()) {
	        		
	        		if(event.getAction() == MotionEvent.ACTION_DOWN){
	        			
	        			if(shapemenuclicked == true && erasemenuclicked == false){
		        			//if(GraphicObject instanceof Line){
		        			if(ShapeObject_to_be_created == ShapeLine){
		        				currentGraphicObject = new Line();
		        				
		        				((Line) currentGraphicObject).getBegin().setX(event.getX());
		        				//((Line)(GraphicObject).getBegin().setX(event.getX()));//.setX(event.getX());
		            			//begin.setY(event.getY());
		        				((Line) currentGraphicObject).getBegin().setY(event.getY());
		        			}
		        			
		        			//if(GraphicObject instanceof FreeHand){
		        			if(ShapeObject_to_be_created == ShapeFreehand){	
		        				currentGraphicObject = new FreeHand();
		        						        				
		        				((FreeHand)currentGraphicObject).getPath().moveTo(event.getX(), event.getY());
		  
		        				((FreeHand)currentGraphicObject).getPath().lineTo(event.getX(), event.getY());
		        				
		        			}     
		        			
		        			//if(GraphicObject instanceof Rectangle){
		        			if(ShapeObject_to_be_created == ShapeRect){	
		        				//(Rectangle)GraphicObject.set
		        				//It will be later decided whether this end is upper-left/lower-left/upper-right
		        				//lower-right
		        				currentGraphicObject = new Rectangle();
		        				Point temp = new Point(event.getX(), event.getY());
		        				((Rectangle) currentGraphicObject).settemppointOfOneEndRectangle(temp);
		        			}
		        			
		        			//if(GraphicObject instanceof Circle){
		        			if(ShapeObject_to_be_created == ShapeCircle){	
		        				currentGraphicObject = new Circle();
		        				((Circle)currentGraphicObject).getOneEndOfTheCircle().setX(event.getX());
		        				((Circle)currentGraphicObject).getOneEndOfTheCircle().setY(event.getY());
		        			}
		        			
		        			//if(GraphicObject instanceof Oval){
		        			if(ShapeObject_to_be_created == ShapeOval){
		        				currentGraphicObject = new Oval();
		        				((Oval)currentGraphicObject).getoneEndOfTheOval().setX(event.getX());
		        				((Oval)currentGraphicObject).getoneEndOfTheOval().setY(event.getY());
		        			}
		        			
	        			}
	        			
	        			if(shapemenuclicked == false && erasemenuclicked == true){
	                		//if(GraphicObject instanceof Erase){
	        				if(ShapeObject_to_be_created == ShapeErase){	
	            				currentGraphicObject = new Erase();
	            				
	            				((Erase)currentGraphicObject).getPath().moveTo(event.getX(), event.getY());

	            				((Erase)currentGraphicObject).getPath().lineTo(event.getX(), event.getY());
	            				
	            			}
	        			}
	        			
	        		}
	        		else if(event.getAction() == MotionEvent.ACTION_MOVE){
	        			
	        			if(shapemenuclicked == true && erasemenuclicked == false){
		        			
		        			//if(GraphicObject instanceof FreeHand){
		        			if(ShapeObject_to_be_created == ShapeFreehand){
		        				
		        				//Toast.makeText(getApplicationContext(), event.getX()+"------"+event.getY(), Toast.LENGTH_SHORT).show();
		        				vc.add(event.getX()+","+event.getY());
		        				((FreeHand)currentGraphicObject).getPath().lineTo(event.getX(), event.getY());
		        			} 
		        			
		        			/*if(GraphicObject instanceof Rectangle){
		        			}*/
		        			if(ShapeObject_to_be_created == ShapeLine){
		        				
		        			}
		        			/*if(GraphicObject instanceof Circle){
		        				
		        			}*/
		        			
		        			if(ShapeObject_to_be_created == ShapeRect){
		        				
		        			}
		        			if(ShapeObject_to_be_created == ShapeCircle){
		        				
		        			}
		        			/*
		        			if(GraphicObject instanceof Oval){
		        				
		        			}*/
		        			if(ShapeObject_to_be_created == ShapeOval){
		        				
		        			}
	        			}
	        			
	        			if(shapemenuclicked == false && erasemenuclicked == true){
	        				//if(GraphicObject instanceof Erase){
	        				if(ShapeObject_to_be_created == ShapeErase){
		        				((Erase)currentGraphicObject).getPath().lineTo(event.getX(), event.getY());
		        			}
	        				
	        			}
	        			
	        		}
	        		
	        		
	        		else if(event.getAction() == MotionEvent.ACTION_UP){
	        			
	        			if(shapemenuclicked == true && erasemenuclicked == false){
	        			
		        			//if(GraphicObject instanceof Line){
	        				if(ShapeObject_to_be_created == ShapeLine){	
		        				//create a new Line... Add it to the  ArrayList
		        				//currentGraphicObject = new Line();
		        				
		        				((Line) currentGraphicObject).getEnd().setX(event.getX());
		        				((Line) currentGraphicObject).getEnd().setY(event.getY());
		            			
		        				Point temp_begin = ((Line)currentGraphicObject).getBegin();
		        				Point temp_end = ((Line)currentGraphicObject).getEnd();
		            			((Line) currentGraphicObject).setBegin(temp_begin);
		            			
		            			((Line)currentGraphicObject).getPath().moveTo(temp_begin.getX(), temp_begin.getY());
		            			((Line)currentGraphicObject).getPath().lineTo(temp_end.getX(), temp_end.getY());
		            			//currentGraphicObject.setrgb((int)color[0],(int)color[1],(int)color[2]);
		        			}
		        			   
		        			//if(GraphicObject instanceof FreeHand){	        				
	        				if(ShapeObject_to_be_created == ShapeFreehand){
	        					vc.add(event.getX()+","+event.getY()); 
	        					     
		        				((FreeHand)currentGraphicObject).getPath().lineTo(event.getX(), event.getY());
		        				//_graphics.add(((FreeHand)GraphicObject).getPath());
		        				((FreeHand)currentGraphicObject).getGraphicsPath().add(((FreeHand)currentGraphicObject).getPath());
		        				//currentGraphicObject.setrgb((int)color[0],(int)color[1],(int)color[2]);
		        			}
		        			
		        			//if(GraphicObject instanceof Rectangle){
	        				if(ShapeObject_to_be_created == ShapeRect){
		        				float tempX = ((Rectangle) currentGraphicObject).gettemppointOfOneEndRectangle().getX();
		        				//the X co-ordinate of the first up
		        				float tempY = ((Rectangle) currentGraphicObject).gettemppointOfOneEndRectangle().getY();
		        				float tempX1 = event.getX();
		        				float tempY1 = event.getY();
		        				if(tempX<tempX1 && tempY>tempY1){
		        					((Rectangle)currentGraphicObject).getPath().addRect(tempX, tempY1, tempX1, tempY, Path.Direction.CW);
		        				}
		        				if(tempX<tempX1 && tempY<tempY1){
		        					((Rectangle)currentGraphicObject).getPath().addRect(tempX, tempY, tempX1, tempY1, Path.Direction.CW);
		        				}
		        				if(tempX>tempX1  && tempY>tempY1){
		        					((Rectangle)currentGraphicObject).getPath().addRect(tempX1, tempY1, tempX, tempY, Path.Direction.CW);
		        				}
		        				if(tempX>tempX1 && tempY<tempY1){
		        					((Rectangle)currentGraphicObject).getPath().addRect(tempX1, tempY, tempX, tempY1, Path.Direction.CW);
		        				}
		        				//currentGraphicObject.setrgb((int)color[0],(int)color[1],(int)color[2]);
		        			}
		        			
		        			//if(GraphicObject instanceof Circle){
	        				if(ShapeObject_to_be_created == ShapeCircle){
		        				float tempX1 = ((Circle)currentGraphicObject).getOneEndOfTheCircle().getX();
		        				float tempY1 = ((Circle)currentGraphicObject).getOneEndOfTheCircle().getY();
		        				float tempX2 = event.getX();
		        				float tempY2 = event.getY();
		        				double temp = Math.pow((tempX1-tempX2),2) + Math.pow((tempY1-tempY2),2);
		        				float radius = (float)Math.sqrt(temp)/2;
		        				((Circle)currentGraphicObject).getPath().addCircle((tempX1 + tempX2)/2,(tempY1 + tempY2)/2, radius, Path.Direction.CW);
		        				
		        				//currentGraphicObject.setrgb((int)color[0],(int)color[1],(int)color[2]);
		        			}
		        			
		        			//if(GraphicObject instanceof Oval){
	        				if(ShapeObject_to_be_created == ShapeOval){	
		        				float tempX = ((Oval)currentGraphicObject).getoneEndOfTheOval().getX();
		        				float tempY = ((Oval)currentGraphicObject).getoneEndOfTheOval().getY();
		        				float tempX1 = event.getX();
		        				float tempY1 = event.getY();
		        				
		        				if(tempX<=tempX1 && tempY>=tempY1){
		           					((Oval)currentGraphicObject).getRectangle().set(tempX,tempY1,tempX1,tempY);	
		        				}
		        				
		        				if(tempX<=tempX1 && tempY<=tempY1){
		        					
		        					((Oval)currentGraphicObject).getRectangle().set(tempX,tempY,tempX1,tempY1);
		        					
		        				}
		        				
		        				if(tempX>=tempX1  && tempY>=tempY1){
		        					
		        					((Oval)currentGraphicObject).getRectangle().set(tempX1,tempY1,tempX,tempY);
		        					
		        				}
		        				
		        				if(tempX>=tempX1 && tempY<=tempY1){
		        					
		        					((Oval)currentGraphicObject).getRectangle().set(tempX1,tempY,tempX,tempY1);
		        				}
		        				
		        				//RectF r = ((Oval)GraphicObject).getRectangle();
		        				((Oval)currentGraphicObject).getPath().addOval(((Oval)currentGraphicObject).getRectangle(), Path.Direction.CW);
		        				//((Oval)GraphicObject).getPath().addOval(r, Path.Direction.CW);
		        			}
		        		}
	        			
	        			if(shapemenuclicked == false && erasemenuclicked == true){
	        				//if(GraphicObject instanceof Erase){
	        				if(ShapeObject_to_be_created == ShapeErase){
		        				((Erase)currentGraphicObject).getPath().lineTo(event.getX(), event.getY());
		        				//_graphics.add(((FreeHand)GraphicObject).getPath());
		        				((Erase)currentGraphicObject).getGraphicsPath().add(((Erase)currentGraphicObject).getPath());
		        				//currentGraphicObject.setrgb((int)color[0],(int)color[1],(int)color[2]);
		        			}
	        			}
	        			
	        			if(colormenuclicked == false && shapemenuclicked == true){
		        			currentGraphicObject.setrgb(100,100,100);	
		        		}
		        		if(colormenuclicked == true && shapemenuclicked == true){
		        			currentGraphicObject.setrgb((int)color[0],(int)color[1],(int)color[2]);
		        		}
		        		
		        		if(erasemenuclicked == true && colormenuclicked == false){
		        			currentGraphicObject.setrgb(0,0,0);
		        			//mPaint.setStrokeWidth(6);
		        		}
		        		
		        		if(brushwidthmenuclicked == true){//  || erasemenuclicked == true){
		        			currentGraphicObject.setStrokeWidth(BrushWidth);
		        		}
		        		graphicobjects.add(currentGraphicObject);
		        		//path_of_shape_for_WallPaperBitmap = currentGraphicObject.getPath();
		        		//path_of_shape_for_WallPaperBitmap.offset(640, 0);
		        		number_of_graphicObjects++;
	        		}
	        		//add the GraphicObject to the ArrayList graphicobjects
	        		//GraphicObject.setPaintColor((int)color[0], (int)color[1], (int)color[2]);
	        		/*if(colormenuclicked == false && shapemenuclicked == true){
	        			currentGraphicObject.setrgb(100,100,100);	
	        		}
	        		if(colormenuclicked == true && shapemenuclicked == true){
	        			currentGraphicObject.setrgb((int)color[0],(int)color[1],(int)color[2]);
	        		}
	        		
	        		if(erasemenuclicked == true && colormenuclicked == false){
	        			currentGraphicObject.setrgb(0,0,0);
	        			//mPaint.setStrokeWidth(6);
	        		}
	        		
	        		if(brushwidthmenuclicked == true){//  || erasemenuclicked == true){
	        			currentGraphicObject.setStrokeWidth(BrushWidth);
	        		}
	        		graphicobjects.add(currentGraphicObject);
	        		//path_of_shape_for_WallPaperBitmap = currentGraphicObject.getPath();
	        		//path_of_shape_for_WallPaperBitmap.offset(640, 0);
	        		number_of_graphicObjects++;*/
	        	}
        	 //}
        	
	      return true;
    
        }
        
        @Override
        public void onDraw(Canvas canvas) {
        	//if(number_of_graphicObjects != 0){
        	//createBitmapforWallPaper();
        	//Matrix matrix = new Matrix();
        		for(int i = 0; i<number_of_graphicObjects; i++){
	        		
	        		Shape currentGraphicObject = graphicobjects.get(i);
	        		//if colormenu is clicked then only change the color of the brush
	        		//else take the color from InitializePaint
	        		//if(colormenuclicked == true){
	        			mPaint.setColor(Color.rgb(currentGraphicObject.getrgb()[0], currentGraphicObject.getrgb()[1], currentGraphicObject.getrgb()[2]));
	        			mPaint.setStrokeWidth(currentGraphicObject.getStrokeWidth());
	        		//}
	        		
	        		if(currentGraphicObject instanceof FreeHand){
	        			for (Path path : ((FreeHand)currentGraphicObject).getGraphicsPath()) {	    	        		
	                	    canvas.drawPath(path,mPaint);
	                	    bitmapCanvas.drawPath(path,mPaint);
	            		}
	        		}
	        		
	        		else if(currentGraphicObject instanceof Erase){
	        			for (Path path : ((Erase)currentGraphicObject).getGraphicsPath()) {
	                	    canvas.drawPath(path, mPaint);
	                	    bitmapCanvas.drawPath(path, mPaint);
	            		}	
	        		}
	        		
	        		else{
	        			canvas.drawPath(currentGraphicObject.getPath(),mPaint);
	        			//Path pathTemp = currentGraphicObject.getPath();
	        			//pathTemp.offset(640,0);
	        			bitmapCanvas.drawPath(currentGraphicObject.getPath(),mPaint);
	        			//bitmapCanvas.drawPath(path_of_shape_for_WallPaperBitmap, mPaint);
	        		}
	        	}
        		/*try{
        			fos.close();
        		}
        		catch (FileNotFoundException e) {
                    Log.e("Panel", "FileNotFoundException", e);
                } catch (IOException e) {
                    Log.e("Panel", "IOEception", e);
                }*/
        	//}
	      }
 
       
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            // TODO Auto-generated method stub
        	//invalidate();
        	
        }
 
        
        public void surfaceCreated(SurfaceHolder holder) {
            _thread.setRunning(true);
            _thread.start();
        }
 
        
        public void surfaceDestroyed(SurfaceHolder holder) {
            // simply copied from sample application LunarLander:
            // we have to tell thread to shut down & wait for it to finish, or else
            // it might touch the Surface after we return and explode
            boolean retry = true;
            _thread.setRunning(false);
            while (retry) {
                try {
                    _thread.join();
                    retry = false;
                } catch (InterruptedException e) {
                    // we will try it again and again...
                }
            }
        }
        
        public void createBitmapforWallPaper() {
            //if (ensureSDCardAccess()) {
                //Bitmap bitmap = Bitmap.createBitmap(p.getWidth(), p.getHeight(), Bitmap.Config.ARGB_8888);
               // Canvas canvas = new Canvas(bitmap);
                //canvas.drawBitmap(bitmap, bitmap.getWidth(),bitmap.getHeight(), null);
        		//Display display = getWindowManager().getDefaultDisplay();
        		//int width = display.getWidth();
        		//int height = display.getHeight();
        	
            	//wallPaperBitmap = Bitmap.createBitmap(wallpaperWidth, wallpaperHeight, Bitmap.Config.ARGB_8888);
        		//wallPaperBitmap = Bitmap.createBitmap(640, 480, Bitmap.Config.ARGB_8888);
        	       	
        		//bitmapCanvas = new Canvas(wallPaperBitmap);
                //file = new File(mScreenshotPath + "/" + "screeshot.jpg");
                //FileOutputStream fos;
                //try {
                    //fos = new FileOutputStream(file);
                    //wallPaperBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                    //fos.close();
                //} catch (FileNotFoundException e) {
                    //Log.e("Panel", "FileNotFoundException", e);
                //} catch (IOException e) {
                   // Log.e("Panel", "IOEception", e);
                //}
           // }
        }
         
      
    }
 
    class TutorialThread extends Thread {
        private SurfaceHolder _surfaceHolder;
        private Panel _panel;
        private boolean _run = false;
 
        public TutorialThread(SurfaceHolder surfaceHolder, Panel panel) {
            _surfaceHolder = surfaceHolder;
            _panel = panel;
        }
 
        public void setRunning(boolean run) {
            _run = run;
        }
 
        public SurfaceHolder getSurfaceHolder() {
            return _surfaceHolder;
        }
 
        @Override
        public void run() {
            Canvas c;
            while (_run) {
                c = null;
                try {
                    c = _surfaceHolder.lockCanvas(null);
                    synchronized (_surfaceHolder) {
                        _panel.onDraw(c);
                        //_panel.saveScreenshot();
                    }
                } finally {
                    // do this in a finally so that if an exception is thrown
                    // during the above, we don't leave the Surface in an
                    // inconsistent state
                    if (c != null) {
                        _surfaceHolder.unlockCanvasAndPost(c);
                    }
                }
            }
            //_panel.saveScreenshot();
        }
    }

	@Override
	public boolean onTouch(View arg0, MotionEvent event) {
		// TODO Auto-generated method stub
		Toast.makeText(getApplicationContext(), "------------", Toast.LENGTH_SHORT).show();
		builder.setLength(0);
	    switch (event.getAction()) {
	    case MotionEvent.ACTION_DOWN:
	      builder.append("down, ");
	      break;
	    case MotionEvent.ACTION_MOVE:
	      builder.append("move, ");
	      break;
	    case MotionEvent.ACTION_CANCEL:
	      builder.append("cancle, ");
	      break;
	    case MotionEvent.ACTION_UP:
	      builder.append("up, ");
	      break;
	    }
	    builder.append(event.getX());
	    builder.append(", ");
	    builder.append(event.getY());
	    String text = builder.toString();
	    Log.d("TouchTest", text);
	    Toast.makeText(getApplicationContext(), "------------"+text, Toast.LENGTH_SHORT).show();
	    textView.setText(text);
	    return true;
	}
  }
