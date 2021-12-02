package com.example.project_flow_android.ui.chat.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project_flow_android.R
import com.example.project_flow_android.ui.chat.ChatActivity
import com.example.project_flow_android.ui.chat.ScheduleRVAdapter
import com.example.project_flow_android.util.HavePlanDecorator
import com.example.project_flow_android.viewmodel.chat.ChatViewModel
import com.prolificinteractive.materialcalendarview.CalendarDay
import kotlinx.android.synthetic.main.fragment_schedule.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*
import java.util.Collections.singleton

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
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val year = yearFormat.format(currentTime)
        val month = monthFormat.format(currentTime)

        val layoutManager = LinearLayoutManager(requireContext())
        schedule_rv.layoutManager = layoutManager

        viewModel.getMonthPlan(year, month)
        schedule_prev_btn.setOnClickListener {
            (activity as ChatActivity).popBackStack(ScheduleFragment())
        }

        viewModel.monthPlanLiveData.observe(viewLifecycleOwner, { it ->
            it.getContentIfNotHandled()?.let {
                val data = it.monthPlans
                for (i in 0 until data.size) {
                    val startDate = data[i].startDate
                    val endDate = data[i].endDate

                    schedule_cv.addDecorator(HavePlanDecorator(singleton(CalendarDay.from(dateFormat.parse(
                        startDate))), data[i].isFinish))
                }
            }
        })
        viewModel.datePlanLiveData.observe(viewLifecycleOwner, { it ->
            it.getContentIfNotHandled()?.let {
                val adapter = ScheduleRVAdapter(it)
                schedule_rv.adapter = adapter
            }
        })

        schedule_cv.setOnDateChangedListener { widget, date, selected ->
            viewModel.getDatePlan(dateFormat.format(date.date).toString())
        }
    }
}