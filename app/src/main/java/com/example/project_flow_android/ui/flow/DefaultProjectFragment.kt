package com.example.project_flow_android.ui.flow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.project_flow_android.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class DefaultProjectFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_default_project, container, false)
    }

     fun observeEvent() {
        TODO("Not yet implemented")
    }

}