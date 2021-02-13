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
import nivaldo.dh.exercise.webservices.R
import nivaldo.dh.exercise.webservices.databinding.FragmentComicImageBinding

class ComicImageFragment : Fragment() {

    private lateinit var binding: FragmentComicImageBinding
    private val args: ComicImageFragmentArgs by navArgs()

    private fun initComponents() {
        args.result.let { result ->
            Glide.with(this)
                    .load(result.mThumbnailPath)
                    .centerCrop()
                    .into(binding.ivThumbnailLarge)
        }
    }

    private fun initActionBar() {
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
        binding.toolbar.setNavigationIcon(R.drawable.ic_close)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentComicImageBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View,
                               savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initActionBar()
        initComponents()
    }

}