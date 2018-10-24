package harry.XmlDom;

import org.dom4j.Document;
import org.dom4j.io.XPPReader;

/**
 * using the XML Pull Parser to create a dom4j Document
 * @author harry
 *
 */
public class PullParserDemo extends AbstractParser{
	public static void main(String[] args) {
        run(new PullParserDemo(), args);
    }
	
	protected Document parse(String xmlFile) throws Exception {
        return new XPPReader().read(xmlFile);
    }
}
