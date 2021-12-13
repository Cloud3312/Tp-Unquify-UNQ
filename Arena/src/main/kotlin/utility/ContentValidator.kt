package utility

import kotlin.Exception

object ContentValidator {

    fun assertThatFieldsAreNotBlank(listOfFields : List<String>, exceptionWithMessage: Exception) {
        if (listOfFields.any { it.isBlank() }) throw exceptionWithMessage
    }

    fun assertIsValidImage(image: String, exceptionWithMessage: Exception) {
        val isValid = ((image.startsWith("http://") || image.startsWith("https://")) && (image.endsWith(".png") || image.endsWith(".jpg")))

        if (!isValid) throw exceptionWithMessage
    }


}