package com.emergency.help;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class timer extends Fragment {
	
	 @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	 
	        View rootView = inflater.inflate(R.layout.timer, container, false);
	         
	        return rootView;
	    }
    /** Called when the activity is first created. */
	/*String num1;
	String num2;
	Boolean call100;
	TextView timeDisplay;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	Button cancel ; 
    	 
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        //int value;
        Double j;
        
        if (b.getInt("minut", 0) == 999)
        {
        	j= 0.1;
        }
        else
        {
           j = (double) b.getInt("minut", 0);
        }
        
        num1 = b.getString("num1");
        num2 = b.getString("num2");
        call100 = b.getBoolean("call100");
        		
        //if (value!=0)
        //{j=(double) value;}
        //else { j= 0.5;}
        //TextView tv = new TextView(this);
        //tv.setText("hello android");
        //setContentView(tv);
        setContentView(R.layout.timer);
        //timeDisplay = (TextView) findViewById(R.id.timerText);
        cancel = (Button) findViewById(R.id.button1);
        
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //Intent myIntent = new Intent( view.getContext(), Raja.class);
                finish();
                }
        }
        );
        
         long f= (long) (j*60000);
        new CountDownTimer(f, 1000) {
        	
        	
            @Override
			public void onTick(long millisUntilFinished) {
            	java.lang.System.out.println("Abe ee" + millisUntilFinished);
                timeDisplay.setText(formatTime(millisUntilFinished));
            }

            @Override
			public void onFinish() {
            	
            	sendSMS("I am in trouble");
            	java.lang.System.out.println("Abe oo");
            /*	LocationManager mlocManager =  (LocationManager) getBaseContext().getSystemService(Context.LOCATION_SERVICE);
                LocationListener mlocListener = new MyLocationListener();
                mlocManager.requestLocationUpdates( LocationManager.GPS_PROVIDER, 0, 0, mlocListener);*/
                
         /*   }
           
         }.start();
        
    }
    
    public String formatTime(long millis) {
  	  String output = "00:00:00";
  	  long seconds = millis / 1000;
  	  long minutes = seconds / 60;
  	  long hours = minutes / 60;

  	  seconds = seconds % 60;
  	  minutes = minutes % 60;
  	  hours = hours % 60;

  	  String secondsD = String.valueOf(seconds);
  	  String minutesD = String.valueOf(minutes);
  	  String hoursD = String.valueOf(hours); 

  	  /*if (seconds < 10)
  	    secondsD = "0" + seconds;
  	  if (minutes < 10)
  	    minutesD = "0" + minutes;
  	  if (hours < 10)
  	    hoursD = "0" + hours;*/

  	/*  output = hoursD + " : " + minutesD + " : " + secondsD;
  	  return output;
  	}
    

    public class MyLocationListener implements LocationListener
    {
       
    public void onLocationChanged(Location loc)
    {
    loc.getLatitude();
    loc.getLongitude();
    String Text = "My current location is: " +
    "Latitud = " + loc.getLatitude() +
    "Longitud = " + loc.getLongitude();
    sendSMS(Text);
    String reverseGeocodeLocation = reverseGeocode(loc);
    sendSMS("i am in trouble at this location"+reverseGeocodeLocation);
    }
    
    public String reverseGeocode(Location loc)
    {
            String localityName = "";
            /*   HttpURLConnection connection = null;
        URL serverAddress = null;
        try 
        {
            serverAddress = new URL("http://maps.googleapis.com/maps/geo?q=" + Double.toString(loc.getLatitude()) + "," + Double.toString(loc.getLongitude()) +
                                    "&output=xml&oe=utf8&sensor=true&key=0VhoPZo3nDMV7q2B0cleLkVAtbjBLGHzw-dCM8g");
            connection = null;
                    connection = (HttpURLConnection)serverAddress.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    connection.setReadTimeout(10000);
                    connection.connect();*/
	/*     try
                    {
                    	
                    	HttpPost httppost = new HttpPost("http://maps.googleapis.com/maps/geo?q=" + Double.toString(loc.getLatitude()) + "," + Double.toString(loc.getLongitude()) +
                                "&output=xml&oe=utf8&sensor=true&key=0VhoPZo3nDMV7q2B0cleLkVAtbjBLGHzw-dCM8g");
                        HttpClient client = new DefaultHttpClient();
                        HttpResponse response;
                      //  StringBuilder stringBuilder = new StringBuilder();


                            response = client.execute(httppost);
                            HttpEntity entity = response.getEntity();
                            InputStream stream = entity.getContent();
                            //InputStreamReader isr = new InputStreamReader(connection.getInputStream());
                            InputSource source = new InputSource(stream);
                            SAXParserFactory factory = SAXParserFactory.newInstance();
                            SAXParser parser = factory.newSAXParser();
                            XMLReader xr = parser.getXMLReader();
                            GoogleReverseGeocodeXmlHandler handler = new GoogleReverseGeocodeXmlHandler();
                            xr.setContentHandler(handler);
                            xr.parse(source);
                            localityName = handler.getLocalityName();
                    }
                    catch (Exception ex)
                    {
                            ex.printStackTrace();
                    }
        
        return localityName;
    }
    

    public void onProviderDisabled(String provider)
    {
    }

    public void onProviderEnabled(String provider)
    {
    }

    public void onStatusChanged(String provider, int status, Bundle extras)
    {
    }

    }/* End of Class MyLocationListener */
    

	/*  public void sendSMS(String message){
  		try {

  			/*
  			
  	          SmsManager sm = SmsManager.getDefault(); 	
  	        
  	          sm.sendTextMessage(num1, null, message,null,null);
  	          //Toast.makeText(this, num1, Toast.LENGTH_LONG).show();

  	          sm.sendTextMessage(num2, null, message,null,null);
  	          Toast.makeText(this, num1 + ", " + num2, Toast.LENGTH_LONG).show();
  	          
  	          if(call100)
  	          {
	              Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + 100)); 
			      startActivity(callIntent);
			      
  	          }
  	          
  	         
  	          //}
  	          //db.close();*/
	/*String aid = System.ANDROID_ID;
  		    HttpPost httppost = new HttpPost("http://webbit.in/e/msg.php?imei="+aid+"&msg="+message);
              HttpClient client = new DefaultHttpClient();
              HttpResponse response;
            //  StringBuilder stringBuilder = new StringBuilder();

              
                  response = client.execute(httppost);
                  HttpEntity entity = response.getEntity();
                  InputStream stream = entity.getContent();
  			
  		} catch (Exception e) {
  			e.printStackTrace();
  		}
  }
    
    public void printToast(String message){
  		try {
  	          Toast.makeText(this, message, Toast.LENGTH_LONG).show();
  		} catch (Exception e) {
  			e.printStackTrace();
  		}
  }*/

}
