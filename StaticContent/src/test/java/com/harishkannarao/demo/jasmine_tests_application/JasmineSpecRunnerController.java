package com.harishkannarao.demo.jasmine_tests_application;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class JasmineSpecRunnerController {

    private static final String JASMINE_SPEC_RUNNER = "/JasmineSpecRunner";
    private final ResourceLoader resourceLoader;

    @Autowired
    public JasmineSpecRunnerController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView redirectJasmineSpecRunner() {
        return new ModelAndView(new RedirectView(JASMINE_SPEC_RUNNER));
    }

    @RequestMapping(value = JASMINE_SPEC_RUNNER, method = RequestMethod.GET)
    public ModelAndView displayJasmineSpecRunner() throws IOException {
        ModelMap model = new ModelMap();
        model.put("sources", getJavaScriptFilesFromClassPathDirectory("/static"));
        model.put("specs", getJavaScriptFilesFromClassPathDirectory("/test_static"));

        return new ModelAndView("JasmineSpecRunner", model);
    }

    private List<String> getJavaScriptFilesFromClassPathDirectory(String dir) throws IOException {
        final Resource fileResource = resourceLoader.getResource("classpath:" + dir);

        Collection<File> files = FileUtils.listFiles(fileResource.getFile(), new String[]{"js"}, true);

        return files.stream()
                .map(it -> it.toURI().toString())
                .map(it -> it.split(dir))
                .map(it -> it[1])
                .collect(Collectors.toList());
    }
}
