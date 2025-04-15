package com.xujie.client.dto.converts;

import cn.hutool.core.date.DateUtil;
import com.xujie.client.core.domain.XOrder;
import com.xujie.client.dto.XOrderDto;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;

public class XOrderConvert {
    public static XOrder toXOrder(XOrderDto.XOrderCreateResponse xOrderCreateResponse) {
        XOrder xOrder = new XOrder();
        BeanUtils.copyProperties(xOrderCreateResponse, xOrder);
        if (ObjectUtils.isNotEmpty(xOrderCreateResponse.getCreateTime())) {
            xOrder.setCreateTime(DateUtil.parseDate(xOrderCreateResponse.getCreateTime()));
        }
        if (ObjectUtils.isNotEmpty(xOrderCreateResponse.getExpireTime())) {
            xOrder.setExpireTime(DateUtil.parseDate(xOrderCreateResponse.getExpireTime()));
        }
        if (ObjectUtils.isNotEmpty(xOrderCreateResponse.getPayTime())) {
            xOrder.setPayTime(DateUtil.parseDate(xOrderCreateResponse.getPayTime()));
        }
        return xOrder;
    }

    public static XOrder toXOrder(XOrderDto.XOrderQueryResponse orderQueryResponse) {
        return null;
    }
}
