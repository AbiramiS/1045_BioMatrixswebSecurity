package mobile.bio;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RegisterSecond extends Activity {
    /** Called when the activity is first created. */
	 private RadioGroup rg1;
	 String username, password, email, contactnumber, gender;
    @Override
   
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registersecond);
        
         username=getIntent().getStringExtra("username");
         password=getIntent().getStringExtra("password");
         email=getIntent().getStringExtra("email");
         contactnumber=getIntent().getStringExtra("contactnumber");
         gender=getIntent().getStringExtra("gender");
        rg1=(RadioGroup)findViewById(R.id.service_holder1);
        Button login=(Button)findViewById(R.id.login_next1);
        login.setOnClickListener(new LoginListener());
    }
    
    private class LoginListener implements View.OnClickListener{
	    public void onClick( View view ){
	    	RadioButton rb=(RadioButton)findViewById(rg1.getCheckedRadioButtonId());	    	 
	    	Intent intent=new Intent(getApplicationContext(), RegisterThird.class);
    		intent.putExtra("username", username);
    		intent.putExtra("password", password);
    		intent.putExtra("email", email);
    		intent.putExtra("contactnumber", contactnumber);
    		intent.putExtra("gender", gender);
    		intent.putExtra("proof", rb.getText().toString());
    		startActivityForResult(intent,3);
	    }
	}
    

}