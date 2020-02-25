package com.example.tetris;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;

import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> modosDeJuego;
    private int numeroModo;
    private int paleta;
    private ImageView imagenModo;

    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.imagenModo = (ImageView) findViewById(R.id.vistaModo);
        this.imagenModo.setImageResource(R.drawable.clasico);
        this.paleta = getIntent().getIntExtra("paleta",0);

        this.inicializarListaDeModos();



        checkearPermisos();

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void checkearPermisos(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ) {
            requestPermissions(new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},123);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ) {
            requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},123);
        }
    }

    public void inicializarListaDeModos(){
        this.numeroModo=0;
        this.modosDeJuego = new LinkedList<String>();
        this.modosDeJuego.add("Clasico");
        this.modosDeJuego.add("Piece Madness");
        this.modosDeJuego.add("Color Party");
        this.modosDeJuego.add("50 seconds");
        this.modosDeJuego.add("Horror party");
    }

    public void ejecutarJuego(View view){
        finish();

        Intent intent;

        switch(this.modosDeJuego.get(this.numeroModo)){
            case "Clasico":
                intent = new Intent(this, ActivityClasico.class);
                break;
            case "Piece Madness":
                intent = new Intent(this, ActivityPieceMadness.class);
                break;
            case "Color Party":
                intent = new Intent(this, ActivityColorParty.class);
                break;
            case "50 seconds":
                intent = new Intent(this, Activity50seconds.class);
                break;
            case "Horror party":
                intent = new Intent(this, ActivityHorrorParty.class);
                break;
            default:
                intent = new Intent(this, ActivityClasico.class);
        }


        intent.putExtra("paleta",this.paleta);
        intent.putExtra("modo",this.modosDeJuego.get(this.numeroModo));
        startActivity(intent);
    }

    public void ejecutarOpciones(View view){
        finish();
        Intent intent = new Intent(this, ActivityColores.class);
        intent.putExtra("paleta",this.paleta);
        startActivity(intent);
    }

    public void ejecutarSalir(View view){
        this.finish();
    }

    public void cambiarImagen(){
        switch (this.numeroModo){
            case 0:
                this.imagenModo.setImageResource(R.drawable.clasico);
                break;
            case 1:
                this.imagenModo.setImageResource(R.drawable.piecemadness);
                break;
            case 2:
                this.imagenModo.setImageResource(R.drawable.colorparty);
                break;
            case 3:
                this.imagenModo.setImageResource(R.drawable.cincuentasec);
                break;
            case 4:
                this.imagenModo.setImageResource(R.drawable.pepe3);
                break;
            default:
                this.imagenModo.setImageResource(R.drawable.clasico);
        }
    }

    public void anterior(View view){
        if(this.numeroModo>0){
            this.numeroModo--;
            cambiarImagen();

        }
    }

    public void siguiente(View view){
        if(this.numeroModo<this.modosDeJuego.size()-1){
            this.numeroModo++;
            cambiarImagen();
        }
    }


    public void ejecutarRanking(View view){
        finish();
        Intent intent = new Intent(this, ActivityRanking.class);
        intent.putExtra("datoNuevo",Boolean.FALSE);
        startActivity(intent);
    }

}

