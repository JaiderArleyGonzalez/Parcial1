package edu.eci.arsw.math;

public class PiThread extends Thread{
    public int startIndex, endIndex, N;
    
    public byte[] digits;
    public PiThread(int startIndex, int endIndex, int N){
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.N=N;
    }
    public void run(){
        digits = PiDigits.getDigits(startIndex, endIndex, N);
    }
    public byte[] getDigits(){
        return digits;
    }
}
