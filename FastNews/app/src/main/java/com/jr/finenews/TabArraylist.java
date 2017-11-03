package com.jr.finenews;

/**
 * Created by alfo6-6 on 2017-06-16.
 */

public class TabArraylist {
    private String title;
    private String content;
    private String date;
    private String link;

    public TabArraylist(String title, String content, String date, String link) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.link = link;
    }
    public TabArraylist(){
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
