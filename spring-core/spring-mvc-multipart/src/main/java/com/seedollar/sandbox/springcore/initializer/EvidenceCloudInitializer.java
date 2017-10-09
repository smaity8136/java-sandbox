package com.seedollar.sandbox.springcore.initializer;

import com.seedollar.sandbox.springcore.configuration.EvidenceCloudWebConfiguration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

public class EvidenceCloudInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {EvidenceCloudWebConfiguration.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

    /**
     * Specify where uploaded files should be temporarily stored when uploaded
     * Also specify the max file size if each individual file - 2MB (in bytes = 2097152)
     * Also specify the max size of the a request part - 4MB (in bytes = 4194304)
     * Also specify that all files should be written to disk - 0
     * @param registration
     */
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setMultipartConfig(new MultipartConfigElement("/tmp/evidence-cloud/uploads", 2097152, 4194304, 0));
    }
}
