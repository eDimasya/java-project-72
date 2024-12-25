package hexlet.code.controller;

import hexlet.code.dto.MainPage;
import hexlet.code.dto.urls.UrlPage;
import hexlet.code.dto.urls.UrlsPage;
import hexlet.code.model.Url;
import hexlet.code.model.UrlCheck;
import hexlet.code.repository.CheckRepository;
import hexlet.code.repository.UrlRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.http.Context;
import kong.unirest.core.HttpResponse;
import kong.unirest.core.Unirest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.net.URI;
import java.net.URL;

import static io.javalin.rendering.template.TemplateUtil.model;

public class UrlsController {
    public static void root(Context context) {
        MainPage page = new MainPage();
        page.setFlash(context.consumeSessionAttribute("flash"));
        context.render("index.jte", model("page", page));
    }

    public static void add(Context context) {
        String str = context.formParam("url");
        try {
            assert str != null;
            URI uri = URI.create(str);
            if (uri.getScheme() == null || uri.getHost() == null) {
                throw new IllegalArgumentException();
            }
            if (!uri.getScheme().equals("http") && !uri.getScheme().equals("https")) {
                throw new IllegalArgumentException();
            }
            URL fullUrl = uri.toURL();
            Url url = new Url(fullUrl.getProtocol() + "://" + fullUrl.getHost() +
                    (fullUrl.getPort() != -1 ? ":" + fullUrl.getPort() : ""));
            if (UrlRepository.getEntities().stream().noneMatch(u ->
                    u.getName().equalsIgnoreCase(url.getName()))) {
                UrlRepository.save(url);
                context.sessionAttribute("flash", "Страница успешно добавлена");
                context.redirect(NamedRoutes.urlsPath());
            } else {
                context.sessionAttribute("flash", "Страница уже существует");
                context.redirect(NamedRoutes.rootPath());
            }
        } catch (Exception e) {
            context.sessionAttribute("flash", "Некорректный URL");
            context.redirect(NamedRoutes.rootPath());
        }
    }

    public static void show(Context context) {
        String id = context.pathParam("id");
        if (UrlRepository.find(Integer.parseInt(id)).isPresent()) {
            Url url = UrlRepository.find(Integer.parseInt(id)).get();
            UrlPage page = new UrlPage(url);
            page.setFlash(context.consumeSessionAttribute("flash"));
            context.render("url/show.jte", model("page", page));
        } else {
            context.sessionAttribute("flash", "Id: " + id + " is not found");
            context.redirect(NamedRoutes.rootPath());
        }
    }

    public static void index(Context context) {
        UrlsPage page = new UrlsPage();
        page.setFlash(context.consumeSessionAttribute("flash"));
        context.render("url/index.jte", model("page", page));
    }

    public static void check(Context context) {
        Url url = UrlRepository.find(Integer.parseInt(context.pathParam("id"))).get();
        HttpResponse<String> response = Unirest.get(url.getName()).asString();
        if (response.isSuccess()) {
            Document body = Jsoup.parse(response.getBody());
            String title = body.title();
            Element h1Element = body.selectFirst("h1");
            String h1 = h1Element != null ? h1Element.text() : "";
            Element metaDescription = body.selectFirst("meta[name=description]");
            String description = metaDescription != null ? metaDescription.attr("content") : "";
            UrlCheck urlCheck = new UrlCheck(response.getStatus(),
                    title, h1, description, url.getId());
            CheckRepository.save(urlCheck);
        } else {
            context.sessionAttribute("flash", "Сайт недоступен");
        }
        context.redirect(NamedRoutes.urlPath(context.pathParam("id")));
    }
}
