package com.example.project_flow_android.ui.chat.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.project_flow_android.R
import kotlinx.android.synthetic.main.fragment_invite.*

class InviteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_invite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        invite_prev_iv.setOnClickListener{
            val fragmentManager = requireActivity().supportFragmentManager
            fragmentManager.beginTransaction().remove(ScheduleFragment()).commit()
            fragmentManager.popBackStack()
        }
    }
}