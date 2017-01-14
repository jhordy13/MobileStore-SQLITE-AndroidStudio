package com.delaguila.mobilestore_v2;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import basedatos.SQLControlador;
import entidad.Producto;
import utilitarios.ProductoAdapter;


public class MainActivity extends AppCompatActivity {

    private TextView tvProductoid, tvNombre, tvPrecio, tvNombreProducto;
    private Button btnAgregar, btnBuscar;
    private ListView listaProductos;

    //obtener datos de la bd
    private SQLControlador controlador;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controlador = new SQLControlador(this);
        controlador.abrirBaseDeDatos();

        btnAgregar = (Button) findViewById(R.id.btnAgregarProducto);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AgregarProducto.class);
                startActivity(intent);
            }
        });

        listaProductos = (ListView) findViewById(R.id.listaProductos);
        List<Producto> productos = obtenerProductos("");

        listaProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                tvProductoid = (TextView) view.findViewById(R.id.tvProductoid);
                tvNombre = (TextView) view.findViewById(R.id.tvNombre);
                tvPrecio = (TextView) view.findViewById(R.id.tvPrecio);

                String aux_productoid = tvProductoid.getText().toString();
                String aux_nombre = tvNombre.getText().toString();
                String aux_precio = tvPrecio.getText().toString();

                Intent intent = new Intent(getApplicationContext(), ModificarProducto.class);
                intent.putExtra("productoid",aux_productoid);
                intent.putExtra("productonombre",aux_nombre);
                intent.putExtra("productoprecio", aux_precio);
                startActivity(intent);
            }
        });

        btnBuscar = (Button) findViewById(R.id.btnBuscar);
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvNombreProducto = (TextView) findViewById(R.id.tvNombreProducto);
                String nombre = tvNombreProducto.getText().toString();
                List<Producto> productos = obtenerProductos(nombre);
            }
        });

    }

    private List<Producto> obtenerProductos() {
        List<Producto> productos = new ArrayList<>();
        Producto producto;
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

    private List<Producto> obtenerProductos(String nombre) {
        List<Producto> productos = new ArrayList<>();
        Producto producto;
        cursor = controlador.buscarProducto(nombre);
        if (cursor.moveToFirst()) {
            do {
                producto = new Producto();
                producto.setProductoid(cursor.getInt(0));
                producto.setNombre(cursor.getString(1));
                producto.setPrecio(cursor.getString(2));
                productos.add(producto);
            } while (cursor.moveToNext());
        }

        ProductoAdapter adapter = new ProductoAdapter(this,productos);
        listaProductos.setAdapter(adapter);
        return productos;
    }


}
