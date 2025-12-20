FROM eclipse-temurin:11 AS build

# RUN curl -ksSL https://gitlab.mitre.org/mitre-scripts/mitre-pki/raw/master/os_scripts/install_certs.sh | MODE=ubuntu sh

WORKDIR /home
# Grab Gradle first so it can be cached
COPY gradle gradle
COPY gradlew .
RUN ./gradlew --version

COPY settings.gradle .
COPY gradle.properties .
COPY build.gradle.kts .
COPY config config
COPY src src

RUN ./gradlew build
RUN mkdir deploy && \
    tar -xvf build/distributions/InfernoFHIRPathService-*.tar -C deploy --strip-components=1

EXPOSE 6789

CMD ["./deploy/bin/InfernoFHIRPathService"]
