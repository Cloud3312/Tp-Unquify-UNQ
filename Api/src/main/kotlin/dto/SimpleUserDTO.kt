package dto

import org.github.unqui.User

class SimpleUserDTO(user: User) {
    val id = user.id
    val displayName = user.displayName
    val image = user.image
}
