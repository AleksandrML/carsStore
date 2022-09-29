public class Main {

    final static protected Integer carsNumber = 10;
    final static protected Store store = new Store(carsNumber);
    final static protected Integer produceTimeMs = 2_500;
    final static protected Integer readinessToWaitInSeconds = 3;

    public static void main(String[] args) {
        ThreadGroup threadGroup = new ThreadGroup("group");
        new ThreadProducer(threadGroup, "Тред производителя", store, produceTimeMs, "Toyota").start();

        for (int i = 1; i < 11; i++) {
            new ThreadBuyer(threadGroup, "Покупатель-тред " + i, store, readinessToWaitInSeconds).start();
        }
//        new ThreadBuyer(threadGroup, "Покупатель-тред 1", store, readinessToWaitInSeconds).start();
//        new ThreadBuyer(threadGroup, "Покупатель-тред 2", store, readinessToWaitInSeconds).start();
//        new ThreadBuyer(threadGroup, "Покупатель-тред 3", store, readinessToWaitInSeconds).start();

        while (!store.isFinishedSellPlan()) {
        }
        System.out.println("План выполнен");
        threadGroup.interrupt();
    }

}