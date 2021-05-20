import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.4.4"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.4.31"
	kotlin("plugin.spring") version "1.4.31"
	id("io.gitlab.arturbosch.detekt").version("1.17.0")
}

group = "top.devexp"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

buildscript {
	repositories {
		maven("https://plugins.gradle.org/m2/")
	}
	dependencies {
		classpath("org.jlleitschuh.gradle:ktlint-gradle:10.0.0")
	}
}

detekt {
	buildUponDefaultConfig = true
	allRules = false
	config = files("$projectDir/practice/detekt/detekt.yml")
	baseline = file("$projectDir/practice/detekt/baseline.xml") // a way of suppressing issues before introducing detekt

	reports {
		html.enabled = true
		xml.enabled = false
		txt.enabled = false
		sarif.enabled = false
	}
}

repositories {
	mavenCentral()
}

apply(plugin = "org.jlleitschuh.gradle.ktlint")



dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

apply(from = "${projectDir}/gradle/git-hooks.gradle")
apply(from = "${projectDir}/gradle/checkstyle.gradle")
apply(from = "${projectDir}/gradle/pmd.gradle")