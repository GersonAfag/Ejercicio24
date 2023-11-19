package com.example.ejercicio24;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.ejercicio24.config.ListAdapter;
import com.example.ejercicio24.config.objetFirmas;

import java.util.ArrayList;
import java.util.List;

public class registrosActivity extends AppCompatActivity {

    ListView listView;
    List<objetFirmas> mData = new ArrayList<>();
    ListAdapter mAdapter;
    MyDatabaseHelper conexion;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registros);

        conexion = new MyDatabaseHelper(this);

        backButton = findViewById(R.id.btn_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        listView = (ListView) findViewById(R.id.listView);
        obtenerTabla();
        mAdapter = new ListAdapter(this,mData);
        listView.setAdapter(mAdapter);

    }

    private void obtenerTabla() {

        SQLiteDatabase db = conexion.getReadableDatabase();
        objetFirmas firmas = null;
        //Cursor de base de datos
        Cursor cursor = db.rawQuery(MyDatabaseHelper.SelectTable,null);

        //Recorremos el cursor
        while (cursor.moveToNext()){
            firmas = new objetFirmas();
            firmas.setId(cursor.getString(0));
            firmas.setNombre(cursor.getString(2));
            mData.add(firmas);
        }
        cursor.close();
    }


}
