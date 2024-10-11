public class test {
    public static void main(String[] args) {
        double pre = 75.47;
        double rec = 79.01;
        double f1 = 2 * ((pre * rec) / (pre + rec));
        System.out.println("F1 score: " + f1);

    }
}