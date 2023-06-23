import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class SteamsDemo {
    public static void main(String[] args) {
        List<Integer> nums= Arrays.asList(3,6,4,1,8,7);
       // Stream<Integer> data=nums.stream()//returns a stream of integers that is stored in data.


        nums.stream()
                .filter(n->n%2==1) //n->n%2==1 is a predicate function. predicate is a functional interfaqce
                .sorted()
                .map(n->n*2)
                .forEach(n->System.out.println(n));  //here at every step we are replacing the streams and not duplicating it



//       int result= nums.stream()
//                .filter(n->n%2==1)
//                .map(n->n*2)
//                .reduce(0,(c,e)->c+e);
//        System.out.println(result);



        //data.forEach(n-> System.out.println(n));
        //data.forEach(n-> System.out.println(n)); ---- this throws error as data is already used once, we cant reuse the stream





        //nums.forEach(n-> System.out.println(n));
    }
}
