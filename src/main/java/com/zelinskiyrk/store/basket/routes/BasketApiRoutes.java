package com.zelinskiyrk.store.basket.routes;

import com.zelinskiyrk.store.base.routes.BaseApiRoutes;

public class BasketApiRoutes {
    public static final String ADMIN = BaseApiRoutes.ADMIN + "/basket";
    public static final String ADMIN_BY_ID = ADMIN + "/{id}";
    public static final String ROOT = BaseApiRoutes.V1 + "/basket";
    public static final String BY_ID = ROOT + "/{id}";
}
