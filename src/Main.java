import java.util.LinkedList;
import java.util.List;

public class Main {

    final static protected List<String> cars = new LinkedList<>();
    final static protected Integer carsNumber = 10;
    final static protected Integer produceTimePerOneCarInSeconds = 2;
    final static protected Integer readinessToWaitInSeconds = 1;

    public static void main(String[] args) {
        Thread threadProducer = new ThreadProducer(cars, carsNumber, produceTimePerOneCarInSeconds);
        threadProducer.start();
        ThreadBuyer threadBuyer = new ThreadBuyer(cars, 1, readinessToWaitInSeconds);
        threadBuyer.start();
        ThreadBuyer threadBuyer2 = new ThreadBuyer(cars, 2, readinessToWaitInSeconds);
        threadBuyer2.start();

        while(true) {
            System.out.println(threadBuyer.getQuantityOfBoughtCars() + threadBuyer2.getQuantityOfBoughtCars());

            if (threadBuyer.getQuantityOfBoughtCars() + threadBuyer2.getQuantityOfBoughtCars() == 10) {
                threadBuyer.interrupt();
                threadBuyer2.interrupt();
                break;
            }
        }
    }
}