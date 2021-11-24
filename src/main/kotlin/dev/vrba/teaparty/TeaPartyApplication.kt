package dev.vrba.teaparty

import dev.vrba.teaparty.database.DatabaseSeeder
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.core.env.Environment
import org.springframework.core.env.Profiles

@SpringBootApplication
class TeaPartyApplication(
        private val environment: Environment,
        private val seeder: DatabaseSeeder
) : CommandLineRunner {
    override fun run(vararg args: String?) {
        // TODO: Remove this later probably
        if (environment.acceptsProfiles(Profiles.of("development"))) {
            seeder.run()
        }
    }
}

fun main(args: Array<String>) {
    runApplication<TeaPartyApplication>(*args)
}
