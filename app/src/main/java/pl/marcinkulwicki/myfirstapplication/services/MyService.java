package pl.marcinkulwicki.myfirstapplication.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {

    private Toast toast;
    private Timer timer;
    private TimerTask timerTask;
    private class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            showToast("Your Service is still working");
        }
    }
    private void showToast(String text) {
        toast.setText(text);
        toast.show();
    }


    private void writeToLogs(String message) {
        Log.d("HelloServices", message);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        writeToLogs("Called onCreate() method.");

        timer = new Timer();
        toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        writeToLogs("Called onStartCommand() methond");

        clearTimerSchedule();
        initTask();
        timer.scheduleAtFixedRate(timerTask, 4 * 1000, 4 * 1000);
        showToast("Your service has been started");

        return super.onStartCommand(intent, flags, startId);
    }

    private void clearTimerSchedule() {
        if(timerTask != null) {
            timerTask.cancel();
            timer.purge();
        }
    }

    private void initTask() {
        timerTask = new MyTimerTask();
    }

    @Override
    public void onDestroy() {
        writeToLogs("Called onDestroy() method");

        clearTimerSchedule();

        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
