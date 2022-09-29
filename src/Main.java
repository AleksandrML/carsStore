public class Main {

    final static protected Integer carsNumber = 10;
    final static protected Store store = new Store(carsNumber);
    final static protected Integer produceTimeMs = 2_500;
    final static protected Integer readinessToWaitInSeconds = 3;

    public static void main(String[] args) {
        ThreadGroup threadGroup = new ThreadGroup("group");
        new ThreadProducer(threadGroup, "Тред производителя", store, produceTimeMs, "Toyota").start();
        new ThreadBuyer(threadGroup, "Покупатель-тред 1", store, readinessToWaitInSeconds).start();
        new ThreadBuyer(threadGroup, "Покупатель-тред 2", store, readinessToWaitInSeconds).start();
        new ThreadBuyer(threadGroup, "Покупатель-тред 3", store, readinessToWaitInSeconds).start();

        while (!store.isFinishedSellPlan()) {
        }
        System.out.println("План выполнен");
        threadGroup.interrupt();
    }

}