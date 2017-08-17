package aplicaciones.afinal.proyecto.proyectofinalaplicaciones;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class PrincipalActivity extends AppCompatActivity {
 Button btnDetCli;
 Button btnConfig;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        btnDetCli=(Button)findViewById(R.id.btnDetCli);
        btnConfig=(Button)findViewById(R.id.btnConfig);
    }


    public void callDetallesCliente(View view) {
        Intent i= new Intent(getBaseContext(), DetallesCliente.class);
        startActivity(i);
    }

    public void callPantallaConfiguracion(View view) {
        Intent i= new Intent(getBaseContext(), Pantalla_Configuracion.class);
        startActivity(i);
    }
}
