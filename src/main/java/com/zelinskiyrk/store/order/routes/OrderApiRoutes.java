package com.zelinskiyrk.store.order.routes;

import com.zelinskiyrk.store.base.routes.BaseApiRoutes;

public class OrderApiRoutes {
    public static final String ADMIN = BaseApiRoutes.ADMIN + "/order";
    public static final String ADMIN_BY_ID = ADMIN + "/{id}";
    public static final String ROOT = BaseApiRoutes.V1 + "/order";
    public static final String BY_ORDER_NUMBER = ROOT + "/{orderNumber}";
}
