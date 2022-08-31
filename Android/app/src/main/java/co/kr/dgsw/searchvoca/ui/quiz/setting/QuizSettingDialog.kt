package co.kr.dgsw.searchvoca.ui.quiz.setting

import android.os.Bundle
import android.view.View
import co.kr.dgsw.searchvoca.R
import co.kr.dgsw.searchvoca.ui.dialog.BaseBottomSheetDialog
import co.kr.dgsw.searchvoca.databinding.DialogQuizSettingBinding
import co.kr.dgsw.searchvoca.ui.bind
import co.kr.dgsw.searchvoca.ui.quiz.TestWord
import co.kr.dgsw.searchvoca.ui.launchAndRepeatWithViewLifecycle
import co.kr.dgsw.searchvoca.ui.quiz.setting.QuizSettingNavigationAction.*
import co.kr.dgsw.searchvoca.ui.dialog.vocabulary.VocabularyBottomSheetDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class QuizSettingDialog(
    private val onClickConfirm: (Array<TestWord>) -> Unit
) : BaseBottomSheetDialog<DialogQuizSettingBinding>(R.layout.dialog_quiz_setting) {

    private val viewModel by viewModel<QuizSettingViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bind {
            vm = viewModel
        }

        observeState()
    }

    private fun observeState() {
        launchAndRepeatWithViewLifecycle {
            viewModel.navigationAction.collect { action ->
                when (action) {
                    DismissDialogAction ->
                        dismiss()

                    ShowVocabularyListAction ->
                        VocabularyBottomSheetDialog({
                            viewModel.selectVocabulary.value = it
                        }).show(parentFragmentManager, "")

                    is NavigateToQuizAction ->
                        onClickConfirm.invoke(action.testWords)
                }
            }
        }
    }
}