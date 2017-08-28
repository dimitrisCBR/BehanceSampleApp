package com.cbr.behancesampleapp.model

import com.cbr.behancesampleapp.model.BehanceUser
import com.google.gson.annotations.SerializedName

/**
 * Created by dimitrios on 25/08/2017.
 */
data class BehanceUserResponse(@SerializedName("users") val users: List<BehanceUser>)

data class BehanceSinlgeUserReponse(@SerializedName("user") val user : BehanceUser)