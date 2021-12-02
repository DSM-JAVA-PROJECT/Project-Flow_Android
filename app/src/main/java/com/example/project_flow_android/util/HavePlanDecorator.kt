package com.example.project_flow_android.util

import android.graphics.Color
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.spans.DotSpan

class HavePlanDecorator(dates: Collection<CalendarDay>, finish: Boolean) : DayViewDecorator {
    private val mDates = dates
    private val isFinish = finish

    override fun shouldDecorate(day: CalendarDay?): Boolean {
        return mDates.contains(day)
    }

    override fun decorate(view: DayViewFacade?) {
        if(isFinish)
            view?.addSpan(DotSpan(5F, Color.parseColor("#FF7979")))
        else
            view?.addSpan(DotSpan(5F, Color.parseColor("#1F43C2")))

    }
}