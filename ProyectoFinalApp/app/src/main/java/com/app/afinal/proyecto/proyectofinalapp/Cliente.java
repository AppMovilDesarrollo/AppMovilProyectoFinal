package  com.app.afinal.proyecto.proyectofinalapp;

/**
 * Created by Andres on 6/8/2017.
 */

public class Cliente {
    private int id;
    private int cedula;
    private String nombre;
    private String direccion;
    private int telefono;

    public Cliente(){}

    public Cliente(int cedula, String nombre, String direccion, int telefono) {
        super();
        this.cedula = cedula;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    //getters & setters

    @Override
    public String toString() {
        return "Cliente [id=" + id + ", nombre=" + cedula +", nombre=" + nombre
                + ", telefono=" + telefono + ", direccion=" + direccion + "]";
    }


    public String getNombre() {
        return nombre;
    }

    public int getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setID(int id) {
        this.id = id;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getCedula() {
        return cedula;
    }

    public int getId() {
        return id;
    }
}
