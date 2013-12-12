package mobile.bio;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ServiceChooser extends Activity {
	
	RadioGroup rg,bank;
	EditText transfer_branch,transfer_account,transfer_amount;
	
	private class SubmitListener implements View.OnClickListener{
	    public void onClick( View view ){
	    	RadioButton rb=(RadioButton)findViewById(rg.getCheckedRadioButtonId());
	    	if(rb.getText().toString().equals(getResources().getString(R.string.balance_label))){
	    		HttpClient httpClient = new DefaultHttpClient();
    			HttpGet getRequest=new HttpGet(getResources().getString(R.string.server_url)+"balancesummary.do?balance=BalanceSummary&userid="+getIntent().getStringExtra("useridfinal11"));
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
			        String status=dp.balancedetails(b);
			        
			        Intent inte=new Intent(getApplicationContext(),ViewInformation.class);
			        inte.putExtra("response", status);
			        startActivityForResult(inte, 3);
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
				}
	    	}else {
	    		setContentView(R.layout.schedule_transfer);
	    		bank=(RadioGroup)findViewById(R.id.bank_chooser);
	    		transfer_account=(EditText)findViewById(R.id.transfer_acc_no);
	    		transfer_amount=(EditText)findViewById(R.id.tranfer_amount_to);
	    		transfer_branch=(EditText)findViewById(R.id.transfer_branch_name);
	    		Button transfer_submit=(Button)findViewById(R.id.transfer_submit);
	    		transfer_submit.setOnClickListener(new TransferSubmitListener());
			}
	    }
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_success);
		rg=(RadioGroup)findViewById(R.id.service_holder);
		Button submit=(Button)findViewById(R.id.which_option);
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

	private class TransferSubmitListener implements View.OnClickListener{
	    public void onClick( View view ){
	    	
	    	try{
		    	boolean done=true;
		    	
		    	if(transfer_account.getText().toString().equals("")){
		    		done&=false;
		    		Toast.makeText(getApplicationContext(), "Please enter the acc no.", Toast.LENGTH_SHORT).show();
		    	}
		    	if(transfer_amount.getText().toString().equals("")){
		    		done&=false;
		    		Toast.makeText(getApplicationContext(), "Please enter the amount", Toast.LENGTH_SHORT).show();
		    	}
		    	if(transfer_branch.getText().toString().equals("")){
		    		done&=false;
		    		Toast.makeText(getApplicationContext(), "Please enter the branch name", Toast.LENGTH_SHORT).show();
		    	}
		    	
		    	if(done){
		    		RadioButton rb=(RadioButton)findViewById(bank.getCheckedRadioButtonId());
		    		HttpClient httpClient = new DefaultHttpClient();
		    		String url=getResources().getString(R.string.server_url)+"transaction.do?senderid="+getIntent().getStringExtra("useridfinal11")+"&bankname="+rb.getText().toString();
		    		url+="&accno="+transfer_account.getText().toString() +"&branchname="+transfer_branch.getText().toString()+"&amount="+transfer_amount.getText().toString();
		    		
	    			HttpGet getRequest=new HttpGet(url);
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
				        String status=dp.resultpagedetails(b);
				        
				        Intent inte=new Intent(getApplicationContext(),ViewInformation.class);
				        inte.putExtra("response", status);
				        startActivityForResult(inte, 3);
					} catch (Exception e) {
						Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
					}
		    	}
	    	}catch (Exception e) {
	    		Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
			}
	    }
	}
}
