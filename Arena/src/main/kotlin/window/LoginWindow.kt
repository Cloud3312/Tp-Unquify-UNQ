package window

import model.LoginViewModel
import model.UserHomeViewModel
import org.github.unqui.UserException
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner

class LoginWindow(parent: WindowOwner, model: LoginViewModel): SimpleWindow<LoginViewModel>(parent,model) {
    private val boxSize: Int = 200
    private val labelSize: Int = 100

    override fun addActions(p0: Panel?) { }

    override fun createFormPanel(mainPanel: Panel?) {
        title = "Spotify"

        Panel(mainPanel) with {
            asHorizontal()
            Label(it) with {
                text = "Email:"
                width = labelSize
            }
            TextBox(it) with {
                bindTo("email")
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

        Button(mainPanel) with {
            caption = "Login"
            onClick {
                try {
                    val userHomeViewModel = UserHomeViewModel(modelObject.authUser(), modelObject.spotifyService)
                    thisWindow.close()
                    UserHomeWindow(thisWindow, userHomeViewModel).open()
                }
                catch (error: UserException) {
                    thisWindow.showError(error.message)
                }
            }
        }
    }
}
