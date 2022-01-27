


import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumer extends Thread {
    Buffer buffer;
    int id;
    boolean finish = false;
    int waitTime;
    
    Consumer(Buffer buffer, int id, int waitTime) {
        this.id = id;
        this.buffer = buffer;
        this.waitTime = waitTime;
    }

    public String translate(String mensaje){
        int a = 0;
        int b = 0;
        char op = 0;
        String response;
        String numbers = "0123456789";

        for(int i = 0; i < mensaje.length(); i++){
            char c = mensaje.charAt(i);
            switch(c){
                case ' ':
                    continue;
                case '(':
                    continue;
                case '-':
                    op = c;
                case '+':
                    op = c;
                case '/':
                    op = c;
                case '*':
                    op = c;
                default:
                if(numbers.contains(c)){
                    if(a != 0){
                        a = c;
                    }else{
                        b = c;
                    }}
            }
            response = a + op+ b;
        }
        
        return response;
    }

    @Override
    public void run() {
        System.out.println("Running Consumer " + this.id + "...");
        int product = 0;
        
        if (0 > product && product < 10){
            System.out.println("Value out of range");
        }
        
        

        // while (finish) {
        for(int i=0 ; i<10 ; i++) {
            product = this.buffer.consume(this.waitTime);
            //System.out.println("Consumer consumed: " + product);
            Buffer.print("Consumer " + this.id + " consumed: " + product);
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void stopConsumer() {
        this.finish = true;
    }
}
