package com.app.afinal.proyecto.proyectofinalapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.app.afinal.proyecto.proyectofinalapp.Clientes_Interface.ClientesActivity;
import com.app.afinal.proyecto.proyectofinalapp.basedatos.Clientes;
import com.app.afinal.proyecto.proyectofinalapp.basedatos.ModeladoDB.ConexionHelper;

public class PantallaConfiguracion extends AppCompatActivity {
    EditText txtNombre;
    EditText txtCedula;
    EditText txtTelefono;
    EditText txtDireccion;
    Button btnSave;
    private ConexionHelper mConexionDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_configuracion);
        txtCedula = (EditText)findViewById(R.id.etxtCed);
        txtNombre = (EditText)findViewById(R.id.etxtName);
        txtTelefono = (EditText)findViewById(R.id.etxtTel);
        txtDireccion = (EditText)findViewById(R.id.etxtDir);
        btnSave = (Button)findViewById(R.id.btnSave);

        // Instancia de helper
        mConexionDbHelper = new ConexionHelper(this);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = mConexionDbHelper.allClientes();
                Clientes values = new Clientes();
                values.setID(cursor.getCount()+1);
                values.setCedula_Cliente(txtCedula.getText().toString());
                values.setNombre(txtNombre.getText().toString());
                values.setTelefono(txtTelefono.getText().toString());
                values.setDireccion(txtDireccion.getText().toString());
                mConexionDbHelper.insertClientes(values);

                MsjSaveClient(); //Mensaje de Guardado
                //Limpiar Pantalla
                txtCedula.setText("");
                txtNombre.setText("");
                txtDireccion.setText("");
                txtTelefono.setText("");
                txtNombre.requestFocus();
            }
        });
    }



    public void MsjSaveClient()
    {
        final AlertDialog.Builder msjSave = new AlertDialog.Builder(this);
        msjSave.setTitle(getResources().getString(R.string.msjSave));
        //builder1.setMessage();
        msjSave.setCancelable(false);

        msjSave.setPositiveButton(
                "Aceptar",
                new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        PantallaConfiguracion.this.finish();
                        Intent i= new Intent(PantallaConfiguracion.this, ClientesActivity.class);
                        startActivity(i);
                    }
                });
      /*  msjSave.setNegativeButton(
                "Cancelar",
                new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });*/
        AlertDialog alert02 = msjSave.create();
        alert02.show();
    }
}
