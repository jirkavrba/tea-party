package dev.vrba.teaparty.security

import dev.vrba.teaparty.repositories.PlayersRepository
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class TokenAuthenticationFilter(private val repository: PlayersRepository) : OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        // If there is an Authorization header present,
        // try looking up for a matching player and if found,
        // set it as the authentication principal
        request.getHeader("Authorization")?.let {
            val token = it.removePrefix("Bearer ")
            val player = repository.findByToken(token) ?: return filterChain.doFilter(request, response)

            val authorities = mutableListOf(SimpleGrantedAuthority("ROLE_USER"))
            val authentication = UsernamePasswordAuthenticationToken(player, token, authorities)

            SecurityContextHolder.clearContext()
            SecurityContextHolder.getContext().authentication = authentication
        }

        return filterChain.doFilter(request, response)
    }
}