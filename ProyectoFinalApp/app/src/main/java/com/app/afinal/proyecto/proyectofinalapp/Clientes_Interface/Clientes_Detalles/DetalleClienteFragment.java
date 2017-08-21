package com.app.afinal.proyecto.proyectofinalapp.Clientes_Interface.Clientes_Detalles;


import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.afinal.proyecto.proyectofinalapp.R;
import com.app.afinal.proyecto.proyectofinalapp.basedatos.Clientes;
import com.app.afinal.proyecto.proyectofinalapp.basedatos.ModeladoDB.ConexionHelper;
import com.bumptech.glide.Glide;


public class DetalleClienteFragment extends Fragment {

    private String clienteIDMemoria;

    private CollapsingToolbarLayout tituloCampoImgClientesDetallesActivity;
    private ImageView imgImagenCliente_Foto;
    private TextView etxtDName;
    private TextView etxtDCed;
    private TextView etxtDTel;
    private TextView etxtDDir;

    private ConexionHelper mConnexion;


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

        mConnexion = new ConexionHelper(getActivity());

        cargarCliente();

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Acciones
    }

    private void mostrarCliente(Clientes cliente) {

        tituloCampoImgClientesDetallesActivity.setTitle(cliente.getNombre());

        Glide.with(this)
                .load(Uri.parse("file:///android_asset/" + cliente.getFotografia()))
                .error(R.color.colorAccent)
                .centerCrop()
                .into(imgImagenCliente_Foto);

        etxtDName.setText(cliente.getNombre());
        etxtDCed.setText(cliente.getCedula_Cliente());
        etxtDTel.setText(cliente.getTelefono());
        etxtDDir.setText(cliente.getDireccion());
    }

    private void showLoadError() {
        Toast.makeText(getActivity(),
                "Error al cargar información", Toast.LENGTH_SHORT).show();
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

}
