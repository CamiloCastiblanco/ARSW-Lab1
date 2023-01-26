/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.threads;

/**
 *
 * @author hcadavid
 */
public class CountThread extends Thread{
    private int x;
    private int y;
    public CountThread(int a, int b){
        x = a;
        y = b;
    }

    @Override
    public void run(){
        for (int i=x; i<=y; i++){
            System.out.println("Counting..."+ i);

        }
    }
