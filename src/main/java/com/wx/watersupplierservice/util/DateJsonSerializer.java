package com.wx.watersupplierservice.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.IOException;
import java.util.Date;

/**
 *  @author LS
 *  @version 1.0
 *  @date 2019/3/25
 **/
public class DateJsonSerializer extends JsonSerializer<Date> {

    @Override
    public void serialize(Date o, JsonGenerator j, SerializerProvider s) throws IOException, JsonProcessingException {
        if(o == null) {
            j.writeNull();
        } else {

            j.writeString(DateFormatUtils.format(o,DateUtils.DATE_FORMAT_LONG));
        }
    }
}
