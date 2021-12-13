package controller

import SpotifyService
import dto.ErrorDTO
import dto.SearchDTO
import io.javalin.http.Context

class SearchController(private val spotifyService: SpotifyService) {

    fun textBasedSearch(context: Context): Context {

        val query: String? = context.queryParam("q")

        if (query.isNullOrBlank()) return context.status(400).json(ErrorDTO("error", "The query argument must not be null or blank."))

        val filteredPlaylists = spotifyService.searchPlaylist(query!!)
        val filteredSongs = spotifyService.searchSong(query!!)
        val filteredUsers = spotifyService.searchUser(query!!)

        return context.json(SearchDTO(filteredPlaylists, filteredSongs, filteredUsers))
    }

}
