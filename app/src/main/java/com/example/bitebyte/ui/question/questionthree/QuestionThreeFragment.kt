package com.example.bitebyte.ui.question.questionthree

import android.os.Bundle
import android.text.TextUtils
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.bitebyte.R
import com.example.bitebyte.databinding.FragmentQuestionThreeBinding
import com.example.bitebyte.utils.viewModelsFactory
import com.google.android.material.snackbar.Snackbar

class QuestionThreeFragment : Fragment(), View.OnClickListener {

    private val args: QuestionThreeFragmentArgs by navArgs()

    private lateinit var age : String
    private lateinit var gender : String
    private lateinit var weight : String
    private lateinit var height: String
    private lateinit var healthConcern: String
    private lateinit var menuType: String

    private var _binding: FragmentQuestionThreeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentQuestionThreeBinding.inflate(inflater, container, false)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.slide_left)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        menuType = ""
        healthConcern = args.healthConcern
        age = args.age
        weight = args.weight
        height = args.height
        gender = args.gender

        binding.ibBackButton.setOnClickListener(this)
        binding.btnNext.setOnClickListener(this)
        binding.cvStandart.setOnClickListener(this)
        binding.cvVegetarian.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ib_back_button -> {
                findNavController().navigate(QuestionThreeFragmentDirections.actionQuestionThreeFragmentToQuestionTwoFragment(age, weight, height, gender))
            }
            R.id.btn_next ->{
                if(emptyField(menuType)) {
                    findNavController().navigate(QuestionThreeFragmentDirections.actionQuestionThreeFragmentToQuestionFourFragment(age, weight, height, gender, healthConcern, menuType))
                }else{
                    Snackbar.make(binding.root, getString(R.string.fill_all_fields), Snackbar.LENGTH_SHORT).show()
                }
            }
            R.id.cv_standart->{
                menuType = "1"
                binding.apply {
                    tvStandar.setTextColor(resources.getColor(R.color.white))
                    tvStandarDesc.setTextColor(resources.getColor(R.color.white))
                    tvVegetarian.setTextColor(resources.getColor(R.color.black))
                    tvVegetarianDesc.setTextColor(resources.getColor(R.color.black))
                    cvStandart.setCardBackgroundColor(resources.getColor(R.color.color_theme_primary))
                    cvVegetarian.setCardBackgroundColor(resources.getColor(R.color.color_theme_et))
                }
            }
            R.id.cv_vegetarian->{
                menuType = "2"
                binding.apply {
                    tvStandar.setTextColor(resources.getColor(R.color.black))
                    tvStandarDesc.setTextColor(resources.getColor(R.color.black))
                    tvVegetarian.setTextColor(resources.getColor(R.color.white))
                    tvVegetarianDesc.setTextColor(resources.getColor(R.color.white))
                    cvStandart.setCardBackgroundColor(resources.getColor(R.color.color_theme_et))
                    cvVegetarian.setCardBackgroundColor(resources.getColor(R.color.color_theme_primary))
                }
            }
        }
    }

    private fun emptyField(menuType: String?) : Boolean{
        return when{
            TextUtils.isEmpty(menuType) -> false
            else -> true
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}