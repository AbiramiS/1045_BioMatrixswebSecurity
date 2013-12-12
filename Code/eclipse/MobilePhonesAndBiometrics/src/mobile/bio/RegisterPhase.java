package mobile.bio;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterPhase extends Activity {

	private EditText username,password,email,contactnumber;
	private Button next;
	private RadioGroup rg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_phase_1);
		username=(EditText)findViewById(R.id.register_phase1_username);
		password=(EditText)findViewById(R.id.register_phase1_password);
		email=(EditText)findViewById(R.id.register_phase1_email);
		contactnumber=(EditText)findViewById(R.id.register_phase1_contact);
		rg=(RadioGroup)findViewById(R.id.radioGroup1);

		next=(Button)findViewById(R.id.register_phase1_next);
		next.setOnClickListener(new NextListener());
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
	    try{
	    	if(resultCode == RESULT_OK && requestCode == 3){
		    	if(data.getBooleanExtra("done", false))
		    		finish();
		    }
	    }catch(Exception e){
	    	Toast.makeText(getApplicationContext(),"inside catch activity "+ e.getMessage(), Toast.LENGTH_LONG).show();
	    }
	}

	private class NextListener implements View.OnClickListener{
	    public void onClick( View view ){
	    	boolean done=true;
	    	String regExpn =
	        	"^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
	        	    +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
	        	      +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
	        	      +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
	        	      +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
	        	      +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";
	        Pattern patternObj = Pattern.compile(regExpn);
	        Matcher matcherObj = patternObj.matcher(email.getText().toString());

	        Pattern pattern = Pattern.compile("\\d{10}");
	        Matcher matcher = pattern.matcher(contactnumber.getText().toString());

	    	if(username.getText().toString().equals("")) {
	    		done&=false;
	    		Toast.makeText(getApplicationContext(), "Please enter the username", Toast.LENGTH_SHORT).show();
	    	}
	    	else if(password.getText().toString().equals("")) {
	    		done&=false;
	    		Toast.makeText(getApplicationContext(), "Please enter the password", Toast.LENGTH_SHORT).show();
	    	}
	    	else if(email.getText().toString().equals("")) {
	    		done&=false;
	    		Toast.makeText(getApplicationContext(), "Please enter the email", Toast.LENGTH_SHORT).show();
	    	}
	    	else if(contactnumber.getText().toString().equals("")) {
	    		done&=false;
	    		Toast.makeText(getApplicationContext(), "Please enter the contact number", Toast.LENGTH_SHORT).show();
	    	}
	    	else if(!matcherObj.matches()) {
	    		done&=false;
				Toast.makeText(getApplicationContext(), "Please enter valid email id...", Toast.LENGTH_SHORT).show();

			}
	    	else if (!matcher.matches()) {
	        	done&=false;
	    		Toast.makeText(getApplicationContext(), "Mobile Number should be 10 digits", Toast.LENGTH_SHORT).show();
	        }
	    	if(done) {
	    		RadioButton rb=(RadioButton)findViewById(rg.getCheckedRadioButtonId());
	    		Intent intent=new Intent(getApplicationContext(), RegisterSecond.class);
	    		intent.putExtra("username", username.getText().toString());
	    		intent.putExtra("password", password.getText().toString());
	    		intent.putExtra("email", email.getText().toString());
	    		intent.putExtra("contactnumber", contactnumber.getText().toString());
	    		intent.putExtra("gender", rb.getText().toString());
	    		startActivityForResult(intent,3);
	    	}
	    }
	}
}