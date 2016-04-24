package wordleGenerator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.parser.ParseException;

import weka.core.converters.ArffLoader.ArffReader;

public class TopKAttributes {
	
	public static void main(String[] args) throws IOException, ParseException {
		BufferedReader reader =
				   new BufferedReader(new FileReader("data\topKAttr.arff"));
		ArffReader arff = new ArffReader(reader);
		
	}
}
