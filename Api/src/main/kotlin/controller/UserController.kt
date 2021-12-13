package controller

import SpotifyService
import dto.*
import io.javalin.http.Context
import io.javalin.http.NotFoundResponse
import org.github.unqui.User
import org.github.unqui.UserException

class UserController(private val spotifyService : SpotifyService) {

    fun getUserById(ctx: Context): Context{
        val userId = ctx.pathParam("userId")
        val user: User

        try {
            user = spotifyService.getUser(userId)
        }
        catch (exception: UserException) {
            return ctx
                .status(404)
                .json(SimpleErrorDTO("Not found user with id $userId"))
        }
        return ctx.json(UserDTO(user))
    }

    fun getUser(ctx: Context){
        val user = ctx.attribute<User>("user") ?: throw NotFoundResponse ("User not found")
        ctx.json(UserDTO(user))
    }
}
