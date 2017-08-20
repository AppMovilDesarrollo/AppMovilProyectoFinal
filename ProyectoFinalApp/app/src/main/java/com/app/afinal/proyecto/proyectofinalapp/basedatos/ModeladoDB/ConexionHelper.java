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
        // myContext = context;
        // try {
        //   createDataBase();
        // openDataBase();
        //} catch (IOException e) {
        // TODO Auto-generated catch block
        //  e.printStackTrace();
        //}
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
        c1.setFotografia("Foto1.jpg");
        c1.setNombre("Jeison Andres Cespedes Morales");
        c1.setTelefono("+50685978859");
        c1.setDireccion("Heredia, Heredia, Corazon de Jesus");
        c1.setCedula_Cliente("604150516");


        Clientes c2 = new Clientes();
        c2.setID(2);
        c2.setCedula_Cliente("604110437");
        c2.setSalario(50000);
        c2.setLugarTrabajo("Estudiante");
        c2.setFotografia("Foto2.jpg");
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

    public void insertClientes(Clientes clientes) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(
                ClientesConstract.ClientesEntry.TABLE_NAME,
                null,
                clientes.toContentValues());


    }

    public boolean updateClientes(SQLiteDatabase db, Clientes clientes) {

        boolean update = false;

        // 3. updating row
        int i = db.update(
                ClientesConstract.ClientesEntry.TABLE_NAME,
                null,
                ClientesConstract.ClientesEntry.ID + " = ?", // selections
                new String[]{String.valueOf(clientes.getID())}); //selection args

        // 4. close


        if (i != 0) {
            update = true;
        }

        return update;

    }

    public void deleteClientes(SQLiteDatabase db, Clientes clientes) {

        // 2. delete
        db.delete(
                ClientesConstract.ClientesEntry.TABLE_NAME,
                ClientesConstract.ClientesEntry.ID + " = ?", // selections
                new String[]{String.valueOf(clientes.getID())}); //selections args

        // 3. close


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

    public List<Clientes> getAllClientes(String clientesS) {

        List<Clientes> clienteList = new LinkedList<Clientes>();

        // 1. build the query
        String query = "SELECT  * FROM " + clientesS;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build clientes and add it to list
        Clientes cliente = null;
        if (cursor.moveToFirst()) {
            do {
                cliente = new Clientes();
                cliente.setID(Integer.parseInt(cursor.getString(0)));
                cliente.setCedula_Cliente(cursor.getString(1));
                cliente.setSalario(Float.parseFloat(cursor.getString(2)));
                cliente.setLugarTrabajo(cursor.getString(3));
                cliente.setFotografia(cursor.getString(4));

                // Add clientes to clientes
                clienteList.add(cliente);
            } while (cursor.moveToNext());
        }
        // return books
        return clienteList;
    }


    public void insertTarjetas(Tarjetas tarjetas) {


        SQLiteDatabase db = this.getWritableDatabase();


        db.insert(
                TarjetasConstract.TarjetasEntry.TABLE_NAME,
                null,
                tarjetas.toContentValues());


    }

    public boolean updateTarjetas(SQLiteDatabase db, Tarjetas tarjetas) {

        boolean update = false;

        // 3. updating row
        int i = db.update(
                TarjetasConstract.TarjetasEntry.TABLE_NAME,
                null,
                TarjetasConstract.TarjetasEntry.ID + " = ?", // selections
                new String[]{String.valueOf(tarjetas.getID())}); //selection args


        if (i != 0) {
            update = true;
        }

        return update;

    }

    public void deleteTarjetas(SQLiteDatabase db, Tarjetas tarjetas) {

        // 2. delete
        db.delete(
                TarjetasConstract.TarjetasEntry.TABLE_NAME,
                TarjetasConstract.TarjetasEntry.ID + " = ?", // selections
                new String[]{String.valueOf(tarjetas.getID())}); //selections args

        // 3. close


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

    public Cursor TarjetasById(String tarjetasID) {
        Cursor c = getReadableDatabase().query(
                TarjetasConstract.TarjetasEntry.TABLE_NAME,
                null,
                TarjetasConstract.TarjetasEntry.ID + " LIKE ?",
                new String[]{tarjetasID},
                null,
                null,
                null);
        return c;
    }

    public List<Tarjetas> getAllTarjetas(String TarjetasS) {

        List<Tarjetas> tarjetasIDList = new LinkedList<Tarjetas>();

        // 1. build the query
        String query = "SELECT  * FROM " + TarjetasS;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build Tarjetas and add it to list
        Tarjetas tarjetas = null;
        if (cursor.moveToFirst()) {
            do {

                tarjetas = new Tarjetas();
                tarjetas.setID(Integer.parseInt(cursor.getString(0)));
                tarjetas.setCedula_Cliente(cursor.getString(1));
                tarjetas.setNumeroTarjeta(cursor.getString(2));
                tarjetas.setFechaVencimiento(cursor.getString(3));
                tarjetas.setTipoTarjeta(Integer.parseInt(cursor.getString(4)));

                // Add Tarjetas to Tarjetas
                tarjetasIDList.add(tarjetas);
            } while (cursor.moveToNext());
        }
        // return books
        return tarjetasIDList;
    }
/*
    public void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();

        if (dbExist) {
// Si existe, no haemos nada!
        } else {
// Llamando a este método se crea la base de datos vacía en la ruta
// por defecto del sistema de nuestra aplicación por lo que
// podremos sobreescribirla con nuestra base de datos.
            this.getReadableDatabase();

            try {

                copyDataBase();

            } catch (IOException e) {

                throw new Error("Error copiando database");
            }
        }
    }

    private boolean checkDataBase() {

        SQLiteDatabase checkDB = null;

        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        } catch (SQLiteException e) {
// Base de datos no creada todavia
        }

        if (checkDB != null) {

            checkDB.close();
        }

        return checkDB != null ? true : false;

    }

    public void openDataBase() throws SQLException {

// Open the database
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);

    }

    @Override
    public synchronized void close() {

        if (myDataBase != null)
            myDataBase.close();

        super.close();
    }

    private void copyDataBase() throws IOException {

        OutputStream databaseOutputStream = new FileOutputStream("" + DB_PATH + DB_NAME);
        InputStream databaseInputStream;

        byte[] buffer = new byte[1024];
        int length;

        databaseInputStream = myContext.getAssets().open("proyectofinal");
        while ((length = databaseInputStream.read(buffer)) > 0) {
            databaseOutputStream.write(buffer);
        }

        databaseInputStream.close();
        databaseOutputStream.flush();
        databaseOutputStream.close();
    }


*/
}