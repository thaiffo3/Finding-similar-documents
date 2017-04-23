/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bai2datamining;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author thai
 */
public class MinHashing {

    int nbrDocuments;
    int nbrShingles;
//    int booleanMatrices[][];
    ArrayList<HashMap<Integer,Integer>> M;
    int n;
    Shingling shingling;
    int permutation[][];
    int nbrHashFunc;
    int signatureMatrix[][];

    public MinHashing(int nbrDocuments, int n, int nbrHashFunc) throws IOException {
        this.nbrDocuments = nbrDocuments;
        this.n = n;
        this.nbrHashFunc = nbrHashFunc;
        long t=System.currentTimeMillis();
        shingling = new Shingling(nbrDocuments, n, "CRAWLE_NEWS.txt");
        System.out.println("Shingling Time: "+(System.currentTimeMillis()-t));
        nbrShingles = shingling.nGram.size();
//        booleanMatrices = shingling.getBooleanMatrices();
        initM();
        permutation = new int[nbrShingles][nbrHashFunc];
        permute();
        signatureMatrix = new int[nbrHashFunc][nbrDocuments];
    }

    public void permute() {
        Random R = new Random();
        for (int j = 0; j < nbrHashFunc; j++) {
            for (int i = 0; i < nbrShingles; i++) {
                permutation[i][j] = i + 1;
            }
        }
        int ri;
        for (int j = 0; j < nbrHashFunc; j++) {
            for (int i = 0; i < nbrShingles; i++) {
                while ((ri = R.nextInt(nbrShingles)) == i);
                int temp = permutation[i][j];
                permutation[i][j] = permutation[ri][j];
                permutation[ri][j] = temp;
            }
        }
    }
    
    public void initM(){
        M=new ArrayList<>();
        for(Integer i : shingling.nGram.keySet()){
            M.add(shingling.nGram.get(i).col);
        }
    }
    public int getM(int i,int j){
        if(M.get(i).get(j)==null)
            return 0;
        else
            return 1;
    }

    public void minHashing() {
//        int booleanMatrices[][] = new int[7][4];
//        int permutation[][] = new int[7][3];
//        int signatureMatrix[][]=new int[3][4];
//        booleanMatrices[0][0] = 1;
//        booleanMatrices[0][1] = 0;
//        booleanMatrices[0][2] = 1;
//        booleanMatrices[0][3] = 0;
//        booleanMatrices[1][0] = 1;
//        booleanMatrices[1][1] = 0;
//        booleanMatrices[1][2] = 0;
//        booleanMatrices[1][3] = 1;
//        booleanMatrices[2][0] = 0;
//        booleanMatrices[2][1] = 1;
//        booleanMatrices[2][2] = 0;
//        booleanMatrices[2][3] = 1;
//        booleanMatrices[3][0] = 0;
//        booleanMatrices[3][1] = 1;
//        booleanMatrices[3][2] = 0;
//        booleanMatrices[3][3] = 1;
//        booleanMatrices[4][0] = 0;
//        booleanMatrices[4][1] = 1;
//        booleanMatrices[4][2] = 0;
//        booleanMatrices[4][3] = 1;
//        booleanMatrices[5][0] = 1;
//        booleanMatrices[5][1] = 0;
//        booleanMatrices[5][2] = 1;
//        booleanMatrices[5][3] = 0;
//        booleanMatrices[6][0] = 1;
//        booleanMatrices[6][1] = 0;
//        booleanMatrices[6][2] = 1;
//        booleanMatrices[6][3] = 0;
//        permutation[0][0] = 2;
//        permutation[0][1] = 4;
//        permutation[0][2] = 3;
//        permutation[1][0] = 3;
//        permutation[1][1] = 2;
//        permutation[1][2] = 4;
//        permutation[2][0] = 7;
//        permutation[2][1] = 1;
//        permutation[2][2] = 7;
//        permutation[3][0] = 6;
//        permutation[3][1] = 3;
//        permutation[3][2] = 2;
//        permutation[4][0] = 1;
//        permutation[4][1] = 6;
//        permutation[4][2] = 6;
//        permutation[5][0] = 5;
//        permutation[5][1] = 7;
//        permutation[5][2] = 1;
//        permutation[6][0] = 4;
//        permutation[6][1] = 5;
//        permutation[6][2] = 5;
//        int nbrDocuments = 4;
//        int nbrHashFunc = 3;
//        int nbrShingles = 7;

        for (int j = 0; j < nbrDocuments; j++) {//duyet tat ca cac documents, tuong ung la cac cot cua booleanMatrices
            ArrayList<Integer> rowOne = new ArrayList<>();//rowOne: luu chi? so nhung hang bang` 1 cua document j
            for (int i = 0; i < nbrShingles; i++) {
            //duyet cac hang i cua cot j (duyet cac ngram i cua document j), luu chi? so cac hang` bang 1 vao rowOne
                if (getM(i,j) == 1) {
                    rowOne.add(i);
                }
            }
            for (int h = 0; h < nbrHashFunc; h++) {
                //Voi moi~ hash function thi` xac dinh signature cua document j
                //Xet cac hang` bang 1 cua booleanMatrices
                //Tim gia tri permutation nho nhat trong cac hang` do'
                //Tra ve chi so hang tuong ung voi permutation nho nhat va gan' cho signatureMatrix[h][j]
                int min = permutation[rowOne.get(0)][h];
                int sig = rowOne.get(0);
                for (int i = 0; i < rowOne.size(); i++) {
                    if (permutation[rowOne.get(i)][h] < min) {
                        min = permutation[rowOne.get(i)][h];
                        sig = rowOne.get(i);
                    }
                }
                signatureMatrix[h][j] = sig + 1;
            }
        }
//        for (int i = 0; i < nbrHashFunc; i++) {
//            for (int j = 0; j < nbrDocuments; j++) {
//                System.out.print(signatureMatrix[i][j] + " ");
//            }
//            System.out.println();
//        }
    }

    public float jaccardSimilarity(int[] d1, int d2[]) {
        float sim = 0;
        int numIntersect = 0;
        for (int i = 0; i < d1.length; i++) {
            if (d1[i] == d2[i]) {
                numIntersect++;
            }
        }
        sim = (float) numIntersect / nbrHashFunc;
        return sim;
    }
}
