package com.taobao.trade.platform.api.creating.validator.attributes;

import com.google.common.collect.ImmutableSet;
import com.taobao.tc.domain.dataobject.BizOrderDO;

import java.util.Set;

/**
 * @author caishuang
 * @date 11-3-21
 */
public class OrderAttributesValidator extends AbstractAttributesValidator {

    OrderAttributesValidator() {}

    public static final Set<String> ALLOW_ATTRIBUTE_KEY = ImmutableSet.of(
            BizOrderDO.ATTRIBUTE_ORDER_FROM,
            BizOrderDO.ATTRIBUTE_TOP_APP_KEY,
            BizOrderDO.ATTRIBUTE_TOP_TRADE_SOURCE,
            BizOrderDO.ATTRIBUTE_COOPERATOR_NAME,
            BizOrderDO.ATTRIBUTE_FROM_CART,
            BizOrderDO.ATTRIBUTE_OPENSEARCH_PROMOTE_DESC
    );

    public static final Set<String> ALLOW_BIZ_ATTRIBUTE_KEY = ImmutableSet.of();

    @Override
    protected Set<String> getAllowBizAttributeKey() {
        return ALLOW_BIZ_ATTRIBUTE_KEY;
    }

    @Override
    protected Set<String> getAllowAttributeKey() {
        return ALLOW_ATTRIBUTE_KEY;
    }
}
