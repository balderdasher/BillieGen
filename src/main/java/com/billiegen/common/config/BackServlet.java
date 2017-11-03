//package com.billiegen.common.config;
//
//import com.Applications;
//import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
//
//import javax.servlet.ServletContext;
//
///**
// * @author CodePorter
// * @date 2017-10-31
// */
//public class BackServlet extends AbstractAnnotationConfigDispatcherServletInitializer {
//
//    @Override
//    protected Class<?>[] getRootConfigClasses() {
////        return new Class<?>[]{Applications.class};
//        return new Class[0];
//    }
//
//    @Override
//    protected Class<?>[] getServletConfigClasses() {
//        return new Class<?>[]{BackConfig.class};
//    }
//
//    @Override
//    protected String[] getServletMappings() {
//        return new String[]{"/admin"};
//    }
//
//    @Override
//    protected String getServletName() {
//        return "backServlet";
//    }
//
//    @Override
//    protected void registerContextLoaderListener(ServletContext servletContext) {
//        super.registerContextLoaderListener(servletContext);
//    }
//}
