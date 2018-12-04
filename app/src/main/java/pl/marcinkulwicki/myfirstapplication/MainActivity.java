package pl.marcinkulwicki.myfirstapplication;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    private Button mClickMeButton;
    private TextView mTextView;
    private ListView mDynamicList;


    private String[] NAME = {"ANDZEJ", "JANUSZ", "PONCJUSZ", "ADAM", "KARYNA", "ALICJA","ANIA","ANASTAZJA", "TOMEK", "OLA"};
    private String[] DESCRIPTION = {"Maszynista", "Technik Informatyk", "Nauczyciel", "Oportunista", "Niski",
            "Kierowca Tira i Busa", "Programista", "Architekt AutoCAD", "Rowerzysta", "Szybki i Wysoki"};
    private int[] IMAGE = {R.drawable.photoListOne , R.drawable.photoListTwo , R.drawable.photoListTree ,
            R.drawable.photoListFour , R.drawable.photoListFive , R.drawable.photoListSix , R.drawable.photoListSeven ,
            R.drawable.photoListEight , R.drawable.photoListNine , R.drawable.photoListTen };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mClickMeButton = findViewById(R.id.clickMeButton);
        mTextView = findViewById(R.id.textView);
        mDynamicList = findViewById(R.id.dynamicList);

        mTextView.setText("");




        mClickMeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Random rand = new Random();
                int i = rand.nextInt();

                Snackbar.make(v, i + "", Snackbar.LENGTH_LONG).show();
                mTextView.setText(i+"");

                Intent intent = new Intent(MainActivity.this, Wynik.class);
                intent.putExtra("wynik", i);
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();

        mTextView.append("Stop");
    }
    @Override
    protected void onPause() {
        mTextView.append("Pause");
        super.onPause();
    }
}
