package com.app.afinal.proyecto.proyectofinalapp.Formulario_Completo;

import android.text.method.PasswordTransformationMethod;
import android.view.View;

/**
 * Created by Jeison-UTN on 23/08/2017.
 */

public class MascaraTarjeta extends PasswordTransformationMethod {
    @Override
    public CharSequence getTransformation(CharSequence source, View view) {
        return new PasswordCharSequence(source);
    }

    private class PasswordCharSequence implements CharSequence {
        private CharSequence mSource;

        public PasswordCharSequence(CharSequence source) {
            mSource = source;
        }

        public char charAt(int index) {
            char caracter;
            switch (index) {
                default:
                    if (index < 12)
                        return '*';
                    else
                        caracter = mSource.charAt(index);
                    break;
            }
            return caracter;
        }

        public int length() {
            return mSource.length();
        }

        public CharSequence subSequence(int start, int end) {
            return mSource.subSequence(start, end);
        }

    }
}