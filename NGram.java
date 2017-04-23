/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bai2datamining;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author thai
 */
public class NGram {
    String str;
    int nbrDocuments;
    HashMap<Integer, Integer> col=new HashMap<>();

    public NGram(String str,int nbrDocuments,int doc) {
        this.str = str;
        this.nbrDocuments=nbrDocuments;
        col.put(doc, 1);
    }
    public void printNGram(PrintWriter out) throws IOException{
        for(int i=0;i<nbrDocuments;i++)
            if(col.get(i)==null)
                out.print("0 ");
            else
                out.print(col.get(i)+" ");
        out.println();
    }
    public void addCol(int x){
        col.put(x, 1);
    }
}
