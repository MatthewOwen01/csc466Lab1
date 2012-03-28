import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;




public class Lab1_t {

	static Lab1 l = null;
    @BeforeClass
    public static void setUpClass() throws Exception {
    	l = new Lab1();
    }
	
    @Test
    public void BlankFile() {
    	try {
			assertNotNull(l.parse("src/tests/blank.csv"));
		} catch (InputException e) {
			fail("BlankFile threw an input exception");
		}
    }
    @Test
    public void HugeFile() {
    	try {
			assertNotNull(l.parse("src/tests/huge.csv"));
		} catch (InputException e) {
			fail("HugeFile threw an input exception");

		}
    }
    @Test
    public void NaNFile() {
    	boolean threwUp = false;
    	try {
			l.parse("src/tests/NaN.csv");
		} catch (InputException e) {
			threwUp = true;
		}
    	assertTrue(threwUp);
    }
    @Test
    public void InfinityFile() {
    	boolean threwUp = false;
    	try {
			l.parse("src/tests/Infinity.csv");
		} catch (InputException e) {
			threwUp = true;
		}
    	assertTrue(threwUp);
    }
    @Test
    public void NegativeInfinityFile() {
    	boolean threwUp = false;
    	try {
			l.parse("src/tests/NegativeInfinity.csv");
		} catch (InputException e) {
			threwUp = true;
		}
    	assertTrue(threwUp);
    }
    @Test
    public void nonNumbFile() {
    	boolean threwUp = false;
    	try {
			l.parse("src/tests/nonNumb.csv");
		} catch (InputException e) {
			threwUp = true;
		}
    	assertTrue(threwUp);
    }
    @Test
    public void onlyCommasFile() {
    	try {
			assertNotNull(l.parse("src/tests/onlyCommas.csv"));
		} catch (InputException e) {
			fail("HugeFile threw an input exception");

		}
    }
    @Test
    public void zerosFile() {
    	try {
			assertNotNull(l.parse("src/tests/zeros.csv"));
		} catch (InputException e) {
			fail("HugeFile threw an input exception");

		}
    }
    


}
