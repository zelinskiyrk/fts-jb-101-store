package com.zelinskiyrk.store.guest.routes;

import com.zelinskiyrk.store.base.routes.BaseApiRoutes;

public class GuestApiRoutes {
    public static final String ADMIN = BaseApiRoutes.ADMIN + "/guest";
    public static final String ADMIN_BY_ID = ADMIN + "/{id}";
    public static final String ROOT = BaseApiRoutes.V1 + "/guest";
    public static final String BY_ID = ROOT + "/{id}";
}
