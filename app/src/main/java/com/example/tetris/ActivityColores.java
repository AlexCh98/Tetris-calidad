package com.example.tetris;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ActivityColores extends AppCompatActivity {

    private List<String> listaPaletas;
    private int numeroPaleta = 0;
    private ImageView mostrarPaleta;

    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colores);

        this.numeroPaleta =  getIntent().getIntExtra("paleta",0);

        this.mostrarPaleta = findViewById(R.id.vistaModo);
        this.mostrarPaleta.setImageResource(R.drawable.multicolor);

        this.iniciarListaPaletas();

    }

    private void iniciarListaPaletas(){
        this.listaPaletas = new ArrayList<>();
        this.listaPaletas.add("Original");
        this.listaPaletas.add("Roja");
        this.listaPaletas.add("Azul");
        this.listaPaletas.add("Verde");
        this.listaPaletas.add("Amarillo");
        this.listaPaletas.add("Naranja");
        this.listaPaletas.add("Marrón");
    }

    public void cambiarImagen(){
        switch (this.numeroPaleta){
            case 0:
                this.mostrarPaleta.setImageResource(R.drawable.multicolor);
                break;
            case 1:
                this.mostrarPaleta.setImageResource(R.drawable.rojo);
                break;
            case 2:
                this.mostrarPaleta.setImageResource(R.drawable.azul);
                break;
            case 3:
                this.mostrarPaleta.setImageResource(R.drawable.verde);
                break;
            case 4:
                this.mostrarPaleta.setImageResource(R.drawable.amarillo);
                break;
            case 5:
                this.mostrarPaleta.setImageResource(R.drawable.naranja);
                break;
            case 6:
                this.mostrarPaleta.setImageResource(R.drawable.marron);
                break;
            default:
                this.mostrarPaleta.setImageResource(R.drawable.multicolor);
        }
    }

    public void anterior(View view){
        if(this.numeroPaleta>0){
            this.numeroPaleta--;
        }else{
            this.numeroPaleta = this.listaPaletas.size();
        }

        this.cambiarImagen();
    }

    public void siguiente(View view){
         if(this.numeroPaleta < listaPaletas.size()){
             this.numeroPaleta++;
         }else{
             this.numeroPaleta = 0;
         }
        this.cambiarImagen();
    }

    public void cambiarMain(View view){
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("paleta",numeroPaleta);
        startActivity(intent);
    }


}
