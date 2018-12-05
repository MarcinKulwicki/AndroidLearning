package pl.marcinkulwicki.myfirstapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

public class IntentResponse extends AppCompatActivity {

    public static final String RESPONSE = "Response";
    private EditText mEditTextResponse;
    private Button mButtonResponse;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent_activity_response_layout);

        mEditTextResponse = findViewById(R.id.editTextResponse);
        mButtonResponse = findViewById(R.id.buttonResponse);
        mEditTextResponse.setText("");

        mButtonResponse.setOnClickListener((view) -> {
            prepareResponse();
            finish();
        });
    }

    private void prepareResponse() {
        String response = mEditTextResponse.getText().toString();
        Intent resultIntent = new Intent();
        resultIntent.putExtra(RESPONSE, response);
        setResult(RESULT_OK, resultIntent);
    }
}
