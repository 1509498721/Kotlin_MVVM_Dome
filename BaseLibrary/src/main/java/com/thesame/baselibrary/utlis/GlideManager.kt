package com.thesame.baselibrary.utlis

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.widget.ImageView
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.thesame.baselibrary.R
import java.security.MessageDigest

/**
 * @Author: whz on 2018/7/23 10:53
 * Function: Glide 工具类支持加载常规、圆角、圆形图片
 * Description:
 */
object GlideManager {
    private var sCommonPlaceholder = -1
    private var sCirclePlaceholder = -1
    private var sRoundPlaceholder = -1
    private var sCommonPlaceholderDrawable: Drawable? = null
    private var sCirclePlaceholderDrawable: Drawable? = null
    private var sRoundPlaceholderDrawable: Drawable? = null

    @ColorInt
    private var mPlaceholderColor = Color.LTGRAY
    private var mPlaceholderRoundRadius = 4f
    private fun setDrawable(gd: GradientDrawable?, radius: Float) {
        gd!!.setColor(mPlaceholderColor)
        gd.cornerRadius = radius
    }

    /**
     * 设置默认颜色
     *
     * @param placeholderColor
     */
    @JvmStatic
    fun setPlaceholderColor(@ColorInt placeholderColor: Int) {
        mPlaceholderColor = placeholderColor
        sCommonPlaceholderDrawable = GradientDrawable()
        sCirclePlaceholderDrawable = GradientDrawable()
        sRoundPlaceholderDrawable = GradientDrawable()
        setDrawable(sCommonPlaceholderDrawable as GradientDrawable?, 0f)
        setDrawable(sCirclePlaceholderDrawable as GradientDrawable?, 10000f)
        setDrawable(sRoundPlaceholderDrawable as GradientDrawable?, mPlaceholderRoundRadius)
    }

    /**
     * 设置圆角图片占位背景图圆角幅度
     *
     * @param placeholderRoundRadius
     */
    @JvmStatic
    fun setPlaceholderRoundRadius(placeholderRoundRadius: Float) {
        mPlaceholderRoundRadius = placeholderRoundRadius
        setPlaceholderColor(mPlaceholderColor)
    }

    /**
     * 设置圆形图片的占位图
     *
     * @param circlePlaceholder
     */
    fun setCirclePlaceholder(circlePlaceholder: Int) {
        sCirclePlaceholder = circlePlaceholder
    }

    fun setCirclePlaceholder(circlePlaceholder: Drawable?) {
        sCirclePlaceholderDrawable = circlePlaceholder
    }

    /**
     * 设置正常图片的占位符
     *
     * @param commonPlaceholder
     */
    fun setCommonPlaceholder(commonPlaceholder: Int) {
        sCommonPlaceholder = commonPlaceholder
    }

    fun setCommonPlaceholder(commonPlaceholder: Drawable?) {
        sCommonPlaceholderDrawable = commonPlaceholder
    }

    /**
     * 设置圆角图片的占位符
     *
     * @param roundPlaceholder
     */
    fun setRoundPlaceholder(roundPlaceholder: Int) {
        sRoundPlaceholder = roundPlaceholder
    }

    fun setRoundPlaceholder(roundPlaceholder: Drawable?) {
        sRoundPlaceholderDrawable = roundPlaceholder
    }

    /**
     * 普通加载图片
     *
     * @param obj
     * @param iv
     * @param placeholder
     */
    fun loadImg(obj: Any?, iv: ImageView, placeholder: Drawable?) {
        Glide.with(iv.context).load(obj).apply(
            requestOptions
                .error(placeholder)
                .placeholder(placeholder)
                .fallback(placeholder)
                .dontAnimate()
        ).into(iv)
    }

    @JvmOverloads
    fun loadImg(obj: Any?, iv: ImageView, placeholderResource: Int = sCommonPlaceholder) {
        val drawable = getDrawable(iv.context, placeholderResource)
        loadImg(obj, iv, drawable ?: sCommonPlaceholderDrawable)
    }

    /**
     * 加载圆形图片
     *
     * @param obj
     * @param iv
     * @param placeholder 占位图
     */
    fun loadCircleImg(obj: Any?, iv: ImageView, placeholder: Drawable?) {
        Glide.with(iv.context).load(obj).apply(
            requestOptions
                .error(placeholder)
                .placeholder(placeholder)
                .fallback(placeholder)
                .dontAnimate()
                .transform(CircleCrop())
        ).into(iv)
    }

    @JvmOverloads
    fun loadCircleImg(obj: Any?, iv: ImageView, placeholderResource: Int = sCirclePlaceholder) {
        val drawable = getDrawable(iv.context, placeholderResource)
        loadCircleImg(obj, iv, drawable ?: sCirclePlaceholderDrawable)
    }

    /**
     * 加载圆角图片
     *
     * @param obj                 加载的图片资源
     * @param iv
     * @param dp                  圆角尺寸-dp
     * @param placeholder         -占位图
     * @param isOfficial-是否官方模式圆角
     */
    fun loadRoundImg(
        obj: Any?,
        iv: ImageView,
        dp: Float,
        placeholder: Drawable?,
        isOfficial: Boolean
    ) {
        Glide.with(iv.context).load(obj).apply(
            requestOptions
                .error(placeholder)
                .placeholder(placeholder)
                .fallback(placeholder)
                .dontAnimate()
                .transform(
                    if (isOfficial) RoundedCorners(dp2px(dp)) else GlideRoundTransform(
                        dp2px(
                            dp
                        )
                    )
                )
        ).into(iv)
    }

    fun loadRoundImg(
        obj: Any?,
        iv: ImageView,
        dp: Float,
        placeholderResource: Int,
        isOfficial: Boolean
    ) {
        val drawable = getDrawable(iv.context, placeholderResource)
        loadRoundImg(obj, iv, dp, drawable ?: sRoundPlaceholderDrawable, isOfficial)
    }

    @JvmOverloads
    fun loadRoundImg(obj: Any?, iv: ImageView, dp: Float, isOfficial: Boolean = true) {
        loadRoundImg(obj, iv, dp, sRoundPlaceholder, isOfficial)
    }

    @JvmOverloads
    fun loadRoundImg(obj: Any?, iv: ImageView, isOfficial: Boolean = true) {
        loadRoundImg(obj, iv, mPlaceholderRoundRadius, isOfficial)
    }

    // 填充方式
    //错误图
    //优先级
    //缓存策略
    private val requestOptions: RequestOptions
        private get() = RequestOptions() // 填充方式
            .centerCrop() //错误图
            .error(R.drawable.ic_empty) //优先级
            .priority(Priority.HIGH) //缓存策略
            .diskCacheStrategy(DiskCacheStrategy.ALL)

    private fun dp2px(dipValue: Float): Int {
        val scale = Resources.getSystem().displayMetrics.density
        return (dipValue * scale + 0.5f).toInt()
    }

    private fun getDrawable(context: Context, @DrawableRes res: Int): Drawable? {
        var drawable: Drawable? = null
        try {
            drawable = context.resources.getDrawable(res)
        } catch (e: Exception) {
        }
        return drawable
    }

    private class GlideRoundTransform(var radius: Int) : BitmapTransformation() {
        override fun transform(
            pool: BitmapPool,
            toTransform: Bitmap,
            outWidth: Int,
            outHeight: Int
        ): Bitmap {
            return roundCrop(pool, toTransform)
        }

        private fun roundCrop(pool: BitmapPool, source: Bitmap): Bitmap {
            var result = pool[source.width, source.height, Bitmap.Config.ARGB_8888]
            if (result == null) {
                result = Bitmap.createBitmap(source.width, source.height, Bitmap.Config.ARGB_8888)
            }
            val canvas = Canvas(result)
            val paint = Paint()
            paint.shader =
                BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
            paint.isAntiAlias = true
            val rectF = RectF(
                0f, 0f, source.width.toFloat(), source.height
                    .toFloat()
            )
            canvas.drawRoundRect(rectF, radius.toFloat(), radius.toFloat(), paint)
            return result
        }

        override fun updateDiskCacheKey(messageDigest: MessageDigest) {}
    }
}