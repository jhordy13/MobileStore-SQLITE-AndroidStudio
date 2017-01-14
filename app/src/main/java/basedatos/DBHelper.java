package basedatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jhordy on 13/01/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String TABLE_PRODUCTO = "producto";
    public static final String PRODUCTO_ID = "productoid";
    public static final String PRODUCTO_NOMBRE = "nombre";
    public static final String PRODUCTO_PRECIO = "precio";

    static final String DB_NAME = "BodegaBDV2";
    static final int DB_VERSION = 1;

    // Información de la base de datos
    private static final String CREATE_TABLE = "CREATE TABLE "
            + TABLE_PRODUCTO + "(" + PRODUCTO_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PRODUCTO_NOMBRE + " TEXT NOT NULL, " +
              PRODUCTO_PRECIO + " REAL NOT NULL )";


    public DBHelper(Context context) {
        super(context, DB_NAME, null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTO);
        onCreate(sqLiteDatabase);
    }
}
