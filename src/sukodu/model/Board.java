package sukodu.model;

import java.awt.*;

public class Board {
    // Constants
    private static final int MATRIX_SIZE = 9;
    private static final int SQUARE_SIZE = 3;

    // Fields
    private int[] selectedCell;
    private int[][] matrix;

    public Board(){
        this.matrix = new int[MATRIX_SIZE][MATRIX_SIZE];
        clearBoard();
        newBoard();
        selectedCell = new int[2];
    }

    public void clearBoard() {
        for (int i = 0; i < MATRIX_SIZE; i++){
            for(int j = 0; j < MATRIX_SIZE; j++){
                this.matrix[i][j] = 0;
            }
        }
    }

    public void fillDiagonal() {
        populateSquare(0, Utils.randSeqGen());
        populateSquare( 4, Utils.randSeqGen());
        populateSquare( 8, Utils.randSeqGen());
    }


    public void newBoard(){
        populateSquare(0, Utils.randSeqGen());
        populateSquare( 4, Utils.randSeqGen());
        populateSquare( 8, Utils.randSeqGen());
        fillBoard(0,3);
    }

    public boolean fillBoard(int currentRow, int currentCol){
        if(currentRow >= MATRIX_SIZE && currentCol >=MATRIX_SIZE){
            return true;
        }
        if(currentCol >= MATRIX_SIZE && currentRow <8) {
            currentRow += 1;
            currentCol = 0;
        }
        if (currentRow < SQUARE_SIZE){
            if (currentCol < SQUARE_SIZE){
                currentCol = 3;
            }
        } else if (currentRow < 6){
            if (currentCol == (int)(currentRow/3)*3){
                currentCol = currentCol + 3;
            }
        } else {
            if(currentCol == 6) {
                currentRow += 1;
                currentCol = 0;
                if (currentRow >= MATRIX_SIZE) {
                    return true;
                }
            }
        }

        for (int numToCheck = 1; numToCheck <= MATRIX_SIZE; numToCheck++) {
            if(isValidSpot(currentRow, currentCol, numToCheck)){
                this.matrix[currentRow][currentCol] = numToCheck;
                if (fillBoard(currentRow, currentCol+1)){
                    return true;
                }
                this.matrix[currentRow][currentCol] = 0;
            }
        }
        return false;
    }

    public void populateSquare(int matNo, Integer[] values){
        int matX = (matNo / 3); // zero indexed
        int matY = matNo % 3;
        for(int k = 0; k < values.length; k++){
            this.matrix[matX*3+(k/3)][matY*3+(k%3)] = values[k];
        }
    }

    public void displayBoard(){
        for (int i = 0; i < MATRIX_SIZE; i++){
            for (int j = 0; j < MATRIX_SIZE; j++){
                System.out.print(this.matrix[i][j]);
                if(j % 3 == 2 && j < 6){
                    System.out.print("|");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
            if(i % 3 == 2 && i < 6){
                System.out.println("-----------------");
            }
        }
    }

    public boolean isNotInRow(int row, int numToCheck) {
        int currentRow = row;
        for (int currentCol = 0; currentCol < MATRIX_SIZE; currentCol++){
            if (this.matrix[currentRow][currentCol] == numToCheck) {
                return false;
            }
        }
        return true;
    }

    public boolean isNotInColumn(int col, int numToCheck) {
        int currentCol = col;
        for (int currentRow = 0; currentRow < MATRIX_SIZE; currentRow++){
            if (this.matrix[currentRow][currentCol] == numToCheck) {
                return false;
            }
        }
        return true;
    }

    public boolean isNotInSquare(int startRow, int startCol, int numToCheck){
        for (int i = 0; i < SQUARE_SIZE; i++) {
            for (int j = 0; j < SQUARE_SIZE; j++){
                if(this.matrix[startRow+i][startCol+j] == numToCheck) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValidSpot(int row, int col, int numToCheck) {
        return (isNotInRow(row, numToCheck) && isNotInColumn(col, numToCheck) && isNotInSquare(row-row%3, col-col%3, numToCheck));
    }

    public void removeKValues(int k){
        for (int i = 0; i < k; i++){
            int row = Utils.randNumGen(8);
            int col = Utils.randNumGen(8);
            this.matrix[row][col] = 0;
        }
    }

    // Getters and Setters
    public String[][] getMatrix(){
        String[][] stringMatrix = new String[MATRIX_SIZE][MATRIX_SIZE];
        for(int i = 0; i < MATRIX_SIZE; i++){
            for (int j = 0; j < MATRIX_SIZE; j++){
                stringMatrix[i][j] = String.valueOf(this.matrix[i][j]);
            }
        }
        return stringMatrix;
    }

    public void setMatrix(int[][] newMatrix){
        System.out.println("This operation is currently not available to use.");
    }

    public int[] getSelectedCell(){
        return selectedCell;
    }

    public void setSelectedCell(int i, int j){
        selectedCell[0] = i;
        selectedCell[1] = j;
        System.out.println("New selected cell is row: "+ (i+1) + " col: " + (j+1));
    }




}
