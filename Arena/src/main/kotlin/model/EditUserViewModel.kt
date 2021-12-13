package model

import SpotifyService
import org.github.unqui.EditUser
import org.github.unqui.UserException
import org.uqbar.commons.model.annotations.Observable
import utility.ContentValidator

@Observable
class EditUserViewModel(private val userId : String, private val spotifyService: SpotifyService) {
    var image: String = "${spotifyService.getUser(userId).image}"
    var password: String = "${spotifyService.getUser(userId).password}"
    var displayName: String = "${spotifyService.getUser(userId).displayName}"

    fun editUser() {
        ContentValidator.assertThatFieldsAreNotBlank(listOf(image, password, displayName), UserException("Please complete all fields."))
        ContentValidator.assertIsValidImage(image, UserException("The image must be valid."))
        spotifyService.editUser(userId, EditUser(image, password, displayName))
    }
}
