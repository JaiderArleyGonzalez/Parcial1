package edu.eci.arsw.math;

public class PiThread extends Thread{
    private int startIndex, endIndex, N;
    private final Monitor monitor;
    private String datos;

    public byte[] digits;
    public PiThread(int startIndex, int endIndex, int N, Monitor monitor){
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.N=N;
        this.monitor=monitor;
    }
    public void run(){
        try {
            monitor.esperarSiSuspendido();	
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        digits = PiDigits.getDigits(startIndex, endIndex, N);
    }
    
    public synchronized int getDatos(){
        return PiDigits.getDatos();
    }
    public byte[] getDigits(){
        return digits;
    }
    
}
