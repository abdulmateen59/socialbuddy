package com.bilal.socialbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

public class UserInfo_Activity extends AppCompatActivity {

    DatabaseHelper myDB;
    String fb_id;
    EditText edit_home1;
    EditText edit_home2;

    EditText edit_work1;
    EditText edit_work2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_);
        myDB = new DatabaseHelper(this);

        Intent activityThatCalled = getIntent();
        fb_id = activityThatCalled.getExtras().getString("firstAct1");
        //Log.v("LoginActivity", fb_id);
    }


    public void openHome(View view)
    {
        Intent openRecommendedProgramsIntent = new Intent(getApplicationContext(), MapsActivity.class);
        //startActivity(openRecommendedProgramsIntent);

        startActivityForResult(new Intent(getApplicationContext(),MapsActivity.class),999);
    }

    public void openWork(View view)
    {
        Intent openRecommendedProgramsIntent = new Intent(getApplicationContext(), MapsActivity.class);

        startActivityForResult(new Intent(getApplicationContext(),MapsActivity.class),000);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==999 && resultCode==RESULT_OK)
        {
            edit_home1 = (EditText)findViewById(R.id.edit_home1);
            edit_home2 = (EditText)findViewById(R.id.edit_home2);
            edit_home1.setText(data.getStringExtra("home_lat"));
            edit_home2.setText(data.getStringExtra("home_lang"));

        }


        if(requestCode==000 && resultCode==RESULT_OK)
        {
            edit_work1 = (EditText)findViewById(R.id.edit_work1);
            edit_work2 = (EditText)findViewById(R.id.edit_work2);
            edit_work1.setText(data.getStringExtra("home_lat"));
            edit_work2.setText(data.getStringExtra("home_lang"));

        }
    }

    public void SaveUser(View view)
    {
        String h_lt= edit_home1.getText().toString();
        String h_ln= edit_home2.getText().toString();
        String w_lt= edit_work1.getText().toString();
        String w_ln= edit_work2.getText().toString();

        boolean isInserted=myDB.insertData1(Integer.parseInt(fb_id),
                Double.parseDouble(h_lt),
                Double.parseDouble(h_ln),
                Double.parseDouble(w_lt),
                Double.parseDouble(w_ln),
                0);

        if(isInserted)
        {
            Toast.makeText(getApplicationContext(), "Data Saved! ", Toast.LENGTH_LONG).show();

        }
        else
            Toast.makeText(getApplicationContext(), "An Error Occured ", Toast.LENGTH_LONG).show();

    }
}
