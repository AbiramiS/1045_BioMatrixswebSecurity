package mobile.bio;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.StringTokenizer;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterCaptureImage extends Activity {
	
	private String path="";
	Base64 base64;
	byte[] b=null;
	String aac="",ause="",a1="",a2="";
	final Context context = this;
	protected Button _button,submit;
	protected ImageView _image;
	protected boolean _taken;
	String imeino="", pp="", status="";
	int count=1,ncount=1;
    DetailsPage dp=new DetailsPage();
	
	protected static final String PHOTO_TAKEN = "photo_taken";
	
	private class TakePictureListener implements View.OnClickListener{
	    public void onClick( View view ){
	    	startCameraActivity(); 
	    }
	}
	
	private class SubmitListener implements View.OnClickListener{
	    public void onClick( View view ){    	
	    	if(_taken==true){
	    		try {
	    			Intent intent=getIntent();
	    			pp = getIntent().getStringExtra("username")+".jpg";
    				path = Environment.getExternalStorageDirectory() + "/images/"+getIntent().getStringExtra("username")+".jpg";
    			    HttpClient httpClient = new DefaultHttpClient();
			        HttpPost postRequest = new HttpPost(getResources().getString(R.string.server_url)+"clientregister.do");
					FileInputStream fis1 = new FileInputStream(new File(path));
					byte[] buffer1=new byte[fis1.available()];
					fis1.read(buffer1);
					@SuppressWarnings("static-access")
					String Base64String1 = base64.encodeBytes(buffer1);
					 
					TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
			    	imeino = telephonyManager.getDeviceId();				        
			        MultipartEntity multipartContent = new MultipartEntity();
			        multipartContent.addPart("uploadedFile1", new StringBody(Base64String1.toString()));			        
			        multipartContent.addPart("username", new StringBody(intent.getStringExtra("username")));
			        multipartContent.addPart("password", new StringBody(intent.getStringExtra("password")));
			        multipartContent.addPart("gender", new StringBody(intent.getStringExtra("gender")));
			        multipartContent.addPart("email", new StringBody(intent.getStringExtra("email")));
			        multipartContent.addPart("contactnumber", new StringBody(intent.getStringExtra("contactnumber")));
			        multipartContent.addPart("proof", new StringBody(intent.getStringExtra("proof")));
			        multipartContent.addPart("phase3id", new StringBody(intent.getStringExtra("phase3id")));
			        multipartContent.addPart("imeino", new StringBody(imeino));
			        postRequest.setEntity(multipartContent);
			        HttpResponse res =httpClient.execute(postRequest);
			        InputStream is= res.getEntity().getContent();			          
			        
			        ByteArrayOutputStream bos = new ByteArrayOutputStream();
			        int ch;
					while ((ch = is.read()) != -1){
						bos.write(ch);
					}
					b = bos.toByteArray();
					bos.close();
			        is.close();
				        			        
				        if(dp.details(b).equalsIgnoreCase("Sorry, Password already chosen...Try some other password")) {
				        	Toast.makeText(getApplicationContext(), "Sorry, Password already chosen...Try some other password", Toast.LENGTH_SHORT).show();
				        	startActivity(new Intent(getApplicationContext(), MobilePhonesAndBiometricsActivity.class));
				        } 
				        else if(dp.details(b).equalsIgnoreCase("Fake User Found., Identity Mismatching...")) {
				        	Toast.makeText(getApplicationContext(), "Fake User Found., Identity Mismatching...", Toast.LENGTH_SHORT).show();
				        	startActivity(new Intent(getApplicationContext(), MobilePhonesAndBiometricsActivity.class));
				        } else {				        	
				        	
				        	DetailsPage dp=new DetailsPage();
				            status=dp.details1(b);
				            
				    		StringTokenizer st11=new StringTokenizer(status, "\n");
				    		while(st11.hasMoreTokens()){
				    			aac=st11.nextToken();
				    			ause=st11.nextToken();
				    		}
				        	/**/
				        	AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
									context);			 
								// set title
								alertDialogBuilder.setTitle("Registration Successfull.");			 
								// set dialog message
								alertDialogBuilder 
									.setMessage(aac+"\n"+ause)
									.setCancelable(false)
									.setPositiveButton("Finish",new DialogInterface.OnClickListener() {
										public void onClick(DialogInterface dialog,int id) {
											// if this button is clicked, close
											// current activity									
											startActivity(new Intent(getApplicationContext(), MobilePhonesAndBiometricsActivity.class));					
											//Intent inte=new Intent(getApplicationContext(),AndroidPaint.class);
									       // inte.putExtra("response", dp.details(b));
									        //startActivityForResult(inte,3);	
										}
										})
									.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
										public void onClick(DialogInterface dialog,int id) {
											
											// if this button is clicked, just close
											// the dialog box and do nothing
											dialog.cancel();
											
											startActivity(new Intent(getApplicationContext(), MobilePhonesAndBiometricsActivity.class));
										}
									});			 
									// create alert dialog
									AlertDialog alertDialog = alertDialogBuilder.create();			 
									// show it
									alertDialog.show();	 
				        	/**/
				        	 
				        	
				            } 		
			        
	    		}catch (Exception e) {
	    			Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
				}
	    	}
	    }
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		startPictureHolder();
	}
	
	public void startPictureHolder(){
		path = Environment.getExternalStorageDirectory() + "/images/"+getIntent().getStringExtra("username")+".jpg";
		
		setContentView(R.layout.image_holder);
		
		TextView v=(TextView)findViewById(R.id.registration_or_login);
		v.setText(getResources().getString(R.string.registration_title));
		
		Button takeImage=(Button)findViewById(R.id.imagetaker);
		takeImage.setOnClickListener(new TakePictureListener());
		
		_image=(ImageView)findViewById(R.id.imageView1);
		
		submit=(Button)findViewById(R.id.image_submitter);
		submit.setOnClickListener(new SubmitListener());
	}
	
	void startCameraActivity()	{
	    File file = new File( path );
	    Uri outputFileUri = Uri.fromFile( file );

	    Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
	    intent.putExtra( MediaStore.EXTRA_OUTPUT, outputFileUri );

	    startActivityForResult( intent, 0 );
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
	    try{
		    if (resultCode == RESULT_OK && requestCode == 0) {
		        onPhotoTaken();
		    }else if(resultCode == RESULT_OK && requestCode == 3){
		    	if(data.getBooleanExtra("done", false)){
		    		setResult(RESULT_OK, data);
		    		finish();
		    	}
		    }
	    }catch(Exception e){
	    	Toast.makeText(getApplicationContext(),"inside catch activity "+ e.getMessage(), Toast.LENGTH_LONG).show();
	    }
	}
	
	protected void onPhotoTaken(){
		try{
		    _taken = true;
	
		    BitmapFactory.Options options = new BitmapFactory.Options();
		    options.inSampleSize = 4;
	
		    Bitmap bitmap = BitmapFactory.decodeFile( path, options );
		    _image.setImageBitmap(bitmap);
		    submit.setEnabled(true);
		    Toast.makeText(getApplicationContext(), path, Toast.LENGTH_LONG).show();
		}catch (Exception e) {
			Toast.makeText(getApplicationContext(),"inside catch on photo taken "+ e.getMessage(), Toast.LENGTH_LONG).show();
		}
	}
}