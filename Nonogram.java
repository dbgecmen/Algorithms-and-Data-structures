package ad_extraopdracht;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Nonogram {

    private ArrayList<int[]> rows;
    private ArrayList<int[]> cols;

    public Nonogram(int n, int m, String fileLoc) {
        parse(n, m, fileLoc);
    }

    public void parse(int n, int m, String fileLoc) {
        rows = new ArrayList<int[]>(n);
        cols = new ArrayList<int[]>(m);

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileLoc));
            String line = null;

            for (int i = 0; i < n; i++) {
                line = reader.readLine();
                String[] split = line.split("\\s+");
                int[] res = new int[split.length];
                for (int j = 0; j < res.length; j++) {
                    res[j] = Integer.parseInt(split[j]);
                }
                rows.add(i, res);
            }
            for (int i = 0; i < m; i++) {
                line = reader.readLine();
                String[] split = line.split("\\s+");
                int[] res = new int[split.length];
                for (int j = 0; j < res.length; j++) {
                    res[j] = Integer.parseInt(split[j]);
                }
                cols.add(i, res);
            }

            reader.close();
        } catch (Exception e) {
            System.out.println(e + "Error in reading of file.");
        }
    }

    public ArrayList<int[]> getRows() {
        return rows;
    }

    public ArrayList<int[]> getCols() {
        return cols;
    }

    public int[] getRow(int i) {
        return rows.get(i);
    }

    public int[] getCol(int i) {
        return cols.get(i);
    }

    public int getRowAmount() {
        return rows.size();
    }

    public int getColAmount() {
        return cols.size();
    }

    public int minRows(int[] col) {
        int res = (col.length - 1);
        for (int i : col) {
            res += i;
        }
        return res;
    }

    public int minCols(int[] row) {
        int res = 0;
        for (int i : row) {
            if (i > 0) {
                res += i;
                res++;
            }
        }
        return --res;
    }

    public void clear() {
        rows = new ArrayList<int[]>();
        cols = new ArrayList<int[]>();
    }

    public String toString() {
        String res = "";

        return res;
    }
}