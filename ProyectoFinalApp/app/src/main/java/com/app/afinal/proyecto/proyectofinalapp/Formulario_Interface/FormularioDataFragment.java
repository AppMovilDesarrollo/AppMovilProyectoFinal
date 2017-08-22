package com.app.afinal.proyecto.proyectofinalapp.Formulario_Interface;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.afinal.proyecto.proyectofinalapp.Clientes_Interface.ClientesActivity;
import com.app.afinal.proyecto.proyectofinalapp.Clientes_Interface.Clientes_Detalles.DetallesClientesActivity;
import com.app.afinal.proyecto.proyectofinalapp.R;
import com.app.afinal.proyecto.proyectofinalapp.Tarjeta_Interface.TarjetasActivity;
import com.app.afinal.proyecto.proyectofinalapp.basedatos.Clientes;
import com.app.afinal.proyecto.proyectofinalapp.basedatos.ModeladoDB.ClientesConstract;
import com.app.afinal.proyecto.proyectofinalapp.basedatos.ModeladoDB.ConexionHelper;
import com.app.afinal.proyecto.proyectofinalapp.basedatos.Tarjetas;

import java.io.Serializable;


public class FormularioDataFragment extends Fragment {

    private String clienteIDMemoria;

    private Clientes clientesData;
    private EditText etxtFName;
    private EditText etxtFCed;
    private EditText etxtFTel;
    private EditText etxtSalary;
    private EditText etxtLugTra;
    private EditText etxtFDir;
    private Button bNext;

    private ConexionHelper mConnexion;

    private static final int REQUEST_ADD_UPDATE_DELETE_CLIENT = 1;

    public FormularioDataFragment() {
        // Required empty public constructor
    }

    public static FormularioDataFragment newInstance(String clienteID) {
        FormularioDataFragment fragment = new FormularioDataFragment();
        Bundle args = new Bundle();
        args.putString("CLIENTEID", clienteID);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            clienteIDMemoria = getArguments().getString("CLIENTEID");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_formulario_data, container, false);

        etxtFName = (EditText) root.findViewById(R.id.etxtFName);
        etxtFCed = (EditText) root.findViewById(R.id.etxtFCed);
        etxtFTel = (EditText) root.findViewById(R.id.etxtFTel);
        etxtSalary = (EditText) root.findViewById(R.id.etxtSalary);
        etxtLugTra = (EditText) root.findViewById(R.id.etxtLugTra);
        etxtFDir = (EditText) root.findViewById(R.id.etxtFDir);
        bNext = (Button) root.findViewById(R.id.bNext);

        clientesData = new Clientes();

        mConnexion = new ConexionHelper(getActivity());

        cargarClientes();

        bNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarFormularioData();
            }
        });

        return root;
    }

    private class agregarFormularioDataClientes extends AsyncTask<Clientes, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Clientes... clientes) {

            return mConnexion.updateClientes(clientes[0]) > 0;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            mostrarCompletarTarjeta(result);
        }

    }

    private class cargarClienteID extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Void... voids) {
            return mConnexion.clientesById(clienteIDMemoria);
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            if (cursor != null && cursor.moveToLast()) {
                mostrarCliente(new Clientes(cursor));
            } else {
                showError();
            }
        }

    }

    private void guardarFormularioData() {

        if (etxtFName.length() <= 0) {

            etxtFName.setError("El campo nombre es requerido");

        } else if (etxtFCed.length() <= 0) {

            etxtFCed.setError("Debe ingresar un número de cédula");

        } else if (etxtFTel.length() <= 0) {

            etxtFTel.setError("Digite un teléfono para localizar al cliente");

        } else if (etxtSalary.length() <= 0) {

            etxtSalary.setError("El monto del salario debe ser mayor a 0");

        } else if (etxtLugTra.length() <= 0) {

            etxtLugTra.setError("Debe ingresar el lugar de trabajo");

        } else if (etxtFDir.length() <= 0) {

            etxtFDir.setError("Se debe ingresar una dirección");


        } else {


            clientesData.setID(Integer.parseInt(clienteIDMemoria));
            clientesData.setNombre(etxtFName.getText().toString());
            clientesData.setCedula_Cliente(etxtFCed.getText().toString());
            clientesData.setTelefono(etxtFTel.getText().toString());
            clientesData.setSalario(Double.parseDouble(etxtSalary.getText().toString()));
            clientesData.setLugarTrabajo(etxtLugTra.getText().toString());
            clientesData.setDireccion(etxtFDir.getText().toString());

            etxtFName.setText("");
            etxtFCed.setText("");
            etxtFTel.setText("");
            etxtSalary.setText("");
            etxtLugTra.setText("");
            etxtFDir.setText("");


            new agregarFormularioDataClientes().execute(clientesData);

        }
    }

    public void cargarClientes() {

        new cargarClienteID().execute();

    }

    public void mostrarCliente(Clientes cliente) {

        clientesData = cliente;

        etxtFName.setText(clientesData.getNombre());
        etxtFCed.setText(clientesData.getCedula_Cliente());
        etxtFTel.setText(clientesData.getTelefono());
        etxtFDir.setText(clientesData.getDireccion());


    }

    private void mostrarCompletarTarjeta(boolean result) {

        if (!result) {

            showError();

        } else {

            showSuccess();

            Intent pantallaTarjetas = new Intent(getActivity(), TarjetasActivity.class);
            pantallaTarjetas.putExtra(ClientesActivity.CLIENTECLASS, (Parcelable) clientesData.getCursor());
            startActivityForResult(pantallaTarjetas, REQUEST_ADD_UPDATE_DELETE_CLIENT);

        }

        // getActivity().finish();
    }


    private void showError() {
        Toast.makeText(getActivity(),
                "Error al agregar nueva información", Toast.LENGTH_SHORT).show();
    }

    private void showSuccess() {
        Toast.makeText(getActivity(),
                "Se ha guardado correctamente", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {


        if (Activity.RESULT_OK == resultCode) {
            switch (requestCode) {
                case REQUEST_ADD_UPDATE_DELETE_CLIENT:


                    getActivity().setResult(Activity.RESULT_OK);
                    getActivity().finish();
                    break;
            }
        } else {
            getActivity().setResult(Activity.RESULT_CANCELED);
            getActivity().finish();
        }

    }

}
