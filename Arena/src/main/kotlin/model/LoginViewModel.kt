package model

import SpotifyService
import org.github.unqui.User
import org.github.unqui.UserException
import org.uqbar.commons.model.annotations.Observable
import utility.ContentValidator

@Observable
class LoginViewModel(val spotifyService: SpotifyService) {
    var email: String = ""
    var password: String = ""

    fun authUser(): User {
        ContentValidator.assertThatFieldsAreNotBlank(listOf(email, password), UserException("Please complete all fields."))
        return spotifyService.login(email,password)
    }

}
