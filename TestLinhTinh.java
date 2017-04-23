/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bai2datamining;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.StringTokenizer;

/**
 *
 * @author thai
 */
public class TestLinhTinh {
    private int nbrShingles=100;
    private int[] permutation=new int[nbrShingles];
    private int nbrHashFunc=10;
    
    public void readNGram(String filename) throws FileNotFoundException, IOException {//doc. ra tat ca cac ngram cua tat ca cac van ban
        BufferedReader in = new BufferedReader(new FileReader(filename));
        PrintWriter out=new PrintWriter(new FileWriter("src/input/input.txt"));
        for(int i=0;i<10000;i++){
            StringTokenizer line=new StringTokenizer(in.readLine(),"`~!@#$%^&*()-_=+[]{};':,./<>?|");
            while(line.hasMoreTokens()){
                out.print(line.nextToken());
            }
            out.println();
        }
        out.close();
            
    }
    public static void main(String[] args) throws IOException {
        TestLinhTinh tlt=new TestLinhTinh();
        tlt.readNGram("CRAWLE_NEWS.txt");
//        tlt.permuteToFile();
//        tlt.getPermutation(3);
//        for(int i=0;i<tlt.nbrShingles;i++)
//            System.out.print(tlt.permutation[i]+" ");
    }
}
