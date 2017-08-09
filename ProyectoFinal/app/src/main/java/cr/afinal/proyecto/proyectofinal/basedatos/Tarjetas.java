package cr.afinal.proyecto.proyectofinal.basedatos;

import android.content.ContentValues;

import cr.afinal.proyecto.proyectofinal.basedatos.ModeladoDB.TarejtasConstract;


/**
 * Created by Jeison-UTN on 08/08/2017.
 */

public class Tarjetas {

    private int ID;
	private String Cedula_Cliente;
    private String NumeroTarjeta;
    private String FechaVencimiento;
    private int TipoTarjeta;

    public Tarjetas() {
    }

    public Tarjetas(int ID, String cedula_Cliente, String numeroTarjeta, String fechaVencimiento, int tipoTarjeta) {
        this.ID = ID;
        Cedula_Cliente = cedula_Cliente;
        NumeroTarjeta = numeroTarjeta;
        FechaVencimiento = fechaVencimiento;
        TipoTarjeta = tipoTarjeta;
    }


    public ContentValues toContentValues() {

        ContentValues values = new ContentValues();

        values.put(TarejtasConstract.TarejtasEntry.ID, ID);
        values.put(TarejtasConstract.TarejtasEntry.Cedula_Cliente, Cedula_Cliente);
        values.put(TarejtasConstract.TarejtasEntry.NumeroTarjeta, NumeroTarjeta);
        values.put(TarejtasConstract.TarejtasEntry.FechaVencimiento, FechaVencimiento);
        values.put(TarejtasConstract.TarejtasEntry.TipoTarjeta, TipoTarjeta);


        return values;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCedula_Cliente() {
        return Cedula_Cliente;
    }

    public void setCedula_Cliente(String cedula_Cliente) {
        Cedula_Cliente = cedula_Cliente;
    }

    public String getNumeroTarjeta() {
        return NumeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        NumeroTarjeta = numeroTarjeta;
    }

    public String getFechaVencimiento() {
        return FechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        FechaVencimiento = fechaVencimiento;
    }

    public int getTipoTarjeta() {
        return TipoTarjeta;
    }

    public void setTipoTarjeta(int tipoTarjeta) {
        TipoTarjeta = tipoTarjeta;
    }
}
