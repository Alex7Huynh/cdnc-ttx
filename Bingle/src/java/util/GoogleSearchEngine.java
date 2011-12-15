/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

/**
 *
 * @author Fate
 */
public class GoogleSearchEngine {

    public static ArrayList<Article> Search(String aKeyword, int aPageNumber) {
        ArrayList<Article> articles = new ArrayList<Article>();
        try {
            //Create search string
            String APIKey = "AIzaSyDvysnoSg7Xlw4sKcmtdKhsRx_EaD_59TM";
            String CSEID = "006128248623655005956:_w9w403uat0";
            String google = "https://www.googleapis.com/customsearch/v1?key=" 
                    + APIKey + "&cx=" + CSEID + "&q=" + aKeyword + "&start=" + (aPageNumber-1)*10+1 + "&alt=json";
            //Connect
            URL url = new URL(google);
            URLConnection connection = url.openConnection();
            //Get JSON content
            String line;
            StringBuilder builder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            //Parse JSON content to Array of Articles
            JSONObject json = (JSONObject) JSONSerializer.toJSON(builder.toString());
            JSONArray items = json.getJSONArray("items");
            for (int i = 0; i < items.size(); ++i) {
                JSONObject item = items.getJSONObject(i);
                String title = item.getString("title");
                String link = item.getString("link");
                String description = item.getString("snippet");
                articles.add(new Article(title, link, description, "google"));
            }
        } catch (IOException ex) {
            Logger.getLogger(GoogleSearchEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
        return articles;
    }
}
