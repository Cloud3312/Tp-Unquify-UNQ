package jwtConfiguration

import io.javalin.core.security.RouteRole

enum class Role : RouteRole {
    ANYONE, USER
}