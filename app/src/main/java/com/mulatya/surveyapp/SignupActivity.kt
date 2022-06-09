/*
* Copyright 2020 BlessedCoders
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
* */
package com.mulatya.surveyapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
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


        // mainActivity acts as the Login activity.
        val navigateToLogin: Button = findViewById(R.id.signup_btn)
        navigateToLogin.setOnClickListener {
          val intent = Intent(this, MainActivity::class.java)
          startActivity(intent)
        }

    }

    companion object{
        const val TAG = "###"
    }
}