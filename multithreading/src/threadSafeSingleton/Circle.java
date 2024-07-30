package threadSafeSingleton;

public class Circle {

    private static Circle circle;

    private int radius;

    private Circle() {
        this.radius=12;
    }

    public static Circle getInstance(){
        if(circle==null){
            synchronized (Circle.class){
                if(circle==null)
                    circle=new Circle();
            }
        }
        return circle;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "radius=" + radius +
                '}';
    }
}
