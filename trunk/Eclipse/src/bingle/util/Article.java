/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bingle.util;

/**
 *
 * @author Fate
 */
public class Article {

    private String title;
    private String link;
    private String description;
    private String source;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Article(String title, String link, String description, String source) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.source = source;
    }
}
