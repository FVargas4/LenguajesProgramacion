


import java.util.logging.Level;
import java.util.logging.Logger;

public class Buffer {
    
    private int buffer;
    private int bufferLimit;
    
    Buffer(int bufferLimit) {
        this.buffer = 0;
        this.bufferLimit = bufferLimit;
    }
    
    
    synchronized int consume(int waitTime) {
        int product = 0;
        
        if(this.buffer == 0) {
            try {
                wait(waitTime); // wait(); Esperar un tiempo indeterminado para poder consumir
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        product = this.buffer;
        this.buffer = 0;
        notify();
        
        return product;
    }
    
    synchronized void produce(int product, int waitTime) {
        if(this.buffer != this.bufferLimit) {
            try {
                wait(waitTime);// wait(); Esperar un tiempo indeterminado para poder terminar de producir
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
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
