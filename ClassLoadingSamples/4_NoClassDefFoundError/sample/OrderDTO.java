package com.taobao.trade.platform.api.creating;

import com.taobao.trade.platform.api.creating.validator.attributes.AttributesValidatorFactory;
import com.taobao.trade.platform.api.creating.validator.attributes.IAttributesValidator;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkState;
import static com.google.common.collect.Maps.newHashMap;
import static java.util.Collections.emptyList;

public class OrderDTO {

    public List<OrderLineDTO> orderLineDTOs;
    public PredefinedOrderPropsDTO predefinedOrderPropsDTO;
    public String transportationMethod;
    public int codRank;
    public String outOrderId;
    public PostageInsuranceDTO postageInsurance;
    public String buyerMessage;
    public boolean anonymous;
    public String invoiceTitle;
    public GroupBuyInfoDTO groupBuyInfoDTO;
    public IVerticalMarketExtDTO verticalMarketExtDTO;
    public List<String> shopPromotionIds = emptyList();
    public long predefinedPostageTemplateId;
    private final Map<String, String> attributes = newHashMap();
    private final Map<String, String> bizAttributes = newHashMap();
    private final static IAttributesValidator attributesValidator = AttributesValidatorFactory.getOrderAttributesValidator();

    public static final String TRANSPORTATION_METHOD_SURFACE_MAIL = "SURFACE_MAIL";
    public static final String TRANSPORTATION_METHOD_EXPRESS = "EXPRESS";
    public static final String TRANSPORTATION_METHOD_EMS = "EMS";
    
    public void checkValidity() {
        checkState(groupBuyInfoDTO == null ||
                   (orderLineDTOs.size() == 1 &&
                    groupBuyInfoDTO.itemId == orderLineDTOs.get(0).itemId),"groupBuy parameter error");

    }

    public void addAttributes(@NotNull String key, @NotNull String value) {
        attributesValidator.validateAttribute(key, value);
        attributes.put(key, value);
    }

    public void addBizAttributes(@NotNull String key, @NotNull String value) {
        attributesValidator.validateBizAttribute(key, value);
        bizAttributes.put(key, value);
    }

    public Map<String, String> getAttributes() {
        return Collections.unmodifiableMap(attributes);
    }

    public Map<String, String> getBizAttributes() {
        return Collections.unmodifiableMap(bizAttributes);
    }

}

