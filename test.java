import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

class C1 {
    @Override
    public int hashCode() {
        return 0x12121212;
    }

    @Override
    public String toString() {
        return "C1";
    }
}

class C2 {
    @Override
    public int hashCode() {
        return 0x12120000;
    }

    @Override
    public String toString() {
        return "C2";
    }
}

class C3 {
    @Override
    public int hashCode() {
        return 2;
    }

    @Override
    public String toString() {
        return "C3";
    }
}

class C4 {
    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public String toString() {
        return "C4";
    }
}

public class test {
    public static void main(String[] args) {
        Set set = new HashSet<>();
        for(int i=0; i<10000; ++i){
            set.add(i);
        }
        System.out.println(set);
    }
}