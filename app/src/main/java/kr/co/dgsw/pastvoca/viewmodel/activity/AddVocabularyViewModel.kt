package kr.co.dgsw.pastvoca.viewmodel.activity

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kr.co.dgsw.pastvoca.base.BaseViewModel
import kr.co.dgsw.pastvoca.repository.model.dto.Vocabulary
import kr.co.dgsw.pastvoca.repository.model.repository.VocabularyRepository
import kr.co.dgsw.pastvoca.view.data.LayoutAdd

class AddVocabularyViewModel(
    private val vocabularyRepository: VocabularyRepository
) : BaseViewModel() {
    val etHint = LayoutAdd("그룹명 (필수)", "설명", "언어 지정")

    fun insertVocabulary(vocabulary: Vocabulary) {
        viewModelScope.launch {
            vocabularyRepository.insert(vocabulary)
        }
    }
}