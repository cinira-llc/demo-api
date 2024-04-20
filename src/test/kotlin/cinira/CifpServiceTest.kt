package cinira

import cinira.boot.DbContext
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import jakarta.persistence.EntityManager
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.DisposableBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.Resource
import java.nio.file.Files.delete
import java.nio.file.Files.newOutputStream
import java.nio.file.Path
import java.nio.file.StandardOpenOption
import kotlin.io.path.createTempFile

@SpringBootTest(
    classes = [DbContext::class, CifpServiceTest.Context::class],
    webEnvironment = SpringBootTest.WebEnvironment.NONE
)
class CifpServiceTest {

    @Test
    fun test(@Autowired em: EntityManager) {
        val procedures = em.createQuery("select p from ApproachProcedure p where landingFacilityIcaoIdentifier = :ident")
            .setParameter("ident", "KMSN")
            .resultList
        println(procedures)
    }

    @Configuration
    @EnableAutoConfiguration
    open class Context {

        @Bean
        open fun databaseFile(@Value("classpath:cifp-240418/CIFP_240418.db.bz2") cifp: Resource) =
            createTempFile(javaClass.simpleName, ".db").also { temp ->
                BZip2CompressorInputStream(cifp.inputStream).use { input ->
                    newOutputStream(temp, StandardOpenOption.WRITE).use(input::copyTo)
                }
            }

        @Bean
        open fun dataSource(@Qualifier("databaseFile") databaseFile: Path) =
            HikariConfig().let { config ->
                config.jdbcUrl = "jdbc:sqlite:$databaseFile"
                HikariDataSource(config)
            }

        @Bean
        open fun databaseFileCleanup(@Qualifier("databaseFile") databaseFile: Path) =
            DisposableBean { delete(databaseFile) }
    }
}
