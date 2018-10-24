package harry.XmlSax;

import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.XPath;

/**
 * 
 * @author harry
 *
 */
public class XPathGrep extends SAXDemo {
	protected XPath xpath;

    protected boolean verbose;

    public static void main(String[] args) {
        run(new XPathGrep(), args);
    }

    public XPathGrep() {
    }

    public void run(String[] args) throws Exception {
        if (args.length < 2) {
            printUsage("{options} <XPath expression> <xml file(s)>");
            return;
        }

        for (int i = 0, size = args.length; i < size; i++) {
            String arg = args[i];
            if (arg.startsWith("-")) {
                readOptions(arg);
            } else {
                if (xpath == null) {
                    setXPath(arg);
                } else {
                    Document document = parse(arg);
                    process(document);
                }
            }
        }
    }

    public void setXPath(String xpathExpression) {
        xpath = DocumentHelper.createXPath(xpathExpression);
    }

    protected void process(Document document) throws Exception {
        // perform XPath
        if (verbose) {
            println("About to evalute: " + xpath);
            println("Results:");
        }

        Object object = xpath.evaluate(document);

        if (object instanceof List) {
            List list = (List) object;
            for (Iterator iter = list.iterator(); iter.hasNext();) {
                getXMLWriter().write(iter.next());
                getXMLWriter().println();
            }
            getXMLWriter().flush();
        } else {
            println((object != null) ? object.toString() : "null");
        }
    }

    protected void readOptions(String arg) {
        if (arg.indexOf('v') >= 0) {
            verbose = true;
        }
    }
}
