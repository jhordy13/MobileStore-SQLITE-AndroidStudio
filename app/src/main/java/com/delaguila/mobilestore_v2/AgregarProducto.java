package com.delaguila.mobilestore_v2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import basedatos.SQLControlador;
import utilitarios.Mensaje;

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

                if (!nombre.isEmpty() && !precio.isEmpty()) {
                    double precio_convertido = Double.parseDouble(precio);
                    long registros_exitoso = controlador.insertarDatos(nombre,precio_convertido);
                    if (registros_exitoso != -1) {
                        Mensaje.mostrarMensajeCorto(view.getContext(),"Exito al guardar.");
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                } else {
                    Mensaje.mostrarMensajeCorto(view.getContext(),"Ingresar NOMBRE Y PRECIO");
                }

            }
        });
    }

}
