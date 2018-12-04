package pl.marcinkulwicki.myfirstapplication.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.Toast;



public class FirstService extends IntentService {

    private int i;
    private Handler handler = new Handler();

    public FirstService() {
        super("FirstService");
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {


        while (i<20){
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            handler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), i+"", Toast.LENGTH_SHORT).show();

                }
            });
            i++;
        }
    }
}
