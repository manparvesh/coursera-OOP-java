/**
 * Prints out all links within the HTML source of a web page.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;

public class URLFinder {
	public StorageResource findURLs(String url) {
		URLResource page = new URLResource(url);
		String source = page.asString();
		StorageResource store = new StorageResource();
		int start = 0;
		while (true) {
			int index = source.indexOf("href=", start);
			if (index == -1) {
				break;
			}
			int firstQuote = index+6; // after href="
			int endQuote = source.indexOf("\"", firstQuote);
			String sub = source.substring(firstQuote, endQuote);
			if (sub.startsWith("http")) {
				store.add(sub);
			}
			start = endQuote + 1;
		}
		return store;
	}
	
	public StorageResource findYoutubeURLs(String url) {
		URLResource page = new URLResource(url);
		String source = page.asString(); //.toLowerCase();
		StorageResource store = new StorageResource();
		int start = 0;
		while (true) {
			int index = source.indexOf("href=", start);
			if (index == -1) {
				break;
			}
			int firstQuote = index+6; // after href="
			int endQuote = source.indexOf("\"", firstQuote);
			String sub = source.substring(firstQuote, endQuote);
			if (sub.startsWith("http")) {
				store.add(sub);
			}
			start = endQuote + 1;
		}
		return store;
	}
	
	public String mystery(String dna) {
      int pos = dna.indexOf("T");
      int count = 0;
      int startPos = 0;
      String newDna = "";
      if (pos == -1) {
        return dna;
      }
      while (count < 3) {
        count += 1;
        newDna = newDna + dna.substring(startPos,pos);
        startPos = pos+1;
        pos = dna.indexOf("T", startPos);
        if (pos == -1) {
          break;
        }
      }
      newDna = newDna + dna.substring(startPos);
      return newDna;
    }
	
	public void testURL() {
		StorageResource s1 = findYoutubeURLs("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
		StorageResource s2 = findURLs("http://www.doctorswithoutborders.org");
		for (String link : s1.data()) {
			System.out.println(link);
		}
		System.out.println("size = " + s1.size());
		System.out.println("size = " + s2.size());
		// System.out.println(mystery("ATGCAATAGCTAAC"));
	}
}
