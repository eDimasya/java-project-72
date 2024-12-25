package hexlet.code.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@NoArgsConstructor
@Data
public class Url {
    private int id;
    private String name;
    private Timestamp createdAt;
    public Url(String name) {
        this.name = name;
        this.createdAt = new Timestamp(new Date().getTime());
    }
}
