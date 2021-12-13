package controller

import SpotifyService
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import exception.NotFoundToken
import javalinjwt.JWTProvider
import jwtConfiguration.UserGenerator
import org.github.unqui.User
import java.lang.Exception

class JwtController(private val spotifyService: SpotifyService) {

    private val algorithm = Algorithm.HMAC256("secret")
    private val generator = UserGenerator()
    private val verifier = JWT.require(algorithm).build()
    private val provider = JWTProvider(algorithm, generator, verifier)

    fun generateToken(user: User): String {
        return provider.generateToken(user)
    }

    fun validate(aToken: String): User {
        try {
            val token = provider.validateToken(aToken).orElseThrow { NotFoundToken("Invalid token.") }
            val userId = token.getClaim("id").asString()
            return spotifyService.getUser(userId)
        } catch (e : Exception) {
            throw NotFoundToken("Invalid token.")
        }
    }

}
