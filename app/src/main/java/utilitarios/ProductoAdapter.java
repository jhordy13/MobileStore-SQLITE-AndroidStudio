package utilitarios;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.delaguila.mobilestore_v2.R;

import java.util.List;

import entidad.Producto;

/**
 * Created by Jhordy on 13/01/2017.
 */

public class ProductoAdapter extends BaseAdapter{

    protected Activity activity;
    protected List<Producto> productos;

    public ProductoAdapter(Activity activity, List<Producto> productos) {
        this.activity = activity;
        this.productos = productos;
    }

    @Override
    public int getCount() {
        return productos.size();
    }

    @Override
    public Object getItem(int i) {
        return productos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return productos.get(i).getProductoid();
    }

    @Override
    public View getView(int posicion, View view, ViewGroup viewGroup) {
        View vi = view;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater)
                    activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vi = inflater.inflate(R.layout.items_productos,null);
        }

        Producto producto = productos.get(posicion);

        TextView productoid = (TextView) vi.findViewById(R.id.tvProductoid);
        productoid.setText(Long.toString(producto.getProductoid()));

        TextView nombre = (TextView) vi.findViewById(R.id.tvNombre);
        nombre.setText(producto.getNombre());

        TextView precio = (TextView) vi.findViewById(R.id.tvPrecio);
        precio.setText(producto.getPrecio());

        return vi;
    }
}
