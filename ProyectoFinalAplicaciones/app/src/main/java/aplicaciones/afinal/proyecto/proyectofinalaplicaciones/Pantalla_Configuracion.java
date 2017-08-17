package  aplicaciones.afinal.proyecto.proyectofinalaplicaciones;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import  aplicaciones.afinal.proyecto.proyectofinalaplicaciones.basedatos.ModeladoDB.ConexionHelper;

public class Pantalla_Configuracion extends AppCompatActivity {
    Button btnSave;
    EditText etxtName;
    EditText etxtCed;
    EditText etxtTel;
    EditText etxtDir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla__configuracion);
        btnSave = (Button)findViewById(R.id.btnSave);
        etxtName = (EditText)findViewById(R.id.etxtName);
        etxtCed = (EditText)findViewById(R.id.etxtCed);
        etxtTel = (EditText)findViewById(R.id.etxtTel);
        etxtDir = (EditText)findViewById(R.id.etxtDir);

        final ConexionHelper db = new ConexionHelper(this);

        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String nombre = etxtName.getText().toString();
                Integer cedula = Integer.parseInt(etxtCed.getText().toString());
                Integer telefono = Integer.parseInt(etxtTel.getText().toString());
                String direccion = etxtDir.getText().toString();
                //db.addCliente(new Cliente(cedula,nombre,direccion,telefono));
            }
        });
    }

}
