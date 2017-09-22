package net.simplifiedcoding.androidmysqlsync;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Belal on 1/27/2017.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    //Constants for Database name, table name, and column names
    public static final String DB_NAME = "NamesDB";
    public static final String TABLE_NAME = "names";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_STATUS = "status";

    //database version
    private static final int DB_VERSION = 1;

    //Constructor
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    //creating the database
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + "CLIENTE"
                + "(" + COLUMN_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + "NOMBRE" +
                " VARCHAR, DESCUENTO VARCHAR,RUTA VARCHAR, RAZON VARCHAR,CREDITO VARCHAR," +
                "SUB_CANAL VARCHAR,LDESCUENTO VARCHAR,LPRECIOA,LPRECIOC VARCHAR,LAT VARCHAR,LONG VARCHAR,G VARCHAR,"+
                "FORMAPAGO VARCHAR,PUEDOFACTURAR VARCHAR,TIPOFACTURA VARCHAR," + COLUMN_STATUS +
                " TINYINT);";
        db.execSQL(sql);


         sql = "CREATE TABLE " + TABLE_NAME
                + "(" + COLUMN_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME +
                " VARCHAR, " + COLUMN_STATUS +
                " TINYINT);";
        db.execSQL(sql);

       sql = "CREATE TABLE " + "GENERAL"
                + "(" + COLUMN_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + "SYSTEMRULE" +
                " VARCHAR,PRECIO_LEY VARCHAR,PRECIO_ANTIGUO VARCHAR,URL_CLIENTES VARCHAR,URL_FAC VARCHAR,URL_FAC_BCK VARCHAR);";
        db.execSQL(sql);






    }

    //upgrading the database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS persons";
        db.execSQL(sql);
        onCreate(db);
    }



    /*
    * This method is taking two arguments
    * first one is the name that is to be saved
    * second one is the status
    * 0 means the name is synced with the server
    * 1 means the name is not synced with the server
    * */
    public boolean addName(String name, int status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_STATUS, status);


        db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return true;
    }
    public boolean addCliente(String name, String descuento, String ruta, String Razon,String credito,String sub_canal,String ldescuento,
            String lprecioa,String lprecioc,String LAT, String LONG, String G, String FormaPago, String puedoFacturar,String tipoFactura,
                              int status) {


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("NOMBRE", name);
        contentValues.put("DESCUENTO", descuento);
        contentValues.put("RUTA", ruta);
        contentValues.put("RAZON", Razon);
        contentValues.put("CREDITO",credito);
        contentValues.put("SUB_CANAL", sub_canal);
        contentValues.put("LDESCUENTO",ldescuento);
        contentValues.put("LPRECIOA",lprecioa);
        contentValues.put("LPRECIOC",lprecioc);
        contentValues.put("LAT",LAT);
        contentValues.put("LONG",LONG);
        contentValues.put("G",G);
        contentValues.put("FORMAPAGO",FormaPago);
        contentValues.put("PUEDOFACTURAR",puedoFacturar);
        contentValues.put("TIPOFACTURA",tipoFactura);

        contentValues.put(COLUMN_STATUS, status);


        db.insert("CLIENTE", null, contentValues);
        db.close();
        return true;
    }
    public boolean updateClienteStatus(int id,String name, String descuento, String ruta, String Razon,String credito,String sub_canal,String ldescuento,
                                       String lprecioa,String lprecioc,String LAT, String LONG, String G, String FormaPago, String puedoFacturar,String tipoFactura,
                                       int status) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NOMBRE", name);
        contentValues.put("DESCUENTO", descuento);
        contentValues.put("RUTA", ruta);
        contentValues.put("RAZON", Razon);
        contentValues.put("CREDITO",credito);
        contentValues.put("SUB_CANAL", sub_canal);
        contentValues.put("LDESCUENTO",ldescuento);
        contentValues.put("LPRECIOA",lprecioa);
        contentValues.put("LPRECIOC",lprecioc);
        contentValues.put("LAT",LAT);
        contentValues.put("LONG",LONG);
        contentValues.put("G",G);
        contentValues.put("FORMAPAGO",FormaPago);
        contentValues.put("PUEDOFACTURAR",puedoFacturar);
        contentValues.put("TIPOFACTURA",tipoFactura);
        contentValues.put(COLUMN_STATUS, status);

        db.update("CLIENTE", contentValues, COLUMN_ID + "=" + id, null);
        db.close();
        return true;
    }
    public void DeleteCliente(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        //String sql = "DELETE * FROM " + TABLE_NAME;
        String sql = "DELETE * FROM " + "CLIENTE" + " WHERE id = "+id+";";
        db.execSQL(sql);
        //db.rawQuery(sql,null);

    }
    public Cursor getNombre() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + "CLIENTE" + " ORDER BY " + "NOMBRE" + " ASC;";
        Cursor c = db.rawQuery(sql, null);
        android.util.Log.d("dat01",c.toString());
        return c;
    }
    public void DeleteClients() {
        SQLiteDatabase db = this.getReadableDatabase();

        db.execSQL("delete from "+ "CLIENTE");
        //db.rawQuery(sql,null);

    }
    public Cursor getGpsClientes(String GPS) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + "CLIENTE" + " WHERE G = '"+GPS+"';";
        Cursor c = db.rawQuery(sql, null);
        //android.util.Log.d("dat01",c.toString());
        return c;
    }
    public Cursor getUnsyncedClientes() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + "CLIENTE" + " WHERE " + COLUMN_STATUS + " = 0;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }

    public Cursor getCliente(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + "CLIENTE" + " WHERE id = "+id+";";
        Cursor c = db.rawQuery(sql, null);
        //android.util.Log.d("dat01",c.toString());
        return c;
    }

    /*
    * This method taking two arguments
    * first one is the id of the name for which
    * we have to update the sync status
    * and the second one is the status that will be changed
    * */
    public boolean updateNameStatus(int id, int status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_STATUS, status);
        db.update(TABLE_NAME, contentValues, COLUMN_ID + "=" + id, null);
        db.close();
        return true;
    }



    /*
    * this method will give us all the name stored in sqlite
    * */

    public void Delete() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "DELETE * FROM " + TABLE_NAME;
        db.execSQL("delete from "+ TABLE_NAME);
        //db.rawQuery(sql,null);

    }

    public Cursor getNames() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + COLUMN_ID + " DESC;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }



    /*
    * this method is for getting all the unsynced name
    * so that we can sync it with database
    * */
    public Cursor getUnsyncedNames() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_STATUS + " = 0;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }


    public Cursor getNerdNames() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME + ";";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }
}
