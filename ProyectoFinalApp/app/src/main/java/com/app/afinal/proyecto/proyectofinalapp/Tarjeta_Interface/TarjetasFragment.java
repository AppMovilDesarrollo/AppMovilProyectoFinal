package com.app.afinal.proyecto.proyectofinalapp.Tarjeta_Interface;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
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
import android.widget.RadioButton;
import android.widget.Toast;

import com.app.afinal.proyecto.proyectofinalapp.Formulario_Interface.FormularioDataFragment;
import com.app.afinal.proyecto.proyectofinalapp.R;
import com.app.afinal.proyecto.proyectofinalapp.basedatos.Clientes;
import com.app.afinal.proyecto.proyectofinalapp.basedatos.ModeladoDB.ConexionHelper;
import com.app.afinal.proyecto.proyectofinalapp.basedatos.Tarjetas;

import java.io.Serializable;


public class TarjetasFragment extends Fragment {


    private String clienteCedulaObtenida;
    private FloatingActionButton bGuardarFormulario;

    private EditText etxtNumTar;
    private EditText expFecha;
    private EditText etxtMonto;
    private int radio1;
    private int radio2;

    private ConexionHelper mConnexion;


    public TarjetasFragment() {
        // Required empty public constructor
    }


    public static TarjetasFragment newInstance(String cedula) {
        TarjetasFragment fragment = new TarjetasFragment();
        Bundle args = new Bundle();
        args.putString("CLIENTECEDULA", cedula);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            clienteCedulaObtenida = getArguments().getString("CLIENTECLASS");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_tarjetas, container, false);
        bGuardarFormulario = (FloatingActionButton) getActivity().findViewById(R.id.bGuardarFormulario);

        etxtNumTar = (EditText) root.findViewById(R.id.etxtNumTar);
        expFecha = (EditText) root.findViewById(R.id.expFecha);
        etxtMonto = (EditText) root.findViewById(R.id.etxtMonto);

        mConnexion = new ConexionHelper(getActivity());

        bGuardarFormulario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                guardarFormularioData();

            }
        });

        return root;
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.rdBtn1:
                if (checked)
                    radio1 = 1;
                break;
            case R.id.rdBtn2:
                if (checked)
                    radio2 = 2;
                break;
        }
    }

    public void guardarFormularioData() {

        Tarjetas tarjetas = new Tarjetas();
        Cursor cursor = mConnexion.allTarjetas();

        tarjetas.setID(cursor.getCount() + 1);
        tarjetas.setCedula_Cliente(clienteCedulaObtenida);
        tarjetas.setNumeroTarjeta(String.valueOf(etxtNumTar.getText()));
        tarjetas.setFechaVencimiento(String.valueOf(expFecha.getText()));
        tarjetas.setMonto(Double.parseDouble(String.valueOf(etxtMonto.getText())));


        new agregarFormularioDataTarjetas().execute(tarjetas);
    }

    private class agregarFormularioDataTarjetas extends AsyncTask<Tarjetas, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Tarjetas... tarjetas) {

            return mConnexion.insertTarjetas(tarjetas[0]) > 0;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            mostraListaClintesFinalizar(result);
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
                "Se ha guardado correctamente", Toast.LENGTH_SHORT).show();
    }
}
