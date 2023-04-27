package com.example.yemekuygulamasi.ui.fragment

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.yemekuygulamasi.R
import com.example.yemekuygulamasi.databinding.FragmentAnimationBinding


class AnimationFragment : Fragment() {
    private lateinit var binding:FragmentAnimationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentAnimationBinding.inflate(inflater,container,false)
        val timer = object: CountDownTimer(1700, 1000) {
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
              //  Navigation.findNavController(binding.animationView).navigate(R.id.toAnasayfaFromAnim)

            }
        }
        timer.start()
        return binding.root
    }


}