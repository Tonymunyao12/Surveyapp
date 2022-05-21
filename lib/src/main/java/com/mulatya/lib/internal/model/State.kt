package com.mulatya.lib.internal.model

import com.mulatya.lib.api.Country

sealed class State{

    object Ready : State()

    data class Attached(
        val country: Country,
        val pattern: String,
    ) : State()
}
