package  aplicaciones.afinal.proyecto.proyectofinalaplicaciones.basedatos.ModeladoDB;

import android.provider.BaseColumns;

/**
 * Created by Jeison-UTN on 08/08/2017.
 */

public class RegistroPagoConstract {

    public static abstract class RegistroPagoEntry implements BaseColumns {
        public static final String TABLE_NAME = "RegistroPago";


        public static final String ID = "ID";
        public static final String Cedula_Cliente = "Cedula_Cliente";
        public static final String Monto = "Monto";
        public static final String Tarjeta_ID = "Tarjeta_ID";



    }



}
