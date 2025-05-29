plugins {
    id("com.github.ben-manes.versions") version "0.52.0"
    application
    checkstyle
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

application {
    mainClass.set("hexlet.code.App")
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("info.picocli:picocli:4.7.7")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.19.0")
}

tasks.test {
    useJUnitPlatform()
}

checkstyle {
    toolVersion = "10.24.0"
    configFile = file("config/checkstyle/checkstyle.xml")
}