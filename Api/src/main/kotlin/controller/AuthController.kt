package controller

import SpotifyService
import dto.ErrorDTO
import dto.UserDTO
import dto.UserLoginDTO
import io.javalin.http.Context
import org.github.unqui.UserException
import utility.DataContextValidator

class AuthController(private val spotifyService: SpotifyService, private val jwtController: JwtController) {

    private val dataValidator = DataContextValidator()

    fun registerUser(context : Context) {
        val userDraft = dataValidator.getUserDraftIfValidDataOrElseThrowException(context)
        try {
            val createdUser = spotifyService.register(userDraft)
            val token = jwtController.generateToken(createdUser)
            context.header("Authentication", token)
            context.status(201).json(UserDTO(createdUser))
        } catch (exception : UserException) {
            context.status(400).json(ErrorDTO("error", exception.message!!))
        }
    }

    fun loginUser(context: Context) {
        val loginData = context.bodyAsClass<UserLoginDTO>()
        try {
            val user = spotifyService.login(loginData.email, loginData.password)
            val token = jwtController.generateToken(user)
            context.header("Authentication", token)
            context.json(UserDTO(user))
        } catch (exception : UserException) {
            context.status(404).json(ErrorDTO("error", "User not found"))
        }
    }

}
