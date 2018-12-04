package ru.olegshulika.asmeet2;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "2_MainAct";
    private EditText mMainDataText;
    private Button mPrevActButton;
    private Button mNextActButton;
    public static final int REQUEST_CODE_DETAIL = 1302;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initExtra();
        initListeners();
        Log.d(TAG, this.getLocalClassName()+" onCreate");
    }

    void initViews(){
        mMainDataText = findViewById(R.id.main_textView);
        mPrevActButton = findViewById(R.id.main_button_Prev);
        mNextActButton = findViewById(R.id.main_button_Next);
        mMainDataText.setText("/"+TAG);
        this.setTitle(getString(R.string.app_name)+"---"+TAG);
    }

    void initExtra() {
        String newText = getIntent().getStringExtra(getString(R.string.main_data_key));
        Log.d(TAG, "load EXTRA "+newText);

        if (newText!=null)
            mMainDataText.setText(newText+"/"+TAG);
    }

    void initListeners(){
        mPrevActButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = saveSharedData(getString(R.string.splash_data_key));
                setResult(RESULT_OK, data);
                finish();
            }
        });

        mNextActButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startDetail = new Intent(MainActivity.this, DetailActivity.class);
                String savedText = mMainDataText.getText().toString();
                Log.d(TAG, "save EXTRA "+savedText);
                startDetail.putExtra(getString(R.string.detail_data_key), savedText);
                startActivityForResult(startDetail, REQUEST_CODE_DETAIL);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d(TAG, this.getLocalClassName()+" onBackPressed");
        Intent data = saveSharedData(getString(R.string.splash_data_key));
        setResult(RESULT_OK, data);
        finish();
    }

    private Intent saveSharedData (String key) {
        String savedText = mMainDataText.getText().toString();
        Log.d(TAG, "save EXTRA "+savedText);
        Intent dataInt = new Intent();
        dataInt.putExtra(key,savedText);
        return dataInt;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, this.getLocalClassName()+" onActivityResult, req="+requestCode+" res="+resultCode+" data="+data);
        if (requestCode==REQUEST_CODE_DETAIL){
            if (data==null)
                return;
            String newText = data.getStringExtra(getString(R.string.main_data_key));
            if (newText!=null)
                mMainDataText.setText(newText+"/"+TAG);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, this.getLocalClassName()+" onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, this.getLocalClassName()+" onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, this.getLocalClassName()+" onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, this.getLocalClassName()+" onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, this.getLocalClassName()+" onDestroy");
    }

}