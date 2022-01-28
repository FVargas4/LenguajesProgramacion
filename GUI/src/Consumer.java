


import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumer extends Thread {
    Buffer buffer;
    int id;
    boolean finish = false;
    int waitTime;
    
    Consumer(Buffer buffer, int id, int waitTime) {
        this.id = id;
        this.buffer = buffer;
        this.waitTime = waitTime;
    }
    
    @Override
    public void run() {
        System.out.println("Running Consumer " + this.id + "...");
        String product ;
       
        // while (finish) {
        for(int i=0 ; i<10 ; i++) {
            product = this.buffer.consume(this.waitTime);
            //System.out.println("Consumer consumed: " + product);
            Buffer.print("Consumer " + this.id + " consumed: " + product);
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void stopConsumer() {
        this.finish = true;
    }
}
