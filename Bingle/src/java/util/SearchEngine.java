/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.google.code.bing.search.client.BingSearchClient;
import com.google.code.bing.search.client.BingSearchClient.SearchRequestBuilder;
import com.google.code.bing.search.client.BingSearchServiceClientFactory;
import com.google.code.bing.search.schema.AdultOption;
import com.google.code.bing.search.schema.SearchOption;
import com.google.code.bing.search.schema.SearchResponse;
import com.google.code.bing.search.schema.SourceType;
import com.google.code.bing.search.schema.web.WebResult;
import com.google.code.bing.search.schema.web.WebSearchOption;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

/**
 *
 * @author Fate
 */
public class SearchEngine {

    public static ArrayList<Article> SearchGoogle(String aKeyword, int aPageNumber) {
        ArrayList<Article> articles = new ArrayList<Article>();
        try {
            //Create search string
            String APIKey = "AIzaSyDvysnoSg7Xlw4sKcmtdKhsRx_EaD_59TM";
            String CSEID = "006128248623655005956:_w9w403uat0";
            String urlTemplate = "https://www.googleapis.com/customsearch/v1?key="
                    + APIKey + "&cx=" + CSEID + "&q=" + aKeyword + "&start=" + (aPageNumber - 1) * 10 + 1 + "&alt=json";
            //Connect
            URL url = new URL(urlTemplate);
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
                articles.add(new Article(title, link, description, "Google"));
            }
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
        return articles;
    }

    public static ArrayList<Article> SearchBing(String aKeyword, int aPageNumber) {
        ArrayList<Article> articles = new ArrayList<Article>();
        try {
            //Create search string
            String apiID = "FE383F9A948802A6D19102654EE563456120DDC6";
            String urlTemplate = "http://api.bing.net/json.aspx?Appid=" 
                    + apiID + "&query=" + aKeyword + "&web.offset=" + (aPageNumber-1)*10 + "&sources=web";
            //Connect
            URL url = new URL(urlTemplate);
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
            JSONArray items = json.getJSONObject("SearchResponse").getJSONObject("Web").getJSONArray("Results");
            for (int i = 0; i < items.size(); ++i) {
                JSONObject item = items.getJSONObject(i);
                String title, link, description;
                //Get title
                try {
                    title = item.getString("Title");
                } catch (Exception ex) {
                    title = null;
                }
                //Get link
                try {
                    link = item.getString("Url");
                } catch (Exception ex) {
                    link = null;
                }
                //Get description
                try {
                    description = item.getString("Description");
                } catch (Exception ex) {
                    description = null;
                }
                articles.add(new Article(title, link, description, "Bing"));
            }
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
        return articles;
    }

    public static ArrayList<Article> SearchBingLibrary(String aKeyword, int aPageNumber) {
        ArrayList<Article> articles = new ArrayList<Article>();
        //Create search string
        String apiID = "FE383F9A948802A6D19102654EE563456120DDC6";
        //String google = "http://api.bing.net/json.aspx?Appid=" + apiID + "&query=" + aKeyword + "&sources=web";
        //Connect and get results
        BingSearchServiceClientFactory factory = BingSearchServiceClientFactory.newInstance();
        BingSearchClient client = factory.createBingSearchClient();

        SearchRequestBuilder builder = client.newSearchRequestBuilder();
        builder.withAppId(apiID);
        builder.withQuery(aKeyword);
        builder.withSourceType(SourceType.WEB);
        builder.withVersion("2.0");
        builder.withMarket("en-us");
        builder.withAdultOption(AdultOption.MODERATE);
        builder.withSearchOption(SearchOption.ENABLE_HIGHLIGHTING);

        builder.withWebRequestCount(10L);
        builder.withWebRequestOffset(0L);
        builder.withWebRequestSearchOption(WebSearchOption.DISABLE_HOST_COLLAPSING);
        builder.withWebRequestSearchOption(WebSearchOption.DISABLE_QUERY_ALTERATIONS);

        SearchResponse response = client.search(builder.getResult());

        for (WebResult result : response.getWeb().getResults()) {
            articles.add(new Article(result.getTitle(), result.getUrl(), result.getDescription(), "Bing"));
        }

        return articles;
    }
}
