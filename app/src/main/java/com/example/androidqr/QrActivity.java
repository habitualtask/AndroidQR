package com.example.androidqr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

public class QrActivity extends AppCompatActivity {

    private Button buttonStart;
    private TextView Imgview,Titleview,Categoryview,Companyview,Serialview,Remarkview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);
        buttonStart = (Button) findViewById(R.id.startbtn);
        Imgview = (TextView) findViewById(R.id.imgview);
        Titleview = (TextView) findViewById(R.id.titleview);
        Categoryview = (TextView)  findViewById(R.id.categoryview);
        Companyview=(TextView)findViewById(R.id.companyview);
        Serialview = (TextView) findViewById(R.id.serialview);
        Remarkview = (TextView) findViewById(R.id.remarkview);
        Intent intent =getIntent(); //데이터수신
        String Img=intent.getExtras().getString("img");
        Imgview.setText(Img);
        String Title=intent.getExtras().getString("title");
        Titleview.setText(Title);
        String Category=intent.getExtras().getString("category");
        Categoryview.setText(Category);
        String Company=intent.getExtras().getString("company");
        Companyview.setText(Company);
        String Serial=intent.getExtras().getString("serial");
        Serialview.setText(Serial);
        String Remark=intent.getExtras().getString("remark");
        Remarkview.setText(Remark);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    //Getting the scan results
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //qrcode 가 없으면
            if (result.getContents() == null) {
                Toast.makeText(this, "취소!", Toast.LENGTH_SHORT).show();
            } else {
                //qrcode 결과가 있으면
                Toast.makeText(this, "스캔완료!", Toast.LENGTH_SHORT).show();
                try {
                    //data를 json으로 변환
                    JSONObject obj = new JSONObject(result.getContents());
                } catch (JSONException e) {
                    e.printStackTrace();
                    //Toast.makeText(MainActivity.this, result.getContents(), Toast.LENGTH_LONG).show();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}