package com.quickblox.gateway;

import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;

import com.quickblox.sdk.ApiHelper;
import com.quickblox.sdk.interfaces.IDataSchema;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
//        QuickBloxContentProvider qbp = QuickBloxContentProvider.getInstance(this);
    	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);

		try {
			IDataSchema dataSchema = ApiHelper.createDataSchema(this,1, null, "GoogleProfilePassword", null);
			Log.d(TAG, "test : " + dataSchema.getClassName());
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
        
//        try {
//	//		
//        	
//        	Integer appId = 1491;
//        	String account = null;
//			ISession sessionResponse = ApiHelper.createApplicationSession(this, null, appId, "123456", "yjbbjayryfYJOWA", "9u3YNTVtawzx5Fm");
//			IUser userResponse = ApiHelper.loginUser(this, appId, null, "ldrewery","Kahlan2009");
//			List<IUser> users = ApiHelper.listUsers(this, appId, account);
//			
//			Log.d(TAG, "users " + users.size());
//			
//	//		IHttpResponse response = HttpUtil.authorizeApplication(1389, "123456", "hDKyC4DRtKutG7R", "EXX7r36GhfdRanA");
//	//		Log.d(TAG, "Response " + response.getResponseCode());
//        } catch (MalformedURLException e) {
//			e.printStackTrace();
//		}  catch (IOException e) {
//			e.printStackTrace();
//		} 
//        catch (InvalidKeyException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (NoSuchAlgorithmException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
}
