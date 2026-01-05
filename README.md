# Inferno FHIRPath Service

The `inferno-framework/fhirpath-service` provides a persistent service for
evaluating [FHIRPath](http://hl7.org/fhir/R4/fhirpath.html).
This lightweight wrapper enables applications not implemented in Java, such as
the [Inferno Framework](https://inferno-framework.github.io), to interface with
the FHIRPath in a service environment.  It is primarily being used within
the Inferno Framework to provide HL7® FHIR® validation services for tests.

Since this is just a lightweight wrapper around the FHIRPathEngine, most of
the functionality provided by this service is [implemented within the HL7® FHIR®
Core library](https://github.com/hapifhir/org.hl7.fhir.core), which is
developed and maintained independently of this project.

## Definitions

The base FHIR R4 definitions are loaded during setup. No other IGs or FHIR
versions are currently available.

## REST API

**[See here](rest-api.md) for the REST API documentation.**

## Installation

**System Requirements:** The FHIRPath Service requires Java 11 or above.

## Running Locally with Java

To build and run the test suite:

### *nix

```shell script
./gradlew build check test
```

### Windows

```shell script
gradlew.bat build check test
```

To run the app:

```shell script
./gradlew run
```

The port can also be set through the environment

```shell script
FHIRPATH_PORT=8080 ./gradlew run
```

## Running with Docker

Build

```shell script
./build_docker.sh
```

Run

```shell script
docker run -p 6789:6789 inferno_fhirpath_service
```


## Creating an Uber Jar

An uber jar can be created with:

```shell
./gradlew uberJar
```

By default, the uber jar will be located in `build/lib/`.

This uber jar can be executed with `java -jar InfernoFHIRPathService-<version>-uber.jar`

## Contact Us

The Inferno development team can be reached by email at
You may reach the team on the [Inferno HL7 FHIR chat
channel](https://chat.fhir.org/#narrow/stream/153-inferno).

## License

Copyright 2024 The MITRE Corporation

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
```
http://www.apache.org/licenses/LICENSE-2.0
```
Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.

## Trademark Notice

HL7, FHIR and the FHIR [FLAME DESIGN] are the registered trademarks of Health
Level Seven International and their use does not constitute endorsement by HL7.
