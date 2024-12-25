package hexlet.code.dto.urls;

import hexlet.code.dto.BasePage;
import hexlet.code.model.Url;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static hexlet.code.repository.UrlRepository.getEntities;

@Getter
@Setter
public class UrlsPage extends BasePage {
    private List<Url> urls;
    public UrlsPage() {
        this.setUrls(getEntities());
    }
}
