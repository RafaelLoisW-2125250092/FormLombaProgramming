package com.if3a.formlombaprogramming;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class ConfirmActivity extends AppCompatActivity
{
    DatePickerDialog datePickerDialog;
    TextView tvNama, tvJK, tvNoWhatsapp, tvAlamat, tvTanggal;
    Button btnTanggal, btnKonfirmasi;

    String nama, jk, noWhatsapp, alamat, choosenDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        tvNama = findViewById(R.id.tv_nama);
        tvJK = findViewById(R.id.tv_jk);
        tvNoWhatsapp = findViewById(R.id.tv_no_whatsapp);
        tvAlamat = findViewById(R.id.tv_alamat);
        tvTanggal = findViewById(R.id.tv_tanggal);

        btnTanggal = findViewById(R.id.btn_tanggal);
        btnKonfirmasi = findViewById(R.id.btn_konfirmasi);

        //ambil Intent yg dikirim oleh MainActivity
        Intent terima = getIntent();
        nama = terima.getStringExtra("varNama");
        noWhatsapp = terima.getStringExtra("varNoWhatsapp");
        alamat = terima.getStringExtra("varAlamat");
        jk = terima.getStringExtra("varJenisKelamin");

        //set variabel
        tvNama.setText(nama);
        tvNoWhatsapp.setText(noWhatsapp);
        tvAlamat.setText(alamat);
        tvJK.setText(jk);

        btnTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Calendar newCalender = Calendar.getInstance();

               datePickerDialog = new DatePickerDialog(ConfirmActivity.this, new DatePickerDialog.OnDateSetListener() {
                   @Override
                   public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                       String tahun = Integer.toString(year);
                       String bulan = Integer.toString(month + 1);
                       String tanggal = Integer.toString(dayOfMonth);

                       choosenDate = tanggal + " / " + bulan + " / " + tahun;
                       tvTanggal.setText(choosenDate);
                   }
               }, newCalender.get(Calendar.YEAR), newCalender.get(Calendar.MONTH),
                newCalender.get(Calendar.DAY_OF_MONTH));

               //tampilkan datPickerDialog
                datePickerDialog.show();
            }
        });

        btnKonfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(ConfirmActivity.this);

                dialog.setTitle("Perhatian");
                dialog.setMessage("Apakah Data yang Anda Isi Telah Benar?");

                //button positif
                dialog.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(ConfirmActivity.this,
                                "Terimakasih Pendaftaraan Anda Berhasil.",
                                Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });

                //button negatif
                dialog.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                //tampilkan dialog
                dialog.show();
            }
        });

    }
}