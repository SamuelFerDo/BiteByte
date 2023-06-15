package com.example.bitebyte.ui.question.questiontwo

import android.os.Bundle
import android.text.TextUtils
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.bitebyte.R
import com.example.bitebyte.databinding.FragmentQuestionTwoBinding
import com.example.bitebyte.utils.viewModelsFactory
import com.google.android.material.snackbar.Snackbar

class QuestionTwoFragment : Fragment(), View.OnClickListener {

    private val args: QuestionTwoFragmentArgs by navArgs()

    private lateinit var age : String
    private lateinit var gender : String
    private lateinit var weight : String
    private lateinit var height: String
    private lateinit var healthConcern : String

    private var _binding: FragmentQuestionTwoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentQuestionTwoBinding.inflate(inflater, container, false)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.slide_left)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        healthConcern = "9"
        age = args.age
        weight = args.weight
        height = args.height
        gender = args.gender

        binding.cvDontHave.setOnClickListener(this)
        binding.ibBackButton.setOnClickListener(this)
        binding.cvObesity.setOnClickListener(this)
        binding.cvCancer.setOnClickListener(this)
        binding.cvCholesterol.setOnClickListener(this)
        binding.cvDiabetes.setOnClickListener(this)
        binding.cvStroke.setOnClickListener(this)
        binding.cvHeart.setOnClickListener(this)
        binding.cvKidney.setOnClickListener(this)
        binding.cvLiver.setOnClickListener(this)
        binding.btnNext.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ib_back_button -> {
                findNavController().navigate(QuestionTwoFragmentDirections.actionQuestionTwoFragmentToQuestionOneFragment())
            }
            R.id.btn_next ->{
                    findNavController().navigate(QuestionTwoFragmentDirections.actionQuestionTwoFragmentToQuestionThreeFragment(age, weight, height, gender, healthConcern))
            }
            R.id.cv_dont_have->{
                healthConcern = "9"
                binding.apply {
                    tvDontHave.setTextColor(ContextCompat.getColor(requireContext(),R.color.white))
                    ivObesity.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.obesity))
                    ivCancer.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.cancer))
                    ivCholesterol.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.cholesterol))
                    ivDiabetes.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.diabetes))
                    ivStroke.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.stroke))
                    ivLiver.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.liver))
                    ivHeart.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.heart))
                    ivKidney.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.kidneys))
                    cvDontHave.setCardBackgroundColor(ContextCompat.getColor(requireContext(),R.color.color_theme_primary))
                    cvObesity.setCardBackgroundColor(ContextCompat.getColor(requireContext(),R.color.color_theme_et))
                    cvCancer.setCardBackgroundColor(ContextCompat.getColor(requireContext(),R.color.color_theme_et))
                    cvCholesterol.setCardBackgroundColor(ContextCompat.getColor(requireContext(),R.color.color_theme_et))
                    cvDiabetes.setCardBackgroundColor(ContextCompat.getColor(requireContext(),R.color.color_theme_et))
                    cvStroke.setCardBackgroundColor(ContextCompat.getColor(requireContext(),R.color.color_theme_et))
                    cvLiver.setCardBackgroundColor(ContextCompat.getColor(requireContext(),R.color.color_theme_et))
                    cvHeart.setCardBackgroundColor(ContextCompat.getColor(requireContext(),R.color.color_theme_et))
                    cvKidney.setCardBackgroundColor(ContextCompat.getColor(requireContext(),R.color.color_theme_et))
                }
            }
            R.id.cv_obesity ->{
                healthConcern = "1"
                binding.apply {
                    tvDontHave.setTextColor(ContextCompat.getColor(requireContext(),R.color.black))
                    cvDontHave.setCardBackgroundColor(ContextCompat.getColor(requireContext(),R.color.color_theme_et))
                    ivObesity.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.obesity_white))
                    ivCancer.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.cancer))
                    ivCholesterol.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.cholesterol))
                    ivDiabetes.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.diabetes))
                    ivStroke.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.stroke))
                    ivLiver.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.liver))
                    ivHeart.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.heart))
                    ivKidney.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.kidneys))
                    cvObesity.setCardBackgroundColor(ContextCompat.getColor(requireContext(),R.color.color_theme_primary))
                    cvCancer.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_theme_et))
                    cvCholesterol.setCardBackgroundColor(ContextCompat.getColor(requireContext(),R.color.color_theme_et))
                    cvDiabetes.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_theme_et))
                    cvStroke.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_theme_et))
                    cvHeart.setCardBackgroundColor(ContextCompat.getColor(requireContext(),R.color.color_theme_et))
                    cvKidney.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_theme_et))
                    cvLiver.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_theme_et))
                }
            }
            R.id.cv_cancer ->{
                healthConcern = "2"
                binding.apply {
                    tvDontHave.setTextColor(ContextCompat.getColor(requireContext(),R.color.black))
                    cvDontHave.setCardBackgroundColor(ContextCompat.getColor(requireContext(),R.color.color_theme_et))
                    ivObesity.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.obesity))
                    ivCancer.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.cancer_white))
                    ivCholesterol.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.cholesterol))
                    ivDiabetes.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.diabetes))
                    ivStroke.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.stroke))
                    ivLiver.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.liver))
                    ivHeart.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.heart))
                    ivKidney.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.kidneys))
                    cvCancer.setCardBackgroundColor(ContextCompat.getColor(requireContext(),R.color.color_theme_primary))
                    cvObesity.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_theme_et))
                    cvCholesterol.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_theme_et))
                    cvDiabetes.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_theme_et))
                    cvStroke.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_theme_et))
                    cvHeart.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_theme_et))
                    cvKidney.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_theme_et))
                    cvLiver.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_theme_et))
                }
            }
            R.id.cv_diabetes ->{
                healthConcern = "4"
                binding.apply {
                    tvDontHave.setTextColor(ContextCompat.getColor(requireContext(),R.color.black))
                    cvDontHave.setCardBackgroundColor(ContextCompat.getColor(requireContext(),R.color.color_theme_et))
                    ivObesity.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.obesity))
                    ivCancer.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.cancer))
                    ivCholesterol.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.cholesterol))
                    ivDiabetes.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.diabetes_white))
                    ivStroke.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.stroke))
                    ivLiver.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.liver))
                    ivHeart.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.heart))
                    ivKidney.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.kidneys))
                    cvDiabetes.setCardBackgroundColor(ContextCompat.getColor(requireContext(),R.color.color_theme_primary))
                    cvObesity.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_theme_et))
                    cvCancer.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_theme_et))
                    cvCholesterol.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_theme_et))
                    cvStroke.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_theme_et))
                    cvHeart.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_theme_et))
                    cvKidney.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_theme_et))
                    cvLiver.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_theme_et))
                }
            }
            R.id.cv_cholesterol ->{
                healthConcern = "3"
                binding.apply {
                    tvDontHave.setTextColor(ContextCompat.getColor(requireContext(),R.color.black))
                    cvDontHave.setCardBackgroundColor(ContextCompat.getColor(requireContext(),R.color.color_theme_et))
                    ivObesity.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.obesity))
                    ivCancer.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.cancer))
                    ivCholesterol.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.cholestrol_white))
                    ivDiabetes.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.diabetes))
                    ivStroke.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.stroke))
                    ivLiver.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.liver))
                    ivHeart.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.heart))
                    ivKidney.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.kidneys))
                    cvCholesterol.setCardBackgroundColor(ContextCompat.getColor(requireContext(),R.color.color_theme_primary))
                    cvObesity.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_theme_et))
                    cvCancer.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_theme_et))
                    cvDiabetes.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_theme_et))
                    cvStroke.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_theme_et))
                    cvHeart.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_theme_et))
                    cvKidney.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_theme_et))
                    cvLiver.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_theme_et))
                }
            }
            R.id.cv_stroke ->{
                healthConcern = "5"
                binding.apply {
                    tvDontHave.setTextColor(ContextCompat.getColor(requireContext(),R.color.black))
                    cvDontHave.setCardBackgroundColor(ContextCompat.getColor(requireContext(),R.color.color_theme_et))
                    ivObesity.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.obesity))
                    ivCancer.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.cancer))
                    ivCholesterol.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.cholesterol))
                    ivDiabetes.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.diabetes))
                    ivStroke.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.stroke_white))
                    ivLiver.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.liver))
                    ivHeart.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.heart))
                    ivKidney.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.kidneys))
                    cvStroke.setCardBackgroundColor(ContextCompat.getColor(requireContext(),R.color.color_theme_primary))
                    cvObesity.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_theme_et))
                    cvCancer.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_theme_et))
                    cvCholesterol.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_theme_et))
                    cvDiabetes.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_theme_et))
                    cvHeart.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_theme_et))
                    cvKidney.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_theme_et))
                    cvLiver.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_theme_et))
                }
            }
            R.id.cv_liver ->{
                healthConcern = "8"
                binding.apply {
                    tvDontHave.setTextColor(ContextCompat.getColor(requireContext(),R.color.black))
                    cvDontHave.setCardBackgroundColor(ContextCompat.getColor(requireContext(),R.color.color_theme_et))
                    ivObesity.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.obesity))
                    ivCancer.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.cancer))
                    ivCholesterol.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.cholesterol))
                    ivDiabetes.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.diabetes))
                    ivStroke.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.stroke))
                    ivLiver.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.liver_white))
                    ivHeart.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.heart))
                    ivKidney.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.kidneys))
                    cvLiver.setCardBackgroundColor(ContextCompat.getColor(requireContext(),R.color.color_theme_primary))
                    cvObesity.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_theme_et))
                    cvCancer.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_theme_et))
                    cvCholesterol.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_theme_et))
                    cvDiabetes.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_theme_et))
                    cvStroke.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_theme_et))
                    cvHeart.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_theme_et))
                    cvKidney.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_theme_et))
                }
            }
            R.id.cv_heart ->{
                healthConcern = "6"
                binding.apply {
                    tvDontHave.setTextColor(ContextCompat.getColor(requireContext(),R.color.black))
                    cvDontHave.setCardBackgroundColor(ContextCompat.getColor(requireContext(),R.color.color_theme_et))
                    ivObesity.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.obesity))
                    ivCancer.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.cancer))
                    ivCholesterol.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.cholesterol))
                    ivDiabetes.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.diabetes))
                    ivStroke.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.stroke))
                    ivLiver.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.liver))
                    ivHeart.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.heart_white))
                    ivKidney.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.kidneys))
                    cvHeart.setCardBackgroundColor(ContextCompat.getColor(requireContext(),R.color.color_theme_primary))
                    cvObesity.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_theme_et))
                    cvCancer.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_theme_et))
                    cvCholesterol.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_theme_et))
                    cvDiabetes.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_theme_et))
                    cvStroke.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_theme_et))
                    cvLiver.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_theme_et))
                    cvKidney.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_theme_et))
                }
            }
            R.id.cv_kidney ->{
                healthConcern = "7"
                binding.apply {
                    tvDontHave.setTextColor(ContextCompat.getColor(requireContext(),R.color.black))
                    cvDontHave.setCardBackgroundColor(ContextCompat.getColor(requireContext(),R.color.color_theme_et))
                    ivObesity.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.obesity))
                    ivCancer.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.cancer))
                    ivCholesterol.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.cholesterol))
                    ivDiabetes.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.diabetes))
                    ivStroke.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.stroke))
                    ivLiver.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.liver))
                    ivHeart.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.heart))
                    ivKidney.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.kidney_white))
                    cvKidney.setCardBackgroundColor(ContextCompat.getColor(requireContext(),R.color.color_theme_primary))
                    cvObesity.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_theme_et))
                    cvCancer.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_theme_et))
                    cvCholesterol.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_theme_et))
                    cvDiabetes.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_theme_et))
                    cvStroke.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_theme_et))
                    cvHeart.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_theme_et))
                    cvLiver.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_theme_et))
                }
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}