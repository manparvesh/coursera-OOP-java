import edu.duke.*;
/**
 * Write a description of PartIII here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PartIII {
    void processGenes(StorageResource sr){
        PartII y = new PartII();
        StorageResource longer9 = new StorageResource();
        StorageResource cg35 = new StorageResource();
        String longest = "" ;
        for(String z : sr.data()){
            if(z.length() > 60){
                longer9.add(z);
            }
            if(y.cgRatio(z) > .35){
                cg35.add(z);
            }
            if(z.length() > longest.length()){
                longest = z ;
            }
        }
        // First part the Strings in sr that are longer than 9 characters
        System.out.println("the number of Strings in sr that are longer than 60 characters");
        System.out.println(longer9.size());
        
        // 2nd part print the number of Strings in sr that are longer than 9 characters
        System.out.println("the number of Strings in sr that are longer than 9 characters");
        System.out.println(longer9.size());
        // 3rd part print the Strings in sr whose C-G-ratio is higher than 0.35
        //System.out.println("the Strings in sr whose C-G-ratio is higher than 0.35");
        //for(String z : cg35.data()){
        //    System.out.println(z);
        //}
        //4th part print the number of strings in sr whose C-G-ratio is higher than 0.35
        System.out.println("the number of strings in sr whose C-G-ratio is higher than 0.35");
        System.out.println(cg35.size());
        //5th part print the length of the longest gene in sr
        System.out.println("the length of the longest gene in sr");
        System.out.println(longest.length());
        System.out.println("Number of Genes Found");
        System.out.println(sr.size());
    }
    
    void countCodon(String dna , String codon){
        int count = 0 ;
        int current = dna.indexOf(codon);
        while(current != -1){
            count ++ ;
            current = dna.indexOf(codon , current + codon.length());
        }
        System.out.println("Number of Appears of " + codon);
        System.out.println(count);
    }
    
    void testing(){
        FileResource fr = new FileResource("../dna/GRch38dnapart.fa");
        String dna = fr.asString();
        dna = dna.toUpperCase();
        PartI x = new PartI();
        processGenes(x.getAllGenes(dna));
        countCodon(dna ,"CTG");
    }
}