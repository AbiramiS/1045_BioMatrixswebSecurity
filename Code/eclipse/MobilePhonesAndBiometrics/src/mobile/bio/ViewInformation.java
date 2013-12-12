package mobile.bio;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ViewInformation extends Activity {
		
	@Override
	public void onBackPressed() {
		Intent intent= new Intent();
    	intent.putExtra("done", true);
    	setResult(RESULT_OK,intent);
    	finish();
    	super.onBackPressed();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail);
		TextView tv=(TextView)findViewById(R.id.all_detail);
		tv.setText(getIntent().getStringExtra("response"));
	}

}
