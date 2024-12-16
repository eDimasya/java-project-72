# Используем OpenJDK 21
FROM eclipse-temurin:21-jdk

ARG GRADLE_VERSION=8.5

# Устанавливаем необходимые зависимости
RUN apt-get update && apt-get install -yq --no-install-recommends unzip wget \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

# Загружаем и устанавливаем Gradle
RUN wget -q https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip \
    && unzip gradle-${GRADLE_VERSION}-bin.zip \
    && rm gradle-${GRADLE_VERSION}-bin.zip

ENV GRADLE_HOME=/opt/gradle
ENV PATH=$PATH:$GRADLE_HOME/bin

RUN mv gradle-${GRADLE_VERSION} ${GRADLE_HOME}

WORKDIR /app

COPY /app .

RUN gradle installDistr

CMD ./build/install/app/bin/app