package cr.afinal.proyecto.proyectofinal.basedatos.ModeladoDB;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.LinkedList;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import cr.afinal.proyecto.proyectofinal.basedatos.Clientes;
import cr.afinal.proyecto.proyectofinal.basedatos.ModeladoDB.ClientesConstract;


/**
 * Created by Andres on 6/8/2017.
 */

public class ClientesHelper extends SQLiteOpenHelper {


    private static String DB_PATH = "cr/afinal/proyecto/proyectofinal/basedatos/";
    private static String DB_NAME = "proyectofinal.db";
    private SQLiteDatabase myDataBase;
    private final Context myContext;

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "proyectofinal.db";

    public ClientesHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        myContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            createDataBase();
            openDataBase();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        this.onCreate(db);
    }

    public void insertClientes(SQLiteDatabase db, Clientes clientes) {

        db.insert(
                ClientesConstract.ClientesEntry.TABLE_NAME,
                null,
                clientes.toContentValues());

        db.close();
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
        db.close();

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
        db.close();

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
                cliente.setFotografia(Integer.parseInt(cursor.getString(4)));

                // Add clientes to clientes
                clienteList.add(cliente);
            } while (cursor.moveToNext());
        }
        // return books
        return clienteList;
    }


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
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

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

}


//    public Clientes getCliente(int id){
//
//        // 1. get reference to readable DB
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        // 2. build query
//        Cursor cursor =
//                db.query(TABLE_CLIENTES, // a. table
//                        COLUMNS, // b. column names
//                        " cedula = ?", // c. selections
//                        new String[] { String.valueOf(id) }, // d. selections args
//                        null, // e. group by
//                        null, // f. having
//                        null, // g. order by
//                        null); // h. limit
//
//        // 3. if we got results get the first one
//        if (cursor != null)
//            cursor.moveToFirst();
//
//        // 4. build clientes object
//        Cliente cliente = new Cliente();
//        cliente.setCedula(Integer.parseInt(cursor.getString(0)));
//        cliente.setNombre(cursor.getString(1));
//        cliente.setTelefono(Integer.parseInt(cursor.getString(2)));
//        cliente.setDireccion(cursor.getString(3));
//
//        //log
//        Log.d("getCliente("+id+")", cliente.toString());
//
//        // 5. return clientes
//        return cliente;
//    }




