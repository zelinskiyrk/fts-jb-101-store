package com.zelinskiyrk.store.photo.routes;

import com.zelinskiyrk.store.base.routes.BaseApiRoutes;

public class PhotoApiRoutes {
    public static final String ADMIN = BaseApiRoutes.ADMIN + "/photo";
    public static final String ADMIN_BY_ID = ADMIN + "/{id}";
    public static final String ROOT = BaseApiRoutes.V1 + "/photo";
    public static final String BY_ID = ROOT + "/{id}";
    public static final String DOWNLOAD = "/photo/{id}";
}
