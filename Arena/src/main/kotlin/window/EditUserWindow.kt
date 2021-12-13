package window

import model.EditUserViewModel
import org.github.unqui.UserException
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner

class EditUserWindow(parent: WindowOwner, model: EditUserViewModel): Dialog<EditUserViewModel>(parent, model) {
    private val boxSize: Int = 200
    private val labelSize: Int = 100

    override fun addActions(actionPanel: Panel?) { }

    override fun createFormPanel(mainPanel: Panel?) {
        title = "Spotify"

        Panel(mainPanel) with {
            asHorizontal()
            Label(it) with {
                text = "Name:"
                width = labelSize
            }
            TextBox(it) with {
                bindTo("displayName")
                width = boxSize
            }
        }

        Panel(mainPanel) with {
            asHorizontal()
            Label(it) with {
                text = "Password:"
                width = labelSize
            }
            PasswordField(it) with {
                bindTo("password")
                width = boxSize
            }
        }

        Panel(mainPanel) with {
            asHorizontal()
            Label(it) with {
                text = "Image:"
                width = labelSize
            }
            TextBox(it) with {
                bindTo("image")
                width = boxSize
            }
        }

        Panel(mainPanel) with {
            asHorizontal()
            Button(it) with {
                text = "Accept"
                onClick {
                    try {
                        thisWindow.modelObject.editUser()
                        accept()
                    }
                    catch (error: UserException) {
                        thisWindow.showError(error.message)
                    }
                }
            }
            Button(it) with {
                text = "Cancel"
                onClick {
                    thisWindow.close()
                }
            }
        }
    }
}
