package utility

import io.javalin.http.Context
import org.github.unqui.UserDraft

class DataContextValidator {

    fun getUserDraftIfValidDataOrElseThrowException(context: Context): UserDraft {
        return context.bodyValidator<UserDraft>()
                .check({ it.displayName.isNotBlank() }, "Display name cannot be blank.")
                .check({ it.email.isNotBlank() }, "Email cannot be blank.")
                .check({ it.password.isNotBlank() }, "Password cannot be blank.")
                .check({ it.image.isNotBlank() }, "Image cannot be blank.")
                .get()
    }

}