public class Main {

    final static Integer carsNumber = 10;
    final static Store store = new Store(carsNumber);
    final static Integer produceTimeMs = 2_500;
    final static Integer readinessToWaitInSeconds = 3;

    public static void main(String[] args) {
        ThreadGroup threadGroup = new ThreadGroup("group");
        new ThreadProducer(threadGroup, "Тред производителя", store, produceTimeMs, "Toyota").start();

        for (int i = 1; i < 11; i++) {
            new ThreadBuyer(threadGroup, "Покупатель-тред " + i, store, readinessToWaitInSeconds).start();
        }

        while (!store.isFinishedSellPlan()) {
        }
        System.out.println("План выполнен");
        threadGroup.interrupt();
    }

}