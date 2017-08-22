package com.app.afinal.proyecto.proyectofinalapp.Clientes_Interface;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.app.afinal.proyecto.proyectofinalapp.Clientes_Interface.Clientes_Configuracion.ConfiguracionClientesActivity;
import com.app.afinal.proyecto.proyectofinalapp.Clientes_Interface.Clientes_Detalles.DetallesClientesActivity;
import com.app.afinal.proyecto.proyectofinalapp.R;
import com.app.afinal.proyecto.proyectofinalapp.basedatos.Clientes;
import com.app.afinal.proyecto.proyectofinalapp.basedatos.ModeladoDB.ClientesConstract;
import com.app.afinal.proyecto.proyectofinalapp.basedatos.ModeladoDB.ConexionHelper;

public class ClientesFragment extends Fragment {


    private ConexionHelper mConnexion;
    private ListView clientes_list;
    private ClientesCursorAdapter mClientesAdapter;
    private Button btnConfig;

    private static final int REQUEST_ADD_UPDATE_DELETE_CLIENT = 1;


    public ClientesFragment() {

    }


    public static ClientesFragment newInstance() {

        return new ClientesFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_clientes, container, false);


        btnConfig = (Button) getActivity().findViewById(R.id.bConfigurar);
        clientes_list = (ListView) root.findViewById(R.id.clientes_list);
        mClientesAdapter = new ClientesCursorAdapter(getActivity(), null);

        // Setup
        clientes_list.setAdapter(mClientesAdapter);

        clientes_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Cursor currentItem = (Cursor) mClientesAdapter.getItem(i);
                String clienteActual = currentItem.getString(
                        currentItem.getColumnIndex(ClientesConstract.ClientesEntry.ID));

                mostrarDetallesCliente(clienteActual);

            }
        });

    btnConfig.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            callPantallaConfiguracion();
        }
    });



        // Instancia de helper
        mConnexion = new ConexionHelper(getActivity());

        // Carga de datos
        allClientes();

        return root;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {


        if (Activity.RESULT_OK == resultCode) {
            switch (requestCode) {
                case REQUEST_ADD_UPDATE_DELETE_CLIENT:
                    allClientes();
                    break;
            }
        }

    }

    public void callPantallaConfiguracion() {
        Intent pantallaConfiguracion= new Intent(getActivity(), ConfiguracionClientesActivity.class);
        startActivityForResult(pantallaConfiguracion, REQUEST_ADD_UPDATE_DELETE_CLIENT);
    }

    private void mostrarDetallesCliente(String clienteID) {
        Intent pantallaDetallesClientes = new Intent(getActivity(), DetallesClientesActivity.class);
        pantallaDetallesClientes.putExtra(ClientesActivity.CLIENTEID, clienteID);
        startActivityForResult(pantallaDetallesClientes, REQUEST_ADD_UPDATE_DELETE_CLIENT);
    }

    private class ClientesLoadTask extends AsyncTask<Void, Void, Cursor> {
        @Override
        protected Cursor doInBackground(Void... params) {


            Cursor cursor = mConnexion.allClientes();

            Clientes clasdwa = new Clientes();
            clasdwa.setID(cursor.getCount() + 1);
            clasdwa.setCedula_Cliente("602950169");
            clasdwa.setNombre("Mitilene Morales Caballero");
            clasdwa.setDireccion("Heredia, Heredia, Corazón de Jesús");
            clasdwa.setTelefono("+50689962662");

            Clientes clasda = new Clientes();
            clasda.setID(cursor.getCount() + 2);
            clasda.setCedula_Cliente("602130169");
            clasda.setNombre("Bienvenido Cespedes Anchia");
            clasda.setDireccion("Ciudad Neilly Puntarenas");
            clasda.setTelefono("+50688538875");

            mConnexion.insertClientes(clasdwa);
            mConnexion.insertClientes(clasda);



            return mConnexion.allClientes();
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
