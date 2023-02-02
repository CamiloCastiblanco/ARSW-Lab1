package edu.eci.arsw.math;

public class PidDigitsThread extends Thread {
    int digits1;
    int digits2;
    byte[] digits3;
    public PidDigitsThread(int ini, int fin){
        digits1 = ini;
        digits2 = fin;      
    }
    public byte[] digits3(){
        return digits3;
    }
    public void run(){                 
        digits3 = PiDigits.getDigits(digits1, digits2);        
        }
    }    
    

