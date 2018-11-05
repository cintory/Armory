package com.cintory.armory.model.bean

import android.os.Parcel
import android.os.Parcelable
import com.cintory.armory.model.http.api.WarcraftLogsApi

/**
 * Created by Cintory on 2018/7/30 16:33
 * Email：Cintory@gmail.com
 */
data class GearBean(
    val name: String,
    val quality: String,
    val id: Int,
    val icon: String
) : Parcelable {

  fun getTalentImage() = WarcraftLogsApi.IMAGE_HOST + "img/warcraft/abilities/$icon.jpg"


  constructor(parcel: Parcel) : this(
      parcel.readString(),
      parcel.readString(),
      parcel.readInt(),
      parcel.readString()
  )

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeString(name)
    parcel.writeString(quality)
    parcel.writeInt(id)
    parcel.writeString(icon)
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Parcelable.Creator<GearBean> {
    override fun createFromParcel(parcel: Parcel): GearBean {
      return GearBean(parcel)
    }

    override fun newArray(size: Int): Array<GearBean?> {
      return arrayOfNulls(size)
    }
  }
}