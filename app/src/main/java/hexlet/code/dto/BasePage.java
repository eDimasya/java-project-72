package hexlet.code.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasePage {
    private final String title = "Анализатор страниц";
    private String flash;
}
