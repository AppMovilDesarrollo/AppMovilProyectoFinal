package cr.afinal.proyecto.proyectofinal.basedatos;

import android.content.ContentValues;

import cr.afinal.proyecto.proyectofinal.basedatos.ModeladoDB.Clientes_VisitasConstract;

/**
 * Created by Jeison-UTN on 08/08/2017.
 */

public class Clientes_Visitas {

    private int ID;
    private String Nombre;
    private String Telefono;
    private String Direccion;
    private String Cedula_Cliente;

    public Clientes_Visitas() {
    }

    public Clientes_Visitas(int ID, String nombre, String telefono, String direccion, String cedula_Cliente) {
        this.ID = ID;
        Nombre = nombre;
        Telefono = telefono;
        Direccion = direccion;
        Cedula_Cliente = cedula_Cliente;
    }

    public ContentValues toContentValues() {

        ContentValues values = new ContentValues();

        values.put(Clientes_VisitasConstract.Clientes_VisitasEntry.ID, ID);
        values.put(Clientes_VisitasConstract.Clientes_VisitasEntry.Nombre, Nombre);
        values.put(Clientes_VisitasConstract.Clientes_VisitasEntry.Telefono, Telefono);
        values.put(Clientes_VisitasConstract.Clientes_VisitasEntry.Direccion, Direccion);
        values.put(Clientes_VisitasConstract.Clientes_VisitasEntry.Cedula_Cliente, Cedula_Cliente);

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

    public String getCedula_Cliente() {
        return Cedula_Cliente;
    }

    public void setCedula_Cliente(String cedula_Cliente) {
        Cedula_Cliente = cedula_Cliente;
    }
}
