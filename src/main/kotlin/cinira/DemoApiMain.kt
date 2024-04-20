package cinira

import cinira.boot.AppContext
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication(scanBasePackageClasses = [AppContext::class])
@ComponentScan(basePackages = ["cinira.boot", "cinira.web"])
open class DemoApiMain

fun main(args: Array<String>) {
    SpringApplication.run(DemoApiMain::class.java, *args)
}
