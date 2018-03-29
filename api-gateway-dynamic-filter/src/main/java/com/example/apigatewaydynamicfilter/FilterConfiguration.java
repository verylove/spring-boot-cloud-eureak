package com.example.apigatewaydynamicfilter;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * spring-boot-cloud-eureak
 * com.example.apigatewaydynamicfilter
 *
 * @author happyonion.yt
 * @date Create at $date$ $time$
 */
@ConfigurationProperties("zuul.filter")
public class FilterConfiguration {

    private String root;
    private Integer interval;

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }
}
