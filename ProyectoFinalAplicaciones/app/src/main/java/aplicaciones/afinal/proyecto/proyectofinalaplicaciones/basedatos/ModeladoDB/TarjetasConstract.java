package  aplicaciones.afinal.proyecto.proyectofinalaplicaciones.basedatos.ModeladoDB;

import android.provider.BaseColumns;

/**
 * Created by Jeison-UTN on 08/08/2017.
 */

public class TarjetasConstract {

    public static abstract class TarjetasEntry implements BaseColumns {
        public static final String TABLE_NAME = "Tarjetas";


        public static final String ID = "ID";
        public static final String Cedula_Cliente = "Cedula_Cliente";
        public static final String NumeroTarjeta = "NumeroTarjeta";
        public static final String FechaVencimiento = "FechaVencimiento";
        public static final String TipoTarjeta = "TipoTarjeta";


    }



}