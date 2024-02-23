package com.example.hw25.presentation.screen.bottomSearch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hw25.databinding.FragmentBottomSearchDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment



class BottomSearchFragment : BottomSheetDialogFragment() {

    interface LocationSearchListener {
        fun onLocationSearched(locationName: String)
    }

    private var _binding: FragmentBottomSearchDialogBinding? = null
    private val binding get() = _binding!!

    private var locationSearchListener: LocationSearchListener? = null

    fun setLocationSearchListener(listener: LocationSearchListener) {
        locationSearchListener = listener
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentBottomSearchDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnTakeNewPicture.setOnClickListener {
            locationSearchListener?.onLocationSearched(binding.etSearch.text.toString())
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}