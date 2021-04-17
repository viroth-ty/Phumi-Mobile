package com.pumi.app.view.epoxy

import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.pumi.app.R
import com.pumi.app.databinding.ComponentLottieBinding

@EpoxyModelClass(layout = R.layout.component_lottie)
abstract class LottieModel : EpoxyModelWithHolder<LottieModel.LottieViewHolder>() {

    @field:EpoxyAttribute
    var source: Int? = null

    override fun bind(holder: LottieViewHolder) {
        super.bind(holder)
        holder.binding.lottie.setAnimation(source!!)
        holder.binding.lottie.playAnimation()
    }

    override fun unbind(holder: LottieViewHolder) {
        super.unbind(holder)
        holder.binding.lottie.pauseAnimation()
    }

    class LottieViewHolder : EpoxyHolder() {
        lateinit var binding: ComponentLottieBinding
            private set

        override fun bindView(itemView: View) {
            binding = ComponentLottieBinding.bind(itemView)
        }
    }
}