package mobile.bio;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MobilePhonesAndBiometricsActivity extends Activity {
    /** Called when the activity is first created. */
	ImageView i1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);   
        findViewById(R.id.widget29).setSelected(true);
        i1=(ImageView)findViewById(R.id.imageView1);
        Animation a = AnimationUtils.loadAnimation(this, R.anim.rotation);
		i1.startAnimation(a);
        Button login=(Button)findViewById(R.id.login);
        Button register=(Button)findViewById(R.id.register);
        login.setOnClickListener(new LoginListener());
        register.setOnClickListener(new RegisterListener());
    }
    
    private class LoginListener implements View.OnClickListener{
	    public void onClick( View view ){
	    	startActivity(new Intent(getApplicationContext(), LoginPhase1.class));
	    }
	}
    
    private class RegisterListener implements View.OnClickListener{
	    public void onClick( View view ){
	    	startActivity(new Intent(getApplicationContext(), RegisterPhase.class));
	    }
	}
}