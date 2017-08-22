package com.app.afinal.proyecto.proyectofinalapp.Formulario_Interface;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.app.afinal.proyecto.proyectofinalapp.Clientes_Interface.ClientesActivity;
import com.app.afinal.proyecto.proyectofinalapp.R;
import com.app.afinal.proyecto.proyectofinalapp.Tarjeta_Interface.TarjetasActivity;
import com.app.afinal.proyecto.proyectofinalapp.basedatos.Clientes;
import com.app.afinal.proyecto.proyectofinalapp.basedatos.ModeladoDB.ConexionHelper;
import com.app.afinal.proyecto.proyectofinalapp.basedatos.Tarjetas;

import java.io.Serializable;

import static android.content.Context.MODE_PRIVATE;


public class FormularioDataFragmentCompleto extends Fragment {

    private String clienteIDMemoria;

    private Clientes clientesData;
    private EditText etxtFName;
    private EditText etxtFCed;
    private EditText etxtFTel;
    private EditText etxtSalary;
    private EditText etxtLugTra;
    private EditText etxtFDir;
    private Button bNext;
    private EditText etxtNumTar;
    private EditText expFecha;
    private EditText etxtMonto;
    private RadioGroup grupo;

    private ConexionHelper mConnexion;

    private static final int REQUEST_ADD_UPDATE_DELETE_CLIENT = 1;

    public FormularioDataFragmentCompleto() {
        // Required empty public constructor
    }

    public static FormularioDataFragmentCompleto newInstance(String clienteID) {
        FormularioDataFragmentCompleto fragment = new FormularioDataFragmentCompleto();
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
        cargarTarjetas();

        bNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salirCompletos();
            }
        });

        return root;
    }

    public void salirCompletos() {

        getActivity().setResult(Activity.RESULT_OK);


        getActivity().finish();

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

    private class cargartarjetaID extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Void... voids) {
            return mConnexion.tarjetasByCedula(clientesData.getCedula_Cliente());
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            if (cursor != null && cursor.moveToLast()) {
                mostrarTarjeta(new Tarjetas(cursor));
            } else {
                showError();
            }
        }

    }


    public void cargarClientes() {

        new cargarClienteID().execute();

    }

    public void cargarTarjetas() {

        new cargartarjetaID().execute();

    }

    public void mostrarCliente(Clientes cliente) {

        clientesData = cliente;

        etxtFName.setText(clientesData.getNombre());
        etxtFCed.setText(clientesData.getCedula_Cliente());
        etxtFTel.setText(clientesData.getTelefono());
        etxtSalary.setText((int) clientesData.getSalario());
        etxtLugTra.setText(clientesData.getLugarTrabajo());
        etxtFDir.setText(clientesData.getDireccion());


    }

    public void mostrarTarjeta(Tarjetas tarjeta) {

        etxtFName.setText(clientesData.getNombre());
        etxtFCed.setText(clientesData.getCedula_Cliente());
        etxtFTel.setText(clientesData.getTelefono());
        etxtSalary.setText((int) clientesData.getSalario());
        etxtLugTra.setText(clientesData.getLugarTrabajo());
        etxtFDir.setText(clientesData.getDireccion());


    }


    private void showError() {
        Toast.makeText(getActivity(),
                "Error al agregar nueva información", Toast.LENGTH_SHORT).show();
    }

    private void showSuccess() {
        Toast.makeText(getActivity(),
                "Se ha guardado correctamente", Toast.LENGTH_SHORT).show();
    }


}
