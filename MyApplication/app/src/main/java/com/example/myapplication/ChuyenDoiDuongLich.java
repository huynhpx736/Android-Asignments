package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ChuyenDoiDuongLich extends AppCompatActivity {
    EditText edtDuong;
    TextView txtAm;
    Button btnChuyenNam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chuyen_doi_duong_lich);
        edtDuong = findViewById(R.id.edtDuong);
        btnChuyenNam = findViewById(R.id.btnChuyenNam);
        txtAm = findViewById(R.id.txtAm);
        btnChuyenNam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int namDuong = Integer.parseInt(edtDuong.getText().toString());
                //Năm âm = can +chi
                //can = năm dương%10,    chi = năm dương %12  quy đổi ra chữ
                String namAm= "";
                switch (namDuong%10){
                    case 0: namAm +="Canh";break;
                    case 1: namAm +="Tân";break;
                    case 2: namAm +="Nhâm";break;
                    case 3: namAm +="Quý";break;
                    case 4: namAm +="Giáp";break;
                    case 5: namAm +="Ất";break;
                    case 6: namAm +="Bính";break;
                    case 7: namAm +="Đinh";break;
                    case 8: namAm +="Mậu";break;
                    case 9: namAm +="Kỷ";break;
                }
                namAm+=" ";
                switch (namDuong%12){
                    case 0: namAm +="Thân";break;
                    case 1: namAm +="Dậu";break;
                    case 2: namAm +="Tuất";break;
                    case 3: namAm +="Hợi";break;
                    case 4: namAm +="Tí";break;
                    case 5: namAm +="Sửu";break;
                    case 6: namAm +="Dần";break;
                    case 7: namAm +="Mão";break;
                    case 8: namAm +="Thìn";break;
                    case 9: namAm +="Tỵ";break;
                    case 10: namAm +="Ngọ";break;
                    case 11: namAm +="Mùi";break;
                }
                txtAm.setText(namAm);
            }
        });
    }
}