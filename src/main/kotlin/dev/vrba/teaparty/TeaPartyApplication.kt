package dev.vrba.teaparty

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TeaPartyApplication

fun main(args: Array<String>) {
    runApplication<TeaPartyApplication>(*args)
}
