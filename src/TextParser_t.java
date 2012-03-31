import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class TextParser_t {
	static TextParser t = new TextParser();
	
	@BeforeClass
    public static void setUpClass() throws Exception {
		t = new TextParser();
	}
	
    @Test
    public void emptyFile() {
		TextParser.parse("src/textTests/empty.txt");
		assertTrue(TextParser.paragraphs.size() == 0);
		assertTrue(TextParser.sentences.size() == 0);
		assertTrue(TextParser.words.size() == 0);
		
    }
    
    @Test
    public void whiteFile() {
		TextParser.parse("src/textTests/whiteSpace.txt");
		assertTrue(TextParser.paragraphs.size() == 0);
		assertTrue(TextParser.sentences.size() == 0);
		assertTrue(TextParser.words.size() == 0);
		
    }
    
    @Test
    public void twoParagraphsFile() {
    	TextParser.parse("src/textTests/2paragraphs.txt");
		assertTrue(TextParser.paragraphs.size() == 2);
		assertTrue(TextParser.sentences.size() == 2);
		assertTrue(TextParser.words.size() == 5);
    }
    
    @Test
    public void contractionFile() {
		TextParser.parse("src/textTests/contraction.txt");
		assertTrue(TextParser.paragraphs.size() == 1);
		assertTrue(TextParser.sentences.size() == 1);
		assertTrue(TextParser.words.size() == 14);
    }
    
    @Test
    public void endSpacesFile() {
		TextParser.parse("src/textTests/endSpaces.txt");
		assertTrue(TextParser.paragraphs.size() == 1);
		assertTrue(TextParser.sentences.size() == 1);
		assertTrue(TextParser.words.size() == 13);
		assertTrue(TextParser.sentences.get(0).split(" ").length == 15);

    }
    
    @Test
    public void rejoinFile() {
		TextParser.parse("src/textTests/rejoin.txt");
		assertTrue(TextParser.paragraphs.size() == 1);
		assertTrue(TextParser.sentences.size() == 1);
		assertTrue(TextParser.words.size() == 8);
		assertTrue(TextParser.words.get("rejoining") == 1);

    }
    
    @Test
    public void countFile() {
		TextParser.parse("src/textTests/count.txt");
		assertTrue(TextParser.paragraphs.size() == 1);
		assertTrue(TextParser.sentences.size() == 1);
		assertTrue(TextParser.words.size() == 9);
		assertTrue(TextParser.words.get("these") == 1);
		assertTrue(TextParser.words.get("words") == 1);
		assertTrue(TextParser.words.get("will") == 1);
		assertTrue(TextParser.words.get("be") == 1);
		assertTrue(TextParser.words.get("checked") == 1);
		assertTrue(TextParser.words.get("for") == 1);
		assertTrue(TextParser.words.get("accuracy") == 1);
		assertTrue(TextParser.words.get("and") == 1);
		assertTrue(TextParser.words.get("count") == 3);
    }
    
    @Test
    public void hardFile() {
		TextParser.parse("src/textTests/hard.txt");
		assertTrue(TextParser.paragraphs.size() == 1);
		assertTrue(TextParser.sentences.size() == 1);
		assertTrue(TextParser.words.get("the") == 3);
		assertTrue(TextParser.words.get("dormouse") == 1);
		assertTrue(TextParser.words.get("had") == 1);
		assertTrue(TextParser.words.get("closed") == 1);
		assertTrue(TextParser.words.get("its") == 1);
		assertTrue(TextParser.words.get("eyes") == 1);
		assertTrue(TextParser.words.get("by") == 2);
		assertTrue(TextParser.words.get("this") == 1);
		assertTrue(TextParser.words.get("time") == 1);
		assertTrue(TextParser.words.get("and") == 5);
		assertTrue(TextParser.words.get("mouse-traps") == 1);
		assertTrue(TextParser.words.get("did") == 1);

    }
	
}
