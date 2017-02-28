package com.gojek.sample.kotlin.extensions

import android.content.Context
import android.graphics.drawable.Drawable
import com.bumptech.glide.Glide
import com.bumptech.glide.MemoryCategory
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.SimpleTarget
import de.hdodenhof.circleimageview.CircleImageView

fun loadImage(context: Context, url: String, resourceId: Int, circleImageView: CircleImageView) {
    setMemoryCategory(context)
    Glide.with(context)
         .load(url)
         .centerCrop()
         .diskCacheStrategy(DiskCacheStrategy.SOURCE)
         .placeholder(resourceId).error(resourceId)
         .dontAnimate()
         .into(object : SimpleTarget<GlideDrawable>(128, 128) {
             override fun onLoadStarted(placeholder: Drawable?) {
                 circleImageView.setImageDrawable(placeholder)
             }

             override fun onLoadFailed(e: Exception?, errorDrawable: Drawable?) {
                 circleImageView.setImageDrawable(errorDrawable)
             }

             override fun onResourceReady(resource: GlideDrawable?, glideAnimation: GlideAnimation<in GlideDrawable>) {
                 circleImageView.setImageDrawable(resource)
             }
         })
}

fun setMemoryCategory(context: Context) {
    Glide.get(context).setMemoryCategory(MemoryCategory.HIGH);
}