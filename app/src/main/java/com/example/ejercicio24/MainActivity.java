package com.example.ejercicio24;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ejercicio24.databinding.ActivityMainBinding;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding mainBinding;

    private ImageView imageView;
    private MyDatabaseHelper dbHelper;
    EditText editTextFileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        editTextFileName=findViewById(R.id.editTextFileName);

        mainBinding.btnClearSign.setOnClickListener(view ->{
            mainBinding.signatureView.clearCanvas();
        });

        dbHelper = new MyDatabaseHelper(this);

        mainBinding.btnLoadInIv.setOnClickListener(view -> {

            Bitmap signBitmap = mainBinding.signatureView.getSignatureBitmap();
            if (signBitmap != null) {
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                signBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();


                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("signature", byteArray);
                values.put("nombre", editTextFileName.getText().toString());
                long result = db.insert(MyDatabaseHelper.tableName, null, values);

                if (result != -1) {
                    Toast.makeText(MainActivity.this, "Firma guardada correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Error al guardar la firma", Toast.LENGTH_SHORT).show();
                }
            }

        });

        Button btnVerRegistros = findViewById(R.id.btnVerRegistros);
        btnVerRegistros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, registrosActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }


}