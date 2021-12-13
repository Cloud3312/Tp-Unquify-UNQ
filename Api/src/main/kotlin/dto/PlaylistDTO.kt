package dto

import org.github.unqui.Playlist

class PlaylistDTO(playlist: Playlist) : SimplePlaylistDTO(playlist) {
    val songs = playlist.songs
}
