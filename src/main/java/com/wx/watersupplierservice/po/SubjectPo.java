package com.wx.watersupplierservice.po;

import com.xdf.pscommon.annotation.alias.ID;
import com.xdf.pscommon.annotation.alias.Identity;
import com.xdf.pscommon.annotation.alias.Table;
import lombok.Data;

@Data
@Table(name = "t_subject")
public class SubjectPo {

    @ID
    @Identity
    private Long id;

    private String subjectCode;

    private String subjectName;

}
