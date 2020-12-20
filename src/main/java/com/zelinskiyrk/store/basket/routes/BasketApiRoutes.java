package com.zelinskiyrk.store.basket.routes;

import com.zelinskiyrk.store.base.routes.BaseApiRoutes;

public class BasketApiRoutes {
    public static final String ROOT = BaseApiRoutes.V1 + "/basket";
    public static final String DEL = ROOT + "/delete";
    public static final String INC = ROOT + "/increase";
    public static final String DEC = ROOT + "/decrease";
}
