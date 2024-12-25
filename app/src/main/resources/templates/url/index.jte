@import hexlet.code.dto.urls.UrlsPage
@import hexlet.code.model.Url
@import hexlet.code.repository.CheckRepository
@import hexlet.code.util.NamedRoutes

@param UrlsPage page

@template.layout.page(content = @`
    <div class="container-lg mt-5">
        <h1>Сайты</h1>
        <table class="table table-bordered table-hover mt-3">
            <thead>
            <tr>
                <th class="col-1">ID</th>
                <th>Имя</th>
                <th class="col-2">Последняя проверка</th>
                <th class="col-1">Код ответа</th>
            </tr>
            </thead>
            <tbody>
            @for(Url url : page.getUrls())
                <tr>
                    <td>
                        ${url.getId()}
                    </td>
                    <td>
                        <a href="${NamedRoutes.urlPath(String.valueOf(url.getId()))}">${url.getName()}</a>
                    </td>
                    <td>
                        @if(!CheckRepository.getEntities(url).isEmpty())
                            ${CheckRepository.getEntities(url).getLast().getCreatedAt().toString()}
                        @endif
                    </td>
                    <td>
                        @if(!CheckRepository.getEntities(url).isEmpty())
                            ${CheckRepository.getEntities(url).getLast().getStatusCode()}
                        @endif
                    </td>
                </tr>
            @endfor
            </tbody>
        </table>
    </div>
`,
page = page)
