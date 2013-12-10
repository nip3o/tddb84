package parser;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.*;

import org.xml.sax.*;

public class XMLCounter implements ContentHandler {
	Stack<Vector<Integer>> deweyStack = new Stack<Vector<Integer>>();
	Stack<Integer> length = new Stack<Integer>();
	Stack<String> pathStack = new Stack<String>();
	
	HashMap<String, String> attrsMap = new HashMap<String, String>();
	HashMap<String, String> tagsMap = new HashMap<String, String>();
	
	BufferedWriter textFile;

	public void startDocument( ) throws SAXException {
		Vector<Integer> dewey = new Vector<Integer>();
		deweyStack.push(dewey);
		length.push(1);
		pathStack.push("");
		
		try {
			Charset utf8 = Charset.forName("UTF-8");
			textFile = Files.newBufferedWriter(FileSystems.getDefault().getPath(".", "text.txt"),
											   utf8, StandardOpenOption.CREATE);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	private String formatDewey(Vector<Integer> dewey) {
		StringBuilder s = new StringBuilder();
		
		s.append(dewey.firstElement());
		for (int i = 1; i < dewey.size(); i++) {
			s.append(".").append(dewey.get(i));
		}
		return s.toString();
	}

	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {		
		// copy the dewey code and length for the previous element
		Vector<Integer> dewey = (Vector<Integer>) deweyStack.peek().clone();
		Integer len = length.peek();
		dewey.add(len);
		
		String id = formatDewey(dewey);
		String path = String.format("%s%s/", pathStack.peek(), qName);
		
		System.out.println("Start Element: " + qName + ", dewey " + id);
		
		for(int i = 0; i < attributes.getLength(); i++) {
			addToAttrs(path, id, attributes.getLocalName(i), attributes.getValue(i));
		}

		addToTags(path, id, qName);
		
		// add the dewey code and initial length for this level
		deweyStack.push(dewey);
		length.push(1);
		
		pathStack.push(path);
		System.out.println(pathStack.peek());
	}

	public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println("End Element: " + qName);
        
        // remove the previous level
        deweyStack.pop();
    	length.pop();
    	pathStack.pop();
    	
    	// increase the length of the current level
    	Integer len = length.pop();
    	length.push(len + 1);

    }
    
    public void characters(char ch[], int start, int length) throws SAXException {
    	// copy all chars between the current start and end element
        String text = new String(Arrays.copyOfRange(ch, start, start + length));
        // remove whitespace
        text = text.replaceAll("\\s+", ""); 
        
        if(!text.equals("")) {
        	String id = formatDewey(deweyStack.peek());
        	System.out.println(id + " " + text);
        	
        	try {
				textFile.write(String.format("%s %s\n", id, text));
				
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        
        /* here comes code after the startElement is met */
    }
    
    
    public void endDocument( ) throws SAXException {
        System.out.println("End of document");
        
        try {
        	writeToFile();
        	
			textFile.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    
    private void addToAttrs(String path, String id, String name, String value) {
    	String current = attrsMap.get(path);
    	
    	if(current == null) {
    		current = "";
    	}
    	current += String.format("%s %s %s\n", id, name, value);
    	
    	attrsMap.put(path, current);
	}
    
    
    private void addToTags(String path, String id, String tag) {
    	String current = tagsMap.get(path);
    	
    	if(current == null) {
    		current = "";
    	}
    	current += String.format("%s %s\n", id, tag);
    	
    	tagsMap.put(path, current);
	}
    
    
    private void writeToFile() {
    	BufferedWriter tagsFile, attrsFile;
    	
		try {
			Charset utf8 = Charset.forName("UTF-8");
			
			for (Map.Entry<String, String> entry : tagsMap.entrySet()) {
				tagsFile = Files.newBufferedWriter(FileSystems.getDefault().getPath(".", entry.getKey().replace("/", ".") + "tags.txt"),
						   						   utf8, StandardOpenOption.CREATE);
				tagsFile.write(entry.getValue());
				tagsFile.close();
			}

			for (Map.Entry<String, String> entry : attrsMap.entrySet()) {
				attrsFile = Files.newBufferedWriter(FileSystems.getDefault().getPath(".", entry.getKey().replace("/", ".") + "attrs.txt"),
						   						   utf8, StandardOpenOption.CREATE);
				attrsFile.write(entry.getValue());
				attrsFile.close();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    // Do-nothing methods we have to implement only to fulfill
    // the interface requirements:
    
    public void ignorableWhitespace(char ch[], int start, int length) throws SAXException {}
    
    public void processingInstruction(String target, String data) 	   
    throws SAXException {}
    public void setDocumentLocator(Locator locator) {}
    public void startPrefixMapping(String prefix, String uri)
    throws SAXException {}
    public void endPrefixMapping(String prefix) throws SAXException {}
    public void skippedEntity(String name) throws SAXException {}
    
}
