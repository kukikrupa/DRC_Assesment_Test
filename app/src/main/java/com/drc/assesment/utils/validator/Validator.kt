package com.drc.assesment.utils.validator

import android.content.Context
import android.widget.EditText
import com.drc.assesment.R
import java.util.regex.Pattern


/**
 * A utility class which contains methods with all the validation logic of whole app.
 */
object Validator {

    fun validateUserName(userName: String?): ValidationErrorModel? {
        return if (isBlank(userName))
            ValidationErrorModel(R.string.blank_username, ValidationError.USERNAME)
        else
            null
    }

    fun validatePassword(password: String?, msg: Int): ValidationErrorModel? {
        return when {
            isBlank(password) -> ValidationErrorModel(msg, ValidationError.PASSWORD)
            password!!.length < 6 -> ValidationErrorModel(R.string.invalid_password, ValidationError.PASSWORD)
            else -> null
        }
    }

    private fun isBlank(text: String?): Boolean {
        return text == null || text.trim().isEmpty()
    }
}