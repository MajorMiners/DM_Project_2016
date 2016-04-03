package parser;/* Authored by Kushagra on 3/28/2016. */

import model.AllViolationData;
import model.RestaurantToYelpIdData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestaurantToYelpIdParser {

    private static String filePath = "data/restaurant_ids_to_yelp_ids.csv";

    public static void main(String[] args) throws IOException {
        
    }
    
    public static List<RestaurantToYelpIdData> readReadRestaurentToYelpData()
			throws IOException {
    	
    	List<RestaurantToYelpIdData> list = new ArrayList<RestaurantToYelpIdData>();
    	BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        int linecount = 0;
        while ((line = br.readLine()) != null) {

            linecount++;

            if (linecount > 1) {
                RestaurantToYelpIdData lineData = getRestaurantToYelpIdData(line);
                System.out.println(lineData);
            }
        }

        br.close();
        return list;
    }
    
    public static String getRestaurentIDFromYelpID(String yelpID)
    {
    	Map<String, String> map = new HashMap<String, String>();
    	
    	List<RestaurantToYelpIdData> list = new ArrayList<RestaurantToYelpIdData>();
    	
    	for(RestaurantToYelpIdData line : list){
    		map.put(line.getYelpID(), line.getRestaurantID());
    	}
    	
    	if(map.containsKey(yelpID))
    		return map.get(yelpID);
    	else
    		return "ID does not exists";
    }
    
    public static String getYelpIDFromRestaurentID(String restaurentID)
    {
    	Map<String, String> map = new HashMap<String, String>();
    	
    	List<RestaurantToYelpIdData> list = new ArrayList<RestaurantToYelpIdData>();
    	
    	for(RestaurantToYelpIdData line : list){
    		map.put(line.getRestaurantID(),line.getYelpID());
    	}
    	
    	if(map.containsKey(restaurentID))
    		return map.get(restaurentID);
    	else
    		return "ID does not exists";
    }
    
    private static RestaurantToYelpIdData getRestaurantToYelpIdData(String line) {

        String[] tokens = line.split(",");

        String restID = tokens[0];
        String yelpID = tokens[1];

        return new RestaurantToYelpIdData(restID, yelpID);
    }
}
