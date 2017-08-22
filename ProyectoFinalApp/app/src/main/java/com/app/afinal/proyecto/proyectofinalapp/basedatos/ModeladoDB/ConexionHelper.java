package com.app.afinal.proyecto.proyectofinalapp.basedatos.ModeladoDB;

import java.util.List;
import java.util.LinkedList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.app.afinal.proyecto.proyectofinalapp.basedatos.Clientes;
import com.app.afinal.proyecto.proyectofinalapp.basedatos.Tarjetas;


/**
 * Created by Andres on 6/8/2017.
 */

public class ConexionHelper extends SQLiteOpenHelper {


   // private static String myPath = "/data/user/0/com.app.afinal.proyecto.proyectofinalapp/databases/proyectofinal.db";
    //private static String DB_NAME = "proyectofinal";
    //private SQLiteDatabase myDataBase;
    //private final Context myContext;

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "proyectofinal.db";

    public ConexionHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TarjetasConstract.TarjetasEntry.TABLE_NAME + "("
                + TarjetasConstract.TarjetasEntry.ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
                + TarjetasConstract.TarjetasEntry.Cedula_Cliente + " TEXT NOT NULL,"
                + TarjetasConstract.TarjetasEntry.NumeroTarjeta + " TEXT NOT NULL,"
                + TarjetasConstract.TarjetasEntry.FechaVencimiento + " TEXT NOT NULL,"
                + TarjetasConstract.TarjetasEntry.Monto + " NUMERIC NOT NULL,"
                + TarjetasConstract.TarjetasEntry.TipoTarjeta + " INTEGER NOT NULL)");


        db.execSQL("CREATE TABLE " + ClientesConstract.ClientesEntry.TABLE_NAME + " ("
                + ClientesConstract.ClientesEntry.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ClientesConstract.ClientesEntry.Nombre + " TEXT NOT NULL,"
                + ClientesConstract.ClientesEntry.Cedula_Cliente + " NUMERIC NOT NULL UNIQUE,"
                + ClientesConstract.ClientesEntry.LugarTrabajo + " TEXT,"
                + ClientesConstract.ClientesEntry.Salario + " NUMERIC DEFAULT 0,"
                + ClientesConstract.ClientesEntry.Telefono + " TEXT NOT NULL,"
                + ClientesConstract.ClientesEntry.Direccion + " TEXT NOT NULL,"
                + ClientesConstract.ClientesEntry.Fotografia + " TEXT)");


        createTarjetas(db);
        createClientes(db);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + ClientesConstract.ClientesEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TarjetasConstract.TarjetasEntry.TABLE_NAME);

        this.onCreate(db);
    }


    public void createClientes(SQLiteDatabase db) {

        Clientes c1 = new Clientes();
        c1.setID(1);
        c1.setCedula_Cliente("604150516");
        c1.setSalario(1000000);
        c1.setLugarTrabajo("Grupo Mutual Alajuela");
        c1.setFotografia("foto1.jpg");
        c1.setNombre("Jeison Andres Cespedes Morales");
        c1.setTelefono("+50685978859");
        c1.setDireccion("Heredia, Heredia, Corazon de Jesus");
        c1.setCedula_Cliente("604150516");


        Clientes c2 = new Clientes();
        c2.setID(2);
        c2.setCedula_Cliente("604110437");
        c2.setSalario(50000);
        c2.setLugarTrabajo("Estudiante");
        c2.setFotografia("foto2.jpg");
        c2.setNombre("Yensy Vannessa Cespedes Morales");
        c2.setTelefono("+50683735557");
        c2.setDireccion("Heredia, Heredia, Corazon de Jesus");
        c2.setCedula_Cliente("604110437");


        db.insert(
                ClientesConstract.ClientesEntry.TABLE_NAME,
                null,
                c1.toContentValues());


        db.insert(
                ClientesConstract.ClientesEntry.TABLE_NAME,
                null,
                c2.toContentValues());

    }

    public void createTarjetas(SQLiteDatabase db) {

        Tarjetas tarjetas1 = new Tarjetas();
        tarjetas1.setID(1);
        tarjetas1.setCedula_Cliente("604150516");
        tarjetas1.setNumeroTarjeta("4152779504604072");
        tarjetas1.setFechaVencimiento("07/12");
        tarjetas1.setTipoTarjeta(1);

        Tarjetas tarjetas2 = new Tarjetas();
        tarjetas2.setID(2);
        tarjetas2.setCedula_Cliente("604110437");
        tarjetas2.setNumeroTarjeta("4152046077954040");
        tarjetas2.setFechaVencimiento("07/12");
        tarjetas2.setTipoTarjeta(1);

        db.insert(
                TarjetasConstract.TarjetasEntry.TABLE_NAME,
                null,
                tarjetas1.toContentValues());

        db.insert(
                TarjetasConstract.TarjetasEntry.TABLE_NAME,
                null,
                tarjetas2.toContentValues());


    }

    public long insertClientes(Clientes clientes) {

        SQLiteDatabase db = this.getWritableDatabase();

        return db.insert(
                ClientesConstract.ClientesEntry.TABLE_NAME,
                null,
                clientes.toContentValues());


    }

    public long updateClientes(Clientes clientes) {

        SQLiteDatabase db = this.getWritableDatabase();

        // 3. updating row
        return db.update(
                ClientesConstract.ClientesEntry.TABLE_NAME,
                clientes.toContentValues(),
                ClientesConstract.ClientesEntry.ID + " = ?", // selections
                new String[]{String.valueOf(clientes.getID())}); //selection args



    }

    public Cursor allClientes() {
        return getReadableDatabase()
                .query(
                        ClientesConstract.ClientesEntry.TABLE_NAME,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
    }

    public Cursor clientesById(String clienteID) {
        Cursor c = getReadableDatabase().query(
                ClientesConstract.ClientesEntry.TABLE_NAME,
                null,
                ClientesConstract.ClientesEntry.ID + " LIKE ?",
                new String[]{clienteID},
                null,
                null,
                null);
        return c;
    }


    public long insertTarjetas(Tarjetas tarjetas) {


        SQLiteDatabase db = this.getWritableDatabase();


       return db.insert(
                TarjetasConstract.TarjetasEntry.TABLE_NAME,
                null,
                tarjetas.toContentValues());


    }

    public long updateTarjetas(Tarjetas tarjetas) {

        SQLiteDatabase db = this.getWritableDatabase();

       return db.update(
                TarjetasConstract.TarjetasEntry.TABLE_NAME,
                null,
                TarjetasConstract.TarjetasEntry.ID + " = ?", // selections
                new String[]{String.valueOf(tarjetas.getID())}); //selection args

    }


    public Cursor allTarjetas() {
        return getReadableDatabase()
                .query(
                        TarjetasConstract.TarjetasEntry.TABLE_NAME,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
    }

    public Cursor tarjetasByCedula(String cedulaCliente) {
        Cursor c = getReadableDatabase().query(
                TarjetasConstract.TarjetasEntry.TABLE_NAME,
                null,
                TarjetasConstract.TarjetasEntry.Cedula_Cliente + " LIKE ?",
                new String[]{cedulaCliente},
                null,
                null,
                null);
        return c;
    }


}
