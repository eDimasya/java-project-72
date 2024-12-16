package hexlet.code.controller;

import io.javalin.http.Context;

public class SessionController {
    public static void root(Context context) {
        context.result("Hello world!");
    }
}
