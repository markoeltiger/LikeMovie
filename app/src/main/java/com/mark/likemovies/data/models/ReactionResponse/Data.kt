package com.mark.likemovies.data.models.ReactionResponse


import com.google.gson.annotations.SerializedName


 data class Data(
    @SerializedName("entertainment_id")
    val entertainmentId: String,
    @SerializedName("reaction")
    val reaction: String,
    @SerializedName("un")
    val un: String,
    @SerializedName("user_id")
    val userId: String
)