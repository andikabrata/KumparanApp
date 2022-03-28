package com.example.kumparanapp.feature.photo.adapter

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.example.kumparanapp.R
import com.example.kumparanapp.common.extension.loadImage
import com.example.kumparanapp.model.photo.Photo

class PhotoViewAdapter(val context: Context, val list: ArrayList<Photo>) : PagerAdapter() {

    var url: String = ""

    override fun getCount(): Int {
        return list.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): View {
        val inflater = (context as Activity).layoutInflater
        val viewItem: View =
            inflater.inflate(R.layout.item_list_gallery_detail_image, container, false)
        val imageView = viewItem.findViewById<View>(R.id.image_gallery_detail_image) as ImageView
        val txtTitle = viewItem.findViewById<View>(R.id.tv_title) as TextView

        val photoModel: Photo = list[position]
        txtTitle.text = photoModel.title
        url = photoModel.url
        imageView.loadImage("$url.jpg")

        container.addView(viewItem)
        return viewItem
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    companion object {
        private val sDrawables = intArrayOf(
            R.drawable.wallpaper, R.drawable.wallpaper, R.drawable.wallpaper,
            R.drawable.wallpaper, R.drawable.wallpaper, R.drawable.wallpaper
        )
    }
}