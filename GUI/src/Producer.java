


import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Producer extends Thread {
    Buffer buffer;
    GUIFrame gui;
    int id;
    int waitTime;
    public int n;
    public int m;
    
    
    Producer(Buffer buffer, GUIFrame gui, int id, int waitTime, int n, int m) {
        this.id = id;
        this.buffer = buffer;
        this.gui = gui;
        this.waitTime = waitTime;
        this.n = n;
        this.m = m;
    }

    
    synchronized String scheme(int n, int m){
    
       int n_1, m_1;
       String x;
         
       int RangoN =  n;
       int RangoM =  m;
        
        if(RangoN < RangoM){ 
            
            Random random = new Random();
            
            
            n_1 = random.nextInt((RangoM - RangoN) + 1) + RangoN;
            m_1 =  random.nextInt((RangoM - RangoN) + 1) + RangoN;
             
            String setOfCharacters = "*/+-";
            int randomInt = random.nextInt(setOfCharacters.length());
            char randomChar = setOfCharacters.charAt(randomInt);
            x = "(" + randomChar +" " + n_1 + " " + m_1 + ")" ;
           
        }
        else{
            x = " El primer valor n tiene que ser menor que m.";   
        }
        
        return x;
    }
    
    @Override
    public void run() {
        System.out.println("Running Producer " + this.id + "...");
        String product = "";
        while (this.buffer.isActive) {
            product = scheme(n,m);
            this.buffer.produce(this.id, product);
            this.buffer.print("Producer " + this.id + " produced: " + product);
            
            try {
                Thread.sleep(waitTime);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    

   
    
}
