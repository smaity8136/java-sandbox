package com.seedollar.java.spring.webflux.backend.common.util;

import java.io.Serializable;
import java.util.List;

import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.assertj.core.util.Lists;

@Plugin(name = "TestLoggingAppender", category = "Core", elementType = "appender", printObject = true)
public class TestLoggingAppender extends AbstractAppender {

  private static volatile TestLoggingAppender instance;

  private static List<LogEvent> testLogEvents = Lists.newArrayList();

  public TestLoggingAppender(String name, Filter filter,
      Layout<? extends Serializable> layout,
      boolean ignoreExceptions) {
    super(name, filter, layout, ignoreExceptions, null);
  }

  @PluginFactory
  public static TestLoggingAppender createAppender(@PluginAttribute("name") String name,
      @PluginAttribute("ignoreExceptions") boolean ignoreExceptions,
      @PluginElement("Layout") Layout layout,
      @PluginElement("Filters") Filter filter) {
    if (layout == null) {
      layout = PatternLayout.createDefaultLayout();
    }

    instance = new TestLoggingAppender(name, filter, layout, ignoreExceptions);
    return instance;
  }

  public static TestLoggingAppender getInstance() {
    return instance;
  }

  @Override
  public void append(LogEvent event) {
    testLogEvents.add(event);
  }

  public static void clearLogs() {
    testLogEvents.clear();
  }

  public static List<LogEvent> getLogs() {
    return testLogEvents;
  }
}
