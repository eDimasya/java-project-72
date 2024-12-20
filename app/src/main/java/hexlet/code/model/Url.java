package hexlet.code.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@NoArgsConstructor
@Data
public class Url {
    private Long id;
    private String name;
    private Timestamp created_at;
    public Url(String name) {
        this.name = name;
        this.created_at = new Timestamp(new Date().getTime());
    }
}
