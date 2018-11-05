package com.cintory.armory.extension

import android.os.Parcel

/**
 * Created by Cintory on 2018/8/7 11:12
 * Email：Cintory@gmail.com
 */

fun Parcel.writeIntList(input: List<Int>) {
  writeInt(input.size) // Save number of elements.
  input.forEach(this::writeInt) // Save each element.
}

fun Parcel.createIntList(): List<Int> {
  val size = readInt()
  val output = ArrayList<Int>(size)
  for (i in 0 until size) {
    output.add(readInt())
  }
  return output
}