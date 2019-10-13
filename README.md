# Random Port Initializer

**Description**:  A Spring test util for resolving property placeholders to free random ports.

  - **Technology stack**: Kotlin, Spring 5
  - **Status**:  1.0
  
## Dependencies

Spring 5

## Usage

Add to build.gradle
```
testCompile 'se.svt.oss:random-port-initializer:X.Y.Z
```

When the RandomPortInitializer is applied to the application context, any property placeholders
starting with prefix ```random-port.``` will be resolved to a random free port. If the same
property placeholder appears in multiple places, the same port will be assigned everywhere.

Example on usage in test
```
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

```./gradlew test```

----

## Update version and release to jcenter/bintray (for maintainers)

1. Make sure you are on master branch and that everything is pushed to master
2. ./gradlew release to tag a new version (uses Axion release plugin - needs ssh key for repo)
3.  ./gradlew bintrayUpload to upload to repo - needs BINTRAY_KEY and BINTRAY_USER environment variables



## License

Copyright 2018 Sveriges Television AB

This software is released under the Apache 2.0 License.

## Primary Maintainers

Gustav Grusell https://github.com/grusell

----
