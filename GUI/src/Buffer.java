


import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

public class Buffer {
    
    public boolean isActive;
    public int counter = 0;
//    private int buffer;
    public int bufferLimit;
    private String aux = "+ 7 7";
    ArrayList<String> bufferPool = new ArrayList<String>(bufferLimit);
    
    Buffer(int bufferLimit, GUIFrame gui) {
        this.isActive = true;
//        this.buffer = 0;
        this.bufferPool.add("");
        this.bufferLimit = bufferLimit;
    }
    
    
    synchronized String consume(int waitTime) {
//        int product = 0;
        String product = "";
        
//        if(this.buffer == 0) {
        try {
            wait(); // wait(); Esperar un tiempo indeterminado para poder consumir
        } catch (InterruptedException ex) {
            Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
        }
//        }
        product = this.aux;
        this.bufferPool.add(0,"");
//        product = this.buffer;
//        this.buffer = 0;
        notifyAll();
        
        return product;
    }
    
    synchronized void produce(String product, int waitTime) {
//        if(this.buffer != 0) {
            try {
                wait();// wait(); Esperar un tiempo indeterminado para poder terminar de producir
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
//        }
//        this.buffer = product;
        this.aux = product;
        this.bufferPool.add(product);
        notifyAll();
    }
    
    static int count = 1;
    synchronized void print(String string) {
        System.out.print(count++ + ".....");
        System.out.println(string);
        
        System.out.println(".................");
        this.bufferPool.forEach(item ->{
            System.out.println(item);
        });
        System.out.println(".................");
    }
    
    public int incrementCount(){
        return this.counter += 1;
    }
     public int decrementCount(){
        return this.counter -= 1;
    }
     
    public void stopProducerConsumer() {
        this.isActive = false;
    }
    
}
