package dto

import org.github.unqui.Song

class SongDTO(song : Song) {
    val id = song.id
    val name = song.name
    val band = song.band
    val url = song.url
    val duration = song.duration
}