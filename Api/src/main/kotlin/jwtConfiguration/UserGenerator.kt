package jwtConfiguration

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import javalinjwt.JWTGenerator
import org.github.unqui.User

class UserGenerator : JWTGenerator<User> {
    override fun generate(user: User, algorithm: Algorithm): String {
        val token = JWT.create().withClaim("id", user.id)
        return token.sign(algorithm)
    }

}