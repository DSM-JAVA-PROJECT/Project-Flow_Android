package com.example.project_flow_android.ui.calendar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.project_flow_android.R
import com.example.project_flow_android.base.BaseFragment
import com.example.project_flow_android.databinding.FragmentCalendarBinding
import com.example.project_flow_android.viewmodel.calendar.CalendarViewModel
import com.example.project_flow_android.viewmodel.flow.FlowViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CalendarFragment : BaseFragment<FragmentCalendarBinding>(R.layout.fragment_calendar){

    override val vm: CalendarViewModel by viewModel()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeEvent()
    }

    override fun observeEvent() {
        vm.run {
            getGitInfo()
        }
    }

}