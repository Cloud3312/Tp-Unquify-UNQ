package model

import SpotifyService
import org.github.unqui.PlaylistException
import org.github.unqui.Song
import org.github.unqui.UserException
import utility.ContentValidator

abstract class PlaylistViewModel(
    var userId: String,
    var spotifyService: SpotifyService,
    var playlistName : String = "",
    var playlistDescription : String = "",
    var playlistImage : String = "") {

    open lateinit var addedSongs : MutableList<SongViewModel>
    open lateinit var allSongs : MutableList<SongViewModel>

    var selectedSong : SongViewModel? = null
    var selectedAddedSong : SongViewModel? = null

    fun addSelectedSong() {
        addedSongs.add(selectedSong!!)
        allSongs.remove(selectedSong!!)
    }

    fun removeSelectedSong() {
        allSongs.add(selectedAddedSong!!)
        addedSongs.remove(selectedAddedSong)
    }

    fun acceptAction(){
        ContentValidator.assertThatFieldsAreNotBlank(listOf(playlistName), PlaylistException("The playlist name cannot be blank."))
        ContentValidator.assertIsValidImage(playlistImage, PlaylistException("The image must be valid"))
        apply()
    }

    abstract fun apply()

    protected fun mapSongsAsSongModels(songs: List<Song>): MutableList<SongViewModel> {
        return songs.map { SongViewModel(it) } as MutableList<SongViewModel>
    }

    protected fun getSongsFromSongModels(): MutableList<Song> {
        return addedSongs.map { it.song } as MutableList<Song>
    }

}
