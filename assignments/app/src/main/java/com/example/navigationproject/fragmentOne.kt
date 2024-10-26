package com.example.navigationproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.navigationproject.databinding.FragmentOneBinding

class fragmentOne : Fragment() {

    private var _binding: FragmentOneBinding? = null
    private val binding get() = _binding!!

    //private var imageSent: String? = null
    //private var message: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button1.setOnClickListener {
            val action: fragmentOneDirections.ActionFragmentOneToFragmentTwo = fragmentOneDirections.actionFragmentOneToFragmentTwo()
            action.message = getString(R.string.firstImageString)
            action.imageSent = R.drawable.android_image_1
            Navigation.findNavController(it).navigate(action)
        }

        binding.button2.setOnClickListener {
            val action: fragmentOneDirections.ActionFragmentOneToFragmentTwo = fragmentOneDirections.actionFragmentOneToFragmentTwo()
            action.message = getString(R.string.secondImageString)
            action.imageSent = R.drawable.android_image_2
            Navigation.findNavController(it).navigate(action)
        }

        binding.button3.setOnClickListener {
            val action: fragmentOneDirections.ActionFragmentOneToFragmentTwo = fragmentOneDirections.actionFragmentOneToFragmentTwo()
            action.message = getString(R.string.thirdImageString)
            action.imageSent = R.drawable.android_image_3
            Navigation.findNavController(it).navigate(action)
        }

         fun onDestroy() {
            super.onDestroy()
            _binding = null
        }
            }
    }
