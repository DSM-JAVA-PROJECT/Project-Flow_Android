package com.example.project_flow_android.feature

data class GetGitProjectIssue(val list : ArrayList<GetGitIssue>){
    data class GetGitIssue(
        val number : Int,
        val title : String,
        val user : List<GetUserInfo>
    ){
        data class GetUserInfo(
            val login : String
        )
    }
}
