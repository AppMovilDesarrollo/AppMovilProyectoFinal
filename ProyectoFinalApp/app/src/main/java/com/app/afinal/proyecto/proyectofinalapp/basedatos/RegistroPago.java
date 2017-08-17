package  com.app.afinal.proyecto.proyectofinalapp.basedatos;

import android.content.ContentValues;

import  com.app.afinal.proyecto.proyectofinalapp.basedatos.ModeladoDB.RegistroPagoConstract;

/**
 * Created by Jeison-UTN on 08/08/2017.
 */

public class RegistroPago {

    private int ID;
	private String Cedula_Cliente;
	private float Monto;
    private int Tarjeta_ID;

    public RegistroPago() {
    }

    public RegistroPago(int ID, String cedula_Cliente, float monto, int tarjeta_ID) {
        this.ID = ID;
        Cedula_Cliente = cedula_Cliente;
        Monto = monto;
        Tarjeta_ID = tarjeta_ID;
    }


    public ContentValues toContentValues() {

        ContentValues values = new ContentValues();

        values.put(RegistroPagoConstract.RegistroPagoEntry.ID, ID);
        values.put(RegistroPagoConstract.RegistroPagoEntry.Cedula_Cliente, Cedula_Cliente);
        values.put(RegistroPagoConstract.RegistroPagoEntry.Monto, Monto);
        values.put(RegistroPagoConstract.RegistroPagoEntry.Tarjeta_ID, Tarjeta_ID);


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

    public float getMonto() {
        return Monto;
    }

    public void setMonto(float monto) {
        Monto = monto;
    }

    public int getTarjeta_ID() {
        return Tarjeta_ID;
    }

    public void setTarjeta_ID(int tarjeta_ID) {
        Tarjeta_ID = tarjeta_ID;
    }
}

