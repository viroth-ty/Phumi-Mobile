package com.pumi.app.view.epoxy

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.airbnb.epoxy.EpoxyAsyncUtil
import com.airbnb.epoxy.EpoxyController
import com.pumi.app.R
import com.pumi.app.data.model.Phum
import com.pumi.app.listener.HandleListener
import java.util.concurrent.CopyOnWriteArrayList

class VillageController() : EpoxyController(EpoxyAsyncUtil.getAsyncBackgroundHandler(), EpoxyAsyncUtil.getAsyncBackgroundHandler()) {

    private var provinnces: CopyOnWriteArrayList<Phum> = CopyOnWriteArrayList<Phum>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun buildModels() {

        if (provinnces.isNotEmpty()) {
            provinnces.forEach{ item ->
                village {
                    id(item.id)
                    khmer(item.fullNameKM)
                    english(item.fullNameEn)
                }
            }
        }
    }

    fun submit(posts: ArrayList<Phum>) {
        provinnces.addAll(posts)
        requestModelBuild()
    }

}