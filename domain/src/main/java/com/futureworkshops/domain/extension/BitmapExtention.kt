package com.futureworkshops.domain.extension

import android.content.Context
import android.graphics.Bitmap
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur

fun Bitmap.blurBitmap(context: Context): Bitmap {

    val blurredBitmap = this.copy(this.config, true)
    //User the support version if you want to support api<17
    val rs = RenderScript.create(context)
    val input =
        Allocation.createFromBitmap(rs, blurredBitmap, Allocation.MipmapControl.MIPMAP_NONE, Allocation.USAGE_SCRIPT)
    val output = Allocation.createTyped(rs, input.type)
    val script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs))
    script.setRadius(25f /* e.g. 3.f */)
    script.setInput(input)
    script.forEach(output)
    output.copyTo(blurredBitmap)
    return blurredBitmap
}