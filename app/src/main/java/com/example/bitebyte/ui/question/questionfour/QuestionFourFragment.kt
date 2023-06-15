package com.example.bitebyte.ui.question.questionfour

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
import com.example.bitebyte.databinding.FragmentQuestionFourBinding
import com.example.bitebyte.databinding.FragmentQuestionThreeBinding
import com.example.bitebyte.ui.question.questionthree.QuestionThreeFragmentArgs
import com.example.bitebyte.ui.question.questionthree.QuestionThreeFragmentDirections
import com.google.android.material.snackbar.Snackbar

class QuestionFourFragment : Fragment(), View.OnClickListener {

    private val args: QuestionFourFragmentArgs by navArgs()

    private lateinit var age : String
    private lateinit var gender : String
    private lateinit var weight : String
    private lateinit var height: String
    private lateinit var healthConcern: String
    private lateinit var menuType: String
    private lateinit var activity: String

    private var _binding: FragmentQuestionFourBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuestionFourBinding.inflate(inflater, container, false)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.slide_left)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        menuType = args.menuType
        healthConcern = args.healthConcern
        age = args.age
        weight = args.weight
        height = args.height
        gender = args.gender
        activity = ""

        binding.ibBackButton.setOnClickListener(this)
        binding.btnGenerate.setOnClickListener(this)
        binding.cvSedentaryActivity.setOnClickListener(this)
        binding.cvLightActivity.setOnClickListener(this)
        binding.cvModerateActivity.setOnClickListener(this)
        binding.cvActiveActivity.setOnClickListener(this)
        binding.cvVeryActiveActivity.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.ib_back_button ->{
                findNavController().navigate(QuestionFourFragmentDirections.actionQuestionFourFragmentToQuestionThreeFragment(age, weight, height, gender, healthConcern))
            }
            R.id.btn_generate ->{
                if(emptyField(activity)) {
                    findNavController().navigate(QuestionFourFragmentDirections.actionQuestionFourFragmentToGenerateFragment(age, weight, height, gender, healthConcern, menuType, activity))
                }else{
                    Snackbar.make(binding.root, getString(R.string.fill_all_fields), Snackbar.LENGTH_SHORT).show()
                }
            }
            R.id.cv_sedentary_activity->{
                activity = "1"
                binding.apply {
                    cvSedentaryActivity.setCardBackgroundColor(resources.getColor(R.color.color_theme_primary))
                    cvLightActivity.setCardBackgroundColor(resources.getColor(R.color.color_theme_et))
                    cvModerateActivity.setCardBackgroundColor(resources.getColor(R.color.color_theme_et))
                    cvActiveActivity.setCardBackgroundColor(resources.getColor(R.color.color_theme_et))
                    cvVeryActiveActivity.setCardBackgroundColor(resources.getColor(R.color.color_theme_et))
                    tvSedentary.setTextColor(resources.getColor(R.color.white))
                    tvLight.setTextColor(resources.getColor(R.color.black))
                    tvModerate.setTextColor(resources.getColor(R.color.black))
                    tvActive.setTextColor(resources.getColor(R.color.black))
                    tvVeryActive.setTextColor(resources.getColor(R.color.black))
                    tvSedentaryDesc.setTextColor(resources.getColor(R.color.white))
                    tvLightDesc.setTextColor(resources.getColor(R.color.black))
                    tvModerateDesc.setTextColor(resources.getColor(R.color.black))
                    tvActiveDesc.setTextColor(resources.getColor(R.color.black))
                    tvVeryActiveDesc.setTextColor(resources.getColor(R.color.black))
                }
            }
            R.id.cv_light_activity->{
                activity = "2"
                binding.apply {
                    cvSedentaryActivity.setCardBackgroundColor(resources.getColor(R.color.color_theme_et))
                    cvLightActivity.setCardBackgroundColor(resources.getColor(R.color.color_theme_primary))
                    cvModerateActivity.setCardBackgroundColor(resources.getColor(R.color.color_theme_et))
                    cvActiveActivity.setCardBackgroundColor(resources.getColor(R.color.color_theme_et))
                    cvVeryActiveActivity.setCardBackgroundColor(resources.getColor(R.color.color_theme_et))
                    tvSedentary.setTextColor(resources.getColor(R.color.black))
                    tvLight.setTextColor(resources.getColor(R.color.white))
                    tvModerate.setTextColor(resources.getColor(R.color.black))
                    tvActive.setTextColor(resources.getColor(R.color.black))
                    tvVeryActive.setTextColor(resources.getColor(R.color.black))
                    tvSedentaryDesc.setTextColor(resources.getColor(R.color.black))
                    tvLightDesc.setTextColor(resources.getColor(R.color.white))
                    tvModerateDesc.setTextColor(resources.getColor(R.color.black))
                    tvActiveDesc.setTextColor(resources.getColor(R.color.black))
                    tvVeryActiveDesc.setTextColor(resources.getColor(R.color.black))
                }
            }
            R.id.cv_moderate_activity->{
                activity = "3"
                binding.apply {
                    cvSedentaryActivity.setCardBackgroundColor(resources.getColor(R.color.color_theme_et))
                    cvLightActivity.setCardBackgroundColor(resources.getColor(R.color.color_theme_et))
                    cvModerateActivity.setCardBackgroundColor(resources.getColor(R.color.color_theme_primary))
                    cvActiveActivity.setCardBackgroundColor(resources.getColor(R.color.color_theme_et))
                    cvVeryActiveActivity.setCardBackgroundColor(resources.getColor(R.color.color_theme_et))
                    tvSedentary.setTextColor(resources.getColor(R.color.black))
                    tvLight.setTextColor(resources.getColor(R.color.black))
                    tvModerate.setTextColor(resources.getColor(R.color.white))
                    tvActive.setTextColor(resources.getColor(R.color.black))
                    tvVeryActive.setTextColor(resources.getColor(R.color.black))
                    tvSedentaryDesc.setTextColor(resources.getColor(R.color.black))
                    tvLightDesc.setTextColor(resources.getColor(R.color.black))
                    tvModerateDesc.setTextColor(resources.getColor(R.color.white))
                    tvActiveDesc.setTextColor(resources.getColor(R.color.black))
                    tvVeryActiveDesc.setTextColor(resources.getColor(R.color.black))
                }
            }
            R.id.cv_active_activity->{
                activity = "4"
                binding.apply {
                    cvSedentaryActivity.setCardBackgroundColor(resources.getColor(R.color.color_theme_et))
                    cvLightActivity.setCardBackgroundColor(resources.getColor(R.color.color_theme_et))
                    cvModerateActivity.setCardBackgroundColor(resources.getColor(R.color.color_theme_et))
                    cvActiveActivity.setCardBackgroundColor(resources.getColor(R.color.color_theme_primary))
                    cvVeryActiveActivity.setCardBackgroundColor(resources.getColor(R.color.color_theme_et))
                    tvSedentary.setTextColor(resources.getColor(R.color.black))
                    tvLight.setTextColor(resources.getColor(R.color.black))
                    tvModerate.setTextColor(resources.getColor(R.color.black))
                    tvActive.setTextColor(resources.getColor(R.color.white))
                    tvVeryActive.setTextColor(resources.getColor(R.color.black))
                    tvSedentaryDesc.setTextColor(resources.getColor(R.color.black))
                    tvLightDesc.setTextColor(resources.getColor(R.color.black))
                    tvModerateDesc.setTextColor(resources.getColor(R.color.black))
                    tvActiveDesc.setTextColor(resources.getColor(R.color.white))
                    tvVeryActiveDesc.setTextColor(resources.getColor(R.color.black))
                }
            }
            R.id.cv_very_active_activity->{
                activity = "5"
                binding.apply {
                    cvSedentaryActivity.setCardBackgroundColor(resources.getColor(R.color.color_theme_et))
                    cvLightActivity.setCardBackgroundColor(resources.getColor(R.color.color_theme_et))
                    cvModerateActivity.setCardBackgroundColor(resources.getColor(R.color.color_theme_et))
                    cvActiveActivity.setCardBackgroundColor(resources.getColor(R.color.color_theme_et))
                    cvVeryActiveActivity.setCardBackgroundColor(resources.getColor(R.color.color_theme_primary))
                    tvSedentary.setTextColor(resources.getColor(R.color.black))
                    tvLight.setTextColor(resources.getColor(R.color.black))
                    tvModerate.setTextColor(resources.getColor(R.color.black))
                    tvActive.setTextColor(resources.getColor(R.color.black))
                    tvVeryActive.setTextColor(resources.getColor(R.color.white))
                    tvSedentaryDesc.setTextColor(resources.getColor(R.color.black))
                    tvLightDesc.setTextColor(resources.getColor(R.color.black))
                    tvModerateDesc.setTextColor(resources.getColor(R.color.black))
                    tvActiveDesc.setTextColor(resources.getColor(R.color.black))
                    tvVeryActiveDesc.setTextColor(resources.getColor(R.color.white))
                }
            }
        }
    }

    private fun emptyField(activity: String?) : Boolean{
        return when{
            TextUtils.isEmpty(activity) -> false
            else -> true
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}