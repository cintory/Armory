package com.cintory.armory.widget.glide

import android.content.Context
import android.os.Build
import android.util.Log
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.ViewTarget
import com.cintory.armory.R

/**
 * 作者：Cintory on 2018/7/25 10:38
 * 邮箱：Cintory@gmail.com
 */
@GlideModule
class GlideModule : AppGlideModule() {

  override fun isManifestParsingEnabled() = false

  override fun applyOptions(context: Context, builder: GlideBuilder) {
    builder.setDiskCache(InternalCacheDiskCacheFactory(context, 64 * 1000 * 1000))
    ViewTarget.setTagId(R.id.tag_glide)
    builder.setLogLevel(Log.ERROR)

    var requestOptions = RequestOptions()

    requestOptions.format(DecodeFormat.PREFER_ARGB_8888)


    if (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) {
      requestOptions = requestOptions.disallowHardwareConfig()
    }

    builder.setDefaultRequestOptions(requestOptions)
  }


}

