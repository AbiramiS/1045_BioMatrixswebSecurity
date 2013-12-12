package mobile.bio;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Vector;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginCaptureImage extends Activity {
	
	private String path="";
	protected Button _button,submit;
	protected ImageView _image;
	protected boolean _taken;

	protected static final String PHOTO_TAKEN = "photo_taken";
	
	private class TakePictureListener implements View.OnClickListener {
	    public void onClick( View view ) {
	    	startCameraActivity();
	    }
	}
	
	private class SubmitListener implements View.OnClickListener {
	    public void onClick( View view ) {
	    	if(_taken==true) {
	    		try {
	    				Intent intent=getIntent();
			    		HttpClient httpClient = new DefaultHttpClient();
				        HttpPost postRequest = new HttpPost(getResources().getString(R.string.server_url)+"clientlogin.do");
				        File file=new File(path);
				        
				        InputStreamBody isb = new InputStreamBody(new FileInputStream(file),file.getName());
				        MultipartEntity multipartContent = new MultipartEntity();
				        multipartContent.addPart("uploadedFile", isb);
				        multipartContent.addPart("userid", new StringBody(intent.getStringExtra("userid")));
				        multipartContent.addPart("accno", new StringBody(intent.getStringExtra("accno")));

				        postRequest.setEntity(multipartContent);
				        HttpResponse res =httpClient.execute(postRequest);
				        InputStream is= res.getEntity().getContent();
				        
				        byte[] b=null;
				        ByteArrayOutputStream bos = new ByteArrayOutputStream();
				        int ch;
						while ((ch = is.read()) != -1)
							bos.write(ch);
						
						b = bos.toByteArray();
						bos.close();
				        is.close();
				        
				        DetailsPage dp=new DetailsPage();
				        dp.recognitiondetails(b);
				        
				        if(dp.recognitiondetails(b).equalsIgnoreCase("USER RECOGNIZED")) {
				        	Toast.makeText(getApplicationContext(), "USER RECOGNIZED", Toast.LENGTH_LONG).show();
					        Intent inte=new Intent(getApplicationContext(),LoginPhase2.class);
					        inte.putExtra("userid",intent.getStringExtra("userid"));
					        startActivityForResult(inte,3);
				        } else if(dp.recognitiondetails(b).equalsIgnoreCase("USER NOT RECOGNIZED")){
				        	Toast.makeText(getApplicationContext(), "USER NOT RECOGNIZED", Toast.LENGTH_LONG).show();
				        	//Toast.makeText(getApplicationContext(), dp.recognitiondetails(b), Toast.LENGTH_SHORT).show();
				        	startActivity(new Intent(getApplicationContext(), LoginPhase1.class));
				        } 
				        else if(dp.recognitiondetails(b).equalsIgnoreCase("FAKE USER")){
				        	Toast.makeText(getApplicationContext(), "FAKE USER", Toast.LENGTH_LONG).show();
				        	//Toast.makeText(getApplicationContext(), dp.recognitiondetails(b), Toast.LENGTH_SHORT).show();
				        	startActivity(new Intent(getApplicationContext(), LoginPhase1.class));
				        } 
	    			
	    		} catch (Exception e) {
	    			Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
				} 
	    	}
	    } 
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		path = Environment.getExternalStorageDirectory() + "/images/1.jpg";
		setContentView(R.layout.image_holder);
		
		TextView v=(TextView)findViewById(R.id.registration_or_login);
		v.setText(getResources().getString(R.string.login));
		
		Button takeImage=(Button)findViewById(R.id.imagetaker);
		takeImage.setOnClickListener(new TakePictureListener());
		
		_image=(ImageView)findViewById(R.id.imageView1);
		
		submit=(Button)findViewById(R.id.image_submitter);
		submit.setOnClickListener(new SubmitListener());
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
    	setResult(RESULT_OK,new Intent());
	}
	
	void startCameraActivity() {
		path = Environment.getExternalStorageDirectory() + "/images/1.jpg";
	    File file = new File( path );
	    Uri outputFileUri = Uri.fromFile( file );

	    Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
	    intent.putExtra( MediaStore.EXTRA_OUTPUT, outputFileUri );

	    startActivityForResult( intent, 0 );
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    try {
		    if (resultCode == Activity.RESULT_OK && requestCode == 0) {
		        onPhotoTaken();
		    } else if(resultCode == RESULT_OK && requestCode == 3) {
		    	if(data.getBooleanExtra("done", false)){
		    		setResult(RESULT_OK, data);
		    		finish();
		    	}
		    }
	    }catch(Exception e) {
	    	Toast.makeText(getApplicationContext(),"inside catch activity "+ e.getMessage(), Toast.LENGTH_LONG).show();
	    }
	}
	
	protected void onPhotoTaken() {
		try {
		    _taken = true;
	
		    BitmapFactory.Options options = new BitmapFactory.Options();
		    options.inSampleSize = 4;
	
		    Bitmap bitmap = BitmapFactory.decodeFile( path, options );
		    _image.setImageBitmap(bitmap);
		    submit.setEnabled(true);
		    Toast.makeText(getApplicationContext(), path, Toast.LENGTH_LONG).show();
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(),"inside catch on photo taken "+ e.getMessage(), Toast.LENGTH_LONG).show();
		}
	
	}
	
}