package com.app.afinal.proyecto.proyectofinalapp.basedatos;

import android.content.ContentValues;

import  com.app.afinal.proyecto.proyectofinalapp.basedatos.ModeladoDB.ClientesConstract;

/**
 * Created by Jeison-UTN on 08/08/2017.
 */

public class Clientes {

    private int ID;
    private String Cedula_Cliente;
    private float Salario;
    private String LugarTrabajo;
    private int Fotografia;

    public Clientes() {
    }

    public Clientes(int ID, String Cedula_Cliente, float salario, String lugarTrabajo, int fotografia) {
        this.ID = ID;
        Cedula_Cliente = Cedula_Cliente;
        Salario = salario;
        LugarTrabajo = lugarTrabajo;
        Fotografia = fotografia;
    }


    public ContentValues toContentValues() {

        ContentValues values = new ContentValues();

        values.put(ClientesConstract.ClientesEntry.ID, ID);
        values.put(ClientesConstract.ClientesEntry.ID, Cedula_Cliente);
        values.put(ClientesConstract.ClientesEntry.ID, Salario);
        values.put(ClientesConstract.ClientesEntry.ID, LugarTrabajo);
        values.put(ClientesConstract.ClientesEntry.ID, Fotografia);

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

    public void setCedula_Cliente(String identificacion) {
        Cedula_Cliente = identificacion;
    }

    public float getSalario() {
        return Salario;
    }

    public void setSalario(float salario) {
        Salario = salario;
    }

    public String getLugarTrabajo() {
        return LugarTrabajo;
    }

    public void setLugarTrabajo(String lugarTrabajo) {
        LugarTrabajo = lugarTrabajo;
    }

    public int getFotografia() {
        return Fotografia;
    }

    public void setFotografia(int fotografia) {
        Fotografia = fotografia;
    }



}
