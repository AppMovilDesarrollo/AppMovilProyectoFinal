package cr.afinal.proyecto.proyectofinal;

import java.util.List;
import java.util.LinkedList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Andres on 6/8/2017.
 */

public class MySQLiteHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "ClientesDB";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create clientes table
        String CREATE_CLIENTES_TABLE = "CREATE TABLE clientes ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "cedula INTEGET, "+
                "nombre TEXT, "+
                "telefono INTEGER, "+
                "direccion TEXT )";

        // create clientes table
        db.execSQL(CREATE_CLIENTES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older clientes table if existed
        db.execSQL("DROP TABLE IF EXISTS clientes");

        // create fresh clientes table
        this.onCreate(db);
    }

    // Clientes table name
    private static final String TABLE_CLIENTES = "clientes";

    // Clientes Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_CEDULA = "cedula";
    private static final String KEY_NOMBRE = "nombre";
    private static final String KEY_TELEFONO = "telefono";
    private static final String KEY_DIRECCION = "direccion";

    private static final String[] COLUMNS = {KEY_ID, KEY_CEDULA,KEY_NOMBRE,KEY_TELEFONO,KEY_DIRECCION};


    public void addCliente(Cliente cliente){
        //for logging
        Log.d("addCliente", cliente.toString());

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_CEDULA, cliente.getCedula()); // get Cedula
        values.put(KEY_NOMBRE, cliente.getNombre()); // get Nombre
        values.put(KEY_TELEFONO, cliente.getTelefono()); // get Telefono
        values.put(KEY_TELEFONO, cliente.getDireccion()); // get Direccion

        // 3. insert
        db.insert(TABLE_CLIENTES, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();
    }

    public Cliente getCliente(int id){

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_CLIENTES, // a. table
                        COLUMNS, // b. column names
                        " cedula = ?", // c. selections
                        new String[] { String.valueOf(id) }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build clientes object
        Cliente cliente = new Cliente();
        cliente.setCedula(Integer.parseInt(cursor.getString(0)));
        cliente.setNombre(cursor.getString(1));
        cliente.setTelefono(Integer.parseInt(cursor.getString(2)));
        cliente.setDireccion(cursor.getString(3));

        //log
        Log.d("getCliente("+id+")", cliente.toString());

        // 5. return clientes
        return cliente;
    }

    public List<Cliente> getAllClientes() {
        List<Cliente> clientes = new LinkedList<Cliente>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_CLIENTES;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build clientes and add it to list
        Cliente cliente = null;
        if (cursor.moveToFirst()) {
            do {
                cliente = new Cliente();
                cliente.setID(Integer.parseInt(cursor.getString(0)));
                cliente.setCedula(Integer.parseInt(cursor.getString(1)));
                cliente.setNombre(cursor.getString(2));
                cliente.setTelefono(Integer.parseInt(cursor.getString(3)));
                cliente.setDireccion(cursor.getString(4));

                // Add clientes to clientes
                clientes.add(cliente);
            } while (cursor.moveToNext());
        }

        Log.d("getAllClientes()", clientes.toString());

        // return books
        return clientes;
    }

    public int updateCliente(Cliente cliente) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("cedula", cliente.getCedula()); // get Cedula
        values.put("nombre", cliente.getNombre()); // get Nombre
        values.put("telefono", cliente.getTelefono()); // get Telefono
        values.put("Direccion", cliente.getDireccion()); // get Direccion

        // 3. updating row
        int i = db.update(TABLE_CLIENTES, //table
                values, // column/value
                KEY_ID+" = ?", // selections
                new String[] { String.valueOf(cliente.getId()) }); //selection args

        // 4. close
        db.close();

        return i;

    }

    public void deleteCliente(Cliente cliente) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_CLIENTES, //table name
                KEY_ID+" = ?",  // selections
                new String[] { String.valueOf(cliente.getId()) }); //selections args

        // 3. close
        db.close();

        //log
        Log.d("deleteCliente", cliente.toString());

    }
}
