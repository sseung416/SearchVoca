package co.kr.searchvoca.domain.usecase.word

import co.kr.searchvoca.domain.model.UiState
import co.kr.searchvoca.domain.model.Word
import co.kr.searchvoca.domain.repository.WordRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class DeleteWordUseCase(private val repo: WordRepository) {
    operator fun invoke(word: Word) = flow<UiState<Unit>> {
        val res = repo.deleteWord(word)
        emit(UiState.Success(res))
    }.catch {
        emit(UiState.Failure(it))
    }

    operator fun invoke(ids: List<Int>) = flow<UiState<Unit>> {
        val res = repo.deleteWords(ids)
        emit(UiState.Success(res))
    }.catch {
        emit(UiState.Failure(it))
    }
}