package harry.XmlSax;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/**
 * An abstract base class
 * 
 * @author harry
 *
 */
public abstract class AbstractParser {
	/**
	 * The format of XML / HTML
	 */
	protected OutputFormat format = new OutputFormat();

	/** The writer of XML */
	protected XMLWriter writer;

	protected XMLWriter getXMLWriter() throws Exception {
		if (writer == null) {
			writer = createXMLWriter();
		}

		return writer;
	}

	protected void process(Document document) throws Exception {
		getXMLWriter().write(document);
		getXMLWriter().flush();
	}

	protected static void run(AbstractParser parser, String[] args) {
		try {
			parser.run(args);
		} catch (DocumentException e) {
			System.out.println("Exception occurred: " + e);
			Throwable nestedException = e.getNestedException();
			if (nestedException != null) {
				System.out.println("NestedException: " + nestedException);
				nestedException.printStackTrace();
			} else {
				e.printStackTrace();
			}
		} catch (Throwable t) {
			System.out.println("Exception occurred: " + t);
			t.printStackTrace();
		}
	}

	public void run(String[] args) throws Exception {
		if (args.length < 1) {
			printUsage("no XML document URL specified");
			return;
		}

		int idx = format.parseOptions(args, 0);
		if (idx >= args.length) {
			printUsage("no XML document URL specified");
			return;
		} else {
			writer = createXMLWriter();

			Document document = parse(args[idx]);
			process(document);
		}
	}

	protected Document parse(String xmlFile) throws Exception {
		throw new RuntimeException(
				"parse(String xmlFile) not implemented in this demo");
	}
	
	protected void print(String text) {
		System.out.print(text);
	}

	protected void println(String text) {
		System.out.println(text);
	}

	protected void printUsage(String text) {
		println("Usage: java " + getClass().getName() + " " + text);
	}

	/**
	 * A Factory Method to create an <code>XMLWriter</code> instance allowing
	 * derived classes to change this behaviour
	 */
	protected XMLWriter createXMLWriter() throws Exception {
		return new XMLWriter(System.out, format);
	}
}
