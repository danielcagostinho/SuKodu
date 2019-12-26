package sukodu.controller;

import sukodu.model.Board;
import sukodu.view.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Controller {
    private Board board;
    private View view;

    public Controller(Board b, View v) {
        board = b;
        view = v;
        initView();
    }

    public void initView() {
        view.setGrid(board.getMatrix());
    }

    public void initController() {
        view.getBtnClearBoard().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearBoard();
            }
        });
        view.getBtnDiagonal().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fillDiagonal();
            }
        });
        view.getBtnNewGame().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fillRest();
            }
        });
        view.getBtnRemoveValues().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeValues();
            }
        });
        addMouseAdapters();
    }

    private void addMouseAdapters() {
        JTextField[][] grid = view.getGrid();
        for(int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                grid[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        mouseClick(e);
                    }
                });
            }
        }
    }

    private void mouseClick(MouseEvent e){
        JTextField tfPressed = (JTextField) e.getSource();
        int[] indices = view.getCellClicked(tfPressed);
        int row = indices[0];
        int col = indices[1];
        int currentSelectedRow = board.getSelectedCell()[0];
        board.setSelectedCell(row, col);
        view.removeSelected(row, col);
        updateBoard();
    }

    public void clearBoard(){
        board.clearBoard();
        board.setSelectedCell(10,10);
        updateBoard();
    }

    public void fillDiagonal() {
        board.fillDiagonal();
        board.setSelectedCell(10,10);
        updateBoard();
    }

    public void newGame(){
        board.newBoard();
        board.setSelectedCell(10,10);
        updateBoard();
    }

    public void fillRest(){
        board.fillBoard(0,3);
        updateBoard();
    }

    public void removeValues() {
        board.removeKValues(10);
        updateBoard();
    }

    public void updateBoard() {
        System.out.println("Updating Board...");
        view.setGrid(board.getMatrix());
        view.setSelected(board.getSelectedCell()[0],board.getSelectedCell()[1]);
    }
}
