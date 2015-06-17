package com.emergency.help;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import com.facebook.*;
import com.facebook.model.*;
import com.facebook.widget.FacebookDialog;
import com.facebook.widget.WebDialog;
import com.facebook.widget.WebDialog.OnCompleteListener;

import com.facebook.FacebookException;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
//import android.app.ActionBar;
//import android.app.ActionBar.Tab;
//import android.support.v4.view.ViewPager;
import android.widget.ViewSwitcher.ViewFactory;

public class help extends FragmentActivity implements ActionBar.TabListener
{
	Drawable d1;
	Drawable d2;
	Drawable d3;
	int inde=0;
	Button btnNext;
	JSONObject obj;
	String data;
	Button btnPrev;
	Button share;
	String fburl;
	String fbcaption="Property information from Zillow.com";
	String fbpic;
	String fbprice;
	String fbadd;
	 TableLayout TableVisibility;
	 ImageView a1, a2;
	 
	 public UiLifecycleHelper uiHelper;
	// private ViewPager viewPager;
	  
	    Bitmap e;
	    ViewPager viewPager=null;
	    ImageSwitcher iswitch;
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        
	        setContentView(R.layout.timer);
	        uiHelper = new UiLifecycleHelper(this, null);
	        uiHelper.onCreate(savedInstanceState);
	        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					this);
	        alertDialogBuilder.setTitle("facebook Share");
	 
	        Log.v("output","without ction bar");
	        try {
	            PackageInfo info = getPackageManager().getPackageInfo("com.emergency.help",PackageManager.GET_SIGNATURES);
	            for (Signature signature : info.signatures) {
	                MessageDigest md = MessageDigest.getInstance("SHA");
	                md.update(signature.toByteArray());
	                String sign=Base64.encodeToString(md.digest(), Base64.DEFAULT);
	               // Log.v("MY KEY HASH:", sign);
	               // Toast.makeText(getApplicationContext(),sign,         Toast.LENGTH_LONG).show();
	            }
	} catch (NameNotFoundException e) {
	} catch (NoSuchAlgorithmException e) {}
	        iswitch = (ImageSwitcher) findViewById(R.id.imageSwitcher1);
	        TableVisibility = (TableLayout)findViewById(R.id.tablel1);
	       btnNext=(Button)findViewById(R.id.buttonNext);
	        btnPrev=(Button)findViewById(R.id.buttonPrev);
	        share=(Button)findViewById(R.id.button1);
	       a1=(ImageView)findViewById(R.id.arrow1);
	       a2=(ImageView)findViewById(R.id.arrow2);
	   final ActionBar  actionBar = getActionBar();
	        Log.v("output","action bar got");      
	       
       actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);        
	        addTabs(actionBar);
	        TableVisibility = (TableLayout)findViewById(R.id.tablel1);
	       // TableVisibility.setVisibility(View.GONE);
	        Log.v("output","tabs added");
	        iswitch = (ImageSwitcher) findViewById(R.id.imageSwitcher1);
	        iswitch.setFactory(new ViewFactory() {
                
                public View makeView() {
                    // TODO Auto-generated method stub
                    
                        // Create a new ImageView set it's properties 
                        ImageView imageView = new ImageView(getApplicationContext());
                        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                       // imageView.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
                        return imageView;
                }
            });
	        
	        Animation in = AnimationUtils.loadAnimation(this,android.R.anim.slide_in_left);
            Animation out = AnimationUtils.loadAnimation(this,android.R.anim.slide_out_right);
            
            // set the animation type to imageSwitcher
            iswitch.setInAnimation(in);
            iswitch.setOutAnimation(out);
        
	       // iswitch.setVisibility(View.GONE);
	        //ImageView iv = new ImageView(this);
	        //iv.setImageResource(R.drawable.sky);
	        //iswitch.addView(iv);
	        //if(iswitch!=null)
	        //iswitch.setImageResource(R.drawable.sky);
	     //   iswitch.setFactory((ViewFactory) );
            
            btnNext.setOnClickListener(new View.OnClickListener() {
                
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                     ++inde;
                     if(inde>=4)
                    	 inde=1;
                       // If index reaches maximum reset it
                     if(inde==1)
                     {	//ImageView iv = new ImageView(this);
             	       // iv.setImageResource(R.drawable.sky);
             	        //iswitch.addView(iv);
                    	 iswitch.setImageDrawable(d1);
                    	 }
                     if(inde==2)
                        	iswitch.setImageDrawable(d2);
                        if(inde==3)
                        	{iswitch.setImageDrawable(d3); }
                }
            });
share.setOnClickListener(new View.OnClickListener() {
                
                public void onClick(View v) {
                	 AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
         					help.this);
         	        alertDialogBuilder.setTitle("Share");
                	alertDialogBuilder
    				.setMessage("Post to Facebook")
    				.setCancelable(false)
    				.setPositiveButton("Post Property Details",new DialogInterface.OnClickListener() {
    					public void onClick(DialogInterface dialog,int id) {
    						// if this button is clicked, close
    						// current activity
    						//MainActivity.this.finish();
    						if (FacebookDialog.canPresentShareDialog(getApplicationContext(), 
    	                            FacebookDialog.ShareDialogFeature.SHARE_DIALOG)) {            	
    	                	FacebookDialog shareDialog = new FacebookDialog.ShareDialogBuilder(help.this)
    	                    .setLink(fburl)
    	                    .setCaption(fbcaption)
    	                    .setPicture(fbpic)
    	                    .setDescription(fbprice)
    	                    .setName(fbadd)
    	                    .build();
    	            uiHelper.trackPendingDialogCall(shareDialog.present());}
    	                	else{
    	                		publishFeedDialog();
    	                		
    	                	}
    					}
    				  })
    				.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
    					public void onClick(DialogInterface dialog,int id) {
    						// if this button is clicked, just close
    						// the dialog box and do nothing
    						
    						dialog.cancel();
    						Toast.makeText(getApplicationContext(), "Post Canceled",
    								   Toast.LENGTH_LONG).show();
    					}
    				});
                	
                /*	if (FacebookDialog.canPresentShareDialog(getApplicationContext(), 
                            FacebookDialog.ShareDialogFeature.SHARE_DIALOG)) {            	
                	FacebookDialog shareDialog = new FacebookDialog.ShareDialogBuilder(help.this)
                    .setLink(fburl)
                    .setCaption(fbcaption)
                    .setPicture(fbpic)
                    .setDescription(fbprice)
                    .setName(fbadd)
                    .build();
            uiHelper.trackPendingDialogCall(shareDialog.present());}
                	else{
                		publishFeedDialog();
                		
                	}*/
                	AlertDialog alertDialog = alertDialogBuilder.create();
                	 
    				
    				alertDialog.show();
                }
            });
            
            btnPrev.setOnClickListener(new View.OnClickListener() {
                
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                     --inde;
                     if(inde<=0)
                    	 inde=3;
                       // If index reaches maximum reset it
                     if(inde==1)
                    	 {iswitch.setImageDrawable(d1);}
                     if(inde==2)
                        	iswitch.setImageDrawable(d2);
                        if(inde==3)
                        	{iswitch.setImageDrawable(d3); }
                }
            });
	        Log.v("output","imageswither hidden");
	       // iswitch.setVisibility(View.GONE);
	        
	        Bundle b = getIntent().getExtras();
	        data = b.getString("message");
	        Log.v("messge-secondpage", data);
	        try{
	        this.obj= new JSONObject(data);
	        JSONObject O1=(JSONObject) obj.get("chart1");
	        fbpic=O1.getString("0");
            fburl=(String)obj.get("Result Header");
            fbprice=(String)obj.get("Last Sold Price");
            fbadd=(String)obj.get("Street")+","+ (String)obj.get("City")+","+ (String)obj.get("State")+"-"+(String)obj.get("Zipcode");
	        String ChangeArrow= (String)obj.get("30 days change arrow");
	        String RentChangeArrow = (String)obj.get("30 days rent change arrow");
	        String arrow1, arrow2;
	        if(ChangeArrow.indexOf("up_g")>0){
	        	arrow1="up";
	        	a1.setImageResource(R.drawable.up);
	        	Log.v("arrow1", arrow1);
	        }
	        else{arrow1="down";
	       a1.setImageResource(R.drawable.down);
	        Log.v("arrow1", arrow1);}
	        if(RentChangeArrow.indexOf("up_g")>0){
	        	arrow2="up";
	        	Log.v("arrow2", arrow2);
	        	a2.setImageResource(R.drawable.up);
	        }
	        else{arrow2="down"; 
	        a2.setImageResource(R.drawable.down);
	        Log.v("arrow2", arrow2);}
	        //a1.setImageResource(R.drawable.)
            //(String)obj.get("Property Type");
	        TextView  Address = (TextView) findViewById(R.id.textView11);
	        TextView  PropertyType = (TextView) findViewById(R.id.textView22);
	        TextView  Yearbuilt = (TextView) findViewById(R.id.textView32);
	        TextView  LotSize = (TextView) findViewById(R.id.textView42);
	        TextView  FinishedArea = (TextView) findViewById(R.id.textView52);
	        TextView  Bathrooms = (TextView) findViewById(R.id.textView62);
	        TextView  Bedrooms = (TextView) findViewById(R.id.textView72);
	        TextView  TaxAssesmentYear = (TextView) findViewById(R.id.textView82);
	        TextView  TaxAssesment = (TextView) findViewById(R.id.textView92);
	        TextView  LastSoldPrice = (TextView) findViewById(R.id.textView102);
	        TextView  LastSoldDate = (TextView) findViewById(R.id.textView112);
	        TextView  PropertyEstimate = (TextView) findViewById(R.id.textView122);
	        TextView  OverallChange = (TextView) findViewById(R.id.textView132);
	        TextView  PropertyRange = (TextView) findViewById(R.id.textView142);
	        TextView  RentEstimate = (TextView) findViewById(R.id.textView152);
	        TextView  RentChange = (TextView) findViewById(R.id.textView162);
	        TextView  AllRentChange = (TextView) findViewById(R.id.textView172);
	        TextView  Bottom = (TextView) findViewById(R.id.textView1);
	        PropertyRange.setText((String)obj.get("All Time Property Range"));
	        RentEstimate.setText((String)obj.get("Rent Zestimate"));
	        RentChange.setText((String)obj.get("30 Days Rent Change"));
	        AllRentChange.setText((String)obj.get("All Time rent Change"));
	        String RentDate=(String)obj.get("Rent Zestimate Date");
	        String PropertyDate=(String)obj.get("Property Estimate date");
	        String a="Zestimate&reg; Property Estimate as of "+ PropertyDate;
	        String k="Rent Zestimate&reg; valuation as of "+ RentDate;
	        TextView  RentText =(TextView) findViewById(R.id.textView151);
	        TextView  PropertyText =(TextView) findViewById(R.id.textView121);
	        RentText.setText(Html.fromHtml(a));
	        PropertyText.setText(Html.fromHtml(k));
	        Address.setClickable(true);
	        Address.setMovementMethod(LinkMovementMethod.getInstance());
	        Bottom.setClickable(true);
	        Bottom.setMovementMethod(LinkMovementMethod.getInstance());
	        String t1="http://www.zillow.com/corp/Terms.htm";
	        String t2="http:/www.zillow.com/zestimate/";
	        String temp2="&copy; Zillow, Inc., 2006-2014 <br> Use is subject to <a href="+t1+">Terms of Use</a><br><a href="+t2+">What\'s a Zestimate?</a>";
	        //Address.setClickable(true);
	        //Address.setMovementMethod(LinkMovementMethod.getInstance());
	        String temp = "<a href="+(String)obj.get("Result Header")+">"+(String)obj.get("Street")+","+ (String)obj.get("City")+","+ (String)obj.get("State")+"-"+(String)obj.get("Zipcode")+"</a>";  
	        Address.setText(Html.fromHtml(temp));
	        Bottom.setText(Html.fromHtml(temp2));
	        //Address.setText(temp);
	        PropertyType.setText((String)obj.get("Property Type"));
	        Yearbuilt.setText((String)obj.get("Year built"));
	        LotSize.setText((String)obj.get("Lot Size"));
	        FinishedArea.setText((String)obj.get("Finished Area"));
	        Bathrooms.setText((String)obj.get("Bathrooms"));
	        Bedrooms.setText((String)obj.get("Bedrooms"));
	        TaxAssesmentYear.setText((String)obj.get("Tax Assesment Year"));
	        TaxAssesment.setText((String)obj.get("Tax Assesment"));
	        LastSoldPrice.setText((String)obj.get("Last Sold Price"));
	        LastSoldDate.setText((String)obj.get("Last Sold Date"));
	        PropertyEstimate.setText((String)obj.get("Property Estimate as of"));
	        OverallChange.setText((String)obj.get("30 Days overall change"));
	        new DownloadImageTask().execute(this);
	        }
	        catch(Exception e)
	        {
	        	Log.v("try", "inside try");
	        }
	        
	    }
	    @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        super.onActivityResult(requestCode, resultCode, data);

	        uiHelper.onActivityResult(requestCode, resultCode, data, new FacebookDialog.Callback() {
	            @Override
	            public void onError(FacebookDialog.PendingCall pendingCall, Exception error, Bundle data) {
	                Log.e("Activity", String.format("Error: %s", error.toString()));
	            }

	            @Override
	            public void onComplete(FacebookDialog.PendingCall pendingCall, Bundle data) {
	                Log.i("Activity", "Success!");
	            }
	        });
	    }
	    @Override
	    protected void onResume() {
	        super.onResume();
	        uiHelper.onResume();
	    }

	    @Override
	    protected void onSaveInstanceState(Bundle outState) {
	        super.onSaveInstanceState(outState);
	        uiHelper.onSaveInstanceState(outState);
	    }

	    @Override
	    public void onPause() {
	        super.onPause();
	        uiHelper.onPause();
	    }

	    @Override
	    public void onDestroy() {
	        super.onDestroy();
	        uiHelper.onDestroy();
	    }
	    public void imagegenerator(Bitmap[] B){
	    	
	    	Log.v("inside image genrator", "inside image genrator1");
	    	if(B==null)
	    		Log.v("bitmap array", "null value");
	        d1 =new BitmapDrawable(getResources(),B[0]);
	    	Log.v("inside image genrator", "inside image genrator2");
	    	 d2 =new BitmapDrawable(getResources(),B[1]);
	    	Log.v("inside image genrator", "inside image genrator3");
	        d3=new BitmapDrawable(getResources(),B[2]);
	    	Log.v("inside image genrator", "inside image genrator4");
	    	//Drawable d4= new BitmapDrawable(B[0]);
	    	Log.v("inside image genrator", "inside image genrator5");
	    	//Drawable d5= new BitmapDrawable(B[1]);
	    	Log.v("inside image genrator", "inside image genrator6");
	    	//Drawable d6= new BitmapDrawable(B[2]);
	    	Log.v("inside image genrator", "inside image genrator7");
	    	//this.iswitch.setImageBitmap(B[0]);
	    	if(d1==null)
	    		Log.v("d1", "nul");
	    	if(d2==null)
	    		Log.v("d2", "nul");
	    	if(d3==null)
	    		Log.v("d3", "nul");
	    	//ImageView iv = new ImageView(this);
	        //iv.setImageDrawable(d1);
	        //iswitch.addView(iv);
	       /* ImageView ie = new ImageView(this);
	        ie.setImageDrawable(d2);
	        iswitch.addView(ie);
	        ImageView in = new ImageView(this);
	        in.setImageDrawable(d3);
	        iswitch.addView(in);*/
	    	//iswitch.setImageResource(R.drawable.sky);
	    	Log.v("inside image genrator", "inside image genrator8");
	    	try{
	    	iswitch.setImageDrawable(d1);
	    	Log.v("inside image genrator", "inside image genrator9");
	    	//iswitch.setImageDrawable(d2);
	    	Log.v("inside image genrator", "inside image genrator10");
	    	//iswitch.setImageDrawable(d3);
	    	}
	    	catch(NullPointerException e){ Log.v("erroruuf", e.toString());}
	    }
/*public void img(Bitmap B){
	    	
	    	
	    	Drawable d1 =new BitmapDrawable(getResources(),B);
	    	//Drawable d2 =new BitmapDrawable(getResources(),B[1]);
	    	//Drawable d3=new BitmapDrawable(getResources(),B[2]);
	    	iswitch.setImageDrawable(d1);
	    	//iswitch.setImageDrawable(d2);
	    	//iswitch.setImageDrawable(d3);
	    }*/
	   
	    
    public void addTabs(ActionBar actionBar)
    {
        ActionBar.Tab tab1=actionBar.newTab();
        tab1.setText("Basic Info");
        tab1.setTabListener(this);

        ActionBar.Tab tab2=actionBar.newTab();
        tab2.setText("Historical Zestimate");
        tab2.setTabListener(this);

       
        actionBar.addTab(tab1);//,0,true);
        actionBar.addTab(tab2);//,1,false);
        //actionBar.addTab(tab3);
    }
	
	
	@Override
	public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		String w = String.valueOf(tab.getPosition());
	    Log.v("selecte tab", w);
	    if(tab.getPosition()==0)
	    {
	    	//if(iswitch.VISIBLE){}
	    	iswitch.setVisibility(View.GONE);
	    	btnNext.setVisibility(View.GONE);
	    	btnPrev.setVisibility(View.GONE);
	    	TableVisibility.setVisibility(View.VISIBLE);
	    	
	    }
	    if(tab.getPosition()==1)
	    {
	    	TableVisibility.setVisibility(View.GONE);
	    	iswitch.setVisibility(View.VISIBLE);
	    	btnNext.setVisibility(View.VISIBLE);
	    	btnPrev.setVisibility(View.VISIBLE);
	    	
	    }
		
	}
	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
	private void publishStory() {
	    Session session = Session.getActiveSession();

	    if (session != null){

	        // Check for publish permissions    
	        List<String> permissions = session.getPermissions();
	    /*    if (!isSubsetOf(PERMISSIONS, permissions)) {
	            pendingPublishReauthorization = true;
	            Session.NewPermissionsRequest newPermissionsRequest = new Session
	                    .NewPermissionsRequest(this, PERMISSIONS);
	        session.requestNewPublishPermissions(newPermissionsRequest);
	            return;
	        }*/

	        Bundle postParams = new Bundle();
	        postParams.putString("name", "Facebook SDK for Android");
	        postParams.putString("caption", "Build great social apps and get more installs.");
	        postParams.putString("description", "The Facebook SDK for Android makes it easier and faster to develop Facebook integrated Android apps.");
	        postParams.putString("link", "https://developers.facebook.com/android");
	        postParams.putString("picture", "https://raw.github.com/fbsamples/ios-3.x-howtos/master/Images/iossdk_logo.png");

	        Request.Callback callback= new Request.Callback() {
	            public void onCompleted(Response response) {
	                JSONObject graphResponse = response
	                                           .getGraphObject()
	                                           .getInnerJSONObject();
	                String postId = null;
	                try {
	                    postId = graphResponse.getString("id");
	                } catch (JSONException e) {
	                    Log.v(
	                        "JSON error ", e.getMessage());
	                }
	                FacebookRequestError error = response.getError();
	                if (error != null) {
	                    Toast.makeText(help.this
	                         .getApplicationContext(),
	                         error.getErrorMessage(),
	                         Toast.LENGTH_SHORT).show();
	                    } else {
	                        Toast.makeText(help.this
	                             .getApplicationContext(), 
	                             postId,
	                             Toast.LENGTH_LONG).show();
	                }
	            }
	        };

	        Request request = new Request(session, "me/feed", postParams, 
	                              HttpMethod.POST, callback);

	        RequestAsyncTask task = new RequestAsyncTask(request);
	        task.execute();
	    }

	}
	public void publishFeedDialog() {
	    Bundle params = new Bundle();
	    try{
	    params.putString("name", fbadd);
	    params.putString("caption", "Property Information from Zillow.com");
	    params.putString("description", "last Sold Price:"+obj.getString("Last Sold Price")+", 30 Days Overall Change:"+obj.getString("30 Days overall change"));
	    params.putString("link", obj.getString("Result Header"));
	    params.putString("picture", obj.getString("chart1"));
	    }
	    catch(Exception e){Log.v("json error", "fb json error");}
	    WebDialog feedDialog = (
	        new WebDialog.FeedDialogBuilder(help.this,//getActivity(),
	            Session.getActiveSession(),
	            params))
	        .setOnCompleteListener(new OnCompleteListener() {

	            @Override
	            public void onComplete(Bundle values,
	                FacebookException error) {
	                if (error == null) {
	                    // When the story is posted, echo the success
	                    // and the post Id.
	                    final String postId = values.getString("post_id");
	                    if (postId != null) {
	                        Toast.makeText(help.this,//getActivity(),
	                            "Posted story, id: "+postId,
	                            Toast.LENGTH_SHORT).show();
	                    } else {
	                        // User clicked the Cancel button getActivity()
	                        Toast.makeText(help.this.getApplicationContext(), 
	                            "Publish cancelled", 
	                            Toast.LENGTH_SHORT).show();
	                    }
	                } else if (error instanceof FacebookOperationCanceledException) {
	                    // User clicked the "x" button getActivity()
	                    Toast.makeText(help.this.getApplicationContext(), 
	                        "Publish cancelled", 
	                        Toast.LENGTH_SHORT).show();
	                } else {
	                    // Generic, ex: network error getActivity()
	                    Toast.makeText(help.this.getApplicationContext(), 
	                        "Error posting story", 
	                        Toast.LENGTH_SHORT).show();
	                }
	            }

	        })
	        .build();
	    feedDialog.show();
	}
	
}


	

 class DownloadImageTask extends AsyncTask<help, Void, String> {
	 Bitmap bitmap1;
		Bitmap bitmap2;
		Bitmap bitmap3;
		Bitmap[] B=new Bitmap[3];
		help objc;
		Boolean check=false;
	@Override
	public String doInBackground(help... params) {
		JSONObject obj1;
		objc=params[0];
		obj1=objc.obj;
		Log.v("img string", "inside asynctask");
		
		
		try{
			JSONObject O1=(JSONObject) obj1.get("chart1");
			Log.v("img string", "inside second");
			String p1 = O1.getString("0");
			Log.v("img string", p1);
			JSONObject O2=(JSONObject) obj1.get("chart5");
			String p2 = O2.getString("0");
			JSONObject O3=(JSONObject) obj1.get("chart10");
			String p3 = O3.getString("0");
			Log.v("img string", p1);
			String url1= p1; 
			 
					//obj1.getString("Chart1");
			String url2=p2; 
					//obj1.getString("Chart5");
			String url3= p3;
					//obj1.getString("Chart10");
			url1.replace("\\","");
			 url2.replace("\\","");
			 url3.replace("\\","");
		 HttpURLConnection connection1 =
	        	 (HttpURLConnection)new URL(url1).openConnection();
	        	 connection1.setDoInput(true);
	        	 connection1.connect();
	        	 InputStream input1 = connection1.getInputStream();
	        	  bitmap1 = BitmapFactory.decodeStream(input1);
	        	 Log.v("bitmap", url1);
	        	 HttpURLConnection connection2 =
	    	        	 (HttpURLConnection)new URL(url2).openConnection();
	    	        	 connection2.setDoInput(true);
	    	        	 connection2.connect();
	    	        	 InputStream input2 = connection2.getInputStream();
	    	        	  bitmap2 = BitmapFactory.decodeStream(input2);
	    	        	  Log.v("bitmap", url2);
	    	        	 HttpURLConnection connection3 =
	    	    	        	 (HttpURLConnection)new URL(url3).openConnection();
	    	    	        	 connection3.setDoInput(true);
	    	    	        	 connection3.connect();
	    	    	        	 InputStream input3 = connection3.getInputStream();
	    	    	        	  bitmap3 = BitmapFactory.decodeStream(input3);
	    	    	        	  Log.v("bitmap", url3);
	    	    	        	  input1.close();
	    	    	        	  input2.close();
	    	    	        	 input3.close();
	    	    	        	 if(bitmap1==null)
	    	    	        		 Log.v("value1", "nll");
	    	    	        	 if(bitmap2==null)
	    	    	        		 Log.v("value2", "nll");
	    	    	        	 if(bitmap3==null)
	    	    	        		 Log.v("value3", "nll");
	    	    	        	 if(bitmap1!=null)
	    	    	        		 Log.v("value1", "nonll");
	    	    	        	 if(bitmap2!=null)
	    	    	        		 Log.v("value2", "nonll");
	    	    	        	 if(bitmap3!=null)
	    	    	        		 Log.v("value3", "nonll");
	   if(bitmap1!=null & bitmap2!=null & bitmap3!=null)
	    {
	    	check=true;
	    }
		}
		catch(Exception e){
			String pe =e.toString();
			Log.v("erroroo", pe);
		}
		      
	        	 return null;
		
	}
	 protected void onPostExecute(String v) {
		if(check)
		{
		this.B[0]=bitmap1;
		this.B[1]=bitmap2;
		this.B[2]=bitmap3;
		if(B==null)
		Log.v("post excute", "inside it");
		objc.imagegenerator(this.B);
		}
		 
	 }
	
}
