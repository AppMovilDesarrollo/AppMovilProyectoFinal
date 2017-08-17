package  aplicaciones.afinal.proyecto.proyectofinalaplicaciones.basedatos.ModeladoDB;

import android.provider.BaseColumns;

/**
 * Created by Jeison-UTN on 08/08/2017.
 */

public class Clientes_VisitasConstract {

    public static abstract class Clientes_VisitasEntry implements BaseColumns {
        public static final String TABLE_NAME = "Clientes_Visitas";


        public static final String ID = "ID";
        public static final String Nombre = "Nombre";
        public static final String Telefono = "Telefono";
        public static final String Direccion = "Direccion";
        public static final String Cedula_Cliente = "Cedula_Cliente";


    }



}
