package com.example.androidqr;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {
    //view Objects
    private Button buttonScan;


    //qr code scanner object
    private IntentIntegrator qrScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //View Objects
        buttonScan = (Button) findViewById(R.id.buttonScan);


        //intializing scan object
        qrScan = new IntentIntegrator(this);

        //button onClick
        buttonScan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //scan option
                qrScan.setPrompt("Scanning...");
                //qrScan.setOrientationLocked(false);   //default가 세로모드, 휴대폰 방향에 따라 가로,세로 변경가능
                qrScan.initiateScan();
                Intent intent = new Intent(getApplicationContext(),QrActivity.class);

                intent.putExtra("img","모니터사진");
                intent.putExtra("title","모니터01");
                intent.putExtra("category","장비");
                intent.putExtra("company","델");
                intent.putExtra("serial","101-212");
                intent.putExtra("remark","주의하여 사용하세요");
                //url 접속 후 비품상세정보 가져오기 ...?
                //가져온 정보 putExtra
            }
        });
    }

}