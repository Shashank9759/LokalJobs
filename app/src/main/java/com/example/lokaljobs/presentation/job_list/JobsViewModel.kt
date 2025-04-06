package com.example.lokaljobs.presentation.job_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.lokaljobs.domain.use_case.GetJobsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JobsViewModel @Inject constructor(
    getJobsUseCase: GetJobsUseCase
) : ViewModel() {
    val jobs = getJobsUseCase().cachedIn(viewModelScope)


    fun getjobById(id:Int){
        viewModelScope.launch{

        }
    }
}
