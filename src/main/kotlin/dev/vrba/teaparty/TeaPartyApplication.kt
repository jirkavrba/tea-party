package dev.vrba.teaparty

import dev.vrba.teaparty.service.LobbiesService
import dev.vrba.teaparty.service.PlayersService
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TeaPartyApplication(
    private val playersService: PlayersService,
    private val lobbiesService: LobbiesService
) : CommandLineRunner {

    override fun run(vararg args: String?) {
        playersService.deleteAllPlayerAccounts()
        lobbiesService.deleteAll()
    }

}

fun main(args: Array<String>) {
    runApplication<TeaPartyApplication>(*args)
}
