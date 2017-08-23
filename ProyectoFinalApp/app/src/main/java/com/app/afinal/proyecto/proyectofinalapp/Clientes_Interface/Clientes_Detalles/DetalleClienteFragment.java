package com.app.afinal.proyecto.proyectofinalapp.Clientes_Interface.Clientes_Detalles;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.afinal.proyecto.proyectofinalapp.Clientes_Interface.ClientesActivity;
import com.app.afinal.proyecto.proyectofinalapp.Clientes_Interface.Clientes_Configuracion.ConfiguracionClientesActivity;
import com.app.afinal.proyecto.proyectofinalapp.Formulario_Interface.FormularioDatosActivity;
import com.app.afinal.proyecto.proyectofinalapp.R;
import com.app.afinal.proyecto.proyectofinalapp.basedatos.Clientes;
import com.app.afinal.proyecto.proyectofinalapp.basedatos.ModeladoDB.ConexionHelper;
import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class DetalleClienteFragment extends Fragment {

    private String clienteIDMemoria;

    private CollapsingToolbarLayout tituloCampoImgClientesDetallesActivity;
    private ImageView imgImagenCliente_Foto;
    private TextView etxtDName;
    private TextView etxtDCed;
    private TextView etxtDTel;
    private TextView etxtDDir;

    private Clientes clientesData;

    private Button bLlamar;
    private Button bSMS;
    private Button btnFormulario;

    private ConexionHelper mConnexion;

    private static final int REQUEST_ADD_UPDATE_DELETE_CLIENT = 1;

    public DetalleClienteFragment() {
        // Required empty public constructor
    }

    public static DetalleClienteFragment newInstance(String clienteID) {
        DetalleClienteFragment fragment = new DetalleClienteFragment();
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
        View root = inflater.inflate(R.layout.fragment_detalle_cliente, container, false);
        tituloCampoImgClientesDetallesActivity = (CollapsingToolbarLayout) getActivity().findViewById(R.id.tituloCampoImgClientesDetallesActivity);

        imgImagenCliente_Foto = (ImageView) getActivity().findViewById(R.id.imgImagenCliente_Foto);
        etxtDName = (TextView) root.findViewById(R.id.etxtDName);
        etxtDCed = (TextView) root.findViewById(R.id.etxtDCed);
        etxtDTel = (TextView) root.findViewById(R.id.etxtDTel);
        etxtDDir = (TextView) root.findViewById(R.id.etxtDDir);
        bLlamar = (Button) root.findViewById(R.id.bLlamar);
        bSMS = (Button) root.findViewById(R.id.bSMS);
        btnFormulario = (Button) root.findViewById(R.id.btnFormulario);

        btnFormulario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                callPantallaFormulario();

            }
        });

        bLlamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llamar(String.valueOf(etxtDTel.getText()));
            }
        });

        bSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mensaje(String.valueOf(etxtDTel.getText()), String.valueOf(etxtDName.getText()));
            }
        });

        mConnexion = new ConexionHelper(getActivity());

        cargarCliente();

        return root;
    }

    public void callPantallaFormulario() {
        Intent pantallaFormulario= new Intent(getActivity(), FormularioDatosActivity.class);
        pantallaFormulario.putExtra(ClientesActivity.CLIENTEID, clienteIDMemoria);
        pantallaFormulario.putExtra(ClientesActivity.CLIENTECEDULA, clientesData.getCedula_Cliente());
        startActivityForResult(pantallaFormulario, REQUEST_ADD_UPDATE_DELETE_CLIENT);
    }

    private void llamar(String numero) {

        Intent llamar = new Intent(Intent.ACTION_DIAL);
        llamar.setData(Uri.parse("tel:" + numero));
        startActivity(llamar);
    }

    private void mensaje(String numero, String nombre) {

        Intent mensaje = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + numero));
        mensaje.putExtra("sms_body", "Estimado " + nombre + " estamos en camino");
        startActivity(mensaje);
    }

    private void mostrarCliente(Clientes cliente) {

        clientesData = cliente;
       // tituloCampoImgClientesDetallesActivity.setTitle(cliente.getNombre());

        String fotografia = "";

        if(cliente.getFotografia() == null) {
            fotografia ="foto1.jpg";

            Bitmap bitmap = null;

            try{
                FileInputStream fileInputStream =
                        new FileInputStream(getContext().getFilesDir().getPath()+ "/"+fotografia);
                bitmap = BitmapFactory.decodeStream(fileInputStream);

                imgImagenCliente_Foto.setImageBitmap(bitmap);

            }catch (IOException io){
                io.printStackTrace();
            }

        } else {
            fotografia = cliente.getFotografia();

            Bitmap bitmap = null;

            File archivo = new File(Environment.getExternalStorageDirectory(),fotografia);
            try{
                FileInputStream fileInputStream =
                        new FileInputStream(archivo);
                bitmap = BitmapFactory.decodeStream(fileInputStream);

                imgImagenCliente_Foto.setImageBitmap(bitmap);

            }catch (IOException io){
                io.printStackTrace();
            }

        }





        etxtDName.setText(cliente.getNombre());
        etxtDCed.setText(cliente.getCedula_Cliente());
        etxtDTel.setText(cliente.getTelefono());
        etxtDDir.setText(cliente.getDireccion());
    }

    private void showLoadError() {
        Toast.makeText(getActivity(),
                getString(R.string.errorMessageCargar), Toast.LENGTH_SHORT).show();
    }

    private class CargarClienteID extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Void... voids) {
            return mConnexion.clientesById(clienteIDMemoria);
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            if (cursor != null && cursor.moveToLast()) {
                mostrarCliente(new Clientes(cursor));
            } else {
                showLoadError();
            }
        }

    }

    private void cargarCliente() {
        new CargarClienteID().execute();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {


        if (Activity.RESULT_OK == resultCode) {
            switch (requestCode) {
                case REQUEST_ADD_UPDATE_DELETE_CLIENT:
                    getActivity().setResult(Activity.RESULT_OK);
                    cargarCliente();
                    break;
            }
        }

    }

}
