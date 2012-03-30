import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.regex.Pattern;


public class TextParser {
	static HashMap<String, Integer> words = new HashMap<String, Integer>();
	static ArrayList<String> sentences = new ArrayList<String>();
	static ArrayList<Vector<Integer>> paragraphs = new ArrayList<Vector<Integer>>();
	static String whiteSpace = "\\s";
	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) {
		parse(args[0]);
		printSentences();
		printWords();
		//printParagraphs();
		//printStats();
	}

	public static void printWords(){
		for(String s : words.keySet()){
			System.out.println(s + ": " + words.get(s));
		}
	}

	public static void printStats(){
		System.out.println("Paragraphs: " + paragraphs.size());
		System.out.println("Sentences: " + sentences.size());
	}
	public static void printSentences(){
		for(String s : sentences){
			System.out.println(s);
		}
	}
	public static void printParagraphs(){
		for(int i = 0; i < paragraphs.size(); i++){
			System.out.print("\t");
			printParagraph(i);
			System.out.print("\n\n");
		}
	}
	public static void printParagraph(int p){
		for(String s : sentences.subList(paragraphs.get(p).elementAt(0), 1 + paragraphs.get(p).elementAt(1))){
			System.out.print(s + " ");
		}
	}

	public static void parse(String path){
		words = new HashMap<String, Integer>();
		sentences = new ArrayList<String>();
		paragraphs = new ArrayList<Vector<Integer>>();
		ArrayList<Vector> d = new ArrayList<Vector>();
		boolean eatingW = true;
		int paragraphLine = 0;
		FileReader fr = null;
		try {
			fr = new FileReader (path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scanner scanner = new Scanner(new BufferedReader(fr));
		StringBuilder sentence = new StringBuilder();
		StringBuilder word = new StringBuilder();
		while(scanner.hasNextLine()){
			char c = 0;
			String line = scanner.nextLine();
			if(line.length() == 0 || line.matches(whiteSpace)) {
				if (eatingW){

				} else {
					eatingW = true;
					addSentence(sentence);
					paragraphLine = addParagraph(paragraphLine);
					sentence = new StringBuilder();	
				}

			}else{
				eatingW = false;
				for (int i = 0; i < line.length(); i++){
					c = line.charAt(i); 
					// System.out.print(c);
					//Process char
					if(c == '.' || c == '?' || c == '!'){
						//code the ellipse
						char quote = ' ';
						if ((i+1) < line.length()){
							quote = line.charAt(i+1);
							if(quote == '\'' || quote == '"'){
								sentence.append(c);
								sentence.append(quote);
								i++;
								//sentences.add(sentence.toString());
								//sentence = new StringBuilder();	

							}else{
								sentence.append(c);
								addSentence(sentence);
								sentence = new StringBuilder();	
							}
						}else{
							sentence.append(c);
							addSentence(sentence);
							sentence = new StringBuilder();	
						}
					}else if(c == '\''){
						if(word.length() > 0 && (i+1) < line.length()){
							if(Character.isLetter(line.charAt(i+1)) && Character.isLetter(line.charAt(i-1))){
								word.append(c);
							}

						}
						sentence.append("'");

					}else if(c == '-'){ // Handle the dash ( -- )
						if((i+1) < line.length()){
							if (line.charAt(i+1)== '-'){
								addWord(word.toString());
								sentence.append("--");
								i++;
								word = new StringBuilder();	
							} else {
								word.append(c);
							}
						}
						
						
					}else if(c != ' ' && c != '\t' && c != ',' && c != ':' && c != ';' && c != '(' && c != ')' && c != '\"'){
						sentence.append(c);
						word.append(c);

					}else{
						if (word.length()>0){
							addWord(word.toString());
							sentence.append(c);
							word = new StringBuilder();
						}else{
							sentence.append(c);
						}
					}
				}
			}


			
			//End of line so finish words unless there's a -
			if (c == '-'){
				
			} else if (c != ' '){
				addWord(word.toString());
				word = new StringBuilder();
				sentence.append(' ');
			}

		}
		if (word.length()>0){
			addWord(word.toString());
		}
		if (sentence.length()>0){
			addSentence(sentence);
		}
		paragraphLine=addParagraph(paragraphLine);

	}

	private static void addSentence(StringBuilder sentence) {
		if (sentence.toString().trim().length()>0){
			sentences.add(sentence.toString());
		}
	}

	private static int addParagraph(int paragraphLine) {
		if (sentences.size()>0){
			Vector<Integer> p = new Vector<Integer>();
			p.add(paragraphLine);
			paragraphLine = sentences.size()-1;
			p.add(paragraphLine);
			paragraphs.add(p);
			return paragraphLine+1;
		}
		return 0;
	}

	private static void addWord(String s){
		s=s.trim();
		if (s.length()>0){
			int count = 0;
			if(words.containsKey(s)){
				count = words.get(s);
				words.put(s.toLowerCase(), (count + 1));
			}else{
				words.put(s.toLowerCase(), 1);
			}
		}

	}

	//		public static void parse2(String path) throws InputException{
	//			ArrayList<Vector> d = new ArrayList<Vector>();
	//			FileReader fr = null;
	//			try {
	//				fr = new FileReader (path);
	//			} catch (FileNotFoundException e) {
	//				// TODO Auto-generated catch block
	//				e.printStackTrace();
	//			}
	//			Scanner scanner = new Scanner(new BufferedReader(fr));
	//			StringBuilder sentence = new StringBuilder();
	//			while(scanner.hasNextLine()){
	//				String line = scanner.nextLine();
	//				StringTokenizer st = new StringTokenizer(line);
	//				while(st.hasMoreTokens())
	//				{
	//					String s = st.nextToken();
	//					System.out.println(s);
	//				}
	//			}
	//	}
}
