package window

import model.PlaylistViewModel
import model.SongViewModel
import org.github.unqui.PlaylistException
import org.github.unqui.Song
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner
import java.lang.NullPointerException

class PlaylistWindow(parent: WindowOwner, model: PlaylistViewModel): Dialog<PlaylistViewModel>(parent, model)  {

    private val labelSize : Int = 70
    private val boxSize : Int = 500
    private val listWidth : Int = 250
    private val listHeight : Int = 400

    override fun createFormPanel(p0: Panel?) {
        title = "Spotify"

        Panel(p0) with {
            asHorizontal()
            Label(it) with {
                text = "Name:"
                width = labelSize
            }
            TextBox(it) with {
                bindTo("playlistName")
                width = boxSize
            }
        }

        Panel(p0) with {
            asHorizontal()
            Label(it) with {
                text = "Description:"
                width = labelSize
            }
            KeyWordTextArea(it) with {
                bindTo("playlistDescription")
                width = boxSize
                height = 50
            }
        }

        Panel(p0) with {
            asHorizontal()
            Label(it) with {
                text = "Image:"
                width = labelSize
            }
            TextBox(it) with {
                bindTo("playlistImage")
                width = boxSize
            }
        }

        Label(p0) withText " "

        Panel(p0) with {
            asHorizontal()
            Label(it) with {
                text = "Available songs"
                width = 285
                align = "left"
            }
            Label(it) with {
                text = "Added songs"
                width = 290
                align = "right"
            }
        }

        Panel(p0) with {
            asHorizontal()
            List<Song>(it) with {
                bindItemsTo("allSongs").adaptWithProp<SongViewModel>("songDescription")
                bindSelectedTo("selectedSong")
                height = listHeight
                width = listWidth
            }

            Panel(it) with {
                asVertical()
                Button(it) with {
                    text = ">"
                    onClick {
                        try { thisWindow.modelObject.addSelectedSong() } catch (e : NullPointerException) { }
                    }
                }
                Button(it) with {
                    text = "<"
                    onClick {
                        try { thisWindow.modelObject.removeSelectedSong() } catch (e : NullPointerException) { }
                    }
                }
            }

            List<Song>(it) with {
                bindItemsTo("addedSongs").adaptWithProp<SongViewModel>("songDescription")
                bindSelectedTo("selectedAddedSong")
                height = listHeight
                width = listWidth
            }
        }

        Panel(p0) with {
            asHorizontal()
            Button(it) with {
                text = "Accept"
                onClick {
                    try {
                        thisWindow.modelObject.acceptAction()
                        accept()
                    }
                    catch (error: PlaylistException){
                        thisWindow.showError(error.message)
                    }
                }
            }
            Button(it) with {
                text = "Cancel"
                onClick { close() }
            }
        }

    }
}
