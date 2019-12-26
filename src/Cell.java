public class Cell {

    private int row;
    private int col;
    private int value;
    private Cell next;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.value = 0;
        assignNext();
    }

    public void assignNext() {
        int nextCol, nextRow;
        if (this.col == 8) {
            nextCol = 0;
            nextRow = this.row + 1;
        } else {
            nextCol = this.col + 1;
            nextRow = this.row;
        }
        this.next = new Cell(nextRow, nextCol);
    }

    public void setValue(int value) {
        this.value = value;
    }



}
