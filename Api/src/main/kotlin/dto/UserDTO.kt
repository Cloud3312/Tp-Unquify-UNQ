package dto

import org.github.unqui.User

class UserDTO(user: User) {
    val id = user.id
    val displayName = user.displayName
    val image = user.image
    val myPlaylist : List<SimplePlaylistDTO> = user.myPlaylists.map { playlist -> SimplePlaylistDTO(playlist) }
    val likes : List<SimplePlaylistDTO> = user.likes.map { playlist -> SimplePlaylistDTO(playlist) }
}
