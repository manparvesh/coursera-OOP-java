/**
 * Class written for week 2 quiz of the specialization.
 * 
 * @author Man Parvesh Singh Randhawa 
 * @version 0.1.0
 */

import edu.duke.*;
import java.io.*;
import java.util.*;

public class GeneCount {
    public String findProteins(String dna) {
        int start = dna.indexOf("atg");
        if (start == -1) {
            return "";
        }
        int stop = dna.indexOf("tag", start+3);
        if ((stop - start) % 3 == 0) {
            return dna.substring(start, stop+3);
        }
        else {
            return "";
        }
    }
    
    public ArrayList<String> getAllGenes(String dna){
        ArrayList<String> genes = new ArrayList<>();
        int pos = 0;
        while(true){
            int start = dna.indexOf("atg", pos);
            if (start == -1) {
                break;
            }
            int stop = getStoppingCodon(dna, start);
            if (stop != -1) {
                genes.add(dna.substring(start, stop+3));
                pos = stop + 1;
            } else {
                pos = start + 1;
            }
        }
        return genes;
    }
    
    public int getLengthOfLongestGene(String dna){
        ArrayList<String> genes = getAllGenes(dna);
        int ans = Integer.MIN_VALUE;
        for(String gene : genes){
            ans = Math.max(gene.length(), ans);
        }
        return ans;
    }
    
    public void printAllGenes(String dna){
        ArrayList<String> genes = getAllGenes(dna);
        for(String gene : genes){
            System.out.println(gene);
        }
        //System.out.println(genes.length);
    }
    
    public int getStoppingCodon(String dna, int start){
        int taa = dna.indexOf("taa", start+3);
        if(taa == -1 || (taa-start)%3 != 0) taa = Integer.MAX_VALUE;
        int tag = dna.indexOf("tag", start+3);
        if(tag == -1 || (tag-start)%3 != 0) tag = Integer.MAX_VALUE;
        int tga = dna.indexOf("tga", start+3);
        if(tga == -1 || (tga-start)%3 != 0) tga = Integer.MAX_VALUE;
        int stop = Math.min(taa, Math.min(tag, tga));
        return stop == Integer.MAX_VALUE ? -1 : stop;
    }
    
    public int findNumberOfProteins(String dna) {
        return getAllGenes(dna).size();
    }
    
    public int findNumberOfProteinsLargerThan60(String dna) {
        int ans = 0;
        ArrayList<String> genes = getAllGenes(dna);
        for(String gene : genes){
            if(gene.length() > 60) ans++;
        }
        return ans;
    }
    
    public int numberOfOccurencesOfCTG(String dna){
        int numberOfProteins = findNumberOfProteins(dna);
        ArrayList<String> genes = new ArrayList<>();
        int pos = 0;
        while(true){
            int start = dna.indexOf("ctg", pos);
            if (start == -1) {
                break;
            }
            int stop = getStoppingCodon(dna, start);
            if (stop != -1) {
                genes.add(dna.substring(start, stop+3));
                pos = stop + 1;
            } else {
                pos = start + 1;
            }
        }
        return genes.size();
    }
    
    private double getCGRatio(String gene){
        int ar[] = new int[4];
        for(int i = 0; i < gene.length(); i++){
            char c = gene.charAt(i);
            switch(c){
            case 'c':
                ar[0]++;
                break;
            case 'g':
                ar[1]++;
                break;
            case 'a':
                ar[2]++;
                break;
            case 't':
                ar[3]++;
                break;
            }
        }
        return ((double)(ar[0]+ar[1])) / ((double)(ar[0]+ar[1]+ar[2]+ar[3]));
    }
    
    public int numberOfGenesWithCGRatio(String dna){
        ArrayList<String> genes = getAllGenes(dna);
        int ans = 0;
        for(String gene : genes){
            if(getCGRatio(gene) > 0.35) ans++;
        }
        return ans;
    }
    
    public void testing() {
        FileResource fr = new FileResource();
        String source = fr.asString().toLowerCase();
        System.out.println("------------------------------------------------------------");
        System.out.println("------------------------------------------------------------");
        System.out.println("findNumberOfProteins: "+findNumberOfProteins(source));
        System.out.println("findNumberOfProteinsLargerThan60: "+findNumberOfProteinsLargerThan60(source));
        System.out.println("numberOfOccurencesOfCTG: "+numberOfOccurencesOfCTG(source));
        System.out.println("numberOfGenesWith CG Ratio > 0.35: " + numberOfGenesWithCGRatio(source));
        System.out.println("Length Of Longest Gene: "+getLengthOfLongestGene(source));
        //printAllGenes(source);
    }
}
