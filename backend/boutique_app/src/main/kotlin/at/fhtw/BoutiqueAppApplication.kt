package at.fhtw

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BoutiqueAppApplication

fun main(args: Array<String>) {
	runApplication<BoutiqueAppApplication>(*args)
}
