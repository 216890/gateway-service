package pl.lodz.p.stanczyk.gatewayservice.infrastructure.client

import feign.RequestInterceptor
import feign.RequestTemplate
import org.keycloak.adapters.springsecurity.account.SimpleKeycloakAccount
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class FeignOAuthInterceptor : RequestInterceptor {
    override fun apply(template: RequestTemplate) {
        SecurityContextHolder.getContext().authentication?.let {
            if (it.details is SimpleKeycloakAccount) {
                with(it.details as SimpleKeycloakAccount) {
                    template.header("Authorization", "Bearer ${this.keycloakSecurityContext.tokenString}")
                }
            }
        }
    }
}
