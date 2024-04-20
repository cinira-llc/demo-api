plugins {
    kotlin("plugin.noarg") version "1.9.23"
    kotlin("jvm") version "1.9.23"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
    withJavadocJar()
    withSourcesJar()
}

dependencies {
    //<editor-fold desc="Platform dependencies">
    implementation(platform("org.springframework.boot:spring-boot-dependencies:3.2.4"))
    //</editor-fold>

    //<editor-fold desc="Implementation dependencies">
    implementation("com.zaxxer:HikariCP")
    implementation("jakarta.persistence:jakarta.persistence-api")
    implementation("org.apache.commons:commons-compress:1.26.1")
    implementation("org.slf4j:slf4j-api")
    implementation("org.springframework:spring-context")
    implementation("org.springframework:spring-jdbc")
    implementation("org.springframework.boot:spring-boot-autoconfigure")
    //</editor-fold>

    //<editor-fold desc="Runtime-only dependencies">
    runtimeOnly("org.hibernate.orm:hibernate-community-dialects:6.4.1.Final")
    runtimeOnly("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("org.xerial:sqlite-jdbc")
    //</editor-fold>

    //<editor-fold desc="Test implementation dependencies">
    testImplementation("org.assertj:assertj-core")
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.junit.jupiter:junit-jupiter-params")
    testImplementation("org.springframework:spring-beans")
    testImplementation("org.springframework:spring-core")
    testImplementation("org.springframework.boot:spring-boot-test")
    //</editor-fold>

    //<editor-fold desc="Runtime-only dependencies">
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    testRuntimeOnly("org.springframework.boot:spring-boot-starter-test")
    //</editor-fold>
}

repositories {
    mavenCentral()
    mavenLocal()
}

//kotlin {
//    jvmToolchain {}
//}

tasks.withType<Test> {
    useJUnitPlatform()
}

noArg {
    annotation("jakarta.persistence.Entity")
}