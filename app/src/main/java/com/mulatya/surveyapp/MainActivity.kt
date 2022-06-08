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
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.mulatya.lib.PhoneNumberKit
import com.mulatya.surveyapp.databinding.ActivityMainBinding

/*
* Acts as the Login Activity.
* */

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
        Log.d(SignupActivity.TAG, "Example Number: $exampleNumber")

        // Parses raw phone number to phone object
        val parsedNumber = phoneNumberKit.parsePhoneNumber(
            number = "715790884",
            defaultRegion = "ke"
        )
        Log.d(SignupActivity.TAG, "Parsed Number: $parsedNumber")

        // Converts raw phone number to international formatted phone number
        // Ex: +90 506 606 00 00
        val formattedNumber = phoneNumberKit.formatPhoneNumber(
            number = "715790884",
            defaultRegion = "tr"
        )
        Log.d(SignupActivity.TAG, "Formatted Number: $formattedNumber")

        // New users to navigate to Signup Activity.
        val navigateToSignup: TextView = findViewById(R.id.register_txt)
        navigateToSignup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }


    }


}