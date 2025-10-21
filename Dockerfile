# Java 21 の公式イメージを使用
FROM eclipse-temurin:21-jdk

# 作業ディレクトリを設定
WORKDIR /app

# プロジェクト全体をコピー
COPY . .

# gradlewに実行権限を付与
RUN chmod +x gradlew

# ビルド（テストはスキップ）
RUN ./gradlew build -x test

# 作成された jar を実行
CMD ["java", "-jar", "build/libs/allergy-aplication-0.0.1-SNAPSHOT.jar"]
