package co.kr.dgsw.searchvoca.controller;

import co.kr.dgsw.searchvoca.service.DictionaryService;
import co.kr.dgsw.searchvoca.service.impl.DictionaryServiceImpl;
import co.kr.dgsw.searchvoca.service.PapagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchWordController {
    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private PapagoService papagoService;

    @GetMapping("/{word}")
    public String getLanguage(@PathVariable("word") String word) {
        String langCode = papagoService.postLanguage(word);

        if (langCode.equals("ko"))
            return dictionaryService.getSearchResult(word);
        else
            return papagoService.postTranslate(langCode, "ko", word);
    }
}
