package com.emergency.help;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class setup extends Activity implements Button.OnClickListener {
    /** Called when the activity is first created. */
	
	  Button next;
	   Spinner spinner1;
     EditText add;
     EditText state;
     EditText city;
     TextView e1;
     TextView e2;
     TextView e3;
     TextView e4;
     String State;
     String City;
     String Address;
     ImageView v;
     //CheckBox chkBoxCall100;
     
    @Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
       

        setContentView(R.layout.raja);
        Log.v("create", "button clicked");
        add = (EditText) findViewById(R.id.editText1);
		city = (EditText) findViewById(R.id.editText2);
		
		
      //  state = (EditText) findViewById(R.id.editText3);
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        
        e1 = (TextView)findViewById(R.id.textView5);
        e2 = (TextView)findViewById(R.id.textView6);
        e3 = (TextView)findViewById(R.id.textView7);
        e4 = (TextView)findViewById(R.id.textView8);
        e1.setVisibility(View.GONE);
        e2.setVisibility(View.GONE);
        e3.setVisibility(View.GONE);
        e4.setVisibility(View.GONE);
        e1.setTextColor(Color.rgb(255,0,0));
        e2.setTextColor(Color.rgb(255,0,0));
        e3.setTextColor(Color.rgb(255,0,0));
        e4.setTextColor(Color.rgb(255,0,0));
      v= (ImageView)findViewById(R.id.imageView1);
      v.setOnClickListener(new View.OnClickListener(){
    	    public void onClick(View v){
    	        Intent intent = new Intent();
    	        intent.setAction(Intent.ACTION_VIEW);
    	        intent.addCategory(Intent.CATEGORY_BROWSABLE);
    	        intent.setData(Uri.parse("http://zillow.com"));
    	        startActivity(intent);
    	    }
    	});
        next = (Button) findViewById(R.id.button1);
       
  		next.setOnClickListener(this);
  		
    }
    
    
    
	public void onClick(View v) {
		
		 if(v==next)
		{
			 e1.setVisibility(View.GONE);
		        e2.setVisibility(View.GONE);
		        e3.setVisibility(View.GONE);
		        e4.setVisibility(View.GONE);

			 Log.v("click-button", "button clicked");

		    
			 
             Address=add.getText().toString();
             City=city.getText().toString();
            // State=state.getText().toString();
             State = String.valueOf(spinner1.getSelectedItem());
             if(Address.length()<=0 || City.length()<=0 || State.length()>2 ) {
                //Toast.makeText(setup.this,"Please enter "+"\n"+" valid phone number in first text box",Toast.LENGTH_LONG).show();
               // am_checked=0;
            	 
                //return;
                if(Address.length()<=0   ) {
                    //Toast.makeText(setup.this,"Please enter "+"\n"+" valid phone number in second text box",Toast.LENGTH_LONG).show();
                   // am_checked=0;
                	e1.setVisibility(View.VISIBLE);
                	
                }
                
                if(City.length()<=0   ) {
                    //Toast.makeText(setup.this,"Please enter "+"\n"+" valid phone number in second text box",Toast.LENGTH_LONG).show();
                   // am_checked=0;
                	e2.setVisibility(View.VISIBLE);
                	
                }
                if(State.length()>2   ) {
                    //Toast.makeText(setup.this,"Please enter "+"\n"+" valid phone number in second text box",Toast.LENGTH_LONG).show();
                   // am_checked=0;
                	e3.setVisibility(View.VISIBLE);
                	
                }
                return;
            }
             else
             {
            	 new LongOperation().execute(this);
             }

         }
	}  
	public void err() {
		e4.setVisibility(View.VISIBLE);
		return;
	}
	public  void current(String v )
	{
		Intent intent = new Intent(this, help.class);
    	intent.putExtra("message", v);
    	Log.v("output tcvvyyvuy",v);
    	
    	startActivity(intent);	
    	 //finish();

		
	}
    
}
class LongOperation extends AsyncTask<setup, Void, String> {
	private setup longOperationContext = null;
	InputStream stream;
	String line=null;
	BufferedReader r;
	StringBuilder total;
	Boolean done=false;
	JSONObject obj;
	 int code=1;
    @Override
    protected String doInBackground(setup... params) {
    	longOperationContext=params[0];
    	String a= params[0].Address.replace(" ", "+");
    	String b= params[0].City.replace(" ", "+");
    	String c= params[0].State.replace(" ", "+");
    	Log.v("click-button", params[0].Address);
        //for (int i = 0; i < 5; i++) {
    	HttpPost httppost = new HttpPost("http://suyashco2.elasticbeanstalk.com/?add="+a+"&city="+b+"&state="+c);
    	Log.v("click-button", "post done");
    	HttpClient client = new DefaultHttpClient();
    	
    	HttpResponse response;    
    	Log.v("click-button", "button clicked");
    	try {
    		 response = client.execute(httppost);
    		 Log.v("click-button", "response");
             HttpEntity entity = response.getEntity();
              stream = entity.getContent();
             // InputStreamReader isr = new InputStreamReader(connection.getInputStream());
               //InputSource source = new InputSource(stream);
              Log.v("click-button", "getcontent");
               r = new BufferedReader(new InputStreamReader(stream));
               total = new StringBuilder();
               Log.v("click-button", "builder made");
             while ((line = r.readLine()) != null) {
                  total.append(line);
              }
               
              Log.v("click-button", total.toString());
               obj = new JSONObject(total.toString());
               Log.v("click-button", "object");
               String cod =(String)obj.get("Code"); 
               code =obj.getInt("Code");
                Log.v("click-button", cod);
            	//Thread.sleep(1000);
            } catch (Exception e) {
                Thread.interrupted();
            }
     
    	if(code==0)
    		done=true;
    		
        return total.toString();
    }

    @Override
    protected void onPostExecute(String v) {
    	//obj= new JSONObject(v);
       
    	if(done)
    	{
    		Log.v("output","in done");
    		longOperationContext.current(v);
    		
    	}
    	else
    	{
    		longOperationContext.err();
    	}
    	
    }

    
}
