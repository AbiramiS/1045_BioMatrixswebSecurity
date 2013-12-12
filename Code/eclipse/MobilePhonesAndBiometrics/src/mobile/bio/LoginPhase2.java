package mobile.bio;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginPhase2 extends Activity {
	
	EditText password;
	Button submit;
	String status,imeino;
	final Context context = this;
	
	private class SubmitListener implements View.OnClickListener{
	    public void onClick( View view ){
	    	if(!password.getText().toString().equals("")){
	    		
	    		TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		    	imeino = telephonyManager.getDeviceId();
		    	
	    		SharedPreferences pref=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
	    		HttpClient httpClient = new DefaultHttpClient();
    			HttpGet getRequest=new HttpGet(getResources().getString(R.string.server_url)+"passverification.do?sqlitevalue="+pref.getString("phone_imei_number", "000000000000000")+"&mobpass="+password.getText().toString()+"&mobimeino="+imeino);
    			try {
					HttpResponse res= httpClient.execute(getRequest);
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
			        status=dp.homedetails(b);
			        
			        if(!status.equals("")){
			        	
			        	 AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
									context);			 
								// set title
								alertDialogBuilder.setTitle("Password Verified");			 
								// set dialog message
								alertDialogBuilder
									.setMessage("Proceed to mBanking......")
									.setCancelable(false)
									.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
										public void onClick(DialogInterface dialog,int id) {
											// if this button is clicked, close
											// current activity				
											Intent inte=new Intent(getApplicationContext(),ServiceChooser.class);  
								        	inte.putExtra("useridfinal11", status);
								        	startActivityForResult(inte, 3);
											/*Intent inte=new Intent(getApplicationContext(),AndroidPaintFinal.class);
											inte.putExtra("useridfinal1", status); 
										    startActivityForResult(inte,3);*/
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
									
			        	//Intent inte=new Intent(getApplicationContext(),ServiceChooser.class);
			        	//inte.putExtra("userid", getIntent().getStringExtra("userid"));
			        	//startActivityForResult(inte, 3);			        	
			        	
			        }else {
						Toast.makeText(getApplicationContext(), "Validation Unsuccessful", Toast.LENGTH_SHORT).show();
					}
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
				}
	    	}else {
	    		Toast.makeText(getApplicationContext(), "Please enter the password", Toast.LENGTH_SHORT).show();
			}
	    }
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loginphase_final);
		password=(EditText)findViewById(R.id.login_final_password);
		submit=(Button)findViewById(R.id.login_final_submit);
		submit.setOnClickListener(new SubmitListener());
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode == RESULT_OK && requestCode == 3){
	    	if(data.getBooleanExtra("done", false)){
	    		setResult(RESULT_OK, data);
	    		finish();
	    	}
	    }
	}

}
