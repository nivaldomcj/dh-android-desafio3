package nivaldo.dh.exercise.webservices.home.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import nivaldo.dh.exercise.webservices.R
import nivaldo.dh.exercise.webservices.databinding.ItemResultBinding
import nivaldo.dh.exercise.webservices.home.model.Result

class HomeAdapter(
        private val resultList: List<Result>,
        private val onResultClicked: (Result) -> Unit
) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemResultBinding.inflate(layoutInflater, parent, false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(resultList[position], onResultClicked)
    }

    override fun getItemCount(): Int {
        return resultList.size
    }

    class HomeViewHolder(
            private val binding: ItemResultBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(result: Result, onResultClicked: (Result) -> Unit) = with(binding) {
            tvThumbnailTitle.text = result.mThumbnailTitle

            Glide.with(itemView.context)
                    .load(result.mThumbnailPath)
                    .optionalCenterCrop()
                    .placeholder(R.drawable.layer_img_loading)
                    .into(ivThumbnail)

            itemView.setOnClickListener {
                onResultClicked(result)
            }
        }

    }

}