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
import com.app.afinal.proyecto.proyectofinalapp.basedatos.Tarjetas;

import java.io.Serializable;


public class TarjetasFragment extends Fragment {



    private Clientes clientesObtenidos;
    private FloatingActionButton bGuardarFormulario;

    private EditText etxtNumTar;
    private EditText expFecha;
    private RadioButton rdBtn1;
    private RadioButton rdBtn2;
    private EditText etxtMonto;


    public TarjetasFragment() {
        // Required empty public constructor
    }


    public static TarjetasFragment newInstance(Cursor cliente) {
        TarjetasFragment fragment = new TarjetasFragment();
        Bundle args = new Bundle();
        args.putParcelable("CLIENTECLASS", (Parcelable) cliente);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
           Cursor cursor = (Cursor) getArguments().getSerializable("CLIENTECLASS");
            clientesObtenidos = new Clientes(cursor);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_tarjetas, container, false);
        bGuardarFormulario = (FloatingActionButton) getActivity().findViewById(R.id.bGuardarFormulario);

        etxtNumTar = (EditText) root.findViewById(R.id.etxtNumTar);
        expFecha;
        rdBtn1;
        rdBtn2;
        etxtMonto;

        bGuardarFormulario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                guardarFormularioData();

            }
        });

        return root;
    }



    private class agregarFormularioDataTarjetas extends AsyncTask<Tarjetas, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Tarjetas... tarjetas) {

            return mConnexion.updateTarjetas(tarjetas[0]) > 0;
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
