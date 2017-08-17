package com.app.afinal.proyecto.proyectofinalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.app.afinal.proyecto.proyectofinalapp.Clientes_Interface.ClientesActivity;

public class PrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
    }

    public void callDetallesCliente(View view) {

        Button button = (Button) findViewById(R.id.btnDetCli);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getBaseContext(), ClientesActivity.class);
                startActivity(i);
            }
        });
    }
}
