


import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Producer extends Thread {
    Buffer buffer;
    boolean finish = false;
    
    Producer(Buffer buffer) {
        this.buffer = buffer;
    }
    
    @Override
    public void run() {
        System.out.println("Running Producer...");
        String products = "AEIOU";
        Random r = new Random(System.currentTimeMillis());
        int product = 0;
        
        if (0 > product && product < 10){
            System.out.println("Value out of range");
        }
        
        // while (finish) {
        for(int i=0 ; i<10 ; i++) {
            product = products.charAt(r.nextInt(5));
            this.buffer.produce(product);
            //System.out.println("Producer produced: " + product);
            Buffer.print("Producer produced: " + product);
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void stopProducer() {
        this.finish = true;
    }
    
    
}
