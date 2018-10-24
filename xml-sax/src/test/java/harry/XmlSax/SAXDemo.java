package harry.XmlSax;


import org.dom4j.Document;
import org.dom4j.io.SAXReader;

/**
 * 
 * @author harry
 *
 */
public class SAXDemo extends AbstractParser {
	public static void main(String[] args) {
		run(new SAXDemo(), args);
	}
	
	protected Document parse(String xmlFile) throws Exception {
        return new SAXReader().read(xmlFile);
    }
}
