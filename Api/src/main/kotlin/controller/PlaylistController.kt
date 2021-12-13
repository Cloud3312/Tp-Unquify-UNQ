package controller

import SpotifyService
import dto.PlaylistDTO
import dto.SimpleErrorDTO
import dto.UserDTO
import io.javalin.http.Context
import io.javalin.http.NotFoundResponse
import org.github.unqui.Playlist
import org.github.unqui.PlaylistException
import org.github.unqui.User

class PlaylistController(private val spotifyService: SpotifyService) {

    fun getPlaylist(context: Context): Context {
        val playlistId = context.pathParam("playlistId")
        val playlist: Playlist

        try {
            playlist = spotifyService.getPlaylist(playlistId)
        }
        catch (exception: PlaylistException) {
            return context
                .status(404)
                .json(SimpleErrorDTO("Not found playlist with id $playlistId"))
        }

        return context.json(PlaylistDTO(playlist))
    }

    fun putPlaylist(context: Context): Context {
        val playlistId = context.pathParam("playlistId")
        val userId = context.attribute<User>("user")?.id ?: throw NotFoundResponse ("User not found")
        val user: User

        try {
            spotifyService.addOrRemoveLike(userId, playlistId)
            user = spotifyService.getUser(userId)
        }
        catch (playlistException: PlaylistException) {
            return context
                .status(404)
                .json(SimpleErrorDTO("Not found playlist with id $playlistId"))
        }

        return context.json(UserDTO(user))
    }
}
