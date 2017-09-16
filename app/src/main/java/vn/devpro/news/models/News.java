package vn.devpro.news.models;

/**
 * Created by tuaan on 9/16/2017.
 */

public class News {
    private String title;
    private String link;
    private String imgURL;

    public News(String title, String link, String imgURL) {
        this.title = title;
        this.link = link;
        this.imgURL = imgURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }
}
