import java.util.List;

class ThreadProducer extends Thread {
    final protected List<String> cars;
    final protected Integer carsNumber;
    final protected Integer produceTimePerOneCarInSeconds;


    public ThreadProducer(List<String> cars, Integer carsNumber, Integer produceTimePerOneCarInSeconds) {
        this.cars = cars;
        this.carsNumber = carsNumber;
        this.produceTimePerOneCarInSeconds = produceTimePerOneCarInSeconds;
    }

    @Override
    public void run() {
        for (int i = 0; i < carsNumber; i++) {
            synchronized (cars) {
                cars.add("автомобиль номер " + i);
                System.out.println("Производитель выпустил одну машину с номером: " + i);
                cars.notify();
            }
            try {
                Thread.sleep(produceTimePerOneCarInSeconds * 1_000);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
