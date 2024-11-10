import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class test {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("apple ", "banana ", "orange ", "grape ", "orange ");
        LinkedHashSet<String> collect = list.stream().sorted().collect(Collectors.toCollection(LinkedHashSet::new));
        System.out.println(collect);
    }
}