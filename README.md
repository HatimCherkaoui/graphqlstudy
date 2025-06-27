# Build & run

## Package

mvn clean package -DskipTests

## Run locally

java -jar target/graphql-demo-0.0.1-SNAPSHOT.jar

## Or via Docker Compose

docker-compose up --build

# Sample curl Usage

curl -X POST http://localhost:8080/graphql \
-H "Content-Type: application/json" \
-d '{"query":"{ books { id title author } }"}'
