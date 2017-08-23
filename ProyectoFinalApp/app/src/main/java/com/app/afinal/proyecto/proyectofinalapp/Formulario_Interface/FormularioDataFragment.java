package com.app.afinal.proyecto.proyectofinalapp.Formulario_Interface;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.afinal.proyecto.proyectofinalapp.Clientes_Interface.ClientesActivity;
import com.app.afinal.proyecto.proyectofinalapp.Formulario_Completo.FormularioCompletoActivity;
import com.app.afinal.proyecto.proyectofinalapp.R;
import com.app.afinal.proyecto.proyectofinalapp.Tarjeta_Interface.TarjetasActivity;
import com.app.afinal.proyecto.proyectofinalapp.basedatos.Clientes;
import com.app.afinal.proyecto.proyectofinalapp.basedatos.ModeladoDB.ConexionHelper;
import com.app.afinal.proyecto.proyectofinalapp.basedatos.Utility;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

import static android.content.Context.MODE_PRIVATE;


public class FormularioDataFragment extends Fragment {

    private String clienteIDMemoria;

    private Clientes clientesData;
    private EditText etxtFName;
    private EditText etxtFCed;
    private EditText etxtFTel;
    private EditText etxtSalary;
    private EditText etxtLugTra;
    private EditText etxtFDir;
    private EditText tCopiCed;
    private Button bNext;
    private Button bTomarCedula;
    private String nombreFOTO;

    private ConexionHelper mConnexion;

    private static final int REQUEST_ADD_UPDATE_DELETE_CLIENT = 1;
    private static final int REQUEST_CAMERA = 2;

    public FormularioDataFragment() {
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
        tCopiCed = (EditText) root.findViewById(R.id.tCopiCed);
        bNext = (Button) root.findViewById(R.id.bNext);
        bTomarCedula = (Button) root.findViewById(R.id.bTomarCedula);

        clientesData = new Clientes();

        mConnexion = new ConexionHelper(getActivity());

        cargarClientes();

        bNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarFormularioData();
            }
        });

        bTomarCedula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
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

            etxtFName.setError(getString(R.string.nombreCampoRequerido));

        } else if (etxtFCed.length() <= 0) {

            etxtFCed.setError(getString(R.string.cedCampoRequerido));

        } else if (etxtFTel.length() <= 0) {

            etxtFTel.setError(getString(R.string.telCampoRequerido));

        } else if (etxtSalary.length() <= 0) {

            etxtSalary.setError(getString(R.string.salarioCampoRequerido));

        } else if (etxtLugTra.length() <= 0) {

            etxtLugTra.setError(getString(R.string.trabajoCampoRequerido));

        } else if (etxtFDir.length() <= 0) {

            etxtFDir.setError(getString(R.string.direccionCampoRequerido));

        } else if (tCopiCed.length() <= 0) {

            tCopiCed.setError(getString(R.string.fotoCampoRequerido));

        } else {


            clientesData.setID(Integer.parseInt(clienteIDMemoria));
            clientesData.setNombre(etxtFName.getText().toString());
            clientesData.setCedula_Cliente(etxtFCed.getText().toString());
            clientesData.setTelefono(etxtFTel.getText().toString());
            clientesData.setSalario(Integer.parseInt(etxtSalary.getText().toString()));
            clientesData.setLugarTrabajo(etxtLugTra.getText().toString());
            clientesData.setDireccion(etxtFDir.getText().toString());
            clientesData.setFotografia(nombreFOTO);

            etxtFName.setText("");
            etxtFCed.setText("");
            etxtFTel.setText("");
            etxtSalary.setText("");
            etxtLugTra.setText("");
            etxtFDir.setText("");
            tCopiCed.setText("");


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

    private void selectImage() {
        final CharSequence[] items = {getString(R.string.opt1foto),
                getString(R.string.opt3foto)};

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(getString(R.string.titFoto));
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = Utility.checkPermission(getContext());

                if (items[item].equals(getString(R.string.opt1foto))) {
                    if (result) {
                        cameraIntent();
                    }


                } else if (items[item].equals(getString(R.string.opt3foto))) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }


    public void cameraIntent() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);

    }

    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.PNG, 100, bytes);



        nombreFOTO = System.currentTimeMillis() + ".png";
        tCopiCed.setText(nombreFOTO);

        File destination = new File(Environment.getExternalStorageDirectory(),nombreFOTO);

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void mostrarCompletarTarjeta(boolean result) {

        if (!result) {

            showError();

        } else {

            showSuccess();

            Intent pantallaTarjetas = new Intent(getActivity(), TarjetasActivity.class);
            pantallaTarjetas.putExtra(ClientesActivity.CLIENTECEDULA, clientesData.getCedula_Cliente());
            startActivityForResult(pantallaTarjetas, REQUEST_ADD_UPDATE_DELETE_CLIENT);

        }

        // getActivity().finish();
    }


    private void showError() {
        Toast.makeText(getActivity(),
                getString(R.string.errorMessage), Toast.LENGTH_SHORT).show();
    }

    private void showSuccess() {
        Toast.makeText(getActivity(),
                getString(R.string.saveMessage), Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (Activity.RESULT_OK == resultCode) {

            switch (requestCode) {
                case REQUEST_ADD_UPDATE_DELETE_CLIENT:

                    showSuccess();
                    ClientesActivity.setDataGlobal(this.getContext(), "true", clienteIDMemoria);

                    getActivity().setResult(Activity.RESULT_OK);
                    getActivity().finish();
                    break;
                case REQUEST_CAMERA:
                    onCaptureImageResult(data);
                    break;
            }
        } else {
            showError();
            ClientesActivity.setDataGlobal(this.getContext(), "false", clienteIDMemoria);
            getActivity().setResult(Activity.RESULT_CANCELED);
            getActivity().finish();
        }
    }

}
