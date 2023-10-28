package com.example.navigation.models

import androidx.annotation.DrawableRes
import com.example.navigation.R

data class Pager(@DrawableRes val image:Int,val description:String)

val pagerList= listOf(
    Pager(R.drawable.home,"arts and culture .We bring everything around us as to people"),
    Pager(R.drawable.current,"Booming tech invensions and discoveries"),
    Pager(R.drawable.acceptance,"Main news portal.Everything local ,people get it")
)