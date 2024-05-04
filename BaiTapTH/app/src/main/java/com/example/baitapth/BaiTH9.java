package com.example.baitapth;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

public class BaiTH9 extends AppCompatActivity {
    EditText edtUsername, edtPassword;
    CheckBox ckbRememberPassword;
    Button btnLogin, btnChart;
    PieChart piechartChart;
    int tongso = 10;
    int dnthanhcong = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai_th9);
        setControl();
        setEvent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences("SaveTK", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username",  "").toString();
        String password = sharedPreferences.getString("password", "").toString();

        edtUsername.setText(username);
        edtPassword.setText(password);
    }

    private void setControl() {
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        ckbRememberPassword = findViewById(R.id.cbRememberPW);
        btnLogin = findViewById(R.id.btnLogin);
        btnChart = findViewById(R.id.btnChart);
        piechartChart = findViewById(R.id.piechartChart);
    }

    private void setEvent() {
        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                tongso++;
                if(edtUsername.getText().toString().equals(edtPassword.getText().toString())){
                    dnthanhcong++;
                    Toast.makeText(BaiTH9.this,"Đăng nhập thành công", Toast.LENGTH_LONG).show();

                }
                else Toast.makeText(BaiTH9.this,"Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
//                notWritePassword();
                if(ckbRememberPassword.isChecked()){
                    writePassword();
                }
                else {
                    notWritePassword();

                }
            }
        });
        btnChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HienThiBieuDo();
            }
        });
    }
    private void HienThiBieuDo() {
        ArrayList<PieEntry> data = new ArrayList<>();
        data.add(new PieEntry(dnthanhcong, 0));
        data.add(new PieEntry(tongso-dnthanhcong, 1));
        ArrayList<Integer> data_color = new ArrayList<>();
        data_color.add(Color.BLUE);
        data_color.add(Color.RED);

        PieDataSet pieDataSet = new PieDataSet(data, "user");
        pieDataSet.setColors(data_color);
        PieData pieData =new PieData(pieDataSet);
        piechartChart.setData(pieData);
        piechartChart.invalidate();
    }
    private void writePassword(){
        SharedPreferences sharedPreferences = getSharedPreferences("SaveTK", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", edtUsername.getText().toString());
        editor.putString("password", edtPassword.getText().toString());
        editor.apply();
    }
    private void notWritePassword(){
        SharedPreferences sharedPreferences = getSharedPreferences("SaveTK", Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().apply();
    }
//    private void notWritePassword(){
//        SharedPreferences sharedPreferences = getSharedPreferences("SaveTK", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.remove("username");
//        editor.remove("password");
//        editor.apply();
//        edtUsername.setText("");
//        edtPassword.setText("");
//    }

}