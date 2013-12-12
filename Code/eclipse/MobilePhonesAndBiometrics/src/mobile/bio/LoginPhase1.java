package mobile.bio;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginPhase1 extends Activity {
	
	private Button next;
	private EditText username,account;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		next=(Button)findViewById(R.id.login_next);
		next.setOnClickListener(new NextListener());
		username=(EditText)findViewById(R.id.login_username);
		account=(EditText)findViewById(R.id.login_account);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
	    try{
	    	if(resultCode == RESULT_OK && requestCode == 3){
		    	if(data.getBooleanExtra("done", false)){
		    		finish();
		    	}
		    }
	    }catch(Exception e){
	    	Toast.makeText(getApplicationContext(),"inside catch activity "+ e.getMessage(), Toast.LENGTH_LONG).show();
	    }
	}

	private class NextListener implements View.OnClickListener{
	    public void onClick( View view ){
	    	if(!username.getText().toString().equals("")){
	    		if(!account.getText().toString().equals("")){
	    			Intent inte=new Intent(getApplicationContext(), LoginCaptureImage.class);
	    			inte.putExtra("userid", username.getText().toString());
	    			inte.putExtra("accno", account.getText().toString());
	    			startActivityForResult(inte,3);
		    	}else {
					Toast.makeText(getApplicationContext(), "Please enter the account number", Toast.LENGTH_SHORT).show();
				}
	    	}else {
	    		Toast.makeText(getApplicationContext(), "Please enter the user id.", Toast.LENGTH_SHORT).show();
			}
	    }
	}	
}