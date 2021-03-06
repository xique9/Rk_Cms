package com.rekoe.web.freemarker;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.nutz.lang.Files;
import org.nutz.lang.Lang;
import org.nutz.lang.Streams;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

public class FreeMarkerConfigurer {
	private Configuration configuration;
	private String path;
	private FreemarkerDirectiveFactory freemarkerDirectiveFactory;

	public FreeMarkerConfigurer(Configuration configuration, ServletContext sc, String path, FreemarkerDirectiveFactory freemarkerDirectiveFactory) {
		this.configuration = configuration;
		this.path = sc.getRealPath(path);
		this.freemarkerDirectiveFactory = freemarkerDirectiveFactory;
	}

	public Configuration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	public void init() {
		List<FreemarkerDirective> list = freemarkerDirectiveFactory.getList();
		try {
			initFreeMarkerConfigurer();
		} catch (IOException e) {
			Lang.wrapThrow(e);
		} catch (TemplateException e) {
			Lang.wrapThrow(e);
		}
		for (FreemarkerDirective freemarkerDirective : list) {
			configuration.setSharedVariable(freemarkerDirective.getName(), freemarkerDirective.getTemplateDirectiveModel());
		}
	}

	private void initFreeMarkerConfigurer() throws IOException, TemplateException {
		Properties p = new Properties();
		p.load(Streams.fileIn(freemarkerDirectiveFactory.getFreemarker()));
		configuration.setSettings(p);
		configuration.setDirectoryForTemplateLoading(Files.findFile(path));
	}
}