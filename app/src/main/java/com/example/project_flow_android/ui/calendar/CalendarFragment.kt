package com.example.project_flow_android.ui.calendar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.project_flow_android.R
import com.example.project_flow_android.feature.GitInfoResponse
import kotlinx.android.synthetic.main.fragment_calendar.*

class
CalendarFragment : Fragment() {

    lateinit var gitRV: GitRVAdapter
    val list = mutableListOf<GitInfoResponse>()

    private fun initRecyclerView() {
        gitRV = GitRVAdapter()
        recyclerView.adapter = gitRV

        list.run {
            add(GitInfoResponse("#50 ", "이슈 넘버 수정", "By. 이은별"))
            add(GitInfoResponse("#49", "변수 이름 수정", "By. 이은별"))
            add(GitInfoResponse("#48", "인터페이스 생성", "By. 이은별"))
            add(GitInfoResponse("#47", "인증번호 에러", "By. 이은별"))
            add(GitInfoResponse("#44", "프로젝트 생성 오류 수정", "By. 이은별"))
            add(GitInfoResponse("#43", "회원가입 로직 수정", "By. 안병헌"))
            add(GitInfoResponse("#42", "이미지 전송 오류 ", "By. 안병런"))
        }

        gitRV.list = list
        gitRV.notifyDataSetChanged()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        return inflater.inflate(R.layout.fragment_calendar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

}