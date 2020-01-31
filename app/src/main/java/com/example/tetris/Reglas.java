package com.example.tetris;

public class Reglas {

    public boolean permisoDesplazamiento(int [][] matrizCoords, int [][] matrizTablero){
        boolean permiso = true;

        for(int i = 0; i < matrizCoords.length; i++){
            if (matrizCoords[i][1] > 9 | matrizCoords[i][1] < 0){
                permiso = false;
            }
        }
        if(permiso){
            for(int i = 0; i < matrizCoords.length; i++){
                if(matrizTablero[matrizCoords[i][0]][matrizCoords[i][1]] != 0){
                    permiso = false;
                }
            }
        }

        return permiso;
    }

    public boolean permisoDesplazamientoInferior(int [][] matrizCoords, int [][] matrizTablero){
        boolean permiso = true;

        for(int j = 0; j < matrizCoords.length; j++){
            if(matrizTablero[matrizCoords[j][0]][matrizCoords[j][1]] != 0){
                permiso = false;
            }
        }

        return permiso;
    }

    public boolean superaTopeInferior(int [][] matrizCoords){
        boolean tope = false;

        for (int i = 0; i < matrizCoords.length; i++){
            if(matrizCoords[i][0] > 23){
                tope = true;
            }
        }
        return tope;
    }

    public boolean filaCompleta (int [][] matrizTablero, Tablero tablero){
        boolean resultado = false;
        for (int i = 23; i >= 0; i--){
            if(!resultado){
                boolean incompleta = false;
                for(int j = 0; j < 10; j++){
                    if(matrizTablero[i][j] == 0){
                        incompleta = true;
                    }
                }
                if(!incompleta){
                    resultado = true;
                    tablero.vaciarFila(i);
                }
            }
        }
        return resultado;
    }

    public boolean filaCompleta50seconds (int [][] matrizTablero, Tablero tablero){
        boolean resultado = false;
        for (int i = 23; i >= 0; i--){
            if(!resultado){
                boolean incompleta = false;
                for(int j = 0; j < 10; j++){
                    if((matrizTablero[i][j] == 0) || (matrizTablero[i][j]==8) ){
                        incompleta = true;
                    }
                }
                if(!incompleta){
                    resultado = true;
                    tablero.vaciarFila(i);
                }
            }
        }
        return resultado;
    }
    
    public boolean gameOver(Pieza pieza, int [][] matrizTablero){

        boolean fin = false;

            for(int i=0; i<pieza.getCoords().length;i++){
                if(pieza.getCoords()[i][0] == 3){
                    fin = true;
                }
            }

        return fin;
    }

}
