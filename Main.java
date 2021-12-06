public class Main {
    public static void main (String[] args) {

        Operations operation = new Operations();
        int[][] boolfunction = {{0, 0, 0, 0},{0, 0, 1, 0},{0, 1, 0, 1},{0,1,1,0}, {1,0,0,0}, {1,0,1,0}, {1,1,0,0},{1,1,1,1}};
        int[] function = operation.getFunction(boolfunction);

        operation.printTruthTable(boolfunction);
        System.out.println("Функція");
        operation.printArray(function);
        System.out.println("Двоїста фунція");
        operation.printArray(operation.getDvoistu(function));

        System.out.println("ДДНФ");
        System.out.println(operation.getDdnf("00100001"));

        System.out.println("ДКНФ");
        System.out.println(operation.getDcnf("00100001"));

        System.out.println("Поліном Жегалкіна");
        System.out.println(operation.getJhegalkin("10100001"));

        System.out.println("Зберігає константу 0");
        System.out.println(operation.isZeroConst("00100001"));

        System.out.println("Зберігає константу 1");
        System.out.println(operation.isOneConst("00100001"));

        System.out.println("Є самодвоїстою");
        System.out.println(operation.isSelfDvoista("00100001"));

        System.out.println("Є монотонною");
        System.out.println(operation.isMonotonous("00100001"));

        System.out.println("Є лінійною");
        System.out.println(operation.isLinear("00100001"));




    }

}
