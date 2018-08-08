package com.cintory.armory.model.bean

import android.os.Parcel
import android.os.Parcelable
import com.cintory.armory.model.http.api.WarcraftLogsApi

/**
 * Created by Cintory on 2018/7/30 16:35
 * Email：Cintory@gmail.com
 */
data class TalentBean(
    val name: String,
    val id: Int,
    val icon: String
) : Parcelable {

    fun getTalentImage() = WarcraftLogsApi.IMAGE_HOST + "img/warcraft/abilities/$icon"


    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(id)
        parcel.writeString(icon)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TalentBean> {
        override fun createFromParcel(parcel: Parcel): TalentBean {
            return TalentBean(parcel)
        }

        override fun newArray(size: Int): Array<TalentBean?> {
            return arrayOfNulls(size)
        }
    }
}