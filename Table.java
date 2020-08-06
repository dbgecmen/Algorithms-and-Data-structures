package ad_extraopdracht;

import java.util.ArrayList;
import java.util.List;

public class Table {

    protected boolean[][] elements;

    public Table(int n, int m) {
        elements = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                elements[i][j] = false;
            }
        }
    }

    public void set(int row, int col, boolean val) {
        if (0 <= row && row < numRows() && 0 <= col && col < numCols()) {
            elements[row][col] = val;
        }
    }

    public boolean get(int row, int col) {
        if (0 <= row && row < numRows() && 0 <= col && col < numCols()) {
            return elements[row][col];
        }

        return false;
    }

    public boolean[] getRow(int row) {
        if (0 <= row && row < numRows()) {
            return elements[row];
        }

        return null;
    }

    public void setRow(boolean[] b, int row) {
        for (int i = 0; i < b.length; i++) {
            set(row, i, b[i]);
        }
    }

    public int numRows() {
        return elements.length;
    }

    public int numCols() {
        if (numRows() == 0) {
            return 0;
        }

        return elements[0].length;
    }

    public Table copy() {
        Table res = new Table(numRows(), numCols());
        for (int i = 0; i < numRows(); i++) {
            res.setRow(getRow(i), i);
        }
        return res;
    }

    public int[] getValueOfColumn(int col) {
        int counter = 0;
        List<Integer> lijst = new ArrayList<Integer>();
        for (int i = 0; i < numRows(); i++) {
            if (get(i, col)) { 
                counter++;
            } else if (counter != 0) {
                lijst.add(counter);
                counter = 0;
            }
        }

        if (counter != 0) { //laatste blokjes toevoegen
            lijst.add(counter);
        }

        int[] res = new int[lijst.size()];
        for (int i = 0; i < lijst.size(); i++) { //array maken van arraylist
            res[i] = lijst.get(i);
        }
        return res;
    }

    public String toString() {
        String res = "";

        for (int i = 0; i < numRows(); i++) {
            res += "[ ";
            for (int j = 0; j < numCols(); j++) {
                if (get(i, j)) {
                    res += "X ";
                } else {
                    res += "_ ";
                }
            }
            //res = res.substring(0, res.length() - 2);
            res += "]\n";
        }

        return res;
    }

    public static void main(String[] args) {
    }
}