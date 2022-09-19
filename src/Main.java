import java.util.LinkedList;
import java.util.List;

public class Main {

    final static protected List<String> cars = new LinkedList<>();
    final static protected Integer carsNumber = 10;
    final static protected Integer produceTimePerOneCarInSeconds = 2;
    final static protected Integer readinessToWaitInSeconds = 1;

    // if we have 2 threads limit every thread with half of work:
    final static protected Integer limitCarsNumbersPerHands = carsNumber / 2;

    public static void main(String[] args) {
        Thread threadProducer = new ThreadProducer(cars, carsNumber, produceTimePerOneCarInSeconds);
        threadProducer.start();
        ThreadBuyer threadBuyer = new ThreadBuyer(cars, 1, readinessToWaitInSeconds, limitCarsNumbersPerHands);
        threadBuyer.start();
        Thread threadBuyer2 = new ThreadBuyer(cars, 2, readinessToWaitInSeconds, limitCarsNumbersPerHands);
        threadBuyer2.start();
    }

}