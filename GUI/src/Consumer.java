


import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumer extends Thread {
    Buffer buffer;
    GUIFrame gui;
    int id;
    public int resdiv;
    int waitTime;
    
    Consumer(Buffer buffer, GUIFrame gui, int id, int waitTime,int resdiv) {
        this.id = id;
        this.buffer = buffer;
        this.gui = gui;
        this.waitTime = waitTime;
        this.resdiv =resdiv;
        
    }
    
    @Override
    public void run() {
        System.out.println("Running Consumer " + this.id + "...");
        String product;
        while (this.buffer.isActive) {
            product = this.buffer.consume();
            this.buffer.print("Consumer " + this.id + " consumed: " + product);
            this.gui.removeProducts(this.id, product,this.resdiv); 
            try {
                Thread.sleep(waitTime);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
