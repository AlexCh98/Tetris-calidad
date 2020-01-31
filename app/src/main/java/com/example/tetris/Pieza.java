package com.example.tetris;

public class Pieza implements Cloneable{
    private int[][] matrizCoords;
    private int color;
    private boolean rota;
    private final int centroMatrizX = 2;
    private final int centroMatrizY = 2;
    private final int posicionX = 0;
    private final int posicionY = 1;
    private final int posicionBitImportante = 0;

    public Pieza(int tipo){
        switch (tipo){
            case 0:
                this.crearPieza0();
                break;
            case 1:
                this.crearPieza1(1);
                break;
            case 2:
                this.crearPieza2(2);
                break;
            case 3:
                this.crearPieza3(3);
                break;
            case 4:
                this.crearPieza4(4);
                break;
            case 5:
                this.crearPieza5(5);
                break;
            case 6:
                this.crearPieza6(6);
                break;
            case 7:
                this.crearPieza7(7);
                break;
            default:
        }
    }

    private void crearPieza0(){
        this.color=0;
        this.matrizCoords = new int[4][2];
        this.rota = false;
    }

    private void crearPieza1(int color) {
        this.color = color;
        this.matrizCoords = new int[][]{{3,4},{3, 3}, {3, 5}, {3, 6}};
        this.rota = true;
    }

    private void crearPieza2(int color) {
        this.color = color;
        this.matrizCoords = new int[][]{{3,4}, {3,3},{3,5},{2,4}};
        this.rota = true;
    }

    private void crearPieza3(int color) {
        this.color = color;
        this.matrizCoords = new int[][]{{3,4}, {3,3},{3,5},{2,3}};
        this.rota = true;
    }

    private void crearPieza4(int color) {
        this.color = color;
        this.matrizCoords = new int[][]{{3,4}, {3,3},{3,5},{2,5}};
        this.rota = true;
    }

    private void crearPieza5(int color) {
        this.color = color;
        this.matrizCoords = new int[][]{{3,4},{3,5},{2,4},{2,5}};
        this.rota = false;
    }

    private void crearPieza6(int color) {
        this.color = color;
        this.matrizCoords = new int[][]{{3,4}, {3,3},{2,4},{2,5}};
        this.rota = true;
    }

    private void crearPieza7(int color) {
        this.color = color;
        this.matrizCoords = new int[][]{{3,4}, {3,3},{2,2},{2,3}};
        this.rota = true;
    }

    public int[][] getCoords(){
        return this.matrizCoords;
    }

    public int getColor(){
        return this.color;
    }

    private boolean[][] convertirAMatriz(){
        boolean[][] matrizAux1 = new boolean[5][5];

        int auxX = this.matrizCoords[this.posicionBitImportante][this.posicionX] - this.centroMatrizX;
        int auxY = this.matrizCoords[this.posicionBitImportante][this.posicionY] - this.centroMatrizY;

        for(int i=0;i<this.matrizCoords.length;i++){
            matrizAux1[this.matrizCoords[i][this.posicionX] - auxX][this.matrizCoords[i][this.posicionY] - auxY] = true;
        }

        return matrizAux1;
    }

    private int[][] convertirAPieza(boolean[][] matrizAux2){
        int[][] matrizCoordsAux = new int[4][2];

        int auxX = this.matrizCoords[this.posicionBitImportante][this.posicionX] - this.centroMatrizX;
        int auxY = this.matrizCoords[this.posicionBitImportante][this.posicionY] - this.centroMatrizY;
        int k = 1;

        for(int i=0;i<matrizAux2.length;i++){
            for(int j=0;j<matrizAux2.length;j++){
                if(matrizAux2[i][j]){
                    if(i == this.centroMatrizX && j == this.centroMatrizY){
                        matrizCoordsAux[0][this.posicionX] = this.matrizCoords[0][this.posicionX];
                        matrizCoordsAux[0][this.posicionY] = this.matrizCoords[0][this.posicionY];
                    }
                    else{
                        matrizCoordsAux[k][this.posicionX] = auxX + i;
                        matrizCoordsAux[k][this.posicionY] = auxY + j;

                        k++;
                    }
                }
            }
        }

        return matrizCoordsAux;
    }

    public void rotarIzq(){
        if(this.rota){
            boolean[][] matrizAux1 = this.convertirAMatriz();
            boolean[][] matrizAxu2 = new boolean[5][5];

            for(int i=0;i<matrizAux1.length;i++){
                for(int j=0;j<matrizAux1.length;j++){
                    matrizAxu2[i][j] = matrizAux1[j][i];
                }
            }

            this.matrizCoords = convertirAPieza(matrizAxu2);
        }
    }


    public void rotarDer(){
        if(this.rota){
            boolean[][] matrizAux1 = this.convertirAMatriz();
            boolean[][] matrizAxu2 = new boolean[5][5];

            for(int i=0;i<matrizAux1.length;i++){
                for(int j=0;j<matrizAux1.length;j++){
                    matrizAxu2[i][(matrizAux1.length - 1) - j] = matrizAux1[j][i];
                }
            }

            this.matrizCoords = convertirAPieza(matrizAxu2);
        }
    }


    public void desplazarIzq(){
        for(int i=0;i<this.matrizCoords.length;i++){
            this.matrizCoords[i][this.posicionY]--;
        }
    }


    public void desplazarDer(){
        for(int i=0;i<this.matrizCoords.length;i++){
            this.matrizCoords[i][this.posicionY]++;
        }
    }


    public void desplazarAbajo(){
        for(int i=0;i<this.matrizCoords.length;i++){
            this.matrizCoords[i][this.posicionX]++;
        }
    }

    @Override
    public Pieza clone(){
        Pieza clon = new Pieza(0);

        for(int i=0;i<this.matrizCoords.length;i++){
            clon.matrizCoords[i][0] = this.matrizCoords[i][0];
            clon.matrizCoords[i][1] = this.matrizCoords[i][1];
        }

        clon.color = this.color;
        clon.rota = this.rota;

        return clon;
    }
}
