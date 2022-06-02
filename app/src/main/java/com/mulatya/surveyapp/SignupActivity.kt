package com.mulatya.surveyapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.mulatya.surveyapp.databinding.ActivityMainBinding
import com.mulatya.lib.PhoneNumberKit

class SignupActivity : AppCompatActivity() {

    private lateinit var binding:  ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_activity)

        val phoneNumberKit = PhoneNumberKit.Builder(this)
            .setIconEnabled(true)
            .build()

        //  To attach an editTextLayout
        phoneNumberKit.attachToInput(binding.textField, "tr")

        // Setup country code picker optionally
        phoneNumberKit.setupCountryPicker(
            activity = this,
            searchEnabled = true
        )

        // Provides example phone number for given country iso2 code
        val exampleNumber = phoneNumberKit.getExampleNumber("tr")
        Log.d(TAG, "Example Number: $exampleNumber")

        // Parses raw phone number to phone object
        val parsedNumber = phoneNumberKit.parsePhoneNumber(
            number = "715790884",
            defaultRegion = "ke"
        )
        Log.d(TAG, "Parsed Number: $parsedNumber")

        // Converts raw phone number to international formatted phone number
        // Ex: +90 506 606 00 00
        val formattedNumber = phoneNumberKit.formatPhoneNumber(
            number = "715790884",
            defaultRegion = "tr"
        )
        Log.d(TAG, "Formatted Number: $formattedNumber")

    }

    companion object{
        const val TAG = "###"
    }
}