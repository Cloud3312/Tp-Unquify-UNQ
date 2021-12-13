package jwtConfiguration

import controller.JwtController
import exception.NotFoundToken
import io.javalin.core.security.AccessManager
import io.javalin.core.security.RouteRole
import io.javalin.http.Context
import io.javalin.http.Handler
import io.javalin.http.UnauthorizedResponse
import org.github.unqui.User

class TokenAccessManager(private val jwtController : JwtController) : AccessManager {

    private fun getUser(token : String) : User {
        try {
            return jwtController.validate(token)
        } catch (e : NotFoundToken) {
            throw UnauthorizedResponse("Token invalid")
        }
    }

    override fun manage(handler: Handler, context: Context, roles: MutableSet<RouteRole>) {
        val token = context.header("Authentication")
        when {
            roles.contains(Role.ANYONE) -> handler.handle(context)
            token == null -> throw UnauthorizedResponse("Token not found.")
            roles.contains(Role.USER) -> {
                val user = getUser(token)
                context.attribute("user", user)
                handler.handle(context)
            }
        }
    }
}
