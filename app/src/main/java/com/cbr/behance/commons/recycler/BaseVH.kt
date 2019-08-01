package com.cbr.behance.commons.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer


abstract class BaseVH<in T :ListItem>(
        override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    abstract fun bind(position: Int, data: T)

}