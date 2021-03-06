package co.kr.dgsw.searchvoca.viewmodel.dialog

import co.kr.dgsw.searchvoca.base.BaseViewModel
import co.kr.dgsw.searchvoca.datasource.model.dto.Word
import co.kr.dgsw.searchvoca.datasource.model.repository.WordRepository
import co.kr.dgsw.searchvoca.widget.coroutine.DispatcherProviderImpl
import co.kr.dgsw.searchvoca.widget.livedata.SingleLiveEvent
import kotlinx.coroutines.withContext

// 단어를 수정하거나 삭제할 때 사용하는 viewModel
class WordBottomSheetViewModel(
    dispatcherProvider: DispatcherProviderImpl,
    private val wordRepository: WordRepository
) : BaseViewModel(dispatcherProvider) {
    val deleteEvent = SingleLiveEvent<Unit>()

    fun deleteWord(word: Word) = onIO {
        wordRepository.delete(word)
        withContext(main){ deleteEvent.call() }
    }
}