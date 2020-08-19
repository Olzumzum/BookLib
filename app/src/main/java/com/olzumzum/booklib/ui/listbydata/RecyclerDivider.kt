package com.olzumzum.booklib.ui.listbydata

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.olzumzum.booklib.R

class RecyclerDivider(context: Context): RecyclerView.ItemDecoration() {
    private lateinit var mDivider:Drawable

    init {
        mDivider = context.resources.getDrawable(R.drawable.recycler_divider)
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        val left = parent.paddingLeft + 80
        val rights = parent.width - parent.paddingRight -30

        val childCount = parent.childCount
        for(i in 0 until childCount){
            val child: View = parent.getChildAt(i)
            val params: RecyclerView.LayoutParams = child.layoutParams as RecyclerView.LayoutParams

            val top = child.bottom + params.bottomMargin
            val bottom = top + mDivider.intrinsicHeight

            mDivider.setBounds(left, top, rights, bottom)
            mDivider.draw(c)
        }
    }
}