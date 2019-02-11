package com.bilal.socialbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {



    LoginButton loginButton;
    TextView textView;
    CallbackManager callbackManager;
    String answer;



    double longitude;
    double latitude;

    //private Button b_get;
    //private TrackGPS gps;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);








        loginButton = (LoginButton)findViewById(R.id.fb_login_bn);
        textView =(TextView)findViewById(R.id.textView);
        loginButton.setReadPermissions(Arrays.asList(
                "public_profile", "email", "user_friends", "user_tagged_places","user_events"));


        callbackManager = CallbackManager.Factory.create();




        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult)
            {

                // App code
                new GraphRequest(
                        AccessToken.getCurrentAccessToken(),
                        "/me/tagged_places",
                        null,
                        HttpMethod.GET,
                        new GraphRequest.Callback()
                        {
                            public void onCompleted(GraphResponse response)
                            {
                                //Log.v("LoginActivity", response.toString());
                                JSONObject obj= response.getJSONObject();
                                insertFB(obj);
                                Log.v("LoginActivity", "////////////////////////////");
                                Iterator<String> keys = obj.keys();
                                while(keys.hasNext()) {
                                    String key = keys.next();
                                    String val = null;

                                    try {
                                        JSONObject value = obj.getJSONObject(key);


                                    } catch (Exception e) {
                                        try {
                                            val = obj.getString(key);
                                            Log.v("LoginActivity", val.toString()+"\n");
                                        } catch (JSONException e1) {
                                            e1.printStackTrace();
                                        }
                                    }

                                    if (val != null)
                                    {


                                        Intent openRecommendedProgramsIntent = new Intent(getApplicationContext(), GPS_Act.class);


                                        String fb_id = Profile.getCurrentProfile().getId();
                                       // Log.v("LoginActivity", fb_id+"==="+val);
                                        //openRecommendedProgramsIntent.putExtra("fb_id", fb_id);

                                        startActivity(openRecommendedProgramsIntent);
                                        /*
                                        String[] words=val.split(":");

                                        for(String w:words)
                                        {
                                            Log.v("LoginActivity", w+"--");

                                        } */



                                    }
                                   // Log.v("LoginActivity", obj.toString());
                                }
                            }
                        }
                ).executeAsync();


            }

            @Override
            public void onCancel() {
                textView.setText("Login Cancelled!");

                Intent openRecommendedProgramsIntent = new Intent(getApplicationContext(), GPS_Act.class);

                startActivity(openRecommendedProgramsIntent);

            }

            @Override
            public void onError(FacebookException error) {

                textView.setText("An error Occured");

            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }



    public void insertFB(JSONObject obj)
    {

        String result = obj.toString();
        Log.v("LoginActivity", result);


    }



}
