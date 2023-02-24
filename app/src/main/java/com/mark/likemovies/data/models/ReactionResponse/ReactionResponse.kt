package com.mark.likemovies.data.models.ReactionResponse


import com.google.gson.annotations.SerializedName

 data class ReactionResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)