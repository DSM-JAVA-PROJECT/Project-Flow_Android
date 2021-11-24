package com.example.project_flow_android.ui.flow

import com.example.project_flow_android.R
import com.example.project_flow_android.base.BaseFragment
import com.example.project_flow_android.data.remote.toRealPath
import com.example.project_flow_android.databinding.FragmentAddProjectBinding
import com.example.project_flow_android.ui.main.MainActivity
import com.example.project_flow_android.viewmodel.flow.AddProjectViewModel
import gun0912.tedimagepicker.builder.TedRxImagePicker
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddProjectFragment :
    BaseFragment<FragmentAddProjectBinding>(R.layout.fragment_add_project) {

    override val vm: AddProjectViewModel by viewModel()

    private fun getImage(){
        TedRxImagePicker.with(requireContext())
            .start()
            .subscribe({ uri ->
                val imagePath = uri.toRealPath(requireContext())
                vm.imagePath = imagePath
                binding.getImg.setImageURI(uri)
            }, Throwable::printStackTrace)

    }

    override fun observeEvent() {
        vm.run {
            successAddProject.observe(viewLifecycleOwner, {
                (activity as MainActivity).backFragment()
            })
            binding.goProjectImg.setOnClickListener{
                (activity as MainActivity).backFragment()
            }
            binding.getImg.setOnClickListener{
                getImage()
            }
        }
    }
}