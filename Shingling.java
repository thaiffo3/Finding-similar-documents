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
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author thai
 */
public class Shingling {

    HashMap<Integer, NGram> nGram = new HashMap<>();
    int n;
    int nbrDocuments;
    String filename;

    public Shingling(int nbrDocuments, int n, String filename) throws IOException {
        this.filename = filename;
        this.n = n;
        this.nbrDocuments = nbrDocuments;
        readNGram(filename);
    }

    public Integer hashStringToInt(String x) {
        int hash = 7;
        for (int i = 0; i < x.length(); i++) {
            hash = hash * 31 + x.charAt(i);
        }
        return hash;
    }

    public void readNGram(String filename) throws FileNotFoundException, IOException {//doc. ra tat ca cac ngram cua tat ca cac van ban
        BufferedReader in = new BufferedReader(new FileReader(filename));
        for (int i = 0; i < nbrDocuments; i++) {//doc tung van ban
            String[] line = in.readLine().split(" ");
            for (int j = 0; j <= line.length - n; j++) {//doc tung token cua 1 van ban
                String temp = line[j];
                j++;
                for (int k = 1; k < n; k++) {
                    temp += line[j];
                    j++;
                }
                j -= n;

                Integer hashValue = hashStringToInt(temp);
                NGram nGramTemp = nGram.get(hashValue);//Tim ngram co str=temp
                if (nGramTemp == null)//neu chua ton tai thi khoi tao moi
                {
                    nGram.put(hashValue, new NGram(temp, nbrDocuments, i));
                } else//neu co roi thi them: so' cua van ban ( i ) ma ngram do xuat hien
                {
                    nGramTemp.addCol(i);
                }
            }
        }
    }

    public void printNGrams() throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter("shingles.txt"));
        for (Integer i : nGram.keySet()) {
            nGram.get(i).printNGram(out);
        }
        out.close();
    }
    
}
