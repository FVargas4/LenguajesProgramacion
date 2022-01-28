


import java.util.logging.Level;
import java.util.logging.Logger;

public class Buffer {
    
    private String buffer;
    private final int bufferLimit;
    
    Buffer(int bufferLimit) {
        this.buffer = "";
        this.bufferLimit = bufferLimit;
    }
    
    
    synchronized String consume(int waitTime) {
        String product = "";
        
        if("".equals(this.buffer)) {
            try {
                wait(waitTime); // wait(); Esperar un tiempo indeterminado para poder consumir
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        product = this.buffer;
        this.buffer = "";
        notify();
        
        return product;
    }
    
    synchronized void produce(String product, int waitTime) {
        for (int i = 0; i < this.bufferLimit;i++) {
            try {
                wait(waitTime);// wait(); Esperar un tiempo indeterminado para poder terminar de producir
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.buffer += 1;
        }
        this.buffer = product;
        
        notify();
    }
    
    static int count = 1;
    synchronized static void print(String string) {
        System.out.print(count++ + ".....");
        System.out.println(string);
    }
    
}
