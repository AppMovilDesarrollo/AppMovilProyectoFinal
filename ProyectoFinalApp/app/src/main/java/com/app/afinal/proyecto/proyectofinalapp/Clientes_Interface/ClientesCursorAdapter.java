package com.app.afinal.proyecto.proyectofinalapp.Clientes_Interface;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.afinal.proyecto.proyectofinalapp.R;
import com.app.afinal.proyecto.proyectofinalapp.basedatos.ModeladoDB.ClientesConstract;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;


/**
 * Created by JeCespedes on 17/8/2017.
 */

public class ClientesCursorAdapter extends CursorAdapter {


    public ClientesCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return inflater.inflate(R.layout.list_item_clientes, viewGroup, false);
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {

        final Context context1 = context;
        // Referencias UI.
        TextView nameText = (TextView) view.findViewById(R.id.tv_name);
        final ImageView iv_avatar = (ImageView) view.findViewById(R.id.iv_avatar);

        // Get valores.
        String name = cursor.getString(cursor.getColumnIndex(ClientesConstract.ClientesEntry.Nombre));
        String foto = cursor.getString(cursor.getColumnIndex(ClientesConstract.ClientesEntry.Fotografia));

        // Setup.
        nameText.setText(name);

        Glide.with(context1).load(Uri.parse("file://android_asset/" + foto)).asBitmap()
                .error(R.color.colorAccent)
                .centerCrop()
                .into(new BitmapImageViewTarget(iv_avatar) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(context1.getResources(), resource);
                        drawable.setCircular(true);
                        iv_avatar.setImageDrawable(drawable);
                    }
                });


    }
}
