package cinira.boot

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.DisposableBean
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.Resource
import java.nio.file.Files.delete
import java.nio.file.Files.newOutputStream
import java.nio.file.Path
import java.nio.file.StandardOpenOption
import kotlin.io.path.createTempFile

@Configuration
open class DbContext {

    @Bean
    open fun databaseFile(@Value("classpath:CIFP_240418.db.bz2") db: Resource) =
        createTempFile("cifp", ".db").also { temp ->
            db.inputStream.use { input ->
                newOutputStream(temp, StandardOpenOption.WRITE).use(input::copyTo)
            }
        }

    @Bean
    open fun databaseFileCleanup(@Qualifier("databaseFile") file: Path) =
        DisposableBean {
            delete(file)
        }

    @Bean
    open fun dataSource(@Qualifier("databaseFile") file: Path) =
        HikariDataSource(HikariConfig().apply {
            jdbcUrl = "jdbc:sqlite:$file"
        })
}