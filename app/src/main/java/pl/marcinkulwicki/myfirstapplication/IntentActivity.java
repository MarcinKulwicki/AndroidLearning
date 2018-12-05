package pl.marcinkulwicki.myfirstapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class IntentActivity extends AppCompatActivity {

    private TextView mTextViewNewIntent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent_activity_layout);

        mTextViewNewIntent = findViewById(R.id.textViewNewIntent);
        Intent intent = getIntent();
        mTextViewNewIntent.setText(intent.getStringExtra("name"));
    }
}
