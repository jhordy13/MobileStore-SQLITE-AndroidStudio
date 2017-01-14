package com.delaguila.mobilestore_v2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import basedatos.SQLControlador;
import utilitarios.Mensaje;

public class ModificarProducto extends AppCompatActivity implements View.OnClickListener{

    private EditText etNombre, etPrecio;
    private Button btnModficar, btnEliminar;
    private long productoid;
    //controlador de bd
    private SQLControlador controlador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_producto);

        controlador = new SQLControlador(this);
        controlador.abrirBaseDeDatos();

        etNombre = (EditText) findViewById(R.id.etNombre);
        etPrecio = (EditText) findViewById(R.id.etPrecio);

        Intent intent = getIntent();
        productoid = Long.parseLong(intent.getExtras().getString("productoid"));
        etNombre.setText(intent.getStringExtra("productonombre"));
        etPrecio.setText(intent.getStringExtra("productoprecio"));

        btnModficar = (Button) findViewById(R.id.btnModificar);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);

        btnModficar.setOnClickListener(this);
        btnEliminar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.btnModificar:
                String newName = etNombre.getText().toString();
                String newPrice = etPrecio.getText().toString();
                if (!newName.isEmpty() && !newPrice.isEmpty()) {
                    double newprice_convert = Double.parseDouble(newPrice);
                    int ocurrio_error = controlador.actualizarDatos(productoid,newName,newprice_convert);
                    if (ocurrio_error != -1)
                        Mensaje.mostrarMensajeCorto(this,"Update Exitosa");
                    this.returnHome();
                } else {
                    Mensaje.mostrarMensajeCorto(this,"INGRESAR NOMBRE Y PRECIO");
                }
                break;
            case R.id.btnEliminar:
                final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle("Importante");
                dialog.setMessage("¿ Elimina este producto ?");
                dialog.setCancelable(false);
                dialog.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        controlador.deleteData(productoid);
                        returnHome();
                    }
                });
                dialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                    }
                });
                dialog.show();
                break;
            default:
                break;
        }
    }

    private void returnHome() {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
