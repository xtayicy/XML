package harry.XmlSax;

import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;

/**
 * the use of XPath expressions.
 * @author harry
 *
 */
public class XPathDemo extends SAXDemo {
	protected String xpath = "*";
	
	public static void main(String[] args) {
        run(new XPathDemo(), args);
    }
	
	public void run(String[] args) throws Exception {
        if (args.length < 2) {
            printUsage("<XML document URL> <XPath expression>");
            return;
        }

        String xmlFile = args[0];
        xpath = args[1];

        writer = createXMLWriter();

        Document document = parse(xmlFile);
        process(document);
    }
	
	protected void process(Document document) throws Exception {
        println("Evaluating XPath: " + xpath);

        List list = document.selectNodes(xpath);

        println("Found: " + list.size() + " node(s)");
        println("Results:");

        for (Iterator iter = list.iterator(); iter.hasNext();) {
            Object object = iter.next();
            writer.write(object);
            writer.println();
        }

        writer.flush();
    }
}
