import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SaleTicketDemo2 {
    /**
     * 采用lock上锁
     * @param args
     */
    public static void main(String[] args) {
        Ticket2 ticket = new Ticket2();
        new Thread(()->{
            ticket.sale();
        }).start();
        new Thread(()->{
            ticket.sale();
        }).start();
        new Thread(()->{
            ticket.sale();
        }).start();
    }
}

class Ticket2{
    private int tickets = 20;

    Lock lock = new ReentrantLock();


    public void sale(){
        lock.lock();//加锁
        try {
            while (tickets>0){

                tickets--;
                System.out.println("卖出了"+(20-tickets)+"张票");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();//解锁
        }


    }
}
