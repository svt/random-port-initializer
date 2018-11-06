package se.svt.util.spring

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = arrayOf(RandomPortInitializerTest.MyTestConfiguration::class),
        initializers = arrayOf(RandomPortInitializer::class))
class RandomPortInitializerTest {

    @Value("\${random-port.port1}")
    var port1: Int = 0

    @Value("\${random-port.port1}")
    var port2: Int = 0

    @Value("\${random-port.port3}")
    var port3: Int = 0

    @Configuration
    open class MyTestConfiguration

    @Test
    fun `Random ports are assigned, same identifier gives same port`() {
        assertThat(port1).isGreaterThan(0)

        assertThat(port1).isEqualTo(port2)

        assertThat(port3)
                .isGreaterThan(0)
                .isNotEqualTo(port1)
    }
}