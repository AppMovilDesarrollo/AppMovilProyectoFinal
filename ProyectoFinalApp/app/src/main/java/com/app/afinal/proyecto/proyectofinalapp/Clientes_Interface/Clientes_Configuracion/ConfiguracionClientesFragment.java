package com.app.afinal.proyecto.proyectofinalapp.Clientes_Interface.Clientes_Configuracion;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

        txtCedula = (EditText) root.findViewById(R.id.etxtCed);
        txtNombre = (EditText) root.findViewById(R.id.etxtName);
        txtTelefono = (EditText) root.findViewById(R.id.etxtTel);
        txtDireccion = (EditText) root.findViewById(R.id.etxtDir);

        bGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                guardarClientes();

            }
        });


        return root;
    }

    private class agregarNuevoCliente extends AsyncTask<Clientes, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Clientes... clientes) {
            return mConexionDbHelper.insertClientes(clientes[0]) > 0;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            mostraListaClintesFinalizar(result);
        }

    }

    private void guardarClientes() {

        boolean error = false;

        Cursor cursor = mConexionDbHelper.allClientes();

        if (txtNombre.length() <= 0) {

            txtNombre.setError("El campo nombre es requerido");

        } else if (txtCedula.length() <= 0) {

            txtCedula.setError("Debe ingresar un número de cédula");

        } else if (txtTelefono.length() <= 0) {

            txtTelefono.setError("Digite un teléfono para localizar al cliente");

        } else if (txtDireccion.length() <= 0) {

            txtDireccion.setError("El campo dirección es requerido");

        } else {


            Clientes values = new Clientes();
            values.setID(cursor.getCount() + 1);
            values.setCedula_Cliente(txtCedula.getText().toString());
            values.setNombre(txtNombre.getText().toString());
            values.setTelefono(txtTelefono.getText().toString());
            values.setDireccion(txtDireccion.getText().toString());


            //Limpiar Pantalla
            txtCedula.setText("");
            txtNombre.setText("");
            txtDireccion.setText("");
            txtTelefono.setText("");
            txtNombre.requestFocus();

            new agregarNuevoCliente().execute(values);
        }
    }


    private void mostraListaClintesFinalizar(boolean result) {

        if (!result) {

            showError();
            getActivity().setResult(Activity.RESULT_CANCELED);
        } else {

            showSuccess();
            getActivity().setResult(Activity.RESULT_OK);
        }

        getActivity().finish();
    }

    private void showError() {
        Toast.makeText(getActivity(),
                "Error al agregar nueva información", Toast.LENGTH_SHORT).show();
    }

    private void showSuccess() {
        Toast.makeText(getActivity(),
                "Cliente guardado correctamente", Toast.LENGTH_SHORT).show();
    }

}
