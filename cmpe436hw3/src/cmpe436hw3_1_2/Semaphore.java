package cmpe436hw3_1_2;


public class Semaphore {

    protected int permit = 0;

    public Semaphore() {
        permit = 0;
    }

    public Semaphore(int permit) {
        this.permit = permit;
    }

    public synchronized void P() {
        permit--;
        if (permit < 0) {
            try {
                wait();
            } catch (InterruptedException e) {

            }
        }
    }

    public synchronized void V() {
        permit++;
        if (permit >= 0) {
            notify();
        }
    }

}
