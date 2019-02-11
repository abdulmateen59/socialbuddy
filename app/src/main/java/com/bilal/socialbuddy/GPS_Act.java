package com.bilal.socialbuddy;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class GPS_Act extends Activity {

    String fb_id;
    private GLSurfaceView glView;


    double longitude;
    double latitude;

    private Button b_get;
    private TrackGPS gps;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        gps.stopUsingGPS();
    }























    public void openUser(View view) {
        fb_id ="87384798";
        Intent openRecommendedProgramsIntent = new Intent(getApplicationContext(), UserInfo_Activity.class);
        openRecommendedProgramsIntent.putExtra("firstAct1", fb_id);

        startActivity(openRecommendedProgramsIntent);
    }

    public void openFriend(View view) {
        Intent openRecommendedProgramsIntent = new Intent(getApplicationContext(), circle_social.class);

        startActivity(openRecommendedProgramsIntent);
    }

    public void openFeedback(View view) {

        //glView = new Cubesurfaceview(this);           // Allocate a GLSurfaceView
        // Use a custom renderer
        //this.setContentView(glView);

        Intent openRecommendedProgramsIntent = new Intent(getApplicationContext(), Feed_Act.class);

        startActivity(openRecommendedProgramsIntent);
    }

    public void openSugg(View view)
    {
        Intent activityThatCalled = getIntent();
        fb_id = activityThatCalled.getExtras().getString("fb_id");

        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/me/events",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback()
                {
                    public void onCompleted(GraphResponse response)
                    {
                        //Log.v("LoginActivity", response.toString());
                        JSONObject obj= response.getJSONObject();
                        Iterator<String> keys = obj.keys();
                        while(keys.hasNext()) {
                            String key = keys.next();
                            String val = null;
                            try {
                                JSONObject value = obj.getJSONObject(key);

                            } catch (Exception e) {
                                try {
                                    val = obj.getString(key);
                                } catch (JSONException e1) {
                                    e1.printStackTrace();
                                }
                            }

                            if (val != null)
                            {


                                //Intent openRecommendedProgramsIntent = new Intent(getApplicationContext(), GPS_Act.class);

                                //String fb_id = Profile.getCurrentProfile().getId();
                                //Log.v("LoginActivity", fb_id+"==="+val);
                                //openRecommendedProgramsIntent.putExtra("fb_id", fb_id);

                                //startActivity(openRecommendedProgramsIntent);

                                String[] words=val.split(":");

                                for(String w:words)
                                {
                                    Log.v("LoginActivity", w+"--");

                                }



                            }
                            Log.v("LoginActivity", obj.toString());
                        }
                    }
                }
        ).executeAsync();




    }
}
