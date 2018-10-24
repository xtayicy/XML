package harry.XmlDom.rule;

import java.net.URL;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.dom4j.rule.Action;
import org.dom4j.rule.Rule;
import org.dom4j.rule.Stylesheet;

/**
 * the usage of dom4j <i>Declarative Rule API </i>
 * @author harry
 *
 */
public class SongFilter {
	private Document resultDoc;

	private Element songElement;

	private Element currentSongElement;

	private Stylesheet style;
	
	/** Creates a new instance of SongFilter */
    public SongFilter() {
        this.songElement = DocumentHelper.createElement("song");
    }

	public static void main(String[] args) throws Exception {
		SongFilter filter = new SongFilter();
		URL source = filter.getClass().getResource(
				"/harry/XmlDom/rule/Songs.xml");
		Document result = filter.filtering(new SAXReader().read(source));

		XMLWriter writer = new XMLWriter(OutputFormat.createPrettyPrint());
		writer.setOutputStream(System.out);
		writer.write(result);

	}

	public Document filtering(org.dom4j.Document doc) throws Exception {
		Element resultRoot = DocumentHelper.createElement("result");
		this.resultDoc = DocumentHelper.createDocument(resultRoot);

		Rule songElementRule = new Rule();
		songElementRule.setPattern(DocumentHelper
				.createPattern("/Songs/song/mp3/id3"));
		songElementRule.setAction(new SongElementBuilder());

		Rule titleTextNodeFilter = new Rule();
		titleTextNodeFilter.setPattern(DocumentHelper
				.createPattern("/Songs/song/mp3/id3/title"));
		titleTextNodeFilter.setAction(new NodeTextFilter());

		this.style = new Stylesheet();
		this.style.addRule(songElementRule);
		this.style.addRule(titleTextNodeFilter);

		style.run(doc);

		return this.resultDoc;
	}

	private class SongElementBuilder implements Action {
		public void run(Node node) throws Exception {
			currentSongElement = songElement.createCopy();
			resultDoc.getRootElement().add(currentSongElement);

			style.applyTemplates(node);
		}
	}
	
	private class NodeTextFilter implements Action {
        public void run(Node node) throws Exception {
            if (currentSongElement != null) {
                currentSongElement.setText(node.getText());
            }
        }
    }
}
