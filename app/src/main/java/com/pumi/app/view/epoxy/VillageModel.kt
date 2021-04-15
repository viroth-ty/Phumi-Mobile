package com.pumi.app.view.epoxy

import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.pumi.app.R
import com.pumi.app.databinding.ComponentVillageBinding

@EpoxyModelClass(layout = R.layout.component_village)
abstract class VillageModel : EpoxyModelWithHolder<VillageModel.VillageViewHolder>() {

    @field:EpoxyAttribute
    var khmer: String? = null

    @field:EpoxyAttribute
    var english: String? = null

    override fun bind(holder: VillageViewHolder) {
        super.bind(holder)
        holder.binding.apply {
            khmerTitle.text = khmer
            englishTitle.text = english
        }
    }

    class VillageViewHolder : EpoxyHolder() {
        lateinit var binding: ComponentVillageBinding
            private set

        override fun bindView(itemView: View) {
            binding = ComponentVillageBinding.bind(itemView)
        }
    }
}