package hexlet.code.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@ToString
public class UrlCheck {
    private int id;
    private int statusCode;
    private String title;
    private String h1;
    private String description;
    private int urlId;
    private Timestamp createdAt;

    public UrlCheck(int statusCode, String title, String h1, String description, Url url) {
        new UrlCheck(statusCode, title, h1, description, url.getId());
    }

    public UrlCheck(int statusCode, String title, String h1, String description, int urlId) {
        this.statusCode = statusCode;
        this.title = title;
        this.h1 = h1;
        this.description = description;
        this.urlId = urlId;
        this.createdAt = new Timestamp(new Date().getTime());
    }
}
