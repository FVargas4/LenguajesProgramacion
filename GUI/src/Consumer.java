


import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumer extends Thread {
    Buffer buffer;
    int id;
    int waitTime;
    
    Consumer(Buffer buffer, int id, int waitTime) {
        this.id = id;
        this.buffer = buffer;
        this.waitTime = waitTime;
    }
    
    @Override
    public void run() {
        System.out.println("Running Consumer " + this.id + "...");
        String product;
        
        // while (finish) {
//        for(int i=0 ; i<10 ; i++) {
//            product = this.buffer.consume(this.waitTime);
//            //System.out.println("Consumer consumed: " + product);
//            Buffer.print("Consumer " + this.id + " consumed: " + product);
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
        while (this.buffer.isActive) {
            if(this.buffer.counter > 0) {

                product = this.buffer.consume(this.waitTime);
                //System.out.println("Consumer consumed: " + product);
                this.buffer.print("Consumer " + this.id + " consumed: " + product);

                try {
                    Thread.sleep(waitTime);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.buffer.decrementCount();
            }
        }
    }
    
}
