plugins {
    java
    kotlin("jvm") version "1.4.21"
}

group = "daniil.akifev"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation("io.reactivex.rxjava3:rxjava:3.0.10")

    implementation("org.mongodb:mongodb-driver-rx:1.5.0")

    implementation("io.reactivex:rxnetty-http:0.5.3")
    implementation("io.reactivex:rxnetty-common:0.5.3")
    implementation("io.reactivex:rxnetty-tcp:0.5.3")

    implementation("io.netty:netty-all:4.1.59.Final")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}