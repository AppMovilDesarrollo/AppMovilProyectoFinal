package  com.app.afinal.proyecto.proyectofinalapp.basedatos;

import android.content.ContentValues;
import android.database.Cursor;


import  com.app.afinal.proyecto.proyectofinalapp.basedatos.ModeladoDB.TarjetasConstract;


/**
 * Created by Jeison-UTN on 08/08/2017.
 */

public class Tarjetas {

    private int ID;
	private String Cedula_Cliente;
    private String NumeroTarjeta;
    private String FechaVencimiento;
    private double Monto;
    private int TipoTarjeta;

    public Tarjetas() {
    }

    public Tarjetas(int ID, String cedula_Cliente, String numeroTarjeta, String fechaVencimiento, double monto, int tipoTarjeta) {
        this.ID = ID;
        Cedula_Cliente = cedula_Cliente;
        NumeroTarjeta = numeroTarjeta;
        FechaVencimiento = fechaVencimiento;
        Monto = monto;
        TipoTarjeta = tipoTarjeta;
    }


    public ContentValues toContentValues() {

        ContentValues values = new ContentValues();

        values.put(TarjetasConstract.TarjetasEntry.ID, ID);
        values.put(TarjetasConstract.TarjetasEntry.Cedula_Cliente, Cedula_Cliente);
        values.put(TarjetasConstract.TarjetasEntry.NumeroTarjeta, NumeroTarjeta);
        values.put(TarjetasConstract.TarjetasEntry.FechaVencimiento, FechaVencimiento);
        values.put(TarjetasConstract.TarjetasEntry.Monto, Monto);
        values.put(TarjetasConstract.TarjetasEntry.TipoTarjeta, TipoTarjeta);


        return values;
    }

    public Tarjetas (Cursor cursor) {

        this.ID =  cursor.getInt(cursor.getColumnIndex(TarjetasConstract.TarjetasEntry.ID));
        Cedula_Cliente =  cursor.getString(cursor.getColumnIndex(TarjetasConstract.TarjetasEntry.Cedula_Cliente));
        NumeroTarjeta = cursor.getString(cursor.getColumnIndex(TarjetasConstract.TarjetasEntry.NumeroTarjeta));
        FechaVencimiento = cursor.getString(cursor.getColumnIndex(TarjetasConstract.TarjetasEntry.FechaVencimiento));
        Monto = cursor.getDouble(cursor.getColumnIndex(TarjetasConstract.TarjetasEntry.Monto));
        TipoTarjeta = cursor.getInt(cursor.getColumnIndex(TarjetasConstract.TarjetasEntry.TipoTarjeta));
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


    public Double getMonto() {
        return Monto;
    }

    public void setMonto(Double monto) {
        Monto = monto;
    }
}
