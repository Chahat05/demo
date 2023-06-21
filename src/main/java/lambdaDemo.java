import ch.qos.logback.core.CoreConstants;

@FunctionalInterface
interface A
        {
            void show();
        }

        //functional interface can only have one method except it can have object class methods as every class in java extends to Object class by default .

//class B implements A {
// public void show(){
//     System.out.println("HI");
// }
//}

public class lambdaDemo {
    public static void main(String[] args) {
        //A obj= new B(){gvdasidgfaiuyfdv}
        
        A object=  () -> System.out.println("HI");;
        object.show();

    }
}
