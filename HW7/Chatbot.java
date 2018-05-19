import java.util.*;
import java.util.Map.Entry;
import java.io.*;

public class Chatbot{
	private static class triple {
	    private final int i;
	    private final double l;
	    private final double r;
	
	    public triple (int i, double l, double r) {
	    	this.i = i;
	    	this.l = l;
	    	this.r = r;
	    }
	    public int getIndex() {
	    	return i;
	    }
	    public double getLeft() {
	    	return l;
	    }
		public double getRight() {
			return r;
		}
	}
	
    private static String filename = "./WARC201709_wid.txt";
    private static ArrayList<Integer> readCorpus(){
        ArrayList<Integer> corpus = new ArrayList<Integer>();
        try{
            File f = new File(filename);
            Scanner sc = new Scanner(f);
            while(sc.hasNext()){
                if(sc.hasNextInt()){
                    int i = sc.nextInt();
                    corpus.add(i);
                }
                else{
                    sc.next();
                }
            }
        }
        catch(FileNotFoundException ex){
            System.out.println("File Not Found.");
        }
        return corpus;
    }
    
    private static ArrayList<Integer> biFind(int h, ArrayList<Integer> corpus){
        ArrayList<Integer> words_after_h = new ArrayList<>();
        for (int i = 0; i < corpus.size(); i++) {
        	if(corpus.get(i) == h) {
        		words_after_h.add(corpus.get(i + 1));
        	}
        }
        return words_after_h;
    }
    
    private static ArrayList<Integer> triFind(int h1, int h2, ArrayList<Integer> corpus){
        ArrayList<Integer> words_after_h1h2 = new ArrayList<>();
        for (int i = 0; i < corpus.size() - 2; i++) {
        	if (corpus.get(i) == h1 && corpus.get(i + 1) == h2) {
        		words_after_h1h2.add(corpus.get(i + 2));
        	}
        }
        return words_after_h1h2;
    }
    
    private static triple generate(double r, ArrayList<Integer> corpus) {
    	HashMap<Integer, Integer> map = new HashMap<>();
    	for (int i : corpus) {
    		if(map.containsKey(i)) {
    			map.put(i, map.get(i) + 1);
    		}
    		else {
    			map.put(i, 1);
    		}
    	}
    	double prob = 0.0;
    	int index = 0;
    	triple[] triplets = new triple[map.keySet().size()];
    	for (int j = 0; j < 4700; j++) {
    		if(!map.keySet().contains(j)) {
    			continue;
    		}
    		prob = (double) map.get(j) / corpus.size();
    		if(Math.abs(prob) < 1e-8) {
    			continue;
    		} else {
    			if(index == 0) {
    				triplets[index] = new triple(j, 0.0, prob);
    			} else {
    				triplets[index] = new triple(j, triplets[index-1].getRight(), triplets[index-1].getRight() + prob);
    			}
    			index++;
    		}
    	}

    	for (triple k : triplets) {
    		if(Math.abs(r) < 1e-8) {
    			if(r >= k.getLeft() && r <= k.getRight()) {
    				return k;
    			}
    		} else if (r > k.getLeft() && r <= k.getRight()) {
    			return k;
    		}
    	}
    	return null;
    }
    
    static public void main(String[] args){
        ArrayList<Integer> corpus = readCorpus();
		int flag = Integer.valueOf(args[0]);
        
        if(flag == 100){
			int w = Integer.valueOf(args[1]);
            int count = 0;
            for (Integer i : corpus) {
            	if(i == w) {
            		count++;
            	}
            }
            System.out.println(count);
            System.out.println(String.format("%.7f",count/(double)corpus.size()));
        }
        else if(flag == 200){
            int n1 = Integer.valueOf(args[1]);
            int n2 = Integer.valueOf(args[2]);
            double r = n1 * 1.0 / n2; // the random number
            triple result = generate(r, corpus);
            if(result != null) {
            	System.out.printf("%d\n%.7f\n%.7f", result.getIndex(), result.getLeft(), result.getRight());
            }
        }
        else if(flag == 300){
            int h = Integer.valueOf(args[1]);
            int w = Integer.valueOf(args[2]);
            int count = 0;
            ArrayList<Integer> words_after_h = new ArrayList<Integer>();
            
            for (int i = 0; i < corpus.size(); i++) {
            	if(corpus.get(i) == h) {
            		words_after_h.add(corpus.get(i + 1));
            		if(corpus.get(i + 1) == w)
            			count++;
            	}
            }
            //output 
            System.out.println(count);
            System.out.println(words_after_h.size());
            System.out.println(String.format("%.7f",count/(double)words_after_h.size()));
        }
        else if(flag == 400){
            int n1 = Integer.valueOf(args[1]);
            int n2 = Integer.valueOf(args[2]);
            int h = Integer.valueOf(args[3]);
            
            double r = n1 * 1.0 / n2; // the random number
            ArrayList<Integer> words_after_h = new ArrayList<Integer>();
            for (int i = 0; i < corpus.size(); i++) {
            	if(corpus.get(i) == h)
            		words_after_h.add(corpus.get(i + 1));
            }
            triple result = generate(r, words_after_h);
            if(result != null) {
            	System.out.printf("%d\n%.7f\n%.7f", result.getIndex(), result.getLeft(), result.getRight());
            }
        }
        else if(flag == 500){
            int h1 = Integer.valueOf(args[1]);
            int h2 = Integer.valueOf(args[2]);
            int w = Integer.valueOf(args[3]);
            int count = 0;
            ArrayList<Integer> words_after_h1h2 = new ArrayList<Integer>();
 
            for (int i = 0; i < corpus.size() - 2; i++) {
            	if (corpus.get(i) == h1 && corpus.get(i + 1) == h2) {
            		words_after_h1h2.add(corpus.get(i + 2));
            		if(corpus.get(i + 2) == w)
            			count++;
            	}
            }
            //output 
            System.out.println(count);
            System.out.println(words_after_h1h2.size());
            if(words_after_h1h2.size() == 0)
                System.out.println("undefined");
            else
                System.out.println(String.format("%.7f",count/(double)words_after_h1h2.size()));
        }
        else if(flag == 600){
            int n1 = Integer.valueOf(args[1]);
            int n2 = Integer.valueOf(args[2]);
            int h1 = Integer.valueOf(args[3]);
            int h2 = Integer.valueOf(args[4]);
            double r = n1 * 1.0 / n2; // the random number
            ArrayList<Integer> words_after_h1h2 = new ArrayList<Integer>();
            for (int i = 0; i < corpus.size() - 2; i++) {
            	if (corpus.get(i) == h1 && corpus.get(i + 1) == h2) {
            		words_after_h1h2.add(corpus.get(i + 2));
            	}
            }
            triple result = generate(r, words_after_h1h2);
            if(result != null) {
            	System.out.printf("%d\n%.7f\n%.7f", result.getIndex(), result.getLeft(), result.getRight());
            } else {
                System.out.println("undefined");
            }
        }
        else if(flag == 700){
            int seed = Integer.valueOf(args[1]);
            int t = Integer.valueOf(args[2]);
            int h1=0,h2=0;

            Random rng = new Random();
            if (seed != -1) rng.setSeed(seed);

            if(t == 0){
                // Generate first word using r
                double r = rng.nextDouble();
                triple temp = generate(r, corpus);
                h1 = temp.getIndex();
                System.out.println(h1);
                if(h1 == 9 || h1 == 10 || h1 == 12){
                    return;
                }

                // Generate second word using r
                r = rng.nextDouble();
                ArrayList<Integer> words_after_h = biFind(h1, corpus);
                triple temp2 = generate(r, words_after_h);
                h2 = temp2.getIndex();
                System.out.println(h2);
            }
            else if(t == 1){
                h1 = Integer.valueOf(args[3]);
                // Generate second word using r
                double r = rng.nextDouble();
                ArrayList<Integer> words_after_h = biFind(h1, corpus);
                triple temp = generate(r, words_after_h);
                h2 = temp.getIndex();
                System.out.println(h2);
            }
            else if(t == 2){
                h1 = Integer.valueOf(args[3]);
                h2 = Integer.valueOf(args[4]);
            }

            while(h2 != 9 && h2 != 10 && h2 != 12){
                double r = rng.nextDouble();
                int w  = 0;
                // Generate new word using h1,h2
                ArrayList<Integer> words_after_h1h2 = triFind(h1,h2, corpus);
                triple result = generate(r, words_after_h1h2);
                if(result != null)
                    w = result.getIndex();
                else {
                    ArrayList<Integer> words_after_h = biFind(h1, corpus);
                    triple temp3 = generate(r, words_after_h);
                    if(temp3 != null)
                    	w = temp3.getIndex();
                    else {
                        triple temp4 = generate(r, corpus);
                        w = temp4.getIndex();
                    }
                }
                System.out.println(w);
                h1 = h2;
                h2 = w;
            }
        }

        return;
    }
}
