package com.delaguila.mobilestore_v2;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import basedatos.SQLControlador;
import entidad.Producto;
import utilitarios.ProductoAdapter;


public class MainActivity extends AppCompatActivity {

    private Button btnAgregar;
    private ListView listaProductos;

    //obtener datos de la bd
    private SQLControlador controlador;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAgregar = (Button) findViewById(R.id.btnAgregarProducto);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AgregarProducto.class);
                startActivity(intent);
            }
        });

        listaProductos = (ListView) findViewById(R.id.listaProductos);
        List<Producto> productos = obtenerProductos();
        ProductoAdapter adapter = new ProductoAdapter(this, productos);
        listaProductos.setAdapter(adapter);

        listaProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), ModificarProducto.class);
                startActivity(intent);
            }
        });

    }

    private List<Producto> obtenerProductos() {
        List<Producto> productos = new ArrayList<>();
        List<String> items = new ArrayList<>();
        Producto producto;
        controlador = new SQLControlador(this);
        controlador.abrirBaseDeDatos();
        cursor = controlador.leerDatos();
        if (cursor.moveToFirst()) {
           do {
               producto = new Producto();
               producto.setProductoid(cursor.getInt(0));
               producto.setNombre(cursor.getString(1));
               producto.setPrecio(cursor.getString(2));
               productos.add(producto);
           } while (cursor.moveToNext());
        }
        return productos;
    }


}
