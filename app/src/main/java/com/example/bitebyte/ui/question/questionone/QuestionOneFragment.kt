package com.example.bitebyte.ui.question.questionone

import android.os.Bundle
import android.text.TextUtils
import android.transition.TransitionInflater
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.bitebyte.R
import com.example.bitebyte.databinding.FragmentQuestionOneBinding
import com.example.bitebyte.utils.viewModelsFactory
import com.google.android.material.snackbar.Snackbar

class QuestionOneFragment : Fragment(), View.OnClickListener {

    private val viewModel: QuestionOneViewModel by viewModelsFactory {
        QuestionOneViewModel(requireActivity().application)
    }

    private var _binding: FragmentQuestionOneBinding? = null
    private val binding get() = _binding!!

    private lateinit var age : String
    private lateinit var gender : String
    private lateinit var weight : String
    private lateinit var height: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentQuestionOneBinding.inflate(inflater, container, false)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.slide_left)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        gender = ""

        binding.ibBackButton.setOnClickListener(this)
        binding.cvMale.setOnClickListener(this)
        binding.cvFemale.setOnClickListener(this)
        binding.btnNext.setOnClickListener(this)
        binding.etAge.setOnClickListener(this)
        binding.etWeight.setOnClickListener(this)
        binding.etHeight.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.ib_back_button ->{
                findNavController().navigate(QuestionOneFragmentDirections.actionQuestionOneFragmentToQuestionStartFragment())
            }
            R.id.cv_male ->{
                gender = "1"
                binding.apply {
                    genderMale.setImageDrawable(resources.getDrawable(R.drawable.male_white))
                    genderFemale.setImageDrawable(resources.getDrawable(R.drawable.female))
                    tvMale.setTextColor(resources.getColor(R.color.white))
                    tvFemale.setTextColor(resources.getColor(R.color.black_light))
                    cvMale.setCardBackgroundColor(resources.getColor(R.color.color_theme_primary))
                    cvFemale.setCardBackgroundColor(resources.getColor(R.color.color_theme_et))
                }
            }
            R.id.cv_female ->{
                gender = "2"
                binding.apply {
                    genderMale.setImageDrawable(resources.getDrawable(R.drawable.male))
                    genderFemale.setImageDrawable(resources.getDrawable(R.drawable.female_white))
                    tvMale.setTextColor(resources.getColor(R.color.black_light))
                    tvFemale.setTextColor(resources.getColor(R.color.white))
                    cvMale.setCardBackgroundColor(resources.getColor(R.color.color_theme_et))
                    cvFemale.setCardBackgroundColor(resources.getColor(R.color.color_theme_primary))
                }
            }
            R.id.btn_next ->{
                age = viewModel.age.value.toString()
                weight = viewModel.weight.value.toString()
                height = viewModel.height.value.toString()
                if(emptyField(viewModel.age.value, viewModel.weight.value, viewModel.height.value, gender)) {
                    findNavController().navigate(QuestionOneFragmentDirections.actionQuestionOneFragmentToQuestionTwoFragment(age,weight,height,
                        gender

                    ))
                    Log.d(QuestionOneFragment::class.java.toString(), age)

                }
               else{
                  Snackbar.make(binding.root, getString(R.string.fill_all_fields), Snackbar.LENGTH_SHORT).show()
               }
            }
        }
    }

    private fun emptyField(age: String?, weight: String?, height:String?, gender:String?) : Boolean{
        return when{
            TextUtils.isEmpty(age) -> false
            TextUtils.isEmpty(weight) -> false
            TextUtils.isEmpty(height) -> false
            TextUtils.isEmpty(gender) -> false
            else -> true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}