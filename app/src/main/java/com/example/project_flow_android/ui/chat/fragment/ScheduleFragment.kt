package com.example.project_flow_android.ui.chat.fragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project_flow_android.R
import com.example.project_flow_android.network.SocketApplication
import com.example.project_flow_android.ui.chat.ChatActivity
import com.example.project_flow_android.ui.chat.ScheduleRVAdapter
import com.example.project_flow_android.util.DialogUtil
import com.example.project_flow_android.util.HavePlanDecorator
import com.example.project_flow_android.util.SaturdayDecorator
import com.example.project_flow_android.util.SundayDecorator
import com.example.project_flow_android.viewmodel.chat.ChatViewModel
import com.prolificinteractive.materialcalendarview.CalendarDay
import kotlinx.android.synthetic.main.fragment_schedule.*
import kotlinx.android.synthetic.main.schedule_item.*
import kotlinx.android.synthetic.main.schedule_item.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*
import java.util.Collections.singleton

class ScheduleFragment : Fragment() {

    private lateinit var adapter : ScheduleRVAdapter
    private val viewModel : ChatViewModel by viewModel()
    private val socket = SocketApplication.getSocket()
    private var planId : String? = null
    private var curView: View? = null
    private var mPosition: Int? = null

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
        val dialogUtil = DialogUtil(requireActivity())
        schedule_rv.layoutManager = layoutManager

        schedule_cv.apply {
            addDecorator(SaturdayDecorator())
            addDecorator(SundayDecorator())
        }

        viewModel.getMonthPlan(year, month)
        schedule_prev_btn.setOnClickListener {
            (activity as ChatActivity).popBackStack(ScheduleFragment())
        }
        schedule_delete_btn.setOnClickListener{
            planId?.let {
                viewModel.deletePlan(planId!!)
            }
        }
        schedule_exclude_btn.setOnClickListener{
            planId?.let {
                viewModel.resignPlan(planId!!, socket.getChatRoomId())
            }
        }

        viewModel.monthPlanLiveData.observe(viewLifecycleOwner, { it ->
            it.getContentIfNotHandled()?.let {
                val data = it.monthPlans
                for (i in 0 until data.size) {
                    val startDate = data[i].startDate
                    val endDate = data[i].endDate

                    schedule_cv.addDecorator(HavePlanDecorator(singleton(CalendarDay.from(dateFormat.parse(
                        startDate))), false))
                    schedule_cv.addDecorator(HavePlanDecorator(singleton(CalendarDay.from(dateFormat.parse(
                        endDate))), true))
                }
            }
        })
        viewModel.datePlanLiveData.observe(viewLifecycleOwner, { it ->
            it.getContentIfNotHandled()?.let {
                adapter = ScheduleRVAdapter(it)
                schedule_rv.adapter = adapter
                itemClick(adapter)
            }
        })
        viewModel.deletePlanLiveData.observe(viewLifecycleOwner, {
            when(it.peekContent()){
                200, 204 ->{
                    it.getContentIfNotHandled()?.let {
                        dialogUtil.cookieBarBuilder(
                            R.string.delete_plan,
                            "일정이 삭제되었습니다.",
                            null,
                            R.color.color_flow
                        )
                    }
                }
            }
        })
        viewModel.resignPlanLiveData.observe(viewLifecycleOwner, {
            when(it.peekContent()){
                200, 204 ->{
                    it.getContentIfNotHandled()?.let {
                        dialogUtil.cookieBarBuilder(
                            R.string.resign_plan,
                            "일정이 제외되었습니다.",
                            null,
                            R.color.color_flow
                        )
                    }
                    adapter.notifyItemRemoved(mPosition!!)
                }
            }
        })

        schedule_cv.setOnDateChangedListener { widget, date, selected ->
            viewModel.getDatePlan(dateFormat.format(date.date).toString())
        }
    }

    private fun itemClick(adapter: ScheduleRVAdapter){
        adapter.setOnItemClickListener(object: ScheduleRVAdapter.OnItemClickListener{
            override fun onItemClick(v: View, position: Int) {
                if (curView == null) {
                    curView = v
                    v.schedule_item.setBackgroundColor(requireActivity().getColor(R.color.color_flow))
                    v.schedule_plan_tv.setTextColor(Color.WHITE)
                } else {
                    curView!!.schedule_item.setBackgroundColor(Color.WHITE)
                    curView!!.schedule_plan_tv.setTextColor(Color.BLACK)
                    curView = v
                    v.schedule_item.setBackgroundColor(requireActivity().getColor(R.color.color_flow))
                    v.schedule_plan_tv.setTextColor(Color.WHITE)
                }
                val plan = viewModel.datePlanLiveData.value!!.peekContent().planDetails
                planId = plan[position].id
                mPosition = position
            }
        })
    }
}