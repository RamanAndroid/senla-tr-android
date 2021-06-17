package com.example.regexlessonsixteen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import com.example.regexlessonsixteen.Constants.DASH
import com.example.regexlessonsixteen.Constants.EMPTY_STRING
import com.example.regexlessonsixteen.Constants.REGEX_FIND_CODE_PHONE_NUMBER
import com.example.regexlessonsixteen.Constants.REGEX_FIND_EMPTY_SPACES
import com.example.regexlessonsixteen.Constants.REGEX_FIND_FOUR_LETTER_WORD
import com.example.regexlessonsixteen.Constants.REGEX_FIND_LINKS
import com.example.regexlessonsixteen.Constants.REGEX_NEW_PREFIX_PHONE_NUMBER
import com.example.regexlessonsixteen.Constants.SPACE
import com.example.regexlessonsixteen.Constants.START_WWW_LINK
import com.example.regexlessonsixteen.databinding.FragmentRegexBinding
import java.util.*

class RegexFragment : Fragment() {

    private var _binding: FragmentRegexBinding? = null
    private val binding get() = _binding!!
    val conversionText = StringBuilder()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegexBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            conversionBtn.setOnClickListener {
                val text = binding.inputText.text.toString()
                conversionText(
                    "Это тестовый текст. Такие дела\n" +
                            "Номер то 8 (029) 111-22-33 и ещё 8 (029) 333-22-11,\n" +
                            "кроме того <one>один</one> и <one>раз</one>.\n" +
                            "Сайтецы www.google.com и www.senla.ru хорошие."
                )
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun conversionText(text: String) {
        binding.apply {
            PreferenceManager.getDefaultSharedPreferences(activity).all.forEach { (t, any) ->
                if ((any as Boolean)) {
                    when (t) {
                        "replace_minus" -> {
                            Log.d("replace_minus", true.toString())
                            replaceMinus.text = replaceEmptySpaces(text)
                        }
                        "replace_format_phone_numbers" -> {
                            Log.d("replace_format_phone_nu", true.toString())
                            replaceFormatPhoneNumbers.text = replacePhoneNumber(text)

                        }
                        "replace_word_in_upper_case" -> {
                            Log.d("replace_word_in_upper_c", true.toString())
                            replaceWordInUpperCase.text = setUppercaseToFourLetterWords(text)
                        }
                        "find_word_in_tag" -> {
                            Log.d("find_word_in_tag", true.toString())
                            findWordInTag.text = findValueInTags(text)
                        }
                        "find_links" -> {
                            Log.d("find_links", true.toString())
                            findLinks.text = replaceLinks(text)
                        }
                    }
                }
            }

        }
    }

    private fun replacePhoneNumber(value: String): String {
        return value.replace(Constants.REGEX_FIND_PHONE_NUMBER.toRegex()) { number ->
            number.value.replace(Constants.REGEX_FIND_PREFIX_PHONE_NUMBER.toRegex()) { prefix ->
                prefix.value.replace(REGEX_FIND_CODE_PHONE_NUMBER.toRegex()) { code ->
                    REGEX_NEW_PREFIX_PHONE_NUMBER + DASH + code.groups[1]?.value
                } + DASH
            }
        }
    }

    private fun findValueInTags(value: String): String {
        return value.replace(Constants.REGEX_FIND_TAGS.toRegex()) {
            it.value.replace(it.groups[4]?.value.toString(),"")
        }
    }

    private fun setUppercaseToFourLetterWords(value: String): String {
        return value.replace(REGEX_FIND_FOUR_LETTER_WORD.toRegex()) {
            it.value.toUpperCase(Locale.ROOT)
        }
    }

    private fun replaceEmptySpaces(value: String): String {
        return value.replace(REGEX_FIND_EMPTY_SPACES.toRegex(), DASH)
    }


    private fun replaceLinks(value: String): String {
        return value.replace(REGEX_FIND_LINKS.toRegex()) {
            SPACE + START_WWW_LINK + it.value.replace(SPACE, EMPTY_STRING)
        }
    }


}