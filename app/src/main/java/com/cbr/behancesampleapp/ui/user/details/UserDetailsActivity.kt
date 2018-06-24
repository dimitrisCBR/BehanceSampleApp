package com.cbr.behancesampleapp.ui.user.details

import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.graphics.Palette
import android.text.format.DateUtils
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.cbr.behancesampleapp.R
import com.cbr.behancesampleapp.R.id.*
import com.cbr.behancesampleapp.model.BehanceUser
import com.cbr.behancesampleapp.ui.common.MvpView
import com.cbr.behancesampleapp.ui.common.mvp.BaseMvpActivity
import com.cbr.behancesampleapp.ui.user.DaggerUserComponent
import com.cbr.behancesampleapp.ui.user.UserModule
import com.cbr.behancesampleapp.util.BeTextUtils
import com.cbr.behancesampleapp.util.UiUtils
import com.cbr.behancesampleapp.util.loadImage
import com.cbr.behancesampleapp.util.toBitmap
import kotlinx.android.synthetic.main.activity_user_details.*
import kotlinx.android.synthetic.main.header_userdetails.*
import java.text.NumberFormat
import java.util.*
import javax.inject.Inject

/** Created by dimitrios on 24/08/2017. */

interface UserDetailsView : MvpView {

    fun onUserFetched(user: BehanceUser)

    fun showError()
}

class UserDetailsActivity : BaseMvpActivity(), UserDetailsView {

    @Inject
    lateinit var presenter: UserDetailsPresenter

    private val userIdFromIntent: Long
        get() = intent.getLongExtra(EXTRA_USER_ID, 0)

    override fun onActivityInject() {
        DaggerUserComponent.builder()
            .appComponent(appComponent())
            .userModule(UserModule())
            .build().inject(this)
        presenter.attachView(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)
    }

    override fun onStart() {
        super.onStart()
        presenter.onSubscribe()
    }

    override fun onResume() {
        super.onResume()
        presenter.fetchUserById(userIdFromIntent)
    }

    override fun onStop() {
        super.onStop()
        presenter.onUnsubscribe()
    }

    override fun onUserFetched(user: BehanceUser) {
        if (supportActionBar == null) {
            toolbar.title = user.displayName
            setSupportActionBar(toolbar)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
        userImageView.loadImage(user.images.largeUrl)
        userImageView.loadImage(user.images.largeUrl, object : RequestListener<Drawable> {
            override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean = false

            override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                resource?.toBitmap()?.let {
                    Palette.from(resource.toBitmap()).generate { palette -> loadHeaderViews(it, palette) }
                }
                return false
            }
        })
        usernameTextView.text = user.occupation
        //very ugly hack to manage unix timestmap. I feel even worse creating a Util method for a single point of usage.
        joinDateTextView.text = DateUtils.formatDateTime(this, user.createdOn * 1000, DateUtils.FORMAT_ABBREV_RELATIVE)
        userFieldsTextView.text = BeTextUtils.formatUserFields(user.fields)

        //Using drawables instead of setting them on XML because of Vector drawables
        val format = NumberFormat.getInstance(Locale.getDefault())

        appreciationsTextView.text = format.format(user.stats.appreciations)
        val medalDrawable = ContextCompat.getDrawable(this, R.drawable.ic_medal)
        appreciationsTextView.setCompoundDrawablesWithIntrinsicBounds(medalDrawable, null, null, null)

        userFollowersTextView.text = format.format(user.stats.followers)
        val followerDrawable = ContextCompat.getDrawable(this, R.drawable.ic_follower)
        userFollowersTextView.setCompoundDrawablesWithIntrinsicBounds(followerDrawable, null, null, null)

        userViewsTextView.text = format.format(user.stats.views)
        val viewsDrawable = ContextCompat.getDrawable(this, R.drawable.ic_eye)
        userViewsTextView.setCompoundDrawablesWithIntrinsicBounds(viewsDrawable, null, null, null)
    }

    override fun showError() {
        //TODO
    }

    private fun loadHeaderViews(bitmap: Bitmap, palette: Palette) {
        //Palette specific Swatches may not find a suitable color. Thus we get a default first.
        val defaultPaletteColor = UiUtils.getPaletteColor(palette)
        //animate as fade in to avoid the flash
        val animationTime = resources.getInteger(android.R.integer.config_longAnimTime)

        backgroundImageView.alpha = 0f
        //create new bimap to avoid blurring the original one.
        backgroundImageView.setImageBitmap(UiUtils.blurBitmap(baseContext, bitmap))
        ObjectAnimator.ofFloat(backgroundImageView, "alpha", 0f, 1f)
            .setDuration(animationTime.toLong())
            .start()

        collapsingToolbarLayout.setContentScrimColor(palette.getMutedColor(defaultPaletteColor))
        collapsingToolbarLayout.setStatusBarScrimColor(palette.getLightMutedColor(defaultPaletteColor))
        collapsingToolbarLayout.setBackgroundColor(palette.getMutedColor(defaultPaletteColor))

        colorOverlayView.alpha = 0f
        colorOverlayView.setBackgroundColor(UiUtils.getPaletteColor(palette))
        ObjectAnimator.ofFloat(colorOverlayView, "alpha", 0f, 0.33f)
            .setDuration((animationTime * 2).toLong())
            .start()
    }

    companion object {

        private val EXTRA_USER_ID = UserDetailsActivity::class.java.canonicalName + "EXTRA_USER_ID"

        fun newIntent(context: Context?, userId: Long): Intent {
            val intent = Intent(context, UserDetailsActivity::class.java)
            intent.putExtra(EXTRA_USER_ID, userId)
            return intent
        }
    }
}
