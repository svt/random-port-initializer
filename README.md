
# Random Port Initializer

![REUSE Compliance](https://img.shields.io/reuse/compliance/github.com/svt/random-port-initializer)
![GitHub tag (latest SemVer)](https://img.shields.io/github/v/tag/svt/random-port-initializer)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

## What is it?

A Spring test util for resolving property placeholders to free random ports.

## Usage

Add to build.gradle.kts

```console

testCompile 'se.svt.oss:random-port-initializer:X.Y.Z

```

When the RandomPortInitializer is applied to the application context, any property placeholders
starting with prefix ```random-port.``` will be resolved to a random free port. If the same
property placeholder appears in multiple places, the same port will be assigned everywhere.

Example on usage in test

```kotlin
@ContextConfiguration(classes = arrayOf(MyTestConfiguration::class),
        initializers = arrayOf(RandomPortInitializer::class))
class SomeIntegrationTest {

    @Value("\${random-port.port1}")
    var myRandomPort: Int = 0

    @Test
    fun blabla....
}
```


## How to test the software

```console
./gradlew test
```

## Getting help

If you have questions, concerns, bug reports, etc, please file an issue in this repository's Issue Tracker.

## Getting involved

General instructions for contributing [CONTRIBUTING](docs/CONTRIBUTING.adoc).

## License

This software is released under the:

[Apache License 2.0](LICENSE)

Copyright 2018 Sveriges Television AB

## Primary Maintainer

Gustav Grusell <https://github.com/grusell>
