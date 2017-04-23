/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bai2datamining;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author thai
 */
public class Bai2DataMining {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
//        Shingling shingling = new Shingling(200, 3, "output.txt");
//        shingling.printNGrams();
//        System.out.println(shingling.nGram.size());
        
        
        MinHashing minHashing=new MinHashing(1000, 3,20);
        long t=System.currentTimeMillis();
        minHashing.minHashing();
        System.out.println("Min Hashing Time: "+(System.currentTimeMillis()-t));
        System.out.println("nbrShingles: "+minHashing.nbrShingles);
        int signature[][]=minHashing.signatureMatrix;
        int d1[],d2[];
        d1=new int[minHashing.nbrHashFunc];
        d2=new int[minHashing.nbrHashFunc];
        for(int i=0;i<minHashing.nbrHashFunc;i++){
            d1[i]=signature[i][7];
            d2[i]=signature[i][8];
        }
        System.out.println("Jaccard= "+minHashing.jaccardSimilarity(d1, d2));
    
//        int d1[],d2[];
//        d1=new int[minHashing.nbrShingles];
//        d2=new int[minHashing.nbrShingles];
//        for(int i=0;i<minHashing.nbrShingles;i++){
//            d1[i]=minHashing.getM(i, 7);
//            d2[i]=minHashing.getM(i, 8);
//        }
//        int numIntersect=0;
//        int numUnion=0;
//        for(int i=0;i<minHashing.nbrShingles;i++){
//            if((d1[i]==d2[i])&&(d1[i]==1))
//                numIntersect++;
//            if((d1[i]==1)||(d2[i]==1))
//                numUnion++;
//        }
//        System.out.println((float)numIntersect/numUnion);
        
//        MinHashing minHashing=new MinHashing(500, 3,30);
//        minHashing.minHashing();
//        System.out.println("nbrShingles: "+minHashing.nbrShingles);
    }
}
