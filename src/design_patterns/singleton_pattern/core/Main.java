package design_patterns.singleton_pattern.core;

public class Main {

    public static void main(String[] args) {


        MyClass myClass = MyClass.getInstance();


        myClass.name = "Class";

        System.out.println(myClass);





//        System.out.println("Second instance " + secondClass);
//
//        System.out.println("Object person: " + person);
//
//        System.out.println("Second Object person: " + secondPerson);
    }
}
