import sukodu.model.Board;

import javax.swing.*;

public class Proof extends JFrame {

    public static void main(String[] args) {
        Board board = new Board();
        board.newBoard();
        board.displayBoard();
        board.fillBoard(0,3);
        board.displayBoard();

//        sukodu.view.View app = new sukodu.view.View();
//        app.populateBoard(board.getMatrix());

    }
}
