package main

import controller.*
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import io.javalin.core.util.RouteOverviewPlugin
import jwtConfiguration.Role
import jwtConfiguration.TokenAccessManager
import org.github.unqui.getSpotifyService

class APIApplication {

    private val spotifyService = getSpotifyService()
    private val userController = UserController(spotifyService)
    private val playlistController = PlaylistController(spotifyService)
    private val jwtController = JwtController(spotifyService)
    private val searchController = SearchController(spotifyService)
    private val authController = AuthController(spotifyService, jwtController)

    fun startAtPort(aPort : Int) {
        val app = Javalin.create {
            it.accessManager(TokenAccessManager(jwtController))
            it.defaultContentType = "application/json"
            it.enableCorsForAllOrigins()
            it.registerPlugin(RouteOverviewPlugin("/routes"))
        }.start(aPort)

        app.before {
            it.header("Access-Control-Expose-Headers", "*")
        }

        app.routes {
            path("register") {
                post(authController::registerUser, Role.ANYONE)
            }
            path("login") {
                post(authController::loginUser, Role.ANYONE)
            }
            path("user"){
                get(userController::getUser, Role.USER)
                path("{userId}") {
                    get(userController::getUserById, Role.USER)
                }
            }
            path("playlist"){
                path("{playlistId}") {
                    get(playlistController::getPlaylist, Role.USER)
                    put(playlistController::putPlaylist, Role.USER)
                }
            }
            path("search"){
                get(searchController::textBasedSearch, Role.USER)
            }

        }

    }



}
