package com.example.geniusplazausers.models

import android.widget.ImageView
import com.google.gson.annotations.SerializedName
import androidx.databinding.BindingAdapter
import com.example.geniusplazausers.R
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation


class User(
    @SerializedName("id") val id: Int?,
    @SerializedName("email") val email: String?,
    @SerializedName("first_name") val firstName: String?,
    @SerializedName("last_name") val lastName: String?,
    @SerializedName("avatar") val avatar: String?
) {
    companion object {
        @JvmStatic
        @BindingAdapter("bind:imageUrl")
        fun loadImage(view: ImageView, imageUrl: String) {
            Picasso.get()
                .load(imageUrl)
                .error(R.drawable.ic_launcher_background)
                .transform(CropCircleTransformation())
                .into(view)
        }
    }
}

class UserPagedResult(
    @SerializedName("page") val page: Int,
    @SerializedName("per_page") val perPage: Int,
    @SerializedName("total") val total: Int,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("data") val data: List<User>
)

data class UserPostResponse(
    @SerializedName("name") val name: String,
    @SerializedName("job") val job: String,
    @SerializedName("id") val id: String,
    @SerializedName("createdAt") val createdAt: String
)

