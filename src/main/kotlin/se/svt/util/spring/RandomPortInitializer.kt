package se.svt.util.spring

import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.core.env.PropertySource
import org.springframework.core.env.StandardEnvironment
import org.springframework.util.SocketUtils
import java.util.concurrent.ConcurrentHashMap

class RandomPortInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {

    override fun initialize(applicationContext: ConfigurableApplicationContext) {

        applicationContext.environment.propertySources
                .addAfter(StandardEnvironment.SYSTEM_ENVIRONMENT_PROPERTY_SOURCE_NAME,
                        RandomPortPropertySource(RANDOM_PORTS_PROPERTY_SOURCE_NAME))
    }
}

val RANDOM_PORTS_PROPERTY_SOURCE_NAME = "randomPorts"

val RANDOM_PORTS_PROPERTY_SOURCE_PREFIX = "random-port."

class RandomPortPropertySource(name: String) : PropertySource<Any>(name) {

    val ports = ConcurrentHashMap<String, Int>()

    override fun getProperty(name: String): Any? {
        if (! this.isRandomPortProperty(name)) {
            return null
        }
        return ports.computeIfAbsent(name) { _ -> SocketUtils.findAvailableTcpPort() }
    }

    override fun containsProperty(name: String): Boolean {
        return isRandomPortProperty(name)
    }

    private fun isRandomPortProperty(name: String) =
        name.startsWith(RANDOM_PORTS_PROPERTY_SOURCE_PREFIX) && name.length > RANDOM_PORTS_PROPERTY_SOURCE_PREFIX.length
}
