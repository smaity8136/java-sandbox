A simple example that will show how spring manages a bean's lifecycle through it's context aware interfaces that can be implemented by a regular POJO.

The main will list the order of lifecycle awareness method calls, based on what is implemented by our spring managed pojo class.

Here is a list of lifecyle interfaces you can inherit in order of lifecycle:

1 - BeanNameAware
2 - BeanFactoryAware
3 - ApplicationContextAware
4 - InitializingBean
5 - BeanPostProcessor
6 - DisposableBean

