package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DecimalFormat;

public class ChuyenDoiNhietDo extends AppCompatActivity {
    EditText edtF, edtC;
    Button btnFC, btnCF, btnClear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chuyen_doi_nhiet_do);
        edtF = findViewById(R.id.edtF);
        edtC = findViewById(R.id.edtC);
        btnFC = findViewById(R.id.btnFC);
        btnCF = findViewById(R.id.btnCF);
        btnClear = findViewById(R.id.btnClear);

        btnFC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DecimalFormat dfm = new DecimalFormat("0.00");
                int F= Integer.parseInt(edtF.getText().toString());
                Double C = (F-32)/1.8;
                edtC.setText(dfm.format(C)+" ");
            }

        });

        btnCF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DecimalFormat dmf = new DecimalFormat("0.00");
                int C = Integer.parseInt(edtC.getText().toString());
                Double F= (C+32)*1.8;
                edtF.setText(dmf.format(F)+" ");
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtF.setText(" ");
                edtC.setText(" ");
            }
        });
    }
}