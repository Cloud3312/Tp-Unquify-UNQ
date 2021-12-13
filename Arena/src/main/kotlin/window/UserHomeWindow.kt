package window

import model.*
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner

class UserHomeWindow(parent: WindowOwner, model: UserHomeViewModel): SimpleWindow<UserHomeViewModel>(parent, model)  {

    override fun addActions(p0: Panel?) { }

    override fun createFormPanel(mainPanel: Panel?) {
        title = "Spotify"

        Panel(mainPanel) with {
            Label(it) withText "Id: ${thisWindow.modelObject.userId}"
        }
        Panel(mainPanel) with {
            asHorizontal()
            Label(it) withText "Name: "
            Label(it) bindTo "name"
        }
        Panel(mainPanel) with {
            Label(it) withText "Email: ${thisWindow.modelObject.email}"
        }

        Button(mainPanel) with {
            text = "Edit user"
            onClick {
                val editUserViewModel = EditUserViewModel(modelObject.userId, modelObject.spotifyService)
                val editUserDialog = EditUserWindow(thisWindow, editUserViewModel)
                editUserDialog.onAccept { modelObject.updateEditedUserData() }
                editUserDialog.open()
            }
        }
        Label(mainPanel) with {
            text = "-------------------------------------------------------"
        }

        mainPanel?.let {
            table<PlaylistItemViewModel>(it) {
                bindItemsTo("playlists")
                bindSelectionTo("selectedPlaylist")
                visibleRows = 5
                column {
                    title = "Id"
                    align("left")
                    bindContentsTo("id")
                }
                column {
                    title = "Name"
                    align("left")
                    bindContentsTo("name")
                }
                column {
                    title = "Duration (min)"
                    align("left")
                    bindContentsTo("duration")
                }
                column {
                    title = "Amount of songs"
                    align("left")
                    bindContentsTo("amountOfSongs")
                }
                column {
                    title = "Image"
                    align("left")
                    bindContentsTo("image")
                }
            }
        }

        Panel(mainPanel) with {
            asHorizontal()
            Button(it) with {
                text = "Add new playlist"
                onClick {
                    val modelObject = thisWindow.modelObject
                    val playlistCreationViewModel = PlaylistCreationViewModel(modelObject.spotifyService, modelObject.userId)

                    val playlistDialog = PlaylistWindow(thisWindow, playlistCreationViewModel)
                    playlistDialog.onAccept { modelObject.updatePlaylists() }
                    playlistDialog.open()
                }
            }
            Button(it) with {
                text = "Edit playlist"
                onClick {
                    try {
                        val modelObject = thisWindow.modelObject
                        val playlist = modelObject.selectedPlaylist!!.playlist
                        val playlistEditionViewModel = PlaylistEditionViewModel(playlist, modelObject.userId, modelObject.spotifyService)

                        val playlistDialog = PlaylistWindow(thisWindow, playlistEditionViewModel)
                        playlistDialog.onAccept { modelObject.updatePlaylists() }
                        playlistDialog.open()

                    } catch (error: RuntimeException) {
                        thisWindow.showError("Select a playlist to edit")
                    }
                }
            }
        }
    }
}
