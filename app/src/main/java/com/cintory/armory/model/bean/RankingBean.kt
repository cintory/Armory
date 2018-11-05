package com.cintory.armory.model.bean

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by Cintory on 2018/7/30 16:37
 * Email：Cintory@gmail.com
 */
class RankingBean(
    val name: String,
    @SerializedName("class")
    val classID: Int,
    val spec: Int,
    val total: Double,
    val duration: Int,
    val startTime: Long,
    val fightID: Int,
    val reportID: String,
    val guildName: String,
    val serverName: String,
    val regionName: String,
    val hidden: Boolean,
    val itemLevel: Int,
    val exploit: Int,
    val talents: List<TalentBean>,
    val gear: List<GearBean>,
    val size: Int
) : Parcelable {
  constructor(parcel: Parcel) : this(
      parcel.readString(),
      parcel.readInt(),
      parcel.readInt(),
      parcel.readDouble(),
      parcel.readInt(),
      parcel.readLong(),
      parcel.readInt(),
      parcel.readString(),
      parcel.readString(),
      parcel.readString(),
      parcel.readString(),
      parcel.readByte() != 0.toByte(),
      parcel.readInt(),
      parcel.readInt(),
      parcel.createTypedArrayList(TalentBean),
      parcel.createTypedArrayList(GearBean),
      parcel.readInt()
  )

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeString(name)
    parcel.writeInt(classID)
    parcel.writeInt(spec)
    parcel.writeDouble(total)
    parcel.writeInt(duration)
    parcel.writeLong(startTime)
    parcel.writeInt(fightID)
    parcel.writeString(reportID)
    parcel.writeString(guildName ?: "")
    parcel.writeString(serverName)
    parcel.writeString(regionName)
    parcel.writeByte(if (hidden) 1 else 0)
    parcel.writeInt(itemLevel)
    parcel.writeInt(exploit)
    parcel.writeTypedList(talents)
    parcel.writeTypedList(gear)
    parcel.writeInt(size)
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Parcelable.Creator<RankingBean> {
    override fun createFromParcel(parcel: Parcel): RankingBean {
      return RankingBean(parcel)
    }

    override fun newArray(size: Int): Array<RankingBean?> {
      return arrayOfNulls(size)
    }
  }
}
