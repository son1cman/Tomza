package net.simplifiedcoding.androidmysqlsync;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Belal on 1/27/2017.
 */

public class NetworkStateChecker extends BroadcastReceiver {

    //context and database helper object
    private Context context;
    private DatabaseHelper db;


    @Override
    public void onReceive(Context context, Intent intent) {

        this.context = context;

        db = new DatabaseHelper(context);

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        //if there is a network
        if (activeNetwork != null) {
            //if connected to wifi or mobile data plan
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI || activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {

                //getting all the unsynced names
                Cursor cursor = db.getUnsyncedNames();
                if (cursor.moveToFirst()) {
                    do {
                        //calling the method to save the unsynced name to MySQL
                        saveName(
                                cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME))
                        );
                    } while (cursor.moveToNext());
                }


                cursor = db.getUnsyncedClientes();
                if (cursor.moveToFirst()) {
                    do {
                        //calling the method to save the unsynced name to MySQL
                        savecliente(
                                cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID)),cursor.getString(cursor.getColumnIndex("CODIGO")),
                                cursor.getString(cursor.getColumnIndex("NOMBRE")),cursor.getString(cursor.getColumnIndex("DESCUENTO")),
                                cursor.getString(cursor.getColumnIndex("RUTA")),cursor.getString(cursor.getColumnIndex("RAZON")),
                                cursor.getString(cursor.getColumnIndex("CREDITO")),cursor.getString(cursor.getColumnIndex("SUB_CANAL")),
                                cursor.getString(cursor.getColumnIndex("LDESCUENTO")),cursor.getString(cursor.getColumnIndex("LPRECIOA")),
                                cursor.getString(cursor.getColumnIndex("LPRECIOC")),cursor.getString(cursor.getColumnIndex("LAT")),
                                cursor.getString(cursor.getColumnIndex("LONG")),cursor.getString(cursor.getColumnIndex("G")),
                                cursor.getString(cursor.getColumnIndex("FORMAPAGO")),cursor.getString(cursor.getColumnIndex("PUEDOFACTURAR")),
                                cursor.getString(cursor.getColumnIndex("TIPOFACTURA"))

                        );
                    } while (cursor.moveToNext());
                }
            }
        }
    }

    public void ForceSync(){
        //getting all the unsynced names
        Cursor cursor = db.getUnsyncedNames();
        if (cursor.moveToFirst()) {
            do {
                //calling the method to save the unsynced name to MySQL
                saveName(
                        cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME))
                );
            } while (cursor.moveToNext());
        }


        cursor = db.getUnsyncedClientes();
        if (cursor.moveToFirst()) {
            do {
                //calling the method to save the unsynced name to MySQL
                savecliente(
                        cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID)),cursor.getString(cursor.getColumnIndex("CODIGO")),
                        cursor.getString(cursor.getColumnIndex("NOMBRE")),cursor.getString(cursor.getColumnIndex("DESCUENTO")),
                        cursor.getString(cursor.getColumnIndex("RUTA")),cursor.getString(cursor.getColumnIndex("RAZON")),
                        cursor.getString(cursor.getColumnIndex("CREDITO")),cursor.getString(cursor.getColumnIndex("SUB_CANAL")),
                        cursor.getString(cursor.getColumnIndex("LDESCUENTO")),cursor.getString(cursor.getColumnIndex("LPRECIOA")),
                        cursor.getString(cursor.getColumnIndex("LPRECIOC")),cursor.getString(cursor.getColumnIndex("LAT")),
                        cursor.getString(cursor.getColumnIndex("LONG")),cursor.getString(cursor.getColumnIndex("G")),
                        cursor.getString(cursor.getColumnIndex("FORMAPAGO")),cursor.getString(cursor.getColumnIndex("PUEDOFACTURAR")),
                        cursor.getString(cursor.getColumnIndex("TIPOFACTURA"))

                );
            } while (cursor.moveToNext());
        }
    }

    /*
    * method taking two arguments
    * name that is to be saved and id of the name from SQLite
    * if the name is successfully sent
    * we will update the status as synced in SQLite
    * */
    //FACTURASSSSSSSSSSSSSSSSSSS
    private void saveName(final int id, final String name) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, MainActivity.URL_SAVE_NAME,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                //updating the status in sqlite
                                db.updateNameStatus(id, MainActivity.NAME_SYNCED_WITH_SERVER);

                                //sending the broadcast to refresh the list
                                context.sendBroadcast(new Intent(MainActivity.DATA_SAVED_BROADCAST));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("tipo", "f");
                return params;
            }
        };

        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }
//CLIENTESSSSSSSSSSSS
    private void savecliente(final int id, final String codigo, final String name, final String descuento, final String ruta, final String Razon,final String credito,final String sub_canal,final String ldescuento,
                        final String lprecioa,final String lprecioc,final String LAT,final String LONG,final String G,final String FormaPago,final String puedoFacturar,final String tipoFactura
    ) {


        final String _name = name+","+descuento+","+ruta+","+Razon+","+credito+","+sub_canal+","+ldescuento+
                ","+lprecioa+","+lprecioc+","+LAT+","+LONG+","+G+","+FormaPago+","+puedoFacturar+","+tipoFactura;

        StringRequest stringRequest = new StringRequest(Request.Method.POST,  MainActivity.URL_GET_CLIENTE ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                //if there is a success
                                //storing the name to sqlite with status synced
                               db.updateClienteStatus (id,codigo,name,descuento,ruta,Razon,credito,sub_canal,ldescuento,lprecioa,lprecioc,LAT,LONG,G,FormaPago,puedoFacturar,tipoFactura, 1);
                            } else {
                                //if there is some error
                                //saving the name to sqlite with status unsynced
                                db.updateClienteStatus (id,codigo,name,descuento,ruta,Razon,credito,sub_canal,ldescuento,lprecioa,lprecioc,LAT,LONG,G,FormaPago,puedoFacturar,tipoFactura,0);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        error.printStackTrace();
                        //Log.e("okas:", Log.getStackTraceString(error) );

                        //on error storing the name to sqlite with status unsynced
                        //saveClienteToLocalStorage(name,descuento,ruta,Razon,credito,sub_canal,ldescuento,lprecioa,lprecioc,LAT,LONG,G,FormaPago,puedoFacturar,tipoFactura, NAME_NOT_SYNCED_WITH_SERVER);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", _name);
                params.put("tipo", "c");
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));



        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);

    }


}
