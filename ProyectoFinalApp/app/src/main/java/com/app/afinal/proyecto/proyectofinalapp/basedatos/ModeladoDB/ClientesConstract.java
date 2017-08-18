package  com.app.afinal.proyecto.proyectofinalapp.basedatos.ModeladoDB;

import android.provider.BaseColumns;

/**
 * Created by Jeison-UTN on 08/08/2017.
 */

public class ClientesConstract {

    public static abstract class ClientesEntry implements BaseColumns {
        public static final String TABLE_NAME = "Clientes";

        public static final String ID = "_id";
        public static final String Nombre = "Nombre";
        public static final String Cedula_Cliente = "Cedula_Cliente";
        public static final String LugarTrabajo = "LugarTrabajo";
        public static final String Salario = "Salario";
        public static final String Telefono = "Telefono";
        public static final String Direccion = "Direccion";
        public static final String Fotografia = "Fotografia";




    }



}
