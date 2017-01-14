package utilitarios;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Jhordy on 13/01/2017.
 */

public class Mensaje {

    public static final void mostrarMensajeCorto(Context context, String mensaje) {
        Toast toast = Toast.makeText(context,mensaje,Toast.LENGTH_SHORT);
        toast.show();
    }

    public static final void mostrarMensajeLargo(Context context, String mensaje) {
        Toast toast = Toast.makeText(context,mensaje,Toast.LENGTH_LONG);
        toast.show();
    }

}
