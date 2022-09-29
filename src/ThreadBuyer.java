
class ThreadBuyer extends Thread {
    final Store store;
    final Integer readinessToWaitInSeconds;

    public ThreadBuyer(ThreadGroup threadGroup, String threadName, Store store, Integer readinessToWaitInSeconds) {
        super(threadGroup, threadName);
        this.store = store;
        this.readinessToWaitInSeconds = readinessToWaitInSeconds;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                Thread.sleep(readinessToWaitInSeconds * 1_000);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " завершил покупки");
                return;
            }
            store.sellCar();
            break;
        }
    }
}
