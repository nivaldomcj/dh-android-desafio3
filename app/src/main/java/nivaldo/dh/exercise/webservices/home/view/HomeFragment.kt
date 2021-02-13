package nivaldo.dh.exercise.webservices.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import nivaldo.dh.exercise.webservices.databinding.FragmentHomeBinding
import nivaldo.dh.exercise.webservices.home.model.Result
import nivaldo.dh.exercise.webservices.home.view.adapter.HomeAdapter
import nivaldo.dh.exercise.webservices.home.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel

    private fun setupComicsRecyclerView(resultList: List<Result>) {
        binding.pbLoading.visibility = View.GONE

        binding.rvComics.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = HomeAdapter(resultList) {
                val action = HomeFragmentDirections.actionHomeFragmentToComicDetailFragment(it)
                findNavController().navigate(action)
            }
        }
    }

    private fun initObservables() {
        homeViewModel.getComics()
        homeViewModel.onGetComicsResultSuccess.observe(viewLifecycleOwner, { comics ->
            comics?.data?.results?.let { resultList ->
                setupComicsRecyclerView(resultList)
            }
        })
        homeViewModel.onGetComicsResultFailure.observe(viewLifecycleOwner, { error ->
            Toast.makeText(context, "An error occurred: $error", Toast.LENGTH_LONG).show()
        })
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View,
                               savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        initObservables()
    }

}