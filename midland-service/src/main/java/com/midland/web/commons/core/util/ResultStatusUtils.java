package com.midland.web.commons.core.util;

/**
 * @author yek
 * @Title: ConstantStatusUtils.java
 * @Package com.huixin.wks.web.commons
 * @Description: TODO(用一句话描述该文件做什么)
 * @date 2017年3月30日 下午2:03:20
 */
public class ResultStatusUtils {

    /**
     * 服务器已成功处理了请求。通常，这表示服务器提供了请求的网页。
     */
    public final static int STATUS_CODE_200 = 200;

    /**
     * 请求成功且服务器已创建了新的资源。
     */
    public final static int STATUS_CODE_201 = 201;

    /**
     * 服务器已接受了请求，但尚未对其进行处理。
     */
    public final static int STATUS_CODE_202 = 202;

    /**
     * 服务器已成功处理了请求，但返回了可能来自另一来源的信息。
     */
    public final static int STATUS_CODE_203 = 203;

    /**
     * 服务器成功处理了请求，但未返回任何内容。
     */
    public final static int STATUS_CODE_204 = 204;

    /**
     * 服务器不理解请求的语法。
     */
    public final static int STATUS_CODE_400 = 400;

    /**
     * 服务器找不到请求的网页。
     */
    public final static int STATUS_CODE_404 = 404;

    /**
     * 权限验证失败。
     */
    public final static int STATUS_CODE_303 = 303;

    /**
     * 服务器遇到错误，无法完成请求。
     */
    public final static int STATUS_CODE_500 = 500;
}
