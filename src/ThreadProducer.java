class ThreadProducer extends Thread {
    final Store store;
    final Integer producingTime;
    final String carsName;


    public ThreadProducer(ThreadGroup threadGroup, String threadName, Store store,
                          Integer producingTime, String carsName) {
        super(threadGroup, threadName);
        this.store = store;
        this.producingTime = producingTime;
        this.carsName = carsName;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                Thread.sleep(producingTime);
            } catch (InterruptedException e) {
                System.out.println("Производитель завершил работу " + Thread.currentThread().getName());
                return;
            }
            store.receiveCar(carsName);
        }

    }
}
