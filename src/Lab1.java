import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.math.BigDecimal;
import java.util.*
;

public class Lab1 {
	static ArrayList<Vector> data = new ArrayList<Vector>();
	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		try {
			data = parse(args[0]);
		} catch (InputException e) {
			System.out.print(e.getMessage());
			System.exit(1);
		}
		//traverse();
		findLengths();
		findDotProduct(data.get(0), data.get(1));
	}


	public static void findLengths(){
		if (data != null){
			for (Vector<Double> v : data){
				Double length = 0.0;
				Double sum = new Double(0);
				for (Double bd : v){
					sum+= bd*bd;
				}
				length = Math.sqrt(sum);
				System.out.println(length);		
			}
		}
	}
	
	public static Double findDotProduct(Vector<Double> v1, Vector<Double> v2){
		Double result = 0.0;
		if(v1.size() == v2.size())
		{
			for(int i = 0; i < v1.size(); i++)
			{
				result += v1.get(i) * v2.get(i);
			}
		}
		System.out.println(result);
		return result;
	}

	public static void traverse(){
		if (data != null){
			for (Vector<Double> v : data){
				System.out.println("New Vector: ");
				for (Double bd : v){
					System.out.println(v);
				}
			}
		}
	}

	public static ArrayList<Vector> parse(String path) throws InputException{
		ArrayList<Vector> d = new ArrayList<Vector>();
		FileReader fr = null;
		try {
			fr = new FileReader (path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scanner scanner = new Scanner(new BufferedReader(fr));
		while(scanner.hasNextLine()){
			String line = scanner.nextLine();
			if(!line.equals("")){
				String[] sArray = line.split(",");
				Vector<Double> v = new Vector<Double>();
				for (String s : sArray){
					s=s.trim();
					if (s.equals(new String(""))){
						s=new String("0");
					} else if (s.equals("NaN")){
						throw new InputException("NaN is not a valid number for the purposes of this excersise.");
					}
					  else if (s.equals("Infinity")){
							throw new InputException("Infinity is not a valid number for the purposes of this excersise.");
				
					  }  else if (s.equals("-Infinity")){
						throw new InputException("Negative Infinity is not a valid number for the purposes of this excersise.");
					}
					try{
						v.addElement(Double.parseDouble(s));
					} catch (NumberFormatException e){
						try {
							fr.close();
						} catch (IOException e1) {
							//Well this is bad when we can't close the file...
						}
						throw new InputException("Error parsing token: " + s, e);
					}
					//System.out.print(s);
				}
				d.add(v);
			}
		}
		return d;
	}



}