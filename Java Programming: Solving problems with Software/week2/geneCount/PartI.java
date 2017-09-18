import edu.duke.*;
/**
 * Write a description of PartI here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PartI {
    int findStopCodon(String dna , int startIndex , String stopCodon){
        dna = dna.toUpperCase();
        stopCodon = stopCodon.toUpperCase();
        int endIndex = dna.indexOf(stopCodon,startIndex+3);
        while(endIndex != -1){
            if((endIndex-startIndex)%3 == 0){
                return endIndex ;
            }
            endIndex = dna.indexOf(stopCodon,endIndex+1);
        }
        return -1 ;
    }
    
    String findGene(String dna , int startIndex ){
        //int startIndex = dna.indexOf("ATG",startSearch);
        if(startIndex != -1){
            int taa = findStopCodon(dna ,startIndex ,"TAA");
            int tga = findStopCodon(dna ,startIndex ,"TGA");
            int tag = findStopCodon(dna ,startIndex ,"TAG");
            int minOne = -1 ;
            if( taa == -1 || (tga != -1 && tga < taa)){
                minOne = tga ;
            }
            else{
                minOne = taa ;
            }
            if( minOne == -1 || (tag != -1 && tag < minOne)){
                minOne = tag ;
            }
            if(minOne != -1){
                return dna.substring(startIndex , minOne+3);
            }
            else{
                return "";
            }
        }
        else{
            return "";
        }
    }
    
    StorageResource getAllGenes(String dna){
        int currentIndex = dna.indexOf("ATG");
        StorageResource x = new StorageResource();
        while(currentIndex != -1){
            String gene = findGene(dna , currentIndex);
            if(!gene.isEmpty()){
                x.add(gene);
                currentIndex = dna.indexOf("ATG",currentIndex + gene.length());
            }
            else{
                currentIndex = dna.indexOf("ATG",currentIndex + 1);
            }
        }
        return x ;
    }
    
    void testing(){
        StorageResource z = getAllGenes("ATGTAAGATGCCCTAGT");
        for(String x : z.data()){
            System.out.println(x);
        }
    }   
    
    
}