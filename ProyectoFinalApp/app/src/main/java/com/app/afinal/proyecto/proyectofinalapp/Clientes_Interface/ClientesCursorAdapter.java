package com.app.afinal.proyecto.proyectofinalapp.Clientes_Interface;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.afinal.proyecto.proyectofinalapp.R;
import com.app.afinal.proyecto.proyectofinalapp.basedatos.Clientes;
import com.app.afinal.proyecto.proyectofinalapp.basedatos.ModeladoDB.ClientesConstract;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


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

        // Referencias UI.
        TextView lNombreCliente = (TextView) view.findViewById(R.id.lNombreCliente);
        final ImageView imgImagenCliente = (ImageView) view.findViewById(R.id.imgImagenCliente);


        // Get valores.
        String name = cursor.getString(cursor.getColumnIndex(ClientesConstract.ClientesEntry.Nombre));
        String foto = cursor.getString(cursor.getColumnIndex(ClientesConstract.ClientesEntry.Fotografia));


        if (foto == null) {
            foto = "foto1.jpg";
        }

        if (foto.equalsIgnoreCase("foto1.jpg")) {

            Bitmap bmp = null;
            try {
                InputStream inputStream = context.getAssets().open(foto);
                bmp = BitmapFactory.decodeStream(inputStream);
            } catch (IOException io) {
                io.printStackTrace();
            }

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();

            try {
                FileOutputStream outputStream = context.openFileOutput(foto, Context.MODE_PRIVATE);
                outputStream.write(byteArray);
                outputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }


            Bitmap bitmap = null;

            try {
                FileInputStream fileInputStream =
                        new FileInputStream(context.getFilesDir().getPath() + "/" + foto);
                bitmap = BitmapFactory.decodeStream(fileInputStream);
            } catch (IOException io) {
                io.printStackTrace();
            }

            imgImagenCliente.setImageBitmap(bitmap);

        } else {

            Bitmap bitmap = null;

            File archivo = new File(Environment.getExternalStorageDirectory(), foto);
            try {
                FileInputStream fileInputStream =
                        new FileInputStream(archivo);
                bitmap = BitmapFactory.decodeStream(fileInputStream);
            } catch (IOException io) {
                io.printStackTrace();
            }

            imgImagenCliente.setImageBitmap(bitmap);


        }


        // Setup.
        lNombreCliente.setText(name);


    }
}
