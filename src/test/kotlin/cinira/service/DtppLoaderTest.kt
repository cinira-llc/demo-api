package cinira.service

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.support.ResourcePatternResolver
import java.time.Instant.parse

@SpringBootTest
class DtppLoaderTest {

    @Test
    fun test(@Autowired resolver: ResourcePatternResolver) {
        val resources = DtppLoader.load(parse("2024-04-20T00:00:00Z"), "classpath*:dtpp-") { prefix ->
            resolver.getResources("$prefix*/*.json.bz2")
        }
        println(resources)
    }
}
