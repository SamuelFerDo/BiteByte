package com.example.bitebyte.ui.bottomnav.ui.recommendation

import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.bitebyte.adapter.VerticalRecipeAdapter
import com.example.bitebyte.databinding.FragmentRecommendationBinding
import com.example.bitebyte.utils.SessionManager
import com.example.bitebyte.utils.viewModelsFactory

class RecommendationFragment : Fragment() {

    private var _binding: FragmentRecommendationBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RecommendationViewModel by viewModelsFactory {
        val sessionManager = SessionManager(requireContext())
        RecommendationViewModel(sessionManager)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecommendationBinding.inflate(inflater, container, false)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.fade)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = VerticalRecipeAdapter(
            VerticalRecipeAdapter.OnClickRecipeListener { recipe, binding ->
                findNavController().navigate(RecommendationFragmentDirections.actionRecommendationFragmentToDetailRecipeFragment(recipe))
            })

        viewModel.recipes.observe(viewLifecycleOwner){
            if (it != null) {
                adapter.bindRecipe(it.result
                )
            }
            binding.rvForyouRecipe.apply {
                setHasFixedSize(true)
                this.adapter = adapter
            }
        }

        viewModel.loading.observe(viewLifecycleOwner){
            if(it){
                binding.progressBar.visibility = View.VISIBLE
            }else{
                binding.progressBar.visibility = View.GONE
            }
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}