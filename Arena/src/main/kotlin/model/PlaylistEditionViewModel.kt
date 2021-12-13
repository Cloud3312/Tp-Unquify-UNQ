package model

import SpotifyService
import org.github.unqui.PlayListDraft
import org.github.unqui.Playlist
import org.github.unqui.Song
import org.uqbar.commons.model.annotations.Observable

@Observable
class PlaylistEditionViewModel(private val playlist: Playlist, userId : String, spotifyService: SpotifyService) :
    PlaylistViewModel(userId, spotifyService, playlist.name, playlist.description, playlist.image) {

    override var addedSongs = this.mapSongsAsSongModels(playlist.songs)
    override var allSongs = this.mapSongsAsSongModels(filteredNotAddedSongs(spotifyService))

    override fun apply() {
        spotifyService.modifyPlaylist(userId, playlist.id, PlayListDraft(playlistName, playlistDescription, playlistImage, this.getSongsFromSongModels()))
    }

      private fun filteredNotAddedSongs(spotifyService: SpotifyService) : List<Song> {
        return spotifyService.getAllSongs().filter { song -> !playlist.songs.contains(song) }
    }

}
