package dto

import org.github.unqui.Playlist
import org.github.unqui.Song
import org.github.unqui.User

class SearchDTO(playlists : List<Playlist>, songs : List<Song>, users : List<User>) {

    val playlists = playlists.map { SimplePlaylistDTO(it) }
    val songs = songs.map { SongDTO(it) }
    val users = users.map { SimpleUserDTO(it) }

}
