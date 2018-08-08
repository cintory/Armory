package com.cintory.armory.model.bean

import android.os.Parcel
import android.os.Parcelable
import com.cintory.armory.extension.createIntList
import com.cintory.armory.extension.writeIntList


/**
 * Created by Cintory on 2018/8/2 10:38
 * Email：Cintory@gmail.com
 */
data class TableOverviewBean(
    val name: String,
    val id: Int,
    val guid: Int,
    val type: String,
    val itemLevel: Int,
    val icon: String,
    val total: Long,
    val activeTime: Int,
    val activeTimeReduced: Int,
    val abilities: List<Ability>,
//    val damageAbilities: List<Any>,
    val targets: List<Target>,
    val talents: List<Talent>,
    val gear: List<Gear>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readLong(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.createTypedArrayList(Ability),
        parcel.createTypedArrayList(Target),
        parcel.createTypedArrayList(Talent),
        parcel.createTypedArrayList(Gear)
    )

    data class Ability(
        val name: String,
        val total: Int,
        val type: Int
    ) : Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt()
        )

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(name)
            parcel.writeInt(total)
            parcel.writeInt(type)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Ability> {
            override fun createFromParcel(parcel: Parcel): Ability {
                return Ability(parcel)
            }

            override fun newArray(size: Int): Array<Ability?> {
                return arrayOfNulls(size)
            }
        }
    }

    data class Talent(
        val name: String,
        val guid: Int,
        val type: Int,
        val abilityIcon: String
    ) : Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString()
        )

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(name)
            parcel.writeInt(guid)
            parcel.writeInt(type)
            parcel.writeString(abilityIcon)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Talent> {
            override fun createFromParcel(parcel: Parcel): Talent {
                return Talent(parcel)
            }

            override fun newArray(size: Int): Array<Talent?> {
                return arrayOfNulls(size)
            }
        }
    }

    data class Gear(
        val id: Int,
        val slot: Int,
        val itemLevel: Int,
        val quality: Int,
        val icon: String,
        val bonusIDs: List<Int>?,
        val name: String?,
        val permanentEnchant: Int?,
        val permanentEnchantName: String?,
        val gems: List<Gem>?
    ) : Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.createIntList(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.createTypedArrayList(Gem)
        )

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeInt(id)
            parcel.writeInt(slot)
            parcel.writeInt(itemLevel)
            parcel.writeInt(quality)
            parcel.writeString(icon)
            parcel.writeIntList(bonusIDs ?: listOf())
            parcel.writeString(name ?: "")
            parcel.writeInt(permanentEnchant ?: 0)
            parcel.writeString(permanentEnchantName ?: "")
            parcel.writeTypedList(gems ?: listOf())
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Gear> {
            override fun createFromParcel(parcel: Parcel): Gear {
                return Gear(parcel)
            }

            override fun newArray(size: Int): Array<Gear?> {
                return arrayOfNulls(size)
            }
        }
    }

    data class Gem(
        val id: Int,
        val itemLevel: Int,
        val icon: String
    ) : Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString()
        )

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeInt(id)
            parcel.writeInt(itemLevel)
            parcel.writeString(icon)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Gem> {
            override fun createFromParcel(parcel: Parcel): Gem {
                return Gem(parcel)
            }

            override fun newArray(size: Int): Array<Gem?> {
                return arrayOfNulls(size)
            }
        }
    }

    data class Target(
        val name: String,
        val total: Int,
        val type: String
    ) : Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readInt(),
            parcel.readString()
        )

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(name)
            parcel.writeInt(total)
            parcel.writeString(type)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Target> {
            override fun createFromParcel(parcel: Parcel): Target {
                return Target(parcel)
            }

            override fun newArray(size: Int): Array<Target?> {
                return arrayOfNulls(size)
            }
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(id)
        parcel.writeInt(guid)
        parcel.writeString(type)
        parcel.writeInt(itemLevel)
        parcel.writeString(icon)
        parcel.writeLong(total)
        parcel.writeInt(activeTime)
        parcel.writeInt(activeTimeReduced)
        parcel.writeTypedList(abilities)
        parcel.writeTypedList(targets)
        parcel.writeTypedList(talents)
        parcel.writeTypedList(gear)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TableOverviewBean> {
        override fun createFromParcel(parcel: Parcel): TableOverviewBean {
            return TableOverviewBean(parcel)
        }

        override fun newArray(size: Int): Array<TableOverviewBean?> {
            return arrayOfNulls(size)
        }
    }
}
