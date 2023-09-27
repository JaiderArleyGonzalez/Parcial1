/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author hcadavid
 */
public class Main {
    private static Monitor monitor = new Monitor();
    public static void main(String a[]) {
        int count = 10;
        int N = 4;
        int chunkSize = count / N;
        
        String current = "";
        ArrayList<PiThread> threads = new ArrayList<>();
        for(int i = 0; i < N; i++){
            int startIndex = i * chunkSize;
            chunkSize = (i == N - 1) ? count - chunkSize * i : chunkSize; //Se calcula el índice de inicio del fragmento actual y se ajusta el tamaño del fragmento chunkSize dependiendo del valor de i.
            PiThread thread = new PiThread(startIndex, chunkSize, N, monitor);
            threads.add(thread);
            
        }
        for (PiThread thread : threads){
            thread.start();
        }
        while(Thread.activeCount() > 1){ //verifica la cantidad de hilos activos durante el ciclo
            try{
                Thread.sleep(5000); //el hilo principal se coloca en espera durante 5 seg y luego suspende los hilos
                monitor.suspenderHilos();
                
                for(int i = 0; i < threads.size(); i++){
                    System.out.println(threads.get(i).getName()+" ha procesado: "+threads.get(i).getDatos()); // muestra el número de digitos que han procesado los hilos
                    current += bytesToHex(threads.get(i).getDigits());      
                }
                System.out.println("Así va la cadena: "+ current); //muestra el proceso de la cadena
                Scanner escaner = new Scanner(System.in);
                System.out.println("Presione enter para continuar");
                String entrada = escaner.nextLine();
                
                monitor.reanudarHilos(); //reanuda los hilos nuevamente
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            
            
        }
        for(int i = 0; i < threads.size(); i++){
                try {
                    threads.get(i).join();
                    
                    
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }   
                    
                }
                System.out.println(current);
        
        

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
