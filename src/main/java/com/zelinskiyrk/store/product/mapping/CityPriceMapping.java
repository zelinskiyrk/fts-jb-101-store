package com.zelinskiyrk.store.product.mapping;

import com.zelinskiyrk.store.base.mapping.BaseMapping;
import com.zelinskiyrk.store.product.model.CityPriceDoc;
import com.zelinskiyrk.store.product.model.CityPriceModel;
import lombok.Getter;

@Getter
public class CityPriceMapping {

    public static class RequestMapping extends BaseMapping<CityPriceModel, CityPriceDoc> {

        @Override
        public CityPriceDoc convert(CityPriceModel cityPriceModel) {
            return CityPriceDoc.builder()
                    .cityId(cityPriceModel.getCityId())
                    .price(cityPriceModel.getPrice())
                    .build();
        }

        @Override
        public CityPriceModel unmapping(CityPriceDoc cityPriceDoc) {
            return CityPriceModel.builder()
                    .cityId(cityPriceDoc.getCityId())
                    .price(cityPriceDoc.getPrice())
                    .build();
        }
    }

    private final RequestMapping request = new RequestMapping();

    public static CityPriceMapping getInstance() {
        return new CityPriceMapping();
    }
}
