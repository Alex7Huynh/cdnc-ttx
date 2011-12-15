/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import sun.net.www.http.HttpClient;

/**
 *
 * @author Fate
 */
public class SearchEngine {

    final static String searchURL = "https://www.googleapis.com/customsearch/v1?";
// This is Important : 
    final static String apiKey = "My-Key-has a '-'";
    final static String customSearchEngineKey = "My-Custom-search-engine-that-has a ':' too";

    public String makeSearchString(String qSearch) {
        String toSearch = searchURL + "key=" + apiKey + "&cx=" + customSearchEngineKey;
        toSearch += "&q=" + qSearch + "&alt=json";
        return toSearch;
    }

    public static void main(String[] argv) throws IOException {
        /*System.setProperty("proxyHost", "my-host");
        System.setProperty("proxyPort", "my-Port");
        
        SearchEngine browser = new SearchEngine();
        String toSearch = browser.makeSearchString("flower");
        System.out.println(toSearch);
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(toSearch);
        
        HttpResponse response = httpClient.execute(httpGet);
        // exception is thrown here.
        HttpEntity entity = response.getEntity();
        
        if (entity != null) {
        InputStream inStream = entity.getContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
        int length = 0;
        byte[] buffer = new byte[2048];
        while ((length = inStream.read(buffer)) != -1) {
        System.out.println(buffer);
        }
        String inputLine;
        while ((inputLine = reader.readLine()) != null) {
        System.out.println(inputLine);
        }
        }*/

        String APIKey = "AIzaSyDvysnoSg7Xlw4sKcmtdKhsRx_EaD_59TM";
        String CSEID = "006128248623655005956:_w9w403uat0";
        String Keyword = "flowers";
        String google = "https://www.googleapis.com/customsearch/v1?key=" + APIKey + "&cx=" + CSEID + "&q=" + Keyword + "&alt=json";
        //URL url = new URL("https://ajax.googleapis.com/ajax/services/search/web?v=1.0&"+ "q=Paris%20Hilton&key=INSERT-YOUR-KEY&userip=USERS-IP-ADDRESS");
        URL url = new URL(google);
        URLConnection connection = url.openConnection();
        //connection.addRequestProperty("Referer", /* Enter the URL of your site here */);

        String line;
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }

        //JSONObject json = new JSONObject(builder.toString());
        JSONObject json = (JSONObject)JSONSerializer.toJSON(builder.toString());
        JSONObject searchResult = json.getJSONObject("items");
        String title = searchResult.getString("title");
        String link = searchResult.getString("link");
        String description = searchResult.getString("snippet");

        int a;
        a = 10;
    }
}
