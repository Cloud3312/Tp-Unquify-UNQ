package model

import org.github.unqui.Playlist
import org.uqbar.commons.model.annotations.Observable

@Observable
class PlaylistItemViewModel(var playlist: Playlist) {
    val id = playlist.id
    val name = playlist.name
    val duration = "${playlist.duration().div(60)}:${playlist.duration().mod(60)}"
    val amountOfSongs = playlist.songs.size
    val image = playlist.image
}
