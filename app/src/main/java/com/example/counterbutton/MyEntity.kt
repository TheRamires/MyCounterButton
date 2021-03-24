package com.example.counterbutton

import android.view.View

class MyEntity (
    var id: Int,
    var name: String?,
    var counter: Int=0,
){
    var visibleCounter: Boolean?=false
}