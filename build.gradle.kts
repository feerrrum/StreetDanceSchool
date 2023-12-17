plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation ("mysql:mysql-connector-java:8.0.23")
    implementation ("org.telegram:telegrambots:5.0.1")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.telegram:telegrambots:6.8.0")
    implementation ("org.hibernate:hibernate-core:6.1.3.Final")
}

tasks.test {
    useJUnitPlatform()
}