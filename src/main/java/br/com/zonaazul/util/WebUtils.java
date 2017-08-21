package br.com.zonaazul.util;

public class WebUtils {

    public static String getURLRest() {
    	AppPropertiesService app = new AppPropertiesService();
    	app.initialize();
    	return app.getPropertyString("service.default.url");
    }

    public static String getURLRest(String service) {
        return WebUtils.getURLRest() + "/zonaazul/" + service;
    }

}
