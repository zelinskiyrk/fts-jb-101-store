package com.zelinskiyrk.store.street.routes;

import com.zelinskiyrk.store.base.routes.BaseApiRoutes;

public class StreetApiRoutes {
    public static final String ADMIN = BaseApiRoutes.ADMIN + "/street";
    public static final String ADMIN_BY_ID = ADMIN + "/{id}";
    public static final String ROOT = BaseApiRoutes.V1 + "/street";
    public static final String BY_ID = ROOT + "/{id}";
}
