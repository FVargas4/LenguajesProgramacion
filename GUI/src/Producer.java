


import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Producer extends Thread {
    Buffer buffer;
    int id;
    boolean finish = false;
    int waitTime;
    public int n;
    public int m;
    
    
    Producer(Buffer buffer, int id, int waitTime) {
        this.id = id;
        this.buffer = buffer;
        this.waitTime = waitTime;
    }

    
    public String scheme(Object n, Object m){
    
       int n_1, m_1;
       String x;
         
       int RangoN = (int) n;
       int RangoM = (int) m;
       System.out.println("Tu valor n es:" + RangoN );
       System.out.println("Tu valor m es:" + RangoM );
        
        if(RangoN < RangoM){ 
            
            Random random = new Random();
            
            
            n_1 = random.nextInt((RangoM - RangoN) + 1) + RangoN;
            m_1 =  random.nextInt((RangoM - RangoN) + 1) + RangoN;
             
            String setOfCharacters = "*/+-";
            int randomInt = random.nextInt(setOfCharacters.length());
            char randomChar = setOfCharacters.charAt(randomInt);
            x = "(" + randomChar + n_1 + " " + m_1 + ")" ;
           
        }
        else{
            x = " El primer valor n tiene que ser menor que m.";   
        }
        
        return x;
    }
    
    @Override
    public void run() {
        System.out.println("Running Producer " + this.id + "...");
        String products = "AEIOU";
        Random r = new Random(System.currentTimeMillis());
        int product = 0;
        
        if (0 > product && product < 10){
            System.out.println("Value out of range");
        }
        
        // while (finish) {
        for(int i=0 ; i<10 ; i++) {
            product = products.charAt(r.nextInt(5));
            this.buffer.produce(product, waitTime);
            //System.out.println("Producer produced: " + product);
            Buffer.print("Producer " + this.id + " produced: " + product);
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void stopProducer() {
        this.finish = true;
    }

   
    
}
