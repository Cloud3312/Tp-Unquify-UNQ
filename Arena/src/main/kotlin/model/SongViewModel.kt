package model

import org.github.unqui.Song
import org.uqbar.commons.model.annotations.Observable

@Observable
class SongViewModel(var song: Song) {

    val songDescription : String = song.name + " - " + song.band

}
