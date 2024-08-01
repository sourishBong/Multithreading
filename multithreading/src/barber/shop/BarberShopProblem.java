package barber.shop;


import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class BarberShopProblem {

    final int CHAIRS = 3;

    Semaphore waitForCustomerToEnter = new Semaphore(0);
    Semaphore waitForBarberToGetReady = new Semaphore(0);
    Semaphore waitForCustomerToLeave = new Semaphore(0);
    Semaphore waitForBarberToCutHair = new Semaphore(0);

    int waitingCustomers = 0;
    ReentrantLock lock = new ReentrantLock();
    int hairCutsGiven = 0;

    void customerWalksIn() throws InterruptedException {
        lock.lock();
        if (waitingCustomers == CHAIRS) {
            System.out.println("Customer walks out, all chairs occupied");
            lock.unlock();
            return;
        }
        waitingCustomers++;
        lock.unlock();

        waitForCustomerToEnter.release();
        waitForBarberToGetReady.acquire();

        waitForBarberToCutHair.acquire();
        waitForCustomerToLeave.release();

        lock.lock();
        waitingCustomers--;
        lock.unlock();
    }

    void barber() throws InterruptedException {

        while (true) {
            waitForCustomerToEnter.acquire();
            waitForBarberToGetReady.release();
            hairCutsGiven++;
            System.out.println("Barber cutting hair..." + hairCutsGiven);
            Thread.sleep(50);
            waitForBarberToCutHair.release();
            waitForCustomerToLeave.acquire();
        }
    }

}
