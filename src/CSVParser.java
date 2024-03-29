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

public class CSVParser {
	private static ArrayList<Vector<Double>> data = new ArrayList<Vector<Double>>();
	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) {
		try {
			setData(parse(args[0]));
		} catch (InputException e) {
			System.out.print(e.getMessage());
			System.exit(1);
		}
		//traverse();
		//findLengths();
		//findDotProduct(getData().get(0), getData().get(1));
		//findEuclidianDistance(getData().get(0), getData().get(1));
		//findManhattanDistance(getData().get(0), getData().get(1));
		//findPearsonCorrelation(getData().get(0), getData().get(1));
		//findAverage(getData().get(0));
		//findMedian(getData().get(0));
		//findLargest(getData().get(0));
		//findSmallest(getData().get(0));
		//findAverage(createVerticalVectors(getData(), 3));
		//findMedian(createVerticalVectors(getData(), 3));
		//findLargest(createVerticalVectors(getData(), 3));
		//findSmallest(createVerticalVectors(getData(), 3));
		//findStandardDeviation(getData().get(0));

	}
	public static Double findLength(int i){
		Double length = 0.0;
		Double sum = new Double(0);
		for (Double bd : getData().get(i)){
			sum+= bd*bd;
		}
		length = Math.sqrt(sum);
		//System.out.println(length);		
		return length;
	}

	public static ArrayList<Double> findLengths(){
		ArrayList<Double> results = new ArrayList<Double>();
		if (getData() != null){
			for (Vector<Double> v : getData()){
				Double length = 0.0;
				Double sum = new Double(0);
				for (Double bd : v){
					sum+= bd*bd;
				}
				length = Math.sqrt(sum);
				results.add(length);
				//System.out.println(length);		
			}
		}
		return results;
	}

	public static Double findDotProduct(Vector<Double> v1, Vector<Double> v2){
		Double result = 0.0;
		if (v1 == null || v2 == null){
			return null;
		}
		if(v1.size() == v2.size())
		{
			for(int i = 0; i < v1.size(); i++)
			{
				result += v1.get(i) * v2.get(i);
			}
		}
		//System.out.println(result);
		return result;
	}

	public static Double findEuclidianDistance(Vector<Double> v1, Vector<Double> v2){
		Double result = 0.0;
		if (v1 == null || v2 == null){
			return null;
		}
		if(v1.size() == v2.size())
		{
			for(int i = 0; i < v1.size(); i++)
			{
				Double difference = v2.get(i) - v1.get(i);
				result += (difference * difference);
			}
		} else {
			return null;
		}
		result = Math.sqrt(result);
		//System.out.println("EDistance: " + result);
		return result;
	}

	public static Double findManhattanDistance(Vector<Double> v1, Vector<Double> v2){
		Double result = 0.0;
		if (v1 == null || v2 == null){
			return null;
		}
		if(v1.size() == v2.size())
		{
			for(int i = 0; i < v1.size(); i++)
			{
				Double difference = Math.abs(v2.get(i) - v1.get(i));
				result += difference;
			}
		}
		//System.out.println("MDistance: " + result);
		return result;
	}

	public static Double findPearsonCorrelation(Vector<Double> v1, Vector<Double> v2){
		Double av1 = 0.0;
		Double av2 = 0.0;
		Double result = 0.0;
		Double std1 = 0.0;
		Double std2 = 0.0;
		if (v1 == null || v2 == null){
			return null;
		}
		if(v1.size() == v2.size())
		{
			for(int i = 0; i < v1.size(); i++)
			{
				av1 += v1.get(i);
				av2 += v2.get(i);
			}
			av1 = av1/v1.size();
			av2 = av2/v1.size();

			for(int i = 0; i < v1.size(); i++)
			{
				result += (v1.get(i) - av1) * (v2.get(i) - av2);
				std1 += ((v1.get(i) - av1) * (v1.get(i) - av1));
				std2 += ((v2.get(i) - av2) * (v2.get(i) - av2));
			}
			std1 = Math.sqrt(std1/(v1.size() - 1));
			std2 = Math.sqrt(std2/(v2.size() - 1));
			result = result/((v1.size()-1) * std1 * std2);
		}
		//System.out.println("Std1: " + std1);
		//System.out.println("Std2: " + std2);
		//System.out.println("Pearson Correlation: " + result);
		return result;
	}
	
	public static Double findStandardDeviation(Vector<Double> v1){
		Double av1 = 0.0;
		Double result = 0.0;
		Double std1 = 0.0;
		if (v1 == null){
			return null;
		}
			for(int i = 0; i < v1.size(); i++)
			{
				av1 += v1.get(i);
			}
			av1 = av1/v1.size();

			for(int i = 0; i < v1.size(); i++)
			{
				std1 += ((v1.get(i) - av1) * (v1.get(i) - av1));
			}
			std1 = Math.sqrt(std1/(v1.size() - 1));
		
		//System.out.println("Standard Deviation: " + std1);

		return std1;
	}

	public static Double findAverage(Vector<Double> v1){
		Double av1 = 0.0;
		if (v1 == null){
			return null;
		}
		for(int i = 0; i < v1.size(); i++)
		{
			av1 += v1.get(i);
		}
		av1 = av1/v1.size();

		//System.out.println("Average: " + av1);
		return av1;
	}
	
	public static Double findSmallest(Vector<Double> v1){
		Double result = 0.0;
		if (v1 == null){
			return null;
		}
		result = v1.get(0);
		for(int i = 0; i < v1.size(); i++)
		{
			if ( v1.get(i) < result )
			{
				result = v1.get(i);
			}
		}

		//System.out.println("Smallest: " + result);
		return result;
	}
	
	public static Double findLargest(Vector<Double> v1){
		Double result = 0.0;
		if (v1 == null){
			return null;
		}
		result = v1.get(0);
		for(int i = 0; i < v1.size(); i++)
		{
			if ( v1.get(i) > result )
			{
				result = v1.get(i);
			}
		}

		//System.out.println("Largest: " + result);
		return result;
	}
	
	public static Double findMedian(Vector<Double> v1){
		Double result = 0.0;
		if (v1 == null){
			return null;
		}
		Collections.sort(v1);
		if((v1.size()%2) == 0)
		{
			result = (v1.get(v1.size()/2) + v1.get(v1.size()/2 -1))/2;
		}
		else
		{
			result = v1.get(((v1.size() + 1)/2) - 1);
		}

		//System.out.println("Median: " + result);
		return result;
	}
	
	public static Vector<Double> createVerticalVectors(ArrayList<Vector<Double>> data2, Integer column)
	{
		Vector<Double> result = new Vector<Double>();
		if (data2 != null){
			for (Vector<Double> v : data2){
				result.add(v.get(column));
				//System.out.print(v.get(column) + "    ");
			}
		}
		return result;
	}

	public static void traverse(){
		if (getData() != null){
			for (Vector<Double> v : getData()){
				System.out.println("New Vector: ");
				for (Double bd : v){
					System.out.println(v);
				}
			}
		}
	}

	public static ArrayList<Vector<Double>> parse(String path) throws InputException{
		ArrayList<Vector<Double>> d = new ArrayList<Vector<Double>>();
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
	public static ArrayList<Vector<Double>> getData() {
		return data;
	}
	public static ArrayList<Vector<Double>> setData(ArrayList<Vector<Double>> data) {
		CSVParser.data = data;
		return data;
	}



}