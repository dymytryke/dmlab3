import java.util.*;

public class Operations {
    public void printTruthTable(int[][] function){
        for (int i = 0; i < function.length; i++) {
            for (int j = 0; j < function[0].length; j++) {
                System.out.print(" " + function[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int[] getFunction(int[][] function){
        int[] func = new int[8];
        for (int i = 0; i < function.length; i++){
            int k = function[i][3];
            func[i] = k;
        }
        return func;
    }

    public int[] getDvoistu(int[] function){
        int[] func = invertArray(function);
        for(int i=0;i<func.length;i++){
            if(func[i] == 0){
                func[i]=1;
            }else func[i]=0;
        }
        return func;
    }

    public String getDdnf(String f) {
        StringBuilder sb = new StringBuilder();
        String[] table = StringTruthTable(f);
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < table.length; i++) {
            if (table[i].charAt(7) == '1') list.add(i);
        }
        for(Integer i: list){
            sb.append(toDeny(Character.digit(table[i].charAt(0), 10), 'x') +
                    toDeny(Character.digit(table[i].charAt(2), 10), 'y') +
                    toDeny(Character.digit(table[i].charAt(4), 10), 'z') +" ");
            sb.append("v ");
        }
        sb.deleteCharAt(sb.length()-2);
        return sb.toString();
    }
    public String getDcnf(String f) {
        StringBuilder sb = new StringBuilder();
        String[] table = StringTruthTable(f);
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < table.length; i++) {
            if (table[i].charAt(7) == '0') list.add(i);
        }
        for(Integer i: list){
            sb.append(toDeny(Character.digit(table[i].charAt(0), 10)^1, 'x') + "v" +
                    toDeny(Character.digit(table[i].charAt(2), 10)^1, 'y') + "v" +
                    toDeny(Character.digit(table[i].charAt(4), 10)^1, 'z') +" ");
            sb.append("^");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.deleteCharAt(sb.length()-2);
        return sb.toString();
    }

    static String getJhegalkin(String f){
        StringBuilder sb = new StringBuilder();
        String[][] table = new String[8][8];
        table[0] = f.split("");
        String[] base = {"1","z", "y", "yz", "x", "xz", "xy", "xyz"};
        for(int i = 1; i < table.length; i++){
            for(int k = 0; k < table[i].length - i; k++){
                table[i][k] = String.valueOf(Integer.parseInt(table[i-1][k]) ^ Integer.parseInt(table[i-1][k+1]));
            }
        }
        for(int i = 1; i < table.length; i++){
            if (table[i][0].equals("1")) sb.append(base[i-1] + " + ");
        }
        sb.deleteCharAt(sb.length()-2);
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    public boolean isZeroConst(String f){
        if (f.charAt(0) == '0') return true;
        return false;
    }
    public boolean isOneConst(String f){
        if (f.charAt(f.length()-1) == '1') return true;
        return false;
    }
    public boolean isSelfDvoista(String f){
        return stringDvoista(f).equals(f);
    }
    public boolean isMonotonous(String f){
        String[] x = f.split("");
        return x[0].compareTo(x[1]) <= 0 && x[0].compareTo(x[2]) <= 0 && x[0].compareTo(x[4]) <= 0
                && x[1].compareTo(x[3]) <= 0 && x[1].compareTo(x[5]) <= 0 &&
                x[2].compareTo(x[3]) <= 0 && x[2].compareTo(x[6]) <= 0 &&
                x[4].compareTo(x[5]) <= 0 && x[4].compareTo(x[6]) <= 0 &&
                x[4].compareTo(x[7]) <= 0 && x[5].compareTo(x[7]) <= 0 && x[6].compareTo(x[7]) <= 0;
    }
    public boolean isLinear(String f){
        String polinom = getJhegalkin(f);
        String[] x = polinom.split(" \\+ ");
        for(String y:x){
            y = y.trim();
            if(y.length() > 1) return false;
        }
        return true;
    }

    //Утильные методы
    public String[] StringTruthTable(String f) {
        String[] x = f.split("");
        return new String[]{"x y z  F\n", "0 0 0  " + x[0] + "\n", "0 0 1  " + x[1] + "\n", "0 1 0  " + x[2] + "\n", "0 1 1  " + x[3] + "\n", "1 0 0  "
                + x[4] + "\n", "1 0 1  " + x[5] + "\n", "1 1 0  " + x[6] + "\n", "1 1 1  " + x[7] + "\n"};


    }
    private String toDeny(int i, Character x){
        if(i == 1) return x.toString();
        if(i == 0) return x.toString() + "`";
        else return "Не є бінарною";
    }


    public int[] invertArray(int[] function){
        for (int i = 0; i < function.length / 2; i++) {
            int temp = function[i];
            function[i] = function[function.length - 1 - i];
            function[function.length - 1 - i] = temp;
        }
        return function;
    }

    public void printArray(int[] array){
        for(int i=0;i< array.length; i++){
            System.out.println(array[i]);
        }
    }
    public String stringDvoista(String f) {
        StringBuilder res = new StringBuilder();
        String[] x = f.split("");
        for (int i = x.length - 1; i >= 0; i--) {
            res.append(Integer.parseInt(x[i]) ^ 1);
        }
        return res.toString();
    }

}
