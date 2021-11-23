package dev.vrba.teaparty.security

import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.core.env.Profiles
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@EnableWebMvc
class WebMvcConfiguration(private val environment: Environment) : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        // In production, the frontend package is built in CI and server from static resources,
        // therefore they are served from the same domain as the API and there is no need for CORS mapping
        if (environment.acceptsProfiles(Profiles.of("development"))) {
            registry.addMapping("/api/**")
                    .allowedOrigins("http://localhost:3000")
                    .allowCredentials(false)
        }
    }
}