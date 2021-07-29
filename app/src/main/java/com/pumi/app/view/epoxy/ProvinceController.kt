package com.pumi.app.view.epoxy

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import com.airbnb.epoxy.EpoxyAsyncUtil
import com.airbnb.epoxy.EpoxyController
import com.pumi.app.R
import com.pumi.app.data.model.Phum
import com.pumi.app.listener.HandleListener
import java.util.concurrent.CopyOnWriteArrayList

class ProvinceController(
    private val context: Context,
    private val color: Int,
    private val onclick: HandleListener? = null,
    private val textButton: String,
    private val navController: NavController,
) : EpoxyController(
    EpoxyAsyncUtil.getAsyncBackgroundHandler(),
    EpoxyAsyncUtil.getAsyncBackgroundHandler()
) {

    private var provinces: CopyOnWriteArrayList<Phum> = CopyOnWriteArrayList<Phum>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun buildModels() {

        if (provinces.isNotEmpty()) {
            provinces.forEach { item ->
                province {
                    id(item.id)
                    khmer(item.fullNameKM)
                    english(item.fullNameEn)
                    buttonText(textButton)
                    color(color)
                    itemClickListener { _, _, _, _ ->
                        onclick?.onItemClick(item = item)
                    }
                    mapListener { _, _, _, _ ->
                        navController.navigate(R.id.action_provinceFragment_to_mapFragment)
                    }
                }
            }
        }
    }

    fun submit(posts: ArrayList<Phum>) {
        provinces.addAll(posts)
        requestModelBuild()
    }

}