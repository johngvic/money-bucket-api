package br.com.moneybucket

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MoneyBucketApplication

fun main(args: Array<String>) {
	runApplication<MoneyBucketApplication>(*args)
}
