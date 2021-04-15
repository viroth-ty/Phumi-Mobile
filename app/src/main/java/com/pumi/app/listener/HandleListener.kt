package com.pumi.app.listener

import com.pumi.app.data.model.Phum

interface HandleListener {
    fun onItemClick(item: Phum) {}
}