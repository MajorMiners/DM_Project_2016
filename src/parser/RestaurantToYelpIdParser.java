package parser;/* Authored by Kushagra on 3/28/2016. */

import model.RestaurantToYelpIdData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class RestaurantToYelpIdParser {

    private static String filePath = "data/restaurant_ids_to_yelp_ids.csv";

    public static void main(String[] args) throws IOException {

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
    }

    private static RestaurantToYelpIdData getRestaurantToYelpIdData(String line) {

        String[] tokens = line.split(",");

        String restID = tokens[0];
        String yelpID = tokens[1];

        return new RestaurantToYelpIdData(restID, yelpID);
    }
}
