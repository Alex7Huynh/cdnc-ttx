/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fate
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            //ArrayList<Article> GPage1 = SearchEngine.SearchGoogle("flowers", 1, 10);
            //ArrayList<Article> GPage2 = SearchEngine.SearchGoogle("flowers", 2);
            //ArrayList<Article> BingArticles = SearchEngine.SearchBing("flowers", 1, 10);
            //ArrayList<Article> articles = SearchEngine.Search("flowers", 1, 5);
            //ArrayList<Article> articles = SearchEngine.Search("flowers", 1, 5);

            //System.out.println("Hello World");
            //Create search string
            String APIKey = "AIzaSyDvysnoSg7Xlw4sKcmtdKhsRx_EaD_59TM";
            String CSEID = "006128248623655005956:_w9w403uat0";
            int startIndex = (1 - 1) * 10 + 1;
            String aKeyword = "flowers";
            String urlTemplate = "https://www.googleapis.com/customsearch/v1?key="
                    + APIKey + "&cx=" + CSEID + "&q=" + aKeyword + "&num=" + 3 + "&start=" + startIndex + "&alt=json";
            //Connect
            //URL url = new URL(urlTemplate);
            //URLConnection connection = url.openConnection();
            String abc = URLEncoder.encode("giá trị", "UTF-8");
            int a;
            a = 10;
        } catch (Exception ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
