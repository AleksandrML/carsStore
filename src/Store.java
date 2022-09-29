import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Store {
    private final List<String> cars = new LinkedList<>();
    private final int carNumForSellPlan;
    private final AtomicInteger soldCar = new AtomicInteger(0);

    public Store(int carNumForSellPlan) {
        this.carNumForSellPlan = carNumForSellPlan;
    }

    public synchronized void sellCar() {
        try {
            System.out.println(Thread.currentThread().getName() + " зашел в автосалон");
            while (cars.size() == 0) {
                System.out.println("Машин нет");
                wait();
            }

            System.out.println(Thread.currentThread().getName() + " уехал на новой машине");
            System.out.println("Продано " + soldCar.incrementAndGet() + " машин");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(Thread.currentThread().getName() + " завершил покупки");
            return;
        }
        cars.remove(0);
    }

    public synchronized void receiveCar(String carsName) {
        cars.add(carsName);
        System.out.println("Производитель выпустил автомобиль " + carsName);
        notify();
    }

    public boolean isFinishedSellPlan() {
        return soldCar.get() >= carNumForSellPlan;
    }
}
