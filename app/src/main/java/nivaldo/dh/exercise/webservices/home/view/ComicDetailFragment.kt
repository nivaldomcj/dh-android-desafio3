package nivaldo.dh.exercise.webservices.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import nivaldo.dh.exercise.webservices.R
import nivaldo.dh.exercise.webservices.databinding.FragmentComicDetailBinding

class ComicDetailFragment : Fragment() {

    private lateinit var binding: FragmentComicDetailBinding
    private val args: ComicDetailFragmentArgs by navArgs()

    private fun initComponents() {
        args.result.let {
            binding.tvComicTitle.text = "${it.title}"
            binding.tvComicDescription.text = "${it.description}"
            binding.tvComicPublished.text = "${it.mPublishedDate}"
            binding.tvComicPages.text = "${it.pageCount}"
            binding.tvComicPrice.text = "${it.mPrice}"

            Glide.with(this)
                    .load(it.mImagePath)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .optionalCenterCrop()
                    .error(R.drawable.layer_ic_broken)
                    .into(binding.ivComicCover)

            Glide.with(this)
                    .load(it.mThumbnailPath)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .error(R.drawable.layer_ic_broken)
                    .into(binding.ivThumbnail)
        }
    }

    private fun initActionBar() {
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentComicDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View,
                               savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initActionBar()
        initComponents()
    }

}