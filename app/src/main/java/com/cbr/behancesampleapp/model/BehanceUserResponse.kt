package com.example.dimitrios.disample.model

import com.google.gson.annotations.SerializedName

/**
 * Created by dimitrios on 25/08/2017.
 */
data class BehanceUserResponse(@SerializedName("users") val users: List<BehanceUser>)