/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.ArrayList;

/**
 *
 * @author Fate
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //ArrayList<Article> GPage1 = SearchEngine.SearchGoogle("flowers", 1);
        //ArrayList<Article> GPage2 = SearchEngine.SearchGoogle("flowers", 2);
        ArrayList<Article> BingArticles = SearchEngine.SearchBing("flowers", 1);
        //ArrayList<Article> BingLibraryArticles = SearchEngine.SearchBingLibrary("flowers", 1);
        
        int a;
        a=10;
    }
}
