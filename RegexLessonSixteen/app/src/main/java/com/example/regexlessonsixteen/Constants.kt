package com.example.regexlessonsixteen

import java.util.regex.Pattern

object Constants {
    const val REGEX_FIND_PHONE_NUMBER = "8\\ \\(0[0-9]{2}\\)\\ [0-9]{3}\\-[0-9]{2}\\-[0-9]{2}"
    const val REGEX_FIND_PREFIX_PHONE_NUMBER = "8\\ \\(0[0-9]{2}\\)"
    const val REGEX_FIND_CODE_PHONE_NUMBER = "8\\ \\(0(.*?)\\)"
    const val REGEX_NEW_PREFIX_PHONE_NUMBER = "+375"
    const val REGEX_FIND_FOUR_LETTER_WORD = "\\b\\w[A-Za-zА-Яа-я]{3}\\b"
    const val REGEX_FIND_EMPTY_SPACES = "\\ +"
    const val REGEX_FIND_LINKS = "(\\swww\\.\\S*\\.\\w*)"
    const val START_WWW_LINK = "http://"
    const val REGEX_FIND_TAGS = "(<)(\\w)+(>)((\\w)+)(<)(/)(\\w)+(>)"
    const val SPACE = " "
    const val EMPTY_STRING = ""
    const val DASH = "-"
}