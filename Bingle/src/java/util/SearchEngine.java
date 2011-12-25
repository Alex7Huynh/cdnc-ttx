/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
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
public class SearchEngine {

    public static ArrayList<Article> SearchGoogle(String aKeyword, int aPageNumber, int aQuantity) {
        ArrayList<Article> articles = new ArrayList<Article>();
        try {
            //Create search string
            String APIKey = "AIzaSyDvysnoSg7Xlw4sKcmtdKhsRx_EaD_59TM";
            String CSEID = "006128248623655005956:_w9w403uat0";
            int startIndex = (aPageNumber - 1) * 10 + 1;
            String urlTemplate = "https://www.googleapis.com/customsearch/v1?key="
                    + APIKey + "&cx=" + CSEID + "&q=" + aKeyword + "&num=" + aQuantity + "&start=" + startIndex + "&alt=json";
            //Connect
            URL url = new URL(urlTemplate);
            URLConnection connection = url.openConnection();
            //Get JSON content
            String line;
            StringBuilder builder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
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

    public static ArrayList<Article> SearchBing(String aKeyword, int aPageNumber, int aQuantity) {
        ArrayList<Article> articles = new ArrayList<Article>();
        try {
            //Create search string
            String apiID = "FE383F9A948802A6D19102654EE563456120DDC6";
            String urlTemplate = "http://api.bing.net/json.aspx?Appid="
                    + apiID + "&query=" + aKeyword + "&web.count=" + aQuantity + "&web.offset=" + (aPageNumber - 1) * 10 + "&sources=web";
            //Connect
            URL url = new URL(urlTemplate);
            URLConnection connection = url.openConnection();
            //Get JSON content
            String line;
            StringBuilder builder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
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

    public static ArrayList<Article> Search(String aKeyword, int aPageNumber, int aQuantity) {
        ArrayList<Article> articles = new ArrayList<Article>();
        try {
            String keyWord = URLEncoder.encode(aKeyword, "UTF-8");

            ArrayList<Article> articlesGoogle = SearchEngine.SearchGoogle(keyWord, aPageNumber, aQuantity);
            ArrayList<Article> articlesBing = SearchEngine.SearchBing(keyWord, aPageNumber, aQuantity);

            //Lấy phần chung
            for (int i = 0; i < articlesGoogle.size(); ++i) {
                boolean flag = false;
                for (int j = 0; j < articlesBing.size(); ++j) {
                    if (articlesGoogle.get(i).getLink().equals(articlesBing.get(j).getLink())) {
                        articlesGoogle.get(i).setSource("Google/Bing");
                        Article tmp = articlesGoogle.get(i);
                        tmp.setTitle(TranslateEngine.Translate(tmp.getTitle()));
                        articles.add(tmp);
                        articlesBing.remove(j);
                        j--;
                        flag = true;
                    }
                }
                if (flag) {
                    articlesGoogle.remove(i);
                    i--;
                }
            }
            //Lấy phần riêng của Google
            for (int i = 0; i < articlesGoogle.size(); ++i) {
                Article tmp = articlesGoogle.get(i);
                tmp.setTitle(TranslateEngine.Translate(tmp.getTitle()));
                articles.add(tmp);
            }
            //Lấy phần riêng của Bing
            for (int i = 0; i < articlesBing.size(); ++i) {
                Article tmp = articlesBing.get(i);
                tmp.setTitle(TranslateEngine.Translate(tmp.getTitle()));
                articles.add(tmp);
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(SearchEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return articles;
    }
}
