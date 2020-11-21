package com.zelinskiyrk.store.city.routes;

import com.zelinskiyrk.store.base.routes.BaseApiRoutes;

public class CityApiRoutes {
    public static final String ADMIN = BaseApiRoutes.ADMIN + "/city";
    public static final String ADMIN_BY_ID = ADMIN + "/{id}";
    public static final String ROOT = BaseApiRoutes.V1 + "/city";
    public static final String BY_ID = ROOT + "/{id}";
}
