@import hexlet.code.dto.BasePage
@import gg.jte.Content
@import hexlet.code.util.NamedRoutes

@param Content content
@param BasePage page = null

<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>${page.getTitle()}</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    </head>
    <body class="d-flex flex-column min-vh-100">
        <nav class="navbar navbar-expand-lg navbar-dark">
            <div class="container-fluid">
                <a class="navbar-brand" href="${NamedRoutes.rootPath()}">${page.getTitle()}</a>
                <button class="navbar-toggler" type="button" data-bs-toogle="collapse" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbar">
                    <div class="navbar-nav">
                        <a class="nav-link" href="${NamedRoutes.rootPath()}">Главная</a>
                        <a class="nav-link" href="${NamedRoutes.urlsPath()}">Сайты</a>
                    </div>
                </div>
            </div>
        </nav>
        @if(page.getFlash() != null)
            <div class="alert alert-info">${page.getFlash()}</div>
        @endif
        <main class="flex-grow-1">
            <section>
                ${content}
            </section>
        </main>
        <footer class="footer border-top py-3 mt-5 bg-light">
            <div class="container-x1">
                <div class="text-center">
                    created by
                    <a href="https://github.com/eDimasya" target="_blank">eDimasya</a>
                </div>
            </div>
        </footer>
    </body>
</html>