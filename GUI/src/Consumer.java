


import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumer extends Thread {
    Buffer buffer;
    int id;
    boolean finish = false;
    
    Consumer(Buffer buffer, int id) {
        this.id = id;
        this.buffer = buffer;
    }
    
    @Override
    public void run() {
        System.out.println("Running Consumer " + this.id + "...");
        int product = 0;
        
        if (0 > product && product < 10){
            System.out.println("Value out of range");
        }
        
        // while (finish) {
        for(int i=0 ; i<10 ; i++) {
            product = this.buffer.consume();
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
