package model

import SpotifyService
import org.github.unqui.User
import org.uqbar.commons.model.annotations.Observable

@Observable
class UserHomeViewModel(user: User, val spotifyService: SpotifyService) {
    val userId = user.id
    var name = user.displayName
    val email = user.email
    var playlists = user.myPlaylists.map { PlaylistItemViewModel(it) }
    var selectedPlaylist: PlaylistItemViewModel? = null

    fun updatePlaylists() {
        playlists = spotifyService.getUser(userId).myPlaylists.map { PlaylistItemViewModel(it) }
    }

    fun updateEditedUserData() {
        val updatedUser = spotifyService.getUser(userId)
        name = updatedUser.displayName
    }
}
