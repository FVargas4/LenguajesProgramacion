


import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

public class Buffer {
    
    public boolean isActive;
    public int counter = 1;
    public int bufferLimit;
    private String aux = "+ 7 7";
    ArrayList<String> bufferPool = new ArrayList<String>(bufferLimit);
    
    Buffer(int bufferLimit, GUIFrame gui) {
        this.isActive = true;

        this.bufferLimit = bufferLimit;
    }
    
    
    synchronized String consume(int waitTime) {
//        int product = 0;
        String product = "";
        
        while(this.bufferPool.isEmpty()) {
            try {
                wait(); // wait(); Esperar un tiempo indeterminado para poder consumir
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        String removed = "||||No operations to be resolved available||||";
        if( !this.bufferPool.isEmpty()){
            removed = this.bufferPool.remove(0);
        }
        
        notifyAll();
        
        return removed;
    }
    
    synchronized void produce(String product, int waitTime) {
        while(!this.bufferPool.isEmpty()) {
            try {
                wait();// wait(); Esperar un tiempo indeterminado para poder terminar de producir
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        this.bufferPool.add(product);
        notifyAll();
    }
    
    int count = 1;
    synchronized void print(String string) {
        System.out.print(count++ + ".....");
        System.out.println(string);
        printBuffer();
    }
    
    void printBuffer() {
        System.out.println("\n.................");
        this.bufferPool.forEach(item ->{
            System.out.println(item);
        });
        System.out.println("\n.................");
    }
    
    synchronized int incrementCount(){
        return this.counter += 1;
    }
    synchronized int decrementCount(){
        return this.counter -= 1;
    }
     
    synchronized void stopProducerConsumer() {
        this.isActive = false;
        this.count = 0;
    }
    
}
