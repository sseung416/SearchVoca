package co.kr.dgsw.searchvoca.datasource.model.dao

import androidx.room.Dao
import androidx.room.Query
import co.kr.dgsw.searchvoca.base.BaseDao
import co.kr.dgsw.searchvoca.datasource.model.dto.Vocabulary
import co.kr.dgsw.searchvoca.datasource.model.dto.VocabularyName

@Dao
interface VocabularyDao : BaseDao<Vocabulary> {
    @Query("SELECT * FROM vocabulary")
    suspend fun getVocabularies(): List<Vocabulary>

    @Query("SELECT id, name FROM vocabulary")
    suspend fun getVocabularyNames(): List<VocabularyName>
}