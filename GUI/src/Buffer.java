


import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

public class Buffer {
    
    public boolean isActive;
    public int counter = 1;
    public int resdiv;
    public int bufferLimit;
    private String aux = "+ 7 7";
    
    //es el pool de todas las operaciones que se crean 
    ArrayList<String> bufferPool = new ArrayList<String>(bufferLimit);
    GUIFrame gui;
    
    //
    Buffer(int bufferLimit, GUIFrame gui,int resdiv) {
        //define cuando se va a deterner la ejecucion
        this.isActive = true;
        this.bufferLimit = bufferLimit;
        this.gui = gui;
        this.resdiv = resdiv;
    }
    
    //mantiene en ciclo constante solo si el bufferpool esta vacío
    synchronized String consume() {
        String product = "";
        while(this.bufferPool.isEmpty()) {
            try {
                wait(); // wait(); Esperar un tiempo indeterminado para poder consumir
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //sirve para remover el primer elemento del buffer pool
        String removed = "||||No operations to be resolved available||||";
        if( !this.bufferPool.isEmpty()){
            removed = this.bufferPool.remove(0);
            notifyAll();
        }
        
        return removed;
    }
    
    //obtiene el string y lo pasa a una forma scheme
    synchronized void produce(int id, String product) {
        while(this.bufferPool.size() >= this.bufferLimit) { // Si el buffer esta lleno, espera
            try {
                wait();// wait(); Esperar un tiempo indeterminado para poder terminar de producir
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.gui.addProducts(id, product, this.resdiv);
        this.bufferPool.add(product);
        notifyAll();
    }
    
    int count = 1;
    synchronized void print(String string) {
        System.out.print(count++ + ".....");
        System.out.println(string);
        printBuffer();
    }
    
    //funcion imprimir buffer
    void printBuffer() {
        System.out.println("\n.................");
        this.bufferPool.forEach(item ->{
            System.out.println(item);
        });
        System.out.println("\n.................");
    }
     
    //función del botón de stop
    synchronized void stopProducerConsumer() {
        this.isActive = false;
    }
    
}
