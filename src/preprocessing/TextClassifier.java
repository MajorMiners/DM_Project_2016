package preprocessing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.json.simple.parser.ParseException;

import model.Review;
import model.ReviewData;
import parser.ReviewParser;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

public class TextClassifier {
	public static void main(String[] args) throws IOException, ParseException {
		Attribute a0 = new Attribute("sanity");
		Attribute a1 = new Attribute("table");
		Attribute a2 = new Attribute("healthy");
		Attribute a3 = new Attribute("clean");
		Attribute a4 = new Attribute("tidy");
		Attribute a5 = new Attribute("fork");
		Attribute a6 = new Attribute("glass");
		Attribute a7 = new Attribute("dirty");
		Attribute a8 = new Attribute("gross");
		Attribute a9 = new Attribute("cold");
		Attribute a10 = new Attribute("hot");
		Attribute Y = new Attribute("TargetVar");
		
		ArrayList attrList = new ArrayList<Attribute>();
		attrList.add(a0);
		attrList.add(a1);
		attrList.add(a2);
		attrList.add(a3);
		attrList.add(a4);
		attrList.add(a5);
		attrList.add(a6);
		attrList.add(a7);
		attrList.add(a8);
		attrList.add(a9);
		attrList.add(a10);
		attrList.add(Y);
		
		Instances trainData = new Instances("classification", attrList, 0);
		int cIdx = trainData.numAttributes() - 1;
		trainData.setClassIndex(cIdx);
		
		Instances testData = new Instances("classification", attrList, 0);
		cIdx = testData.numAttributes() - 1;
		testData.setClassIndex(cIdx);
		
		HygieneIdentifier hid = new HygieneIdentifier();
		hid.readHygieneDictionary();
		HashSet<String> dictionary = hid.getDict();
		List<ReviewData> reviews = ReviewParser.readReviews();
		
		for(ReviewData r : reviews){
			String text = r.getText();
			
			for(String word : dictionary){
				if(text.contains(word)){
					
				}
			}
			
			Instance inst;
			inst = new DenseInstance(19);
			
			
		}
		
		
	}
}
