


import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Producer extends Thread {
    Buffer buffer;
    int id;
    boolean finish = true;
    int waitTime;
    public int n;
    public int m;
    
    
    Producer(Buffer buffer, int id, int waitTime, int n, int m) {
        this.id = id;
        this.buffer = buffer;
        this.waitTime = waitTime;
        this.n = n;
        this.m = m;
    }

    
    public String scheme(int n, int m){
    
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
    
    public String logic(){
        // while (finish) {
        String product = null;
        for(int i=0 ; i<10 ; i++) {  
            product = scheme(n,m);
            this.buffer.produce(product, waitTime);
            //System.out.println("Producer produced: " + product);
            Buffer.print("Producer " + this.id + " produced: " + product);
            
            
            
            try {
                Thread.sleep(waitTime);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
            //return "Producer " + this.id + " produced: " + product;
            return product;
        }
        return null;
    }
    
    
    
    @Override
    public void run() {
        System.out.println("Running Producer " + this.id + "...");
        String products;

        Random r = new Random(System.currentTimeMillis());
        logic();
       
    }
    
     
    
    public void stopProducer() {
        this.finish = true;
    }

   
    
}
