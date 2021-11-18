package com.example.redditapp.views

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.redditapp.R

class TrendingTopicView constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

  var text: String = ""
    set(value) {
      field = value
      findViewById<TextView>(R.id.text).text = value
    }

  var image: Int = 0
    set(value) {
      field = value
      findViewById<ImageView>(R.id.image).setImageResource(value)
    }

  init {
    inflate(context, R.layout.view_trending_topic, this)
    radius = resources.getDimension(R.dimen.trending_view_corner_radius)

    val width = resources.getDimensionPixelSize(R.dimen.trending_view_width)
    val height = resources.getDimensionPixelSize(R.dimen.trending_view_height)
    val layoutParams = ViewGroup.LayoutParams(width, height)

    setLayoutParams(layoutParams)
  }
}