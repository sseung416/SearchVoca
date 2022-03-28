package co.kr.dgsw.searchvoca.viewmodel.activity

import android.util.Log
import androidx.lifecycle.viewModelScope
import co.kr.dgsw.searchvoca.base.BaseViewModel
import co.kr.dgsw.searchvoca.repository.remote.repository.SearchRepository
import kotlinx.coroutines.launch

class SearchResultViewModel(
    private val searchRepository: SearchRepository
) : BaseViewModel() {
    fun getSearchResult(keyword: String) {
        viewModelScope.launch {
            Log.e("TAG", "getSearchResult: ${searchRepository.getSearchData(keyword).items[0].description}", )
        }
    }
}