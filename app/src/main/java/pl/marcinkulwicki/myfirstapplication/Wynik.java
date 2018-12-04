package pl.marcinkulwicki.myfirstapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Wynik extends AppCompatActivity {

    private TextView mTextViewWynik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wynik);


        mTextViewWynik =findViewById(R.id.textViewWynik);

        Intent intent = getIntent();
        mTextViewWynik.setText("");
        mTextViewWynik.append(intent.getIntExtra("wynik", 0)+"");


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()){

            case R.id.item1:
            {
                mTextViewWynik.setText("Option 1");
                break;
            }
            case R.id.item2:
            {
                mTextViewWynik.setText("Option 2");
                break;
            }
            case R.id.item3:
            {
                mTextViewWynik.setText("Option 3");
                break;
            }
            default:
            {
                break;
            }
        }



        return super.onOptionsItemSelected(item);
    }


}
