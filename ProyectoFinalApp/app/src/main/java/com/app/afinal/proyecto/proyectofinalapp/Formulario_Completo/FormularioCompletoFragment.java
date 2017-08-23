package com.app.afinal.proyecto.proyectofinalapp.Formulario_Completo;


import android.app.Activity;
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
import android.widget.RadioButton;
import android.widget.Toast;

import com.app.afinal.proyecto.proyectofinalapp.Clientes_Interface.ClientesActivity;
import com.app.afinal.proyecto.proyectofinalapp.R;
import com.app.afinal.proyecto.proyectofinalapp.basedatos.Clientes;
import com.app.afinal.proyecto.proyectofinalapp.basedatos.ModeladoDB.ClientesConstract;
import com.app.afinal.proyecto.proyectofinalapp.basedatos.ModeladoDB.ConexionHelper;
import com.app.afinal.proyecto.proyectofinalapp.basedatos.Tarjetas;

public class FormularioCompletoFragment extends Fragment {

    private String clienteIDMemoria;
    private String clienteCedulaMemoria;

    private EditText etxtFName2;
    private EditText etxtFCed2;
    private EditText etxtFTel2;
    private EditText etxtSalary2;
    private EditText etxtLugTra2;
    private EditText etxtFDir2;
    private FloatingActionButton bSalirFormulario;
    private EditText etxtNumTar2;
    private EditText expFecha2;
    private EditText etxtMonto2;
    private RadioButton rdBtn12;
    private RadioButton rdBtn22;

    private ConexionHelper mConnexion;

    private static final int REQUEST_ADD_UPDATE_DELETE_CLIENT = 1;

    public FormularioCompletoFragment() {
        // Required empty public constructor
    }

    public static FormularioCompletoFragment newInstance(String clienteID, String cedula) {
        FormularioCompletoFragment fragment = new FormularioCompletoFragment();
        Bundle args = new Bundle();
        args.putString("CLIENTEID", clienteID);
        args.putString("CEDULA", cedula);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            clienteIDMemoria = getArguments().getString("CLIENTEID");
            clienteCedulaMemoria = getArguments().getString("CEDULA");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_formulario_completo, container, false);

        etxtFName2 = (EditText) root.findViewById(R.id.etxtFName2);
        etxtFCed2 = (EditText) root.findViewById(R.id.etxtFCed2);
        etxtFTel2 = (EditText) root.findViewById(R.id.etxtFTel2);
        etxtSalary2 = (EditText) root.findViewById(R.id.etxtSalary2);
        etxtLugTra2 = (EditText) root.findViewById(R.id.etxtLugTra2);
        etxtFDir2 = (EditText) root.findViewById(R.id.etxtFDir2);
        etxtNumTar2 = (EditText) root.findViewById(R.id.etxtNumTar2);
        expFecha2 = (EditText) root.findViewById(R.id.expFecha2);
        etxtMonto2 = (EditText) root.findViewById(R.id.etxtMonto2);
        rdBtn12 = (RadioButton) root.findViewById(R.id.rdBtn12);
        rdBtn22 = (RadioButton) root.findViewById(R.id.rdBtn22);
        bSalirFormulario = (FloatingActionButton) getActivity().findViewById(R.id.bSalirFormulario);

          bSalirFormulario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salirCompletos();
            }
        });


        mConnexion = new ConexionHelper(getActivity());

        cargarClientes();
        cargarTarjetas();

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
            return mConnexion.tarjetasByCedula(clienteCedulaMemoria);
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

        etxtFName2.setText(cliente.getNombre());
        etxtFCed2.setText(cliente.getCedula_Cliente());
        etxtFTel2.setText(cliente.getTelefono());
        etxtSalary2.setText(String.valueOf(cliente.getSalario()));
        etxtLugTra2.setText(cliente.getLugarTrabajo());
        etxtFDir2.setText(cliente.getDireccion());


    }

    public void mostrarTarjeta(Tarjetas tarjeta) {

        etxtNumTar2.setText(tarjeta.getNumeroTarjeta());
        etxtNumTar2.setTransformationMethod(new MascaraTarjeta());

        expFecha2.setText(tarjeta.getFechaVencimiento());
        etxtMonto2.setText(String.valueOf(tarjeta.getMonto()));
        if (tarjeta.getTipoTarjeta()==1) {
            rdBtn12.setChecked(true);
        } else {
            rdBtn22.setChecked(true);
        }


    }


    private void showError() {
        Toast.makeText(getActivity(),
               getString(R.string.errorMessage), Toast.LENGTH_SHORT).show();
    }

    private void showSuccess() {
        Toast.makeText(getActivity(),
                getString(R.string.saveMessage), Toast.LENGTH_SHORT).show();
    }


}
