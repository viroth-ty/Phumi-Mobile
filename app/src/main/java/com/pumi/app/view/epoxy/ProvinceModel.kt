package com.pumi.app.view.epoxy

import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.pumi.app.R
import com.pumi.app.databinding.ComponentProvinceBinding

@EpoxyModelClass(layout = R.layout.component_province)
abstract class ProvinceModel : EpoxyModelWithHolder<ProvinceModel.HeaderViewHolder>() {

    @field:EpoxyAttribute
    var khmer: String? = null

    @field:EpoxyAttribute
    var english: String? = null

    @field:EpoxyAttribute
    var buttonText: String? = null

    @field:EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    open var itemClickListener: View.OnClickListener? = null

    override fun bind(holder: HeaderViewHolder) {
        super.bind(holder)
        holder.binding.apply {
            khmerTitle.text = khmer
            englishTitle.text = english
            buttonView.text = buttonText
            buttonView.setOnClickListener(itemClickListener)
        }
    }

    class HeaderViewHolder : EpoxyHolder() {
        lateinit var binding: ComponentProvinceBinding
            private set

        override fun bindView(itemView: View) {
            binding = ComponentProvinceBinding.bind(itemView)
        }
    }
}