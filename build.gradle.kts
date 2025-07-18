plugins {
    kotlin("jvm") version "1.8.0" // укажите вашу версию Kotlin
}

group = "org.elejiviQA"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Для Lombok
    compileOnly("org.projectlombok:lombok:1.18.38")
    annotationProcessor("org.projectlombok:lombok:1.18.38")

    implementation("com.google.code.gson:gson:2.13.1")

    implementation("org.slf4j:slf4j-api:1.7.36")
    implementation("ch.qos.logback:logback-classic:1.2.11")

    // Для тестов
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}
// Настройка кодировки для Java-компиляции и Javadoc на Windows-1251
tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<JavaExec> {
    jvmArgs("-Dfile.encoding=UTF-8")
}

tasks.test {
    jvmArgs("-Dfile.encoding=UTF-8")
}

tasks.withType<Javadoc> {
    options.encoding = "UTF-8"
}

tasks.named<JavaCompile>("compileTestJava") {
    options.encoding = "UTF-8"
}

// Настройка тестов для JUnit 5
tasks.test {
    useJUnitPlatform()
}