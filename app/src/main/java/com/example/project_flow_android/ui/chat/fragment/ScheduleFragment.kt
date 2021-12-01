package com.example.project_flow_android.ui.chat.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.applikeysolutions.cosmocalendar.model.Day
import com.example.project_flow_android.R
import com.example.project_flow_android.ui.chat.ChatActivity
import com.example.project_flow_android.viewmodel.chat.ChatViewModel
import kotlinx.android.synthetic.main.fragment_chat.*
import kotlinx.android.synthetic.main.fragment_schedule.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.net.Socket
import java.text.SimpleDateFormat
import java.util.*

class ScheduleFragment : Fragment() {

    private val viewModel : ChatViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentTime = Calendar.getInstance().time
        val yearFormat = SimpleDateFormat("yyyy", Locale.getDefault())
        val monthFormat = SimpleDateFormat("MM", Locale.getDefault())
        val year = yearFormat.format(currentTime)
        val month = monthFormat.format(currentTime)

        viewModel.getMonthPlan(year, month)
        schedule_prev_btn.setOnClickListener{
            (activity as ChatActivity).popBackStack(ScheduleFragment())
        }

        viewModel.monthPlanLiveData.observe(viewLifecycleOwner, { it ->
            it.getContentIfNotHandled()?.let {
//                schedule_cv.
//                schedule_cv.selectionManager.toggleDay(it.monthPlans[1].endDate)
            }
        })
    }
}