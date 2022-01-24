


import java.util.logging.Level;
import java.util.logging.Logger;

public class Buffer {
    
    private int buffer; //se podría usar una lista, ver si la lista esta vacía o rpeguntar por el size.
    
    Buffer() {
        this.buffer = 0;
    }
    
    
    synchronized int consume() {
        int product = 0;
        
        if(this.buffer == 0) {  //para múltiples, se puede usar un while 
            try {
                wait(); // wait(); Esperar un tiempo indeterminado para poder consumir
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        product = this.buffer;
        this.buffer = 0;
        notify();
        
        return product;
    }
    
    synchronized void produce(int product) {
        if(this.buffer != 0) { //para múltiples, se puede usar un while 
            try {
                wait();// wait(); Esperar un tiempo indeterminado para poder terminar de producir
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.buffer = product;
        
        notify(); //levanta a uno por cada wait  , si uso notify all levanta todos
    }
    
    //esta función se puede eliminar 
    static int count = 1;
    synchronized static void print(String string) {
        System.out.print(count++ + " ");
        System.out.println(string);
    }
    
}
