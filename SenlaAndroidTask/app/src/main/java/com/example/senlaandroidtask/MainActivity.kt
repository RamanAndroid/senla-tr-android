package com.example.senlaandroidtask

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.senlaandroidtask.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val login = "pepa"
    private val password = "pig"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

            loginBtn.setOnClickListener {
                if (isValidation()) {
                    if (loginInput.text.toString() == login && passwordsInput.text.toString() == password) {
                        Toast.makeText(
                            this@MainActivity,
                            getString(R.string.successfully_logged_in),
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        Toast.makeText(
                            this@MainActivity,
                            getString(R.string.wrong_login_or_passwords),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }

            registerBtn.setOnClickListener {
                if (isValidation()) {
                    Toast.makeText(
                        this@MainActivity,
                        getString(R.string.successfully_registered),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }


    }


    private fun isValidation(): Boolean {
        binding.apply {
            if (loginInput.text.isNullOrBlank() && passwordsInput.text.isNullOrBlank()) {
                loginInputLayout.error = getString(R.string.input_login)
                passwordInputLayout.error = getString(R.string.input_password)
                return false
            } else if (loginInput.text.isNullOrBlank()) {
                loginInputLayout.error = getString(R.string.input_login)
                passwordInputLayout.error = null
                return false
            } else if (passwordsInput.text.isNullOrBlank()) {
                passwordInputLayout.error = getString(R.string.input_password)
                loginInputLayout.error = null
                return false
            }
            loginInputLayout.error = null
            passwordInputLayout.error = null
            return true

        }

    }


}