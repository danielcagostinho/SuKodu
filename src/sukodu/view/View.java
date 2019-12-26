package sukodu.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class View extends JFrame {

    private static final int GRID_SIZE = 9;
    private static final int SQUARE_SIZE = 3;

    private static final int CELL_SIZE = 60;
    private static final int CANVAS_HEIGHT = CELL_SIZE * GRID_SIZE;
    private static final int CANVAS_WIDTH = CELL_SIZE * GRID_SIZE;
    private static final Color COLOR_COMPLETED = Color.WHITE;
    private static final Color COLOR_OPEN = Color.YELLOW;
    private static final Color COLOR_SELECTED = Color.BLUE;

    private static final Font FONT_NUMBERS = new Font("Monospaced", Font.BOLD, 20);

    JTextField[][] tfGrid;
    JButton btnClearBoard;
    JButton btnDiagonal;
    JButton btnNewGame;
    JButton btnRemoveValues;

    public View() {

        Container cp = getContentPane();
        tfGrid = new JTextField[GRID_SIZE][GRID_SIZE];
        initGrid();

        // Top Panel
        JPanel pnlTop = new JPanel(new FlowLayout());
        btnClearBoard = new JButton("Clear Board");
        btnDiagonal = new JButton("Fill Diagonal");
        btnNewGame = new JButton("Fill Rest");
        btnRemoveValues = new JButton("Remove Values");
        pnlTop.add(btnClearBoard);
        pnlTop.add(btnDiagonal);
        pnlTop.add(btnNewGame);
        pnlTop.add(btnRemoveValues);

        // Grid Panel
        JPanel pnlGrid = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE));
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                int bottom = 1;
                int right = 1;
                if (i < 8 && i % 3 == 2) {
                    bottom = 5;
                }
                if (j < 8 && j % 3 == 2) {
                    right = 5;
                }
                tfGrid[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, bottom, right, Color.black));
                tfGrid[i][j].setHorizontalAlignment(JTextField.CENTER);
                tfGrid[i][j].setFont(FONT_NUMBERS);
                tfGrid[i][j].setEditable(false);
                pnlGrid.add(tfGrid[i][j]);
            }
        }


        cp.setLayout(new BorderLayout());
        cp.add(pnlTop, BorderLayout.NORTH);
        cp.add(pnlGrid, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(CANVAS_WIDTH + 50, CANVAS_HEIGHT + 50);
        setTitle("SuKoDu");
        setVisible(true);
    }

    public void initGrid() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                this.tfGrid[i][j] = new JTextField("");
                this.tfGrid[i][j].setBackground(COLOR_COMPLETED);
            }
        }
    }

    // Getters and Setters
    public JTextField[][] getGrid() {
        return this.tfGrid;
    }

    public void setGrid(String[][] grid) {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if(grid[i][j].equals("0")){
                    tfGrid[i][j].setText("");
                } else {
                    tfGrid[i][j].setText(grid[i][j]);
                }
            }
        }
    }

    public JButton getBtnClearBoard() {
        return btnClearBoard;
    }

    public JButton getBtnDiagonal(){
        return btnDiagonal;
    }

    public JButton getBtnNewGame() {
        return btnNewGame;
    }
    public JButton getBtnRemoveValues() {
        return btnRemoveValues;
    }


    public void setSelected(int row, int col){
        if(!(row == 10 || col == 10)){
            JTextField[][] grid = getGrid();
            JTextField tfPressed = grid[row][col];
            System.out.println(tfPressed.getBackground().toString());
            if(tfPressed.getBackground() == COLOR_COMPLETED){ // Is Not Selected
                for(int j = 0; j < 9; j++){ // Color the cells in same row
                    grid[row][j].setBackground(Color.GRAY);
                }
                for(int i = 0; i < 9; i++){ // Color the cells in same col
                    grid[i][col].setBackground(Color.GRAY);
                }
                tfPressed.setBackground(COLOR_SELECTED);
            } else {
                System.out.println("Deselecting");
                for(int j = 0; j < 9; j++){ // Color the cells in same row
                    grid[row][j].setBackground(COLOR_COMPLETED);
                }
                for(int i = 0; i < 9; i++){ // Color the cells in same col
                    grid[i][col].setBackground(COLOR_COMPLETED);
                }
            }
        }
    }

    public void removeSelected(int row, int col){
        if(!(row == 10 || col == 10)) {
            JTextField[][] grid = getGrid();
            for (int j = 0; j < 9; j++){ // Set colors for cells in the same row
                grid[row][j].setBackground(COLOR_COMPLETED);
            }
            for (int i = 0; i < 9; i++){
                grid[i][col].setBackground(COLOR_COMPLETED);
            }
        }
    }

    public int[] getCellClicked(JTextField tf) {
        for (int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if (tf == tfGrid[i][j]){
                    System.out.println((1+i) + " " + (1+j));
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new View();
            }
        });
    }



}

