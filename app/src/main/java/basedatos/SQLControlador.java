package basedatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Jhordy on 13/01/2017.
 */

public class SQLControlador {

    private DBHelper dbhelper;
    private Context ourcontext;
    private SQLiteDatabase database;

    public SQLControlador(Context c) {
        ourcontext = c;
    }

    public SQLControlador abrirBaseDeDatos() throws SQLException {
        dbhelper = new DBHelper(ourcontext);
        database = dbhelper.getWritableDatabase();
        return this;
    }

    public void cerrar() {
        dbhelper.close();
    }

    public long insertarDatos(String name, String precio) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.PRODUCTO_NOMBRE, name);
        contentValues.put(DBHelper.PRODUCTO_PRECIO, precio);
        return database.insert(DBHelper.TABLE_PRODUCTO, null, contentValues);
    }

    public Cursor leerDatos() {
        String columnas[] = {DBHelper.PRODUCTO_ID,DBHelper.PRODUCTO_NOMBRE,DBHelper.PRODUCTO_PRECIO};
        Cursor cursor = database.query(DBHelper.TABLE_PRODUCTO,columnas,null,null,null,null,null);
        return cursor;
    }

    public int actualizarDatos(long productoID, String newNombre, String newPrecio) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.PRODUCTO_NOMBRE, newNombre);
        contentValues.put(DBHelper.PRODUCTO_PRECIO, newPrecio);
        int i = database.update(DBHelper.TABLE_PRODUCTO, contentValues,
                DBHelper.PRODUCTO_ID + " = " + productoID, null);
        return i;
    }

    public void deleteData(long productoID) {
        database.delete(DBHelper.TABLE_PRODUCTO, DBHelper.PRODUCTO_ID + "="
                + productoID, null);
    }


}
