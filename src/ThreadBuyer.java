import java.util.List;

class ThreadBuyer extends Thread {
    final protected List<String> cars;
    final protected Integer buyerNumber;
    final protected Integer readinessToWaitInSeconds;
    protected Integer maxCarsNumberPerHands;

    public ThreadBuyer(List<String> cars, Integer buyerNumber, Integer readinessToWaitInSeconds, Integer maxCarsNumberPerHands) {
        this.cars = cars;
        this.buyerNumber = buyerNumber;
        this.readinessToWaitInSeconds = readinessToWaitInSeconds;
        this.maxCarsNumberPerHands = maxCarsNumberPerHands;
    }

    @Override
    public void run() {
        Integer boughtCarsNumber = 0;
        while (boughtCarsNumber < maxCarsNumberPerHands) {
            synchronized (cars) {
                System.out.println("Покупатель номер " + buyerNumber + " зашел в автосалон");
                if (cars.isEmpty()) {
                    try {
                        cars.wait(readinessToWaitInSeconds * 1_000);
                        System.out.println("Автомобилей нет, не дождался");
                        continue;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Покупатель " + buyerNumber + " купил автомобиль номер: " + cars.remove(0));
                boughtCarsNumber += 1;
            }
        }
    }
}
