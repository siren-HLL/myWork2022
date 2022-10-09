package com.ruoyi.common.utils.time;

import lombok.Data;

import java.util.Date;

/**
 * @Author Siren 2022/9/26 11:21
 * 获得指定年，指定周的开始结束日期；
 */
@Data
public class DateBeginEndBo {
    private Date startDate;
    private String startDateStr;
    private Date endDate;
    private String endDateStr;
}
