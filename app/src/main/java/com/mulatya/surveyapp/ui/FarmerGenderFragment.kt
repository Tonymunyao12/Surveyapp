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

package com.mulatya.surveyapp.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.mulatya.surveyapp.R


class FarmerGenderFragment : Fragment() {

    companion object {
        fun newInstance() = FarmerGenderFragment()
    }

    private lateinit var viewModel: FarmerGenderViewModel

            override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_farmer_gender, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(FarmerGenderViewModel::class.java)

    }

    override fun onResume() {
        super.onResume()

        val genderOptions = listOf<String>("Male", "Female", "Other")

        //val genderOptions = resources.getStringArray(R.array.Gender)
        // Array adapter and passing the context, dropdown layout, array
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.gender_dropdown_item, genderOptions)
    }

}