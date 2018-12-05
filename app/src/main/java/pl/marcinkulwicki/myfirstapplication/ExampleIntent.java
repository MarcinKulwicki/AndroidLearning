package pl.marcinkulwicki.myfirstapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ExampleIntent extends AppCompatActivity {

    private static final int RESPONSE_ACTIVITY_REQUEST_CODE = 1;
    public static final String ACTION_NEW_MSG = "pl.marcinkulwicki.myfirstapplication.Reciever.NEW_MSG";
    public static final String MSG_FIELD = "message";

    private Button mButtonOpenNew;
    private Button mButtonOpenNewForResponse;
    private Button mButtonULR;
    private Button mButtonGeo;
    private Button mButtonCall;
    private TextView mTextViewIntent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent_layout);

        mButtonOpenNew = findViewById(R.id.buttonOpenNew);
        mButtonOpenNewForResponse = findViewById(R.id.buttonOpenNewForResponse);
        mButtonULR = findViewById(R.id.buttonULR);
        mButtonGeo = findViewById(R.id.buttonGeo);
        mButtonCall = findViewById(R.id.buttonCall);
        initButtonsOnClick();

        mTextViewIntent = findViewById(R.id.textViewIntent);
    }

    private void initButtonsOnClick() {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.buttonOpenNew:
                        openNewActivity();
                        break;
                    case R.id.buttonOpenNewForResponse:
                        openNewActivityForResponse();
                        break;
                    case R.id.buttonULR:
                        openUrl();
                        break;
                    case R.id.buttonGeo:
                        openGeo();
                        break;
                    case R.id.buttonCall:
                        openCall();
                        break;
                    default:
                        break;
                }
            }


            private void openNewActivity() {
                Intent intent = new Intent(getApplicationContext(), IntentActivity.class);
                intent.putExtra("name", "Dane przekazane do intent");
                sendMessage("OpenNewActivity");
                startActivity(intent);
            }

            private void openNewActivityForResponse() {
                Intent intent = new Intent(getApplicationContext(), IntentResponse.class);
                sendMessage("Open From new Activity");
                startActivityForResult(intent, RESPONSE_ACTIVITY_REQUEST_CODE);
                //Aby uzyc trzeba przeslonic metode onActivityResult()
            }

            private void openUrl() {
                Uri url = Uri.parse("http://www.google.com");
                Intent intent = new Intent(Intent.ACTION_VIEW, url);
                sendMessage("OpenUrl");
                startActivity(intent);
            }

            private void openGeo() {
                Uri geoUri = Uri.parse("geo:50.07,19.97");
                Intent intent = new Intent(Intent.ACTION_VIEW, geoUri);
                if (isIntentAvailable(intent)) {
                    sendMessage("OpenGeo");
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Your system hasn't necessary application for this intent",
                            Toast.LENGTH_LONG)
                            .show();
                }
            }

            private boolean isIntentAvailable(Intent intent) {
                PackageManager packageManager = getApplicationContext().getPackageManager();
                List<ResolveInfo> resolveInfo = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
                return resolveInfo.size() > 0;
            }

            private void openCall() {
                String phone = "884358211";
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                sendMessage("OpenCall");
                startActivity(intent);
            }
            private void sendMessage(String msg) {
                Intent intent = new Intent();
                intent.setAction(ACTION_NEW_MSG);
                intent.putExtra(MSG_FIELD, msg);
                sendBroadcast(intent);
            }
        };

        mButtonOpenNew.setOnClickListener(onClickListener);
        mButtonOpenNewForResponse.setOnClickListener(onClickListener);
        mButtonULR.setOnClickListener(onClickListener);
        mButtonGeo.setOnClickListener(onClickListener);
        mButtonCall.setOnClickListener(onClickListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(RESPONSE_ACTIVITY_REQUEST_CODE == requestCode){
            String response = data.getStringExtra(IntentResponse.RESPONSE);
            insertResponse(response);
        }
    }
    private void insertResponse(String response){
        mTextViewIntent.setText(response);
    }
}
