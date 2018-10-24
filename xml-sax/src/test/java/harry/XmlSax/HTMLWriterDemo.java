package harry.XmlSax;

import harry.XmlDom.AbstractParser;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.io.HTMLWriter;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
/**
 * 
 * @author harry
 *
 */
public class HTMLWriterDemo extends AbstractParser{
	public static void main(String[] args) {
		 run(new HTMLWriterDemo(), args);
	}
	
	@Override
	protected Document parse(String xmlFile) throws Exception {
		return new SAXReader().read(new File(xmlFile));
	}
	
	/**
     * A Factory Method to create an <code>XMLWriter</code> instance allowing
     * derived classes to change this behaviour
     */
	@Override
    protected XMLWriter createXMLWriter() throws Exception {
        return new HTMLWriter(System.out);
    }
}
