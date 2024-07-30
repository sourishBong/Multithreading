package threadSafeSingleton;

public class Demo {

    public static void main(String[] args) {

        Circle circle=Circle.getInstance();

        System.out.println(circle.toString());
    }
}
