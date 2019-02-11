package com.bilal.socialbuddy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class circle_social extends Activity {

    DatabaseHelper myDB;

    EditText edit1_fr1;
    EditText edit2_fr1;

    EditText edit1_fr2;
    EditText edit2_fr2;

    EditText edit1_fr3;
    EditText edit2_fr3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_social);
        myDB = new DatabaseHelper(this);
    }

    public void OpenFriend1(View view)
    {
        Intent openRecommendedProgramsIntent = new Intent(getApplicationContext(), MapsActivity.class);
        //startActivity(openRecommendedProgramsIntent);

        startActivityForResult(new Intent(getApplicationContext(),MapsActivity.class),999);
    }

    public void OpenFriend2(View view)
    {
        Intent openRecommendedProgramsIntent = new Intent(getApplicationContext(), MapsActivity.class);

        startActivityForResult(new Intent(getApplicationContext(),MapsActivity.class),888);

    }
    public void OpenFriend3(View view)
    {
        Intent openRecommendedProgramsIntent = new Intent(getApplicationContext(), MapsActivity.class);
        //startActivity(openRecommendedProgramsIntent);

        startActivityForResult(new Intent(getApplicationContext(),MapsActivity.class),777);
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==999 && resultCode==RESULT_OK)
        {
            edit1_fr1 = (EditText)findViewById(R.id.edit1_fr1 );
            edit2_fr1  = (EditText)findViewById(R.id.edit2_fr1);
            edit1_fr1 .setText(data.getStringExtra("home_lat"));
            edit2_fr1 .setText(data.getStringExtra("home_lang"));

        }


        if(requestCode==888 && resultCode==RESULT_OK)
        {
            edit1_fr2 = (EditText)findViewById(R.id.edit1_fr2 );
            edit2_fr2  = (EditText)findViewById(R.id.edit2_fr2);
            edit1_fr2 .setText(data.getStringExtra("home_lat"));
            edit2_fr2 .setText(data.getStringExtra("home_lang"));

        }

        if(requestCode==777 && resultCode==RESULT_OK)
        {
            edit1_fr3 = (EditText)findViewById(R.id.edit1_fr3 );
            edit2_fr3  = (EditText)findViewById(R.id.edit2_fr3);
            edit1_fr3 .setText(data.getStringExtra("home_lat"));
            edit2_fr3 .setText(data.getStringExtra("home_lang"));

        }

    }


    public void SaveSocial(View view)
    {

        String e1_f1= edit1_fr1.getText().toString();
        String e2_f1= edit2_fr1.getText().toString();
        String e1_f2= edit1_fr2.getText().toString();
        String e2_f2= edit2_fr2.getText().toString();
        String e1_f3= edit1_fr3.getText().toString();
        String e2_f3= edit2_fr3.getText().toString();

        boolean isInserted=myDB.insertData2(Double.parseDouble(e1_f1),
                Double.parseDouble(e2_f1),
                Double.parseDouble(e1_f2),
                Double.parseDouble(e2_f2),
                Double.parseDouble(e1_f3),
                Double.parseDouble(e2_f3));

        if(isInserted)
        {
            Toast.makeText(getApplicationContext(), "Data Saved! ", Toast.LENGTH_LONG).show();

        }
        else
            Toast.makeText(getApplicationContext(), "An Error Occured ", Toast.LENGTH_LONG).show();






    }
}
