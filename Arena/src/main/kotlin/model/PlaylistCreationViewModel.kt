package model

import SpotifyService
import org.github.unqui.PlayListDraft
import org.uqbar.commons.model.annotations.Observable

@Observable
class PlaylistCreationViewModel(spotifyService: SpotifyService, userId : String) : PlaylistViewModel(userId, spotifyService) {

    override var addedSongs : MutableList<SongViewModel> = ArrayList()
    override var allSongs : MutableList<SongViewModel> = this.mapSongsAsSongModels(spotifyService.getAllSongs())

    override fun apply(){
        val playlistDraft = PlayListDraft(playlistName, playlistDescription, playlistImage, this.getSongsFromSongModels())
        spotifyService.addPlaylist(userId, playlistDraft)
    }

}
