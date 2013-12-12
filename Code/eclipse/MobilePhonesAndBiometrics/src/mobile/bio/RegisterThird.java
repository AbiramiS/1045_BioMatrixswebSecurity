package mobile.bio;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RegisterThird extends Activity {
    /** Called when the activity is first created. */
	 private RadioGroup rg1;
	 String username, password, email, contactnumber, gender, proof;
	 EditText phase3id;
    @Override
   
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registerthird);
        
         username=getIntent().getStringExtra("username");
         password=getIntent().getStringExtra("password");
         email=getIntent().getStringExtra("email");
         contactnumber=getIntent().getStringExtra("contactnumber");
         gender=getIntent().getStringExtra("gender");
         proof=getIntent().getStringExtra("proof");
         
         phase3id=(EditText)findViewById(R.id.register_phase3_id);
        
        Button login=(Button)findViewById(R.id.login_next12);
        login.setOnClickListener(new LoginListener());
    }
    
    private class LoginListener implements View.OnClickListener{
	    public void onClick( View view ){	
	    	boolean done=true;
	    	if(phase3id.getText().toString().equals("")){
	    		done&=false;
	    		Toast.makeText(getApplicationContext(), "Please enter the Identity", Toast.LENGTH_SHORT).show();
	    	}
	    	if(done){
	    	Intent intent=new Intent(getApplicationContext(), RegisterCaptureImage.class);
    		intent.putExtra("username", username);
    		intent.putExtra("password", password);
    		intent.putExtra("email", email);
    		intent.putExtra("contactnumber", contactnumber);
    		intent.putExtra("gender", gender);
    		intent.putExtra("proof", proof);
    		intent.putExtra("phase3id", phase3id.getText().toString());
    		startActivityForResult(intent,3);
	    	}
	    }
	}
    

}