package com.app.afinal.proyecto.proyectofinalapp.Clientes_Interface;


import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.app.afinal.proyecto.proyectofinalapp.R;
import com.app.afinal.proyecto.proyectofinalapp.basedatos.ModeladoDB.ConexionHelper;

public class ClientesFragment extends Fragment {


    private ConexionHelper mConexionDbHelper;

    private ListView mClientesList;
    private ClientesCursorAdapter mClientesAdapter;


    public ClientesFragment() {
        // Required empty public constructor
    }


    public static ClientesFragment newInstance() {

        return new ClientesFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_clientes, container, false);

        // Referencias UI
        mClientesList = (ListView) root.findViewById(R.id.clientes_list);
        mClientesAdapter = new ClientesCursorAdapter(getActivity(), null);

        // Setup
        mClientesList.setAdapter(mClientesAdapter);


        // Instancia de helper
        mConexionDbHelper = new ConexionHelper(getActivity());

        // Carga de datos
        allClientes();

        return root;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }


    private class ClientesLoadTask extends AsyncTask<Void, Void, Cursor> {
        @Override
        protected Cursor doInBackground(Void... params) {
            return mConexionDbHelper.allClientes();
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            if (cursor != null && cursor.getCount() > 0) {
                mClientesAdapter.swapCursor(cursor);
            } else {
                // Mostrar empty state
            }
        }
    }

    public void allClientes() {

        //mClientesAdapter.swapCursor( mConexionDbHelper.allClientes());
        new ClientesLoadTask().execute();

    }

}
