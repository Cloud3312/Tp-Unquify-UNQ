package main

import model.LoginViewModel
import org.github.unqui.getSpotifyService
import org.uqbar.arena.Application
import org.uqbar.arena.windows.Window
import window.LoginWindow

class SpotifyApplication: Application() {
    private val spotifyService = getSpotifyService()

    override fun createMainWindow(): Window<*> {
        return LoginWindow(this, LoginViewModel(spotifyService))
    }

}
