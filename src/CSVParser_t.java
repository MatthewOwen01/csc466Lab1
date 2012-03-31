import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.BeforeClass;
import org.junit.Test;




public class CSVParser_t {

	static CSVParser l = null;
    @BeforeClass
    public static void setUpClass() throws Exception {
    	l = new CSVParser();
    }
	
    @Test
    public void BlankFile() {
    	try {
			assertNotNull(CSVParser.parse("src/tests/blank.csv"));
		} catch (InputException e) {
			fail("BlankFile threw an input exception");
		}
  	
    }
    @Test
    public void HugeFile() {
    	try {
			assertNotNull(CSVParser.parse("src/tests/huge.csv"));
		} catch (InputException e) {
			fail("HugeFile threw an input exception");
		}
    }
    @Test
    public void NaNFile() {
    	boolean threwUp = false;
    	try {
			CSVParser.parse("src/tests/NaN.csv");
		} catch (InputException e) {
			threwUp = true;
		}
    	assertTrue(threwUp);
    }
    @Test
    public void InfinityFile() {
    	boolean threwUp = false;
    	try {
			CSVParser.parse("src/tests/Infinity.csv");
		} catch (InputException e) {
			threwUp = true;
		}
    	assertTrue(threwUp);
    }
    @Test
    public void NegativeInfinityFile() {
    	boolean threwUp = false;
    	try {
			CSVParser.parse("src/tests/NegativeInfinity.csv");
		} catch (InputException e) {
			threwUp = true;
		}
    	assertTrue(threwUp);
    }
    @Test
    public void nonNumbFile() {
    	boolean threwUp = false;
    	try {
			CSVParser.parse("src/tests/nonNumb.csv");
		} catch (InputException e) {
			threwUp = true;
		}
    	assertTrue(threwUp);
    }
    @Test
    public void onlyCommasFile() {
    	try {
			assertNotNull(CSVParser.parse("src/tests/onlyCommas.csv"));
		} catch (InputException e) {
			fail("HugeFile threw an input exception");

		}
    }
    @Test
    public void zerosFile() {
    	try {
			assertNotNull(CSVParser.parse("src/tests/zeros.csv"));
		} catch (InputException e) {
			fail("HugeFile threw an input exception");

		}
    }
    @Test
    public void case1File() {
    	try {
			assertNotNull(CSVParser.setData(CSVParser.parse("src/tests/case1.csv")));
		} catch (InputException e) {
			fail("HugeFile threw an input exception");
		}
    	assertTrue(6 == CSVParser.findAverage(CSVParser.getData().get(0)));
    	assertTrue(190 == CSVParser.findDotProduct(CSVParser.getData().get(0), CSVParser.getData().get(1)));
    	assertTrue(Math.sqrt(5) == CSVParser.findEuclidianDistance(CSVParser.getData().get(0), CSVParser.getData().get(1)));
    	assertTrue(10 == CSVParser.findLargest(CSVParser.getData().get(0)));
    	assertTrue(Math.sqrt(220) == CSVParser.findLength(0));
    	assertTrue(5 == CSVParser.findManhattanDistance(CSVParser.getData().get(0), CSVParser.getData().get(1)));
    	assertTrue(6 == CSVParser.findMedian(CSVParser.getData().get(0)));
    	assertTrue(1.0 == (double)Math.round(CSVParser.findPearsonCorrelation(CSVParser.getData().get(0), CSVParser.getData().get(1)) * 1000) / 1000);
    	assertTrue(2 == CSVParser.findSmallest(CSVParser.getData().get(0)));
    	assertTrue(3.162 == (double)Math.round(CSVParser.findStandardDeviation(CSVParser.getData().get(0)) * 1000) / 1000);
    	assertTrue(4.0 ==  CSVParser.createVerticalVectors(CSVParser.getData(), 1).get(0));
    	assertTrue(3.0 ==  CSVParser.createVerticalVectors(CSVParser.getData(), 1).get(1));
    }
    
    
    
    
	
	


}
