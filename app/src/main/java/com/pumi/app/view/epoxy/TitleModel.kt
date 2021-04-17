package com.pumi.app.view.epoxy

import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.pumi.app.R
import com.pumi.app.databinding.ComponentTitleBinding

@EpoxyModelClass(layout = R.layout.component_title)
abstract class TitleModel : EpoxyModelWithHolder<TitleModel.TitleViewHolder>() {

    @field:EpoxyAttribute
    var title: String? = null

    override fun bind(holder: TitleViewHolder) {
        super.bind(holder)
        holder.binding.titleTextView.text = title
    }

    class TitleViewHolder : EpoxyHolder() {
        lateinit var binding: ComponentTitleBinding
            private set

        override fun bindView(itemView: View) {
            binding = ComponentTitleBinding.bind(itemView)
        }
    }
}