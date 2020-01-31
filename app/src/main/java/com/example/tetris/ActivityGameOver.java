package com.example.tetris;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import static java.lang.Thread.sleep;

public class ActivityGameOver extends Activity {

    private Button tryAgain;
    private Button verRanking;
    private ImageButton volver;

    private Button añadir;
    private EditText editText;
    private TextView puntuacion;
    private int puntuacionAdquirida;
    private final int puntuacionMinima = 250;
    private String nombreArchivo = "Ranking.txt";
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private Bitmap imagen;

    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover);

        this.volver = findViewById(R.id.botonVolver);
        this.tryAgain = findViewById(R.id.botonTryagain);
        this.verRanking = findViewById(R.id.botonVerRanking);
        this.añadir = findViewById(R.id.botonAñadir);
        this.editText = findViewById(R.id.entradaNombre);
        this.puntuacion = findViewById(R.id.textViewPuntuacion);



        this.puntuacionAdquirida = getIntent().getIntExtra("puntuacion",0);
        this.puntuacion.setText(this.puntuacionAdquirida + " puntos");



    }


    public void volverAlMain(View view){
        if(this.puntuacionAdquirida < this.puntuacionMinima){
            finish();

        }
        else{

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

            finish();
        }

    }


    public void insertarNombre(View view) throws InterruptedException {

        this.verRanking.setEnabled(Boolean.TRUE);
        this.añadir.setEnabled(Boolean.FALSE);
        this.editText.setEnabled(Boolean.FALSE);


        dispatchTakePictureIntent();

        //this.tryAgain.setEnabled(Boolean.FALSE);
        //this.volver.setEnabled(Boolean.FALSE);
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            this.imagen = (Bitmap) extras.get("data");
        }
    }


    public void jugarDeNuevo(View view){

        Intent intent;

        switch (getIntent().getStringExtra("modo")) {
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
            default:
                intent = new Intent(this, ActivityClasico.class);
        }

        intent.putExtra("paleta", getIntent().getIntExtra("paleta", 0));
        intent.putExtra("modo", getIntent().getStringExtra("modo"));
        startActivity(intent);

        finish();
    }

    public void verRanking(View view){

        this.verRanking.setEnabled(Boolean.FALSE);
        this.volver.setEnabled(Boolean.TRUE);

        if(this.puntuacionAdquirida<this.puntuacionMinima){

            this.volver.setImageResource(R.drawable.salir);
            this.tryAgain.setEnabled(Boolean.FALSE);

        }else{


            this.tryAgain.setEnabled(Boolean.TRUE);

        }

        Intent intent = new Intent(this, ActivityRanking.class);

        intent.putExtra("imagen",this.imagen);
        intent.putExtra("puntuacion",getIntent().getIntExtra("puntuacion",0));
        intent.putExtra("datoNuevo",true);
        intent.putExtra("nombre",this.editText.getText().toString());
        intent.putExtra("modo",getIntent().getStringExtra("modo"));

        startActivity(intent);

    }
}
