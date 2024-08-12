package blocking.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

class FirstPriorityWorker implements Runnable{

    private BlockingQueue<Person> queue;

    public FirstPriorityWorker(BlockingQueue<Person> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        try {
            queue.put(new Person(12,"Sourish"));
            queue.put(new Person(12,"Sarmistha"));
            queue.put(new Person(12,"Sourabh"));
            Thread.sleep(2000);
            queue.add(new Person(12,"Dipyaman"));
            Thread.sleep(1000);
            queue.add(new Person(12,"Debanjali"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class SecondPriorityWorker implements Runnable{

    private BlockingQueue<Person> queue;

    public SecondPriorityWorker(BlockingQueue<Person> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        try {
            Thread.sleep(5000);
            System.out.println(queue.take());
            Thread.sleep(1000);
            System.out.println(queue.take());
            Thread.sleep(2000);
            System.out.println(queue.take());
            System.out.println(queue.take());
            System.out.println(queue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Person implements Comparable<Person>{

    private int age;
    private String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public int compareTo(Person person) {
        return name.compareTo(person.getName());
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}

public class PriorityQueueExample {

    public static void main(String[] args) {
        BlockingQueue<Person> queue=new PriorityBlockingQueue<>();

        FirstPriorityWorker first=new FirstPriorityWorker(queue);
        SecondPriorityWorker second=new SecondPriorityWorker(queue);

        new Thread(first).start();
        new Thread(second).start();
    }
}
