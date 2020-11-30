package com.zelinskiyrk.store.product.routes;

import com.zelinskiyrk.store.base.routes.BaseApiRoutes;

public class ProductApiRoutes {
    public static final String ADMIN = BaseApiRoutes.ADMIN + "/product";
    public static final String ADMIN_BY_ID = ADMIN + "/{id}";
    public static final String ROOT = BaseApiRoutes.V1 + "/product";
    public static final String BY_ID = ROOT + "/{id}";
}
