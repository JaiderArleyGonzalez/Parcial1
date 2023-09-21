/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.math;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author hcadavid
 */
public class Main {

    public static void main(String a[]) {
        int count = 10;
        int N = 3;
        int chunkSize = count / N;
        
        String current = "";
        ArrayList<PiThread> threads = new ArrayList<>();
        for(int i = 0; i < N; i++){
            int startIndex = i * chunkSize;
            int endIndex = (i == N - 1) ? count : (i + 1) * chunkSize;
            PiThread thread = new PiThread(startIndex, endIndex, N);
            threads.add(thread);
            
        }
        for (PiThread thread : threads){
            thread.start();
        }
        
        
        if(threads.size() == 1){
            try {
                threads.get(0).join();
                System.out.println(bytesToHex(threads.get(0).getDigits()));
                } catch (InterruptedException e) {        
                    e.printStackTrace();
                }
                    
                }else{
                for(int i = 0; i < threads.size(); i++){
                    try {
                        threads.get(i).join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(i==0){

                        current = bytesToHex(threads.get(i).getDigits());
                    }else{
                        current += '-'+bytesToHex(threads.get(i).getDigits());
                    }
                }
                System.out.println(current);
            }

    }

    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        StringBuilder sb=new StringBuilder();
        for (int i=0;i<hexChars.length;i=i+2){
            //sb.append(hexChars[i]);
            sb.append(hexChars[i+1]);            
        }
        return sb.toString();
    }
    
}
