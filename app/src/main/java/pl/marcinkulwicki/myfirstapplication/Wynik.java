package pl.marcinkulwicki.myfirstapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import pl.marcinkulwicki.myfirstapplication.services.MyService;

public class Wynik extends AppCompatActivity {

    private TextView mTextViewWynik;
    private Button mButtonServiceStart;
    private Button mButtonServiceStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wynik);


        mTextViewWynik =findViewById(R.id.textViewWynik);

        Intent intent = getIntent();
        mTextViewWynik.setText("");
        mTextViewWynik.append(intent.getIntExtra("wynik", 0)+"");


        //Service
        mButtonServiceStart = findViewById(R.id.buttonServiceStart);
        mButtonServiceStop = findViewById(R.id.buttonServicStop);
        initButtonsOnClick();


    }

    //Service
    private void initButtonsOnClick(){
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.buttonServiceStart:
                        startMyService();
                        break;
                    case R.id.buttonServicStop:
                        stopMyService();
                        break;
                    default:
                        break;
                }
            }
        };
        mButtonServiceStart.setOnClickListener(onClickListener);
        mButtonServiceStop.setOnClickListener(onClickListener);
    }
    private void startMyService(){
        Intent serviceIntent = new Intent(this, MyService.class);
        startService(serviceIntent);
    }
    private void stopMyService(){
        Intent serviceIntent = new Intent(this, MyService.class);
        stopService(serviceIntent);
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
