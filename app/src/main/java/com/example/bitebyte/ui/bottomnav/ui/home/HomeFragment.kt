package com.example.bitebyte.ui.bottomnav.ui.home

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bitebyte.R
import com.example.bitebyte.adapter.HorizontalRecipeAdapter
import com.example.bitebyte.adapter.ShoppingListAdapter
import com.example.bitebyte.adapter.VerticalRecipeAdapter
import com.example.bitebyte.databinding.FragmentHomeBinding
import com.example.bitebyte.ui.bottomnav.ui.recommendation.RecommendationFragmentDirections
import com.example.bitebyte.ui.bottomnav.ui.recommendation.RecommendationViewModel
import com.example.bitebyte.utils.SessionManager
import com.example.bitebyte.utils.viewModelsFactory
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.w3c.dom.Text


class HomeFragment : Fragment(), View.OnClickListener {

    private lateinit var sessionManager: SessionManager

    private lateinit var dialog: BottomSheetDialog

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModelsFactory {
        HomeViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.fade)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sessionManager = SessionManager(requireContext())
        binding.tvHomeName.text = getString(R.string.hi_user_name, sessionManager.getName())
        binding.cvSearch.setOnClickListener(this)

        val adapter = VerticalRecipeAdapter(
            VerticalRecipeAdapter.OnClickRecipeListener { recipe, binding ->
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToDetailRecipeFragment(
                        recipe
                    )
                )
            })

        val adapterHorizontal = HorizontalRecipeAdapter(
            HorizontalRecipeAdapter.OnClickRecipeListener { recipe, binding ->
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToDetailRecipeFragment(
                        recipe
                    )
                )
            })

        viewModel.recipes.observe(viewLifecycleOwner) {
            if (it != null) {
                adapter.bindRecipe(
                    it.result
                )
                adapterHorizontal.bindRecipe(
                    it.result
                )
            }
            binding.rvRecommendationMenu.apply {
                setHasFixedSize(true)
                binding.rvRecommendationMenu.adapter = adapter
            }
            binding.rvBreakfastMenu.apply{
                setHasFixedSize(true)
                binding.rvBreakfastMenu.adapter = adapterHorizontal
                scrollToPosition(30)
            }
            binding.rvTodayMenu.apply{
                setHasFixedSize(true)
                binding.rvTodayMenu.adapter = adapterHorizontal
                scrollToPosition(70)
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

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.cv_search -> {
                val dialogView = layoutInflater.inflate(R.layout.bottom_sheet_search_layout, null)
                dialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme)
                dialog.setContentView(dialogView)
                dialog.behavior.peekHeight = 2000
                dialog.show()

                val adapter = VerticalRecipeAdapter(
                    VerticalRecipeAdapter.OnClickRecipeListener { recipe, binding ->
                        findNavController().navigate(
                            HomeFragmentDirections.actionHomeFragmentToDetailRecipeFragment(
                                recipe
                            )
                        )
                        dialog.dismiss()
                    })

                viewModel.recipeSearch.observe(viewLifecycleOwner){
                    if(it != null){
                        dialogView.findViewById<ImageView>(R.id.iv_no_search).visibility = View.GONE
                        dialogView.findViewById<TextView>(R.id.tv_search_illustration).visibility = View.GONE
                        adapter.bindRecipe(
                            it.result)
                    }
                }
                val rvSearch = dialogView.findViewById<RecyclerView>(R.id.rv_search_recipe)
                rvSearch.apply {
                    setHasFixedSize(true)
                    adapter.clearRecipe()
                    adapter.notifyDataSetChanged()
                    rvSearch.adapter = adapter
                }

                val searchManager = requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager
                val searchView = dialogView.findViewById<SearchView>(R.id.sv_search)

                searchView.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String): Boolean {
                        Log.d("SearchView", "Query submitted: $query")
                        viewModel.searchRecipe(query)
                        searchView.clearFocus()
                        return true
                    }
                    override fun onQueryTextChange(newText: String): Boolean {
                        return false
                    }
                })
            }
        }
    }
}