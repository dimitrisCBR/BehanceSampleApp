package com.cbr.behancesampleapp.ui.common

import com.cbr.behancesampleapp.R
import com.cbr.behancesampleapp.ui.common.mvp.BaseMvpFragment

abstract class GridFragment : BaseMvpFragment() {

    val columnCount: Int
        get() {
            val itemWidth = (resources.getDimension(R.dimen.card_behace_user_item) + resources.getDimension(R.dimen.card_standard_padding)).toInt()
            return UiUtils.getScreenWidth(context) / itemWidth
        }
}