package top.devexp.cr

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CrApplication

fun main(args: Array<String>) {
	runApplication<CrApplication>(*args)
}
