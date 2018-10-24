package harry.XmlSax;

import java.io.StringReader;
import java.io.StringWriter;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * a round trip from XML to dom4j to text to dom4j again
 * @author harry
 *
 */
public class RoundTripDemo extends SAXDemo {
	public static void main(String[] args) {
        run(new RoundTripDemo(), args);
    }
	
	/** Outputs the document to a buffer, parse it back again then output it */
	protected void process(Document document) throws Exception {
		System.out.println("about to output: " + document);
		// output the document to a buffer
		StringWriter out = new StringWriter();
		XMLWriter writer = new XMLWriter(out);
		writer.write(document);
		writer.close();

		// parse back again
		StringReader in = new StringReader(out.toString());
		SAXReader reader = new SAXReader();
		Document doc2 = reader.read(in);

		System.out.println("parsed back again: " + doc2);

		// now lets output it again
		writer.setOutputStream(System.out);
		writer.write(doc2);
	}
}
