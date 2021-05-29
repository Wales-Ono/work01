import java.util.ArrayList;
import java.util.List;

public class Kadai3{
    public static void main(String[] args){
        // List<String> list = new ArrayList<String>();
        // for(String arg : args){
        //     list.add(arg);
        //     System.out.println(arg);
        // }
        @SafeVarargs
        List<String> lists = Arrays.asList(args);
        for(String list : lists){
            System.out.println(list);
        }
    }
}