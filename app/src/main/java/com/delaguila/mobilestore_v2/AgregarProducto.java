package com.delaguila.mobilestore_v2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import basedatos.SQLControlador;

public class AgregarProducto extends AppCompatActivity {

    private EditText txtNombre;
    private EditText txtPrecio;
    private Button btnGuardar;

    //elementos para la bd
    private SQLControlador controlador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_producto);

        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtPrecio = (EditText) findViewById(R.id.txtPrecio);

        controlador = new SQLControlador(this);
        controlador.abrirBaseDeDatos();

        btnGuardar = (Button) findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = txtNombre.getText().toString();
                String precio = txtPrecio.getText().toString();
                long registros_exitoso = controlador.insertarDatos(nombre,precio);
                if (registros_exitoso != -1) {
                    Toast toast = Toast.makeText(getApplicationContext(),"Exito",Toast.LENGTH_SHORT);
                    toast.show();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }

}
