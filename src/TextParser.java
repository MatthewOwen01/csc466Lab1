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
	ArrayList<Vector> paragraphs = new ArrayList<Vector>();

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		try {
			parse(args[0]);
			//printText();
			printWords();
		} catch (InputException e) {
			System.out.print(e.getMessage());
			System.exit(1);
		}


	}

	public static void printWords()
	{
		for(String s : words.keySet())
		{
			System.out.println(s);
		}
	}
	public static void printText()
	{
		for(String s : sentences)
		{
			System.out.println(s);
		}
	}

	public static void parse(String path) throws InputException{
		ArrayList<Vector> d = new ArrayList<Vector>();
		FileReader fr = null;
		try {
			fr = new FileReader (path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scanner scanner = new Scanner(new BufferedReader(fr));
		StringBuilder sentence = new StringBuilder();
		while(scanner.hasNextLine()){
			String line = scanner.nextLine();
			StringBuilder word = new StringBuilder();
			if(line == "")
			{
				//new paragraph
				sentences.add(sentence.toString());
				sentence = new StringBuilder();	
		
			}
			else
			{

				for (int i = 0; i < line.length(); i++){
					char c = line.charAt(i); 
					// System.out.print(c);
					//Process char
					if(c == '.' || c == '?' || c == '!')
					{
						//code the ellipse
						char quote = ' ';
						if ((i+1) < line.length())
						{
							quote = line.charAt(i+1);
							if(quote == '\'' || quote == '"')
							{
								sentence.append(c);
								sentence.append(quote);
								i++;
								//sentences.add(sentence.toString());
								//sentence = new StringBuilder();	

							}
							else
							{
								sentence.append(c);
								sentences.add(sentence.toString());
								sentence = new StringBuilder();	
							}
						}
						else
						{
							sentence.append(c);
							sentences.add(sentence.toString());
							sentence = new StringBuilder();	
						}
					}
					else if(c == '\'')
					{
						if(word.length() > 0 && (i+1) < line.length())
						{
							if(Character.isLetter(line.charAt(i+1)) && Character.isLetter(line.charAt(i-1)))
							{
								word.append(c);
							}
						}
					}
					else if(c != ' ' && c != '\t' && c != ',' && c != ':' && c != ';' && c != '(' && c != ')' && c != '\"')
					{
						
						sentence.append(c);
						word.append(c);
					}
					else
					{
						Integer count = null;
						if(words.containsKey(word.toString()))
						{
							count = words.get(word.toString());
							words.put(word.toString(), (count + 1));
						}
						else
						{
							words.put(word.toString(), 1);
						}

						sentence.append(c);

						word = new StringBuilder();
					}
				}
				sentence.append(" ");
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
