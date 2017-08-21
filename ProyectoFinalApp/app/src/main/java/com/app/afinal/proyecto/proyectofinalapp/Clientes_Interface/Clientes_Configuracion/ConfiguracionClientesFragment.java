package com.app.afinal.proyecto.proyectofinalapp.Clientes_Interface.Clientes_Configuracion;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.app.afinal.proyecto.proyectofinalapp.R;
import com.app.afinal.proyecto.proyectofinalapp.basedatos.Clientes;
import com.app.afinal.proyecto.proyectofinalapp.basedatos.ModeladoDB.ConexionHelper;

public class ConfiguracionClientesFragment extends Fragment {


    private FloatingActionButton bGuardar;
    private EditText txtNombre;
    private EditText txtCedula;
    private EditText txtTelefono;
    private EditText txtDireccion;
    private ConexionHelper mConexionDbHelper;

    public ConfiguracionClientesFragment() {
        // Required empty public constructor
    }

    public static ConfiguracionClientesFragment newInstance() {

        return new ConfiguracionClientesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_configuracion_clientes, container, false);

        bGuardar = (FloatingActionButton) getActivity().findViewById(R.id.bGuardarCliente);
        mConexionDbHelper = new ConexionHelper(getActivity());

        bGuardar.setOnClickListener(new View.OnClickListener() {
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


        return root;
    }

    public void MsjSaveClient()
    {
        final AlertDialog.Builder msjSave = new AlertDialog.Builder(getContext());
        msjSave.setTitle(getResources().getString(R.string.msjSave));
        //builder1.setMessage();
        msjSave.setCancelable(true);

        msjSave.setPositiveButton(
                "Aceptar",
                new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
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
