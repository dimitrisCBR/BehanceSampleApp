package com.cbr.behancesampleapp.ui.landing

import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import com.cbr.behancesampleapp.R
import com.cbr.behancesampleapp.domain.model.BehanceUser
import com.cbr.behancesampleapp.util.UiUtils
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.card_behance_user_item.*

/** Created by Dimitrios on 8/26/2017. */
class BehanceUserGridViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
    
    init {
        ButterKnife.bind(this, itemView)
    }
    
    fun onBind(user: BehanceUser, position: Int) {
        UiUtils.loadImageInto(cardBehanceUserImage, user.images.largeUrl)
        
        cardBehanceUserTitle.text = user.displayName
        cardBehanceUserSubtitle.text = user.country
        cardBehanceUserDivider.visibility = if (TextUtils.isEmpty(user.occupation)) View.GONE else View.VISIBLE
        cardBehanceUserExtra.visibility = if (TextUtils.isEmpty(user.occupation)) View.GONE else View.VISIBLE
        cardBehanceUserExtra.text = user.occupation
    }
    
    companion object {
        
        fun newInstance(parent: ViewGroup): BehanceUserGridViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.card_behance_user_item, parent, false)
            return BehanceUserGridViewHolder(view)
        }
    }
}