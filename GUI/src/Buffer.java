


import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

public class Buffer {
    

   
    private int bufferLimit;
    private String aux;
    public int counter = 0;
    ArrayList<String> buffer = new ArrayList<>(bufferLimit);


    
    Buffer(int bufferLimit, GUIFrame gui) {
        this.buffer.add(0,"");
        this.bufferLimit = bufferLimit;
    }
    
    
    synchronized String consume(int waitTime) {
        String product = "";
        
        if(this.buffer.get(0).isEmpty()) {  //para múltiples, se puede usar un while 
            try {
                wait(); // wait(); Esperar un tiempo indeterminado para poder consumir

            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        product = this.aux;
        this.buffer.add(0,"");//Checar esto por favor, por el momento esta asignando la 
//      primera posicion a un string vacio.
        notifyAll();
        
        return product;
    }

    synchronized void produce(String product, int waitTime) {
        if(counter <= this.bufferLimit) {
            try {
                wait();// wait(); Esperar un tiempo indeterminado para poder terminar de producir
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
            //this.buffer += 1;
        }
        this.aux = product;
        
        notifyAll(); //levanta a uno por cada wait  , si uso notify all levanta todos
    }
    
    //esta función se puede eliminar 
    static int count = 1;
    synchronized static void print(String string) {
        System.out.print(count++ + ".....");
        System.out.println(string);
    }
    
    public int countProductor(){
        return this.counter += 1;
    }
     public int countConsumidor(){
        return this.counter -= 1;
    }
    
    
    
    
}
