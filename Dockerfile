FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

# Maven Wrapper（キャッシュ効率）
COPY mvnw .
COPY mvnw.cmd .
COPY .mvn .mvn
COPY pom.xml .

# 依存関係を先に取得（Maven 3.9系：2024年）
RUN chmod +x mvnw && ./mvnw dependency:go-offline

# ソースコード
COPY src src

# DevTools を有効化
ENV SPRING_DEVTOOLS_RESTART_ENABLED=true

# ★ fork=false が超重要（2024年以降も変わらず必須）
CMD ["./mvnw", "spring-boot:run", "-Dspring-boot.run.fork=false"]
