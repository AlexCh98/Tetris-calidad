package com.example.tetris;

import java.util.Random;

public class Tablero {

    private int [][] matrizTablero;

    public Tablero(){
        this.matrizTablero = new int[24][10];
    }

    public void actualizarTablero(int [][] matrizCoords, int color){
        for(int i = 0; i < matrizCoords.length; i++){
            this.matrizTablero[matrizCoords[i][0]][matrizCoords[i][1]] = color;
        }
    }

    public void vaciarFila (int fila){
        for(int j = 0; j < 10; j++){
            this.matrizTablero[fila][j] = 0;

        }
        bajarFila();
    }

    public void rellenarfila(int fila){
        for(int i=23;i>=23-fila;i--){
            for(int j=0;j<10;j++){
                matrizTablero[i][j]=8;
            }
        }
    }

    private void bajarFila (){
        int color;
        for(int i = 22; i >= 0; i--){
            for(int j = 0; j < 10; j++){
                if(matrizTablero[i + 1][j] == 0){
                    color = matrizTablero[i][j];
                    matrizTablero[i][j] = 0;
                    matrizTablero[i + 1][j] = color;
                }
            }
        }
    }

    public int[][] getMatrizTablero() {
        return matrizTablero;
    }

    public void moverColumnasIzquierda(){
        int[] auxiliar = new int[24];
        for (int i = 0; i < 24;i++){
            auxiliar[i] =  this.matrizTablero[i][0];
        }
        for(int i= 1; i < 10 ; i++){
            for (int j = 0;j<24;j++){
                this.matrizTablero[j][i-1] = this.matrizTablero[j][i];
            }
        }
        for (int i = 0; i < 24;i++){
            this.matrizTablero[i][9] = auxiliar[i];
        }

    }

    public void moverColumnasDerecha(){
        int[] auxiliar = new int[24];
        for (int i = 0; i < 24;i++){
            auxiliar[i] =  this.matrizTablero[i][9];
        }
        for(int i= 8; i > -1 ; i--){
            for (int j = 0;j<24;j++){
                this.matrizTablero[j][i+1] = this.matrizTablero[j][i];
            }
        }
        for (int i = 0; i < 24;i++){
            this.matrizTablero[i][0] = auxiliar[i];
        }

    }


    public void moverColumnasAleatoria(){
        int min = 1;
        int max = 9;
        Random r = new Random();
        int columnaSelect = r.nextInt(max - min + 1) + min;
        int[] auxiliar = new int[24];
        for (int i = 0; i < 23;i++){
            auxiliar[i] =  this.matrizTablero[i][columnaSelect-1];
        }
        for(int i= columnaSelect; i < 10 ; i++){
            for (int j = 0;j<24;j++){
                this.matrizTablero[j][i-1] = this.matrizTablero[j][i];
            }
        }
        for (int i = 0; i < 24;i++){
            this.matrizTablero[i][9] = auxiliar[i];
        }

    }

    public void cambiarColorTablero(){
        for(int i = 0; i < this.matrizTablero.length; i++){
            for(int j=0; j<this.matrizTablero[0].length; j++) {
                if (this.matrizTablero[i][j] != 0){
                    this.matrizTablero[i][j] = 2;
                }
            }
        }
    }
    public boolean sonTodasRosas(){
        for(int i =0; i <this.matrizTablero[0].length; i++){
            if(this.matrizTablero[matrizTablero.length-1][i] != 0 && this.matrizTablero[matrizTablero.length-1][i] != 2){
                return false;
            }
        }
        return true;
    }
}