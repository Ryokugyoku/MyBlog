# ---------- ビルド段階 ----------
FROM eclipse-temurin:24-jdk AS build
WORKDIR /workspace

# Gradle Wrapper をコピー
COPY gradlew .
COPY gradle gradle
RUN chmod +x gradlew

# 依存解決用にビルドスクリプトのみコピー
COPY settings.gradle* build.gradle* ./
RUN ./gradlew --no-daemon build -x test || true

# 残りのソースをコピーして本ビルド
COPY src src
RUN ./gradlew --no-daemon clean bootJar -x test

# ---------- 実行段階 ----------
FROM eclipse-temurin:24-jre
WORKDIR /app
COPY --from=build /workspace/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]