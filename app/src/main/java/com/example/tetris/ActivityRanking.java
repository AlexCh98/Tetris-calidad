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
import java.io.ByteArrayOutputStream;
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
import java.util.LinkedList;
import java.util.List;


public class ActivityRanking extends Activity{

    private String extensionImagen= "IMG";
    private final String rutaFichero = "RankingTetris.txt";
    private final int apartadosRanking = 10;

    private ImageView imageViewRanking;
    private TextView textViewRankingNombre;
    private TextView textViewRankingModo;
    private TextView textViewRankingPuntuacion;

    private boolean datoNuevo;      /*Si esta a false solo se lee el ranking y si esta a true, se escribe en memoria guardando el dato*/

    private List<DatosRanking> listaDatos;

    private int datoVisible = 0;

    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        this.datoNuevo = getIntent().getBooleanExtra("datoNuevo", false);

        if(!datoNuevo){
            setContentView(R.layout.activity_ranking);
        }

        this.imageViewRanking = findViewById(R.id.imagenRanking);
        this.textViewRankingNombre = findViewById(R.id.textViewRanking1);
        this.textViewRankingModo = findViewById(R.id.textViewRanking2);
        this.textViewRankingPuntuacion = findViewById(R.id.textViewRanking3);



        listaDatos = new ArrayList<>();

        ranking();
    }


    private void ranking(){
        if(datoNuevo){
            escribirDatoNuevo();
        }
        else{
            leerTodosLosDatos();
            mostrarDatos();
        }

    }

    private void leerTodosLosDatos() {

        List<String> listaFichero = new LinkedList<>();

        String aux;

        try{
            BufferedReader buffer =
                    new BufferedReader(
                            new InputStreamReader(
                                    openFileInput(this.rutaFichero)));


            while((aux = buffer.readLine()) != null){
                listaFichero.add(aux);
            }

            buffer.close();

        }catch(Exception e){
            e.printStackTrace();
        }

        pasarListaDato(listaFichero);

    }


    private void pasarListaDato(List<String> listaFichero){

        String id;
        String nombre;
        String modo;
        String puntuacion;
        Bitmap imagenAux = null;

        for(String aux:listaFichero){

            id = aux.substring(0,aux.indexOf("/"));
            aux = aux.substring(aux.indexOf("/")+1);
            nombre = aux.substring(0,aux.indexOf("/"));
            aux = aux.substring(aux.indexOf("/")+1);
            modo = aux.substring(0,aux.indexOf("/"));
            aux = aux.substring(aux.indexOf("/")+1);
            puntuacion = aux;

            String path = Environment.getExternalStorageDirectory().toString();
            File file = new File(path, id + ".png");

            try{

                FileInputStream fileImagen = new FileInputStream(file);
                imagenAux = BitmapFactory.decodeStream(fileImagen);

            }catch(IOException e){
                e.printStackTrace();
            }

            DatosRanking datos = new DatosRanking(id,nombre,modo,Integer.parseInt(puntuacion),imagenAux);
            this.listaDatos.add(datos);

        }

    }

    private void escribirDatoNuevo(){

        leerTodosLosDatos();

        String id = asignarId();

        String nombre = getIntent().getStringExtra("nombre");
        String modo = getIntent().getStringExtra("modo");
        int puntuacion = getIntent().getIntExtra("puntuacion",0);
        Bitmap imagen = getIntent().getParcelableExtra("imagen");

        DatosRanking datos = new DatosRanking(id,nombre,modo,puntuacion,imagen);

        this.listaDatos.add(datos);

        Collections.sort(this.listaDatos, new Comparator<DatosRanking>() {
            @Override
            public int compare(DatosRanking o1, DatosRanking o2) {
                return Integer.compare(o2.getPuntuacion(),o1.getPuntuacion());
            }
        });

        if(this.listaDatos.size()>this.apartadosRanking){
            this.listaDatos.remove(this.listaDatos.size()-1);
        }

        guardarRankingEnMemoria();
    }


    private String asignarId(){

        String auxId;
        boolean idAsignado = true;
        int intId;

        do{

            intId = (int)(Math.random()*(102)-2); //Genera un numero aleatorio entre 0 y 99 incluidos

            auxId = intId + this.extensionImagen;

            for(DatosRanking aux:this.listaDatos){

                if(aux.getId().compareTo(auxId) == 0){
                    idAsignado = false;
                }

            }

        }while(!idAsignado);

        return auxId;

    }

    private void guardarRankingEnMemoria()  {

        String lineaAux = null;

        try{

            OutputStreamWriter writer =
                    new OutputStreamWriter(
                            openFileOutput(this.rutaFichero,Context.MODE_PRIVATE));

            for(DatosRanking aux: this.listaDatos){

                lineaAux = (aux.getId() + "/" + aux.getNombre() + "/" + aux.getModo() + "/" + aux.getPuntuacion() + "\n");

                writer.write(lineaAux);

            }

            writer.flush();
            writer.close();

        }catch(Exception e){
            e.printStackTrace();
        }

        Bitmap imagenAux = null;

        try{
            for(DatosRanking aux:this.listaDatos){


                String path = Environment.getExternalStorageDirectory().toString();
                File file = new File(path, aux.getId() + ".png");

                OutputStream outputStream = new FileOutputStream(file);
                //outputStream = new FileOutputStream(file);

                imagenAux = aux.getImagen();
                imagenAux.compress(Bitmap.CompressFormat.PNG,100, outputStream);



                outputStream.flush();
                outputStream.close();

                MediaStore.Images.Media.insertImage(getContentResolver(),file.getAbsolutePath(),file.getName(),file.getName());
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        finish();
    }


    private void mostrarDatos(){
        if(this.listaDatos.size() == 0){

            this.imageViewRanking.setImageBitmap(null);
            this.textViewRankingNombre.setText("Sin datos");
            this.textViewRankingModo.setText("Sin datos");
            this.textViewRankingPuntuacion.setText("Sin datos");

        }
        else{

            String text1 = "";
            String text2 = "";
            String text3 = "";


            text1 = meteEspacios("Nombre",15) + "\n";
            text2 = meteEspacios("Modo",10) + "\n";
            text3 = meteEspacios("Puntuacion",10) + "\n";


            text1 = text1 + meteEspacios(this.listaDatos.get(this.datoVisible).getNombre(),15) + "\n";
            text2 = text2 + meteEspacios(this.listaDatos.get(this.datoVisible).getModo(),10) + "\n";
            text3 = text3 + this.listaDatos.get(this.datoVisible).getPuntuacion() + "\n";


            this.textViewRankingNombre.setText(text1);
            this.textViewRankingModo.setText(text2);
            this.textViewRankingPuntuacion.setText(text3);

            this.imageViewRanking.setImageBitmap(this.listaDatos.get(this.datoVisible).getImagen());

        }
    }

    public String meteEspacios(String aux, int espacios){
        if(aux.length()<espacios){
            for(int i=aux.length();i<espacios;i++){
                aux = aux + " ";
            }
        }
        else if(aux.length()>espacios){
            aux = aux.substring(0,espacios-1);
        }

        aux = aux + " ";

        return aux;
    }

    public void subirRanking(View v){
        if(this.listaDatos.size() != 0 && this.datoVisible>0){

            this.datoVisible--;
            mostrarDatos();


        }
    }

    public void bajarRanking(View v){
        if(this.listaDatos.size() != 0  && this.datoVisible< this.listaDatos.size()-1){

            this.datoVisible++;
            mostrarDatos();


        }
    }


    public void volverAtras(View v){
        if(this.datoNuevo){
            finish();
        }
        else{

            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);

            finish();

        }

    }


    public void cargarDatos(File file) {
        // TODO
    }


    public List<DatosRanking> getListaDatos() {
        // TODO
        return null;
    }

    public void setListaDatos(List<DatosRanking> listaDatos){
        // TODO
    }

    public void escribirDato(DatosRanking dato){
        // TODO
    }

    public String getRutaFichero(){
        // TODO
        return null;
    }
}

