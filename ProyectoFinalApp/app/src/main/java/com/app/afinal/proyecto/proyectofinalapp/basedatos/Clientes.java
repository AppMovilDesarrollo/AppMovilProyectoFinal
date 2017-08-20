package com.app.afinal.proyecto.proyectofinalapp.basedatos;

import android.content.ContentValues;

import  com.app.afinal.proyecto.proyectofinalapp.basedatos.ModeladoDB.ClientesConstract;

/**
 * Created by Jeison-UTN on 08/08/2017.
 */

public class Clientes {

    private int ID;
    private String Nombre;
    private String Cedula_Cliente;
    private String LugarTrabajo;
    private double Salario;
    private String Telefono;
    private String Direccion;
    private String Fotografia;




    public Clientes() {
    }

    public Clientes(int ID, String nombre, String cedula_Cliente, String lugarTrabajo, double salario, String telefono, String direccion, String fotografia) {
        this.ID = ID;
        Nombre = nombre;
        Cedula_Cliente = cedula_Cliente;
        LugarTrabajo = lugarTrabajo;
        Salario = salario;
        Telefono = telefono;
        Direccion = direccion;
        Fotografia = fotografia;
    }

    public ContentValues toContentValues() {

        ContentValues values = new ContentValues();

        values.put(ClientesConstract.ClientesEntry.ID, ID);
        values.put(ClientesConstract.ClientesEntry.Nombre, Nombre);
        values.put(ClientesConstract.ClientesEntry.Cedula_Cliente, Cedula_Cliente);
        values.put(ClientesConstract.ClientesEntry.LugarTrabajo, LugarTrabajo);
        values.put(ClientesConstract.ClientesEntry.Salario, Salario);
        values.put(ClientesConstract.ClientesEntry.Telefono, Telefono);
        values.put(ClientesConstract.ClientesEntry.Direccion, Direccion);
        values.put(ClientesConstract.ClientesEntry.Cedula_Cliente, Cedula_Cliente);
        values.put(ClientesConstract.ClientesEntry.Fotografia, Fotografia);

        return values;
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getCedula_Cliente() {
        return Cedula_Cliente;
    }

    public void setCedula_Cliente(String cedula_Cliente) {
        Cedula_Cliente = cedula_Cliente;
    }

    public String getLugarTrabajo() {
        return LugarTrabajo;
    }

    public void setLugarTrabajo(String lugarTrabajo) {
        LugarTrabajo = lugarTrabajo;
    }

    public double getSalario() {
        return Salario;
    }

    public void setSalario(double salario) {
        Salario = salario;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getFotografia() {
        return Fotografia;
    }

    public void setFotografia(String fotografia) {
        Fotografia = fotografia;
    }
}
