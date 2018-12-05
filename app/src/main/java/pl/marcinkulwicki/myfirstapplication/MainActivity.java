package pl.marcinkulwicki.myfirstapplication;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Random;

import pl.marcinkulwicki.myfirstapplication.services.FirstService;


public class MainActivity extends AppCompatActivity {

    private Button mClickMeButton;
    private TextView mTextView;
    private ListView mDynamicList;
    private Button mButtonIntent;


    private String[] NAME = {"ANDZEJ", "JANUSZ", "PONCJUSZ", "ADAM", "KARYNA", "ALICJA", "ANIA", "ANASTAZJA", "TOMEK", "OLA"};
    private String[] DESCRIPTION = {"Maszynista", "Technik Informatyk", "Nauczyciel", "Oportunista", "Niski",
            "Kierowca Tira i Busa", "Programista", "Architekt AutoCAD", "Rowerzysta", "Szybki i Wysoki"};
    private int[] IMAGE = {R.drawable.photo_one, R.drawable.photo_two, R.drawable.photo_tree,
            R.drawable.photo_four, R.drawable.photo_five, R.drawable.photo_six, R.drawable.photo_seven,
            R.drawable.photo_eight, R.drawable.photo_nine, R.drawable.photo_ten};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar ab = getSupportActionBar();
        try {
            ab.setLogo(R.drawable.android);
            ab.setDisplayUseLogoEnabled(true);
            ab.setDisplayShowHomeEnabled(true);
            ab.setTitle("Helo i use ActionBar");

        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            System.out.println("Cant change logo ActionBar or SetTitle in MainActivity");
        }


        mClickMeButton = findViewById(R.id.clickMeButton);
        mTextView = findViewById(R.id.textView);
        mDynamicList = findViewById(R.id.dynamicList);

        CustomAdapter customAdapter = new CustomAdapter();
        mDynamicList.setAdapter(customAdapter);


        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mDynamicList.setVisibility(View.INVISIBLE);
        }

        mTextView.setText("");
        mClickMeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Random rand = new Random();
                int i = rand.nextInt();

                Snackbar.make(v, i + "", Snackbar.LENGTH_LONG).show();
                mTextView.setText(i + "");

                Intent intent = new Intent(MainActivity.this, Wynik.class);
                intent.putExtra("wynik", i);
                startActivity(intent);

            }
        });

        mDynamicList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mTextView.setText(
                        ((TextView) view.findViewById(R.id.titleList)).getText() + " - " +
                                ((TextView) view.findViewById(R.id.descriptionList)).getText()
                );
            }
        });


        ((Button) findViewById(R.id.buttonIntent)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), ExampleIntent.class));
                }
        });
    }

    @Override
    protected void onPause() {
        mTextView.append("Pause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();

        mTextView.append("Stop");
    }


    //Funkcja wywolywana na onClick button Start, uruchamia Servis
    public void start(View view) {
        startService(new Intent(getBaseContext(), FirstService.class));
    }

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return NAME.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            convertView = getLayoutInflater().inflate(R.layout.custom_layout, null);

            ImageView imageElemetList = convertView.findViewById(R.id.imageList);
            TextView titleElementList = convertView.findViewById(R.id.titleList);
            TextView descriptionElementList = convertView.findViewById(R.id.descriptionList);

            imageElemetList.setImageResource(IMAGE[position]);
            titleElementList.setText(NAME[position]);
            descriptionElementList.setText(DESCRIPTION[position]);


            return convertView;
        }
    }
}
