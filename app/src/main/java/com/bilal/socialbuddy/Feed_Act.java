package com.bilal.socialbuddy;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;

public class Feed_Act extends AppCompatActivity {

    ImageView imageView;
    Button button;
    int count=1;

    //DatabaseHelper db;

    //int progress=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        imageView = (ImageView)findViewById(R.id.imageView);
        button = (Button)findViewById(R.id.nextbtn);


       // SQLiteDatabase db  = this.getReadableDatabase();
        //String selectQuery = "SELECT progress FROM user_info ORDER BY ROWID ASC LIMIT ;
        //Cursor cursor      = db.rawQuery(selectQuery, null);

        //String[] data      = null;

        //if (cursor.moveToFirst()) {
        //do {
          //  } while (cursor.moveToNext());
        //}
        //cursor.close();




    }

    public void nextImg(View view) {

        switch(count)
        {
            case 0:
                imageView.setImageResource(R.drawable.test0);
                count=count+1;
                break;
            case 1:
                imageView.setImageResource(R.drawable.test1);
                count=count+1;
                break;
            case 2:
                imageView.setImageResource(R.drawable.test2);
                count=count+1;
                break;

            case 3:
                imageView.setImageResource(R.drawable.test3);
                count=count+1;
                break;

            case 4:
                imageView.setImageResource(R.drawable.test4);
                count=count+1;
                break;

            case 5:
                imageView.setImageResource(R.drawable.test5);
                count=count+1;
                break;

            case 6:
                imageView.setImageResource(R.drawable.test6);
                count=count+1;
                break;
            case 7:
                imageView.setImageResource(R.drawable.test7);
                count=count+1;
                break;
            case 8:
                imageView.setImageResource(R.drawable.test8);
                count=count+1;
                break;
            case 9:
                imageView.setImageResource(R.drawable.test9);
                count=count+1;
                break;

            case 10:
                imageView.setImageResource(R.drawable.test10);
                count=0;
                break;
            default:
                break;


        }

    }
}
