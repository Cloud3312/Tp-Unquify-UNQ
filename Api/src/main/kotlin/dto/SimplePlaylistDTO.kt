package dto

import org.github.unqui.Playlist

open class SimplePlaylistDTO(playlist: Playlist) {
    val id = playlist.id
    val name = playlist.name
    val description = playlist.description
    val image = playlist.image
    val author = SimpleUserDTO(playlist.author)
    val lastModifiedDate = playlist.lastModifiedDate.toString()
    val likes = playlist.likes.map { user -> SimpleUserDTO(user) }
    val duration = playlist.duration()
}
