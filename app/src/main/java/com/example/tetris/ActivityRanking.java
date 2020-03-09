package com.example.tetris;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ActivityRanking extends Activity {
    private String extensionImagen = "IMG";
    private String rutaFichero = "RankingTetris.txt";
    private static final int apartadosRanking = 10;

    private ImageView imageViewRanking;
    private TextView textViewRankingNombre;
    private TextView textViewRankingModo;
    private TextView textViewRankingPuntuacion;

    private boolean datoNuevo;      /*Si esta a false solo se lee el ranking y si esta a true, se escribe en memoria guardando el dato*/

    private List<DatosRanking> listaDatos;

    private int datoVisible = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        super.onCreate(savedInstanceState);
        this.datoNuevo = getIntent().getBooleanExtra("datoNuevo", false);

        if (!datoNuevo) {
            setContentView(R.layout.activity_ranking);
        }

        this.imageViewRanking = findViewById(R.id.imagenRanking);
        this.textViewRankingNombre = findViewById(R.id.textViewRanking1);
        this.textViewRankingModo = findViewById(R.id.textViewRanking2);
        this.textViewRankingPuntuacion = findViewById(R.id.textViewRanking3);

        listaDatos = new ArrayList<>();
        ranking();
    }

    private void ranking() {
        if (datoNuevo) {
            escribirDatoNuevo(null);
        } else {
            cargarDatos();
            mostrarDatos();
        }
    }

    private String asignarId() {
        String auxId;
        boolean idAsignado = true;
        int intId;

        do {
            intId = (int) (Math.random() * (102) - 2); //Genera un numero aleatorio entre 0 y 99 incluidos

            auxId = intId + this.extensionImagen;

            for (DatosRanking aux : this.listaDatos) {
                if (aux.getId().compareTo(auxId) == 0) {
                    idAsignado = false;
                }
            }

        } while (!idAsignado);
        return auxId;

    }


    private void mostrarDatos() {
        if (this.listaDatos.isEmpty()) {
            this.imageViewRanking.setImageBitmap(null);
            String sinDatos = "Sin datos";
            this.textViewRankingNombre.setText(sinDatos);
            this.textViewRankingModo.setText(sinDatos);
            this.textViewRankingPuntuacion.setText(sinDatos);
        } else {
            String text1 = "";
            String text2 = "";
            String text3 = "";

            text1 = meteEspacios("Nombre", 15) + "\n";
            text2 = meteEspacios("Modo", 10) + "\n";
            text3 = meteEspacios("Puntuacion", 10) + "\n";

            text1 = text1 + meteEspacios(this.listaDatos.get(this.datoVisible).getNombre(), 15) + "\n";
            text2 = text2 + meteEspacios(this.listaDatos.get(this.datoVisible).getModo(), 10) + "\n";
            text3 = text3 + this.listaDatos.get(this.datoVisible).getPuntuacion() + "\n";

            this.textViewRankingNombre.setText(text1);
            this.textViewRankingModo.setText(text2);
            this.textViewRankingPuntuacion.setText(text3);
            this.imageViewRanking.setImageBitmap(this.listaDatos.get(this.datoVisible).getImagen());
        }
    }

    public String meteEspacios(String aux, int espacios) {
        if (aux.length() < espacios) {
            StringBuilder auxBuilder = new StringBuilder(aux);
            for (int i = auxBuilder.length(); i < espacios; i++) {
                auxBuilder.append(" ");
            }
            aux = auxBuilder.toString();
        } else if (aux.length() > espacios) {
            aux = aux.substring(0, espacios - 1);
        }
        aux = aux + " ";
        return aux;
    }

    public void subirRanking() {
        if (!this.listaDatos.isEmpty()) {
            int size = this.listaDatos.size();
            this.datoVisible = (this.datoVisible + size - 1) % size;
            mostrarDatos();
        }
    }

    public void bajarRanking() {
        if (!this.listaDatos.isEmpty()) {
            int size = this.listaDatos.size();
            this.datoVisible = (this.datoVisible + 1) % size;
            mostrarDatos();
        }
    }


    public void volverAtras() {
        if (this.datoNuevo) {
            finish();
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public String getRutaFichero() {
        return this.rutaFichero;
    }

    public void setRutaFichero(String name) {
        this.rutaFichero = name;
    }

    public void cargarDatos() {
        String aux = "";
        Bitmap imagenAux = null;
        BufferedReader buffer = null;
        try {
            buffer =
                    new BufferedReader(
                            new InputStreamReader(
                                    openFileInput(this.rutaFichero)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                if (!((aux = buffer.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            String[] parser = aux.split("/");
            String path = Environment.getExternalStorageDirectory().toString();
            File fileImage = new File(path, parser[0] + ".png");
            try {
                FileInputStream fileImagen = new FileInputStream(fileImage);
                imagenAux = BitmapFactory.decodeStream(fileImagen);
            } catch (IOException e) {
                //e.printStackTrace();
                System.out.println(e);
            }
            DatosRanking datos = new DatosRanking(parser[0], parser[1], parser[2], Integer.parseInt(parser[3]), imagenAux);
            this.listaDatos.add(datos);
        }
        try {
            buffer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<DatosRanking> getListaDatos() {
        return this.listaDatos;
    }
    public void setListaDatos(List<DatosRanking> listaDatos) {
        this.listaDatos = listaDatos;
    }

    public void escribirDatoNuevo(DatosRanking dato) {
        cargarDatos();
        DatosRanking datos = null;
        if (dato == null) {
            String id = asignarId();

            String nombre = getIntent().getStringExtra("nombre");
            String modo = getIntent().getStringExtra("modo");
            int puntuacion = getIntent().getIntExtra("puntuacion", 0);
            Bitmap imagen = getIntent().getParcelableExtra("imagen");
            datos = new DatosRanking(id, nombre, modo, puntuacion, imagen);
        } else {
            datos = dato;
        }
        this.listaDatos.add(datos);

        Collections.sort(this.listaDatos, new Comparator<DatosRanking>() {
            @Override
            public int compare(DatosRanking o1, DatosRanking o2) {
                return Integer.compare(o2.getPuntuacion(), o1.getPuntuacion());
            }
        });

        if (this.listaDatos.size() > this.apartadosRanking) {
            this.listaDatos.remove(this.listaDatos.size() - 1);
        }

        String lineaAux = null;
        try {
            OutputStreamWriter writer =
                    new OutputStreamWriter(
                            openFileOutput(this.rutaFichero, Context.MODE_PRIVATE));
            for (DatosRanking aux : this.listaDatos) {
                lineaAux = (aux.getId() + "/" + aux.getNombre() + "/" + aux.getModo() + "/" + aux.getPuntuacion() + "\n");

                writer.write(lineaAux);
            }
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Bitmap imagenAux = null;
        try {
            for (DatosRanking aux : this.listaDatos) {
                String path = Environment.getExternalStorageDirectory().toString();
                File file = new File(path, aux.getId() + ".png");
                OutputStream outputStream = new FileOutputStream(file);
                imagenAux = aux.getImagen();
                imagenAux.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                outputStream.flush();
                outputStream.close();
                MediaStore.Images.Media.insertImage(getContentResolver(), file.getAbsolutePath(), file.getName(), file.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finish();
    }

    public void bajarRanking(View view) {
        this.bajarRanking();
    }

    public void subirRanking(View view) {
        this.subirRanking();
    }

    public void volverAtras(View view){
        this.volverAtras();
    }
}

