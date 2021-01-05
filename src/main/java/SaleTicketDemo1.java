public class SaleTicketDemo1 {
    /**
     * 真正的多线程开发
     * 线程就是一个单独的资源类，没有任何附属操作，就是属性和方法
     * 采用sync上锁
     * @param args
     */
    public static void main(String[] args) {
        final Ticket ticket = new Ticket();
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

class Ticket{
    private int tickets = 20;

    public synchronized void sale(){
        while (tickets>0){

            tickets--;
            System.out.println("卖出了"+(20-tickets)+"张票");
        }


    }
}
