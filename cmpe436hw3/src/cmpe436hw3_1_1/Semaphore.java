package cmpe436hw3_1_1;

public class Semaphore {

    protected int permit = 0;
    protected int id = 0;

    public Semaphore() {
        permit = 0;
    }

    public Semaphore(int permit,int id) {
        this.permit = permit;
        this.id = id;
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
