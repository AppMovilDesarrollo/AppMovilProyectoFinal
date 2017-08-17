package com.app.afinal.proyecto.proyectofinalapp.Clientes_Interface;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.app.afinal.proyecto.proyectofinalapp.R;
import com.app.afinal.proyecto.proyectofinalapp.basedatos.ModeladoDB.ClientesConstract;
import com.app.afinal.proyecto.proyectofinalapp.basedatos.ModeladoDB.Clientes_VisitasConstract;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

/**
 * Created by JeCespedes on 17/8/2017.
 */

public class ClientesCursorAdapter extends CursorAdapter {



    SimpleCursorAdapter mClientesAdapter;

    public ClientesCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return inflater.inflate(R.layout.list_item_clientes, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        // Referencias UI.
        TextView nameText = (TextView) view.findViewById(R.id.tv_name);
        final ImageView avatarImage = (ImageView) view.findViewById(R.id.iv_avatar);

        // Get valores.
        String name = cursor.getString(cursor.getColumnIndex(Clientes_VisitasConstract.Clientes_VisitasEntry.Nombre));


        // Setup.
        nameText.setText(name);
        ;

    }
}
