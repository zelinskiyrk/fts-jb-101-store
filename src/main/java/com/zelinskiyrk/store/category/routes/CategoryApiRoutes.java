package com.zelinskiyrk.store.category.routes;

import com.zelinskiyrk.store.base.routes.BaseApiRoutes;

public class CategoryApiRoutes {
    public static final String ADMIN = BaseApiRoutes.ADMIN + "/category";
    public static final String ADMIN_BY_ID = ADMIN + "/{id}";
    public static final String ROOT = BaseApiRoutes.V1 + "/category";
    public static final String BY_ID = ROOT + "/{id}";
}
