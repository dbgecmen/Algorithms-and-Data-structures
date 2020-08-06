package ad_extraopdracht;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.media.j3d.Sound;

public class Solver {
    
    private Nonogram nono;
    private List<Table> solutions;
    private static ArrayList<List<boolean[]>> list = new ArrayList<List<boolean[]>>();;

    public Solver(Nonogram n) {
        nono = n;
        Table tab = new Table(n.getRowAmount(), n.getColAmount());
        solutions = new ArrayList<Table>();
        solutions.add(tab);
    }
    
    public boolean checkValidity(Table t, List<int[]> cols) {
        for (int i = 0; i < t.numCols(); i++) {
            if (t.getValueOfColumn(i).length != cols.get(i).length) { //getValueOfColumn geeft getallenrij van kolom in tabel ; extra methode in Table
                return false;
            }

            for (int j = 0; j < t.getValueOfColumn(i).length; j++) {
                if (t.getValueOfColumn(i)[j] != cols.get(i)[j]) {
                    return false;
                }
            }
        }
        return true;
    }
    public List<Table> geefCombinaties(int hoogte, Table table) { //hoogte is de hoogte van je boom
        List<Table> t = new ArrayList<Table>();

        if (hoogte >= list.size()) { //stopvoorwaarde voor recursie
            t.add(table);
            return t;
        }

        for (boolean[] b : list.get(hoogte)) {
            Table temp = table.copy();
            temp.setRow(b, hoogte);

            t.addAll(geefCombinaties(hoogte + 1, temp)); //recursie
        }

        return t;
    }
    public List<boolean[]> getRows(ArrayList<Integer> numbers, int k) {

        List<boolean[]> res = new ArrayList<boolean[]>();

        this.getRowsBT(numbers, 0, new boolean[k], 0, res);

        return res;

    }

    void getRowsBT(ArrayList<Integer> numbers, int i, boolean[] row, int j, List<boolean[]> results) {

        if (i == numbers.size()) {
            if (!results.contains(row)) {
                results.add(row);
            }
            return;
        }

        int tot = 0;
        for (int f = i + 1; f < numbers.size(); f++) {
            tot += numbers.get(f) + 1;
        }
        tot--;

        if (numbers.get(i) >= row.length - j - tot) {
            return;
        }

        boolean[] row1 = row.clone();

        getRowsBT(numbers, i, row1, j + 1, results); //verschuift i

        boolean[] row2 = row1.clone();

        for (int k = j; k < j + numbers.get(i); k++) { //verschuift j
            row2[k] = true;
        }

        getRowsBT(numbers, i + 1, row2, j + numbers.get(i) + 1, results);
    }

    public static String toString(List<boolean[]> LB) {
        String res = "";

        for (int i = 0; i < LB.size(); i++) {
            String lb = toString(LB.get(i));
            System.out.println(lb + " ");
        }


        return res;
    }

    public static String toString(boolean[] b) {
        String res = "[";
        for (boolean bool : b) {
            if (bool) {
                res += "X ";
            } else {
                res += "_ ";
            }
        }
        res += "]";
        return res;
    }

    public static void main(String[] args) {
        
        Scanner sc= new Scanner(System.in);
        
        System.out.println("Voer nu de grootte van de nonogram in: ");
        int y = sc.nextInt();
        int z = sc.nextInt();
        
        Nonogram n = new Nonogram(y, z, "/Users/yaseminsavli/Downloads/src-5/nono.txt");
        Solver s = new Solver(n);
        
        ArrayList<Integer> x;

        for (int i = 0; i < n.getRowAmount(); i++) {
            x = new ArrayList<Integer>();
            for (int a : n.getRow(i)) {
                x.add(a);
            }
            list.add(s.getRows(x, n.getColAmount()));
        }

        List<Table> solutions = new ArrayList<Table>();

        for (Table p : s.geefCombinaties(0, new Table(n.getRowAmount(), n.getColAmount()))) {
            if (s.checkValidity(p, n.getCols())) {
                solutions.add(p);
            }
        }

        System.out.println("Nu worden de oplossingen weergegeven:");

        for (Table t : solutions) {
            System.out.println("Een mogelijke oplossing is:");
            System.out.println(t);
            System.out.println();
        }
    }
}
