package com.example.moonlight.ui

import android.widget.ImageView
import com.example.moonlight.R

object ImageUtil {
    fun setImageResource(view: ImageView, lunAge: Double) {
        val imgResource = getImgResource(lunAge)
        view.setImageResource(imgResource)

    }

    fun setMoonText(lunAge: Double): String{
        return moonText(lunAge)
    }

    // 월령마다 사진 바꿔주기
    private fun getImgResource(lunAge: Double): Int {
        return when (lunAge) {
            in 0.0..1.0 -> R.drawable.m0
            in 1.0..2.0 -> R.drawable.m1_88
            in 2.0..3.0 -> R.drawable.m2_24
            in 3.0..4.0 -> R.drawable.m3_08
            in 4.0..5.0 -> R.drawable.m4_16
            in 5.0..6.0 -> R.drawable.m5_17
            in 6.0..7.0 -> R.drawable.m6_75
            in 7.0..8.0 -> R.drawable.m7_76
            in 8.0..9.0 -> R.drawable.m8_79
            in 9.0..10.0 -> R.drawable.m9_78
            in 10.0..11.0 -> R.drawable.m10_76
            in 11.0..12.0 -> R.drawable.m11_55
            in 12.0..13.0 -> R.drawable.m12_50
            in 13.0..14.0 -> R.drawable.m13_32
            in 14.0..15.0 -> R.drawable.m14_40
            in 15.0..16.0 -> R.drawable.m15_73
            in 16.0..17.0 -> R.drawable.m16_80
            in 17.0..18.0 -> R.drawable.m17_47
            in 18.0..19.0 -> R.drawable.m18_50
            in 19.0..20.0 -> R.drawable.m19_20
            in 20.0..21.0 -> R.drawable.m20_42
            in 21.0..22.0 -> R.drawable.m21_50
            in 22.0..23.0 -> R.drawable.m22_19
            in 23.0..24.0 -> R.drawable.m23_11
            in 24.0..25.0 -> R.drawable.m24
            in 25.0..25.2 -> R.drawable.m25_1
            in 25.3..26.0 -> R.drawable.m25_57
            in 26.0..27.00 -> R.drawable.m27
            in 27.0..29.00 -> R.drawable.m28
            else -> R.drawable.default_moon
        }
    }

    // 월령마다 달 종류 바꾸기
    private fun moonText(lunAge: Double):String{
        return when(lunAge){
            in 0.0..7.0 -> "초승달"
            in 7.0..8.0 -> "상현달"
            in 8.0..14.0 -> "상현망간의 달"
            in 14.0..16.0 -> "보름달"
            in 16.0..22.0 -> "하현망간의 달"
            in 22.0..23.0 -> "하현달"
            in 23.0..29.0 -> "그믐달"
            in 29.0..30.0 -> "삭"
            else -> "달"
        }
    }
}