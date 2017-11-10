package com.mrdios.billiegen.common.fmk;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 扩展spring的FreemarkerView，加上base属性。
 */
public class RichFreeMarkerView extends FreeMarkerView implements EnvironmentAware {
    /**
     * 部署路径属性名称
     */
    private static final String CONTEXT_PATH = "base";

    /**
     * 后台管理根路径
     */
    private static final String BACK_ROOT = "backRoot";

    private String backRoot;

    /**
     * 在model中增加部署路径base，方便处理部署路径问题。
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    protected void exposeHelpers(Map model, HttpServletRequest request)
            throws Exception {
        super.exposeHelpers(model, request);
        model.put(CONTEXT_PATH, request.getContextPath());
        model.put(BACK_ROOT, backRoot);
    }

    @Override
    public void setEnvironment(Environment environment) {
        backRoot = environment.getProperty("billie.back.path");
    }
}