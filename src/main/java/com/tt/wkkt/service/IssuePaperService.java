package com.tt.wkkt.service;

import com.tt.wkkt.common.Result;
import com.tt.wkkt.model.IssuePaper;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/
public interface IssuePaperService {
    Result issuePaper(IssuePaper issuePaper);

}
