package hexlet.code.util;

public class NamedRoutes {
    public static String rootPath() {
        return "/";
    }

    public static String urlsPath() {
        return rootPath() + "urls";
    }

    public static String urlPath() {
        return urlsPath() + "/{id}";
    }

    public static String urlPath(String id) {
        return urlsPath() + "/" + id;
    }

    public static String checkUrlPath(String id) {
        return urlPath(id) + "/checks";
    }

    public static String checkUrlPath() {
        return urlPath() + "/checks";
    }
}
