package com.ziesapp.volunteera

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Interest(
    val nama: String,
    val gambar: Int
) : Parcelable {
}