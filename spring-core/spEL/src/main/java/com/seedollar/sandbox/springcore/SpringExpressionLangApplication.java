package com.seedollar.sandbox.springcore;

import com.seedollar.sandbox.springcore.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.util.List;

@SpringBootApplication
public class SpringExpressionLangApplication implements ApplicationRunner {

    @Autowired
    Environment environment;

    // Static spEL reference
    @Value("#{7771739}")
    private Long spELStaticValue;

    // spEL class reference with method call
    @Value("#{T(java.util.UUID).randomUUID().toString()}")
    private String spELClassMethodInvocation;

    // spEL reference to System Properties arguments
    @Value("#{systemProperties['com.seedollar.spEL.sysProps']}")
    private String systemPropertiesValue;

    // spEL reference to a Spring bean instance called spELUser and calling a class variable
    @Value("#{spELUser.username}")
    private String spELUserBeanUsername;

    // spEL reference to a Spring bean instance called spELUser and invoking a public method
    @Value("#{spELUser.isActive()}")
    private boolean spELUserBeanIsActiveMethod;

    // spEL reference to a Spring bean instance called spELUser and calling a method on a method
    @Value("#{spELUser.getComment().toUpperCase()}")
    private String spELUserBeanCommentUppercase;

    // Check for null before calling byteValue() method, using the null check operator '?'
    @Value("#{spELUser.getSalary()?.byteValue()}")
    private String spELUserBeanSalaryElvisOperator;

    // spEL to show how you can do a compare using ==
    @Value("#{spELUser.isActive() == false}")
    private String isActiveEqualOperator;

    // spEL to show how you can do a compare using 'eq'
    @Value("#{spELUser.isActive() eq true}")
    private String isActiveEqOperator;

    // spEL reference showing how to use the Elvis Operator '?:'
    @Value("#{spELUser.getSalary() ?: 3899}")
    private String elvisOperator;

    // spEL reference showing how you can access an array
    @Value("#{spELUser.getTags()[4]}")
    private Integer fifthTag;

    // spEL reference to show you you can access a List item and reference it's class variable
    @Value("#{spELUser.getRoles()[1].permission}")
    private String secondRolePermission;

    // spEL reference showing how you can filter a list of items using the selection operator '.?[]'
    @Value("#{spELUser.getRoles().?[level == 2]}")
    private List<Role> level2RolesFiltered;

    // spEL reference to filter the roles where level == 2 and return the permission values as a list.
    @Value("#{spELUser.getRoles().?[level == 2].![permission]}")
    private List<String> getPermisssionsWithProjectorOperator;

    public static void main(String[] args) {
        SpringApplication.run(SpringExpressionLangApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Title: " + environment.getProperty("com.seedollar.spEL.title"));
        System.out.println("User Name: " + environment.getProperty("com.seedollar.spEL.username"));
        System.out.println("Password: " + environment.getProperty("com.seedollar.spEL.password", "defaultPassword"));
        System.out.println("Awesome Flag: " + environment.getProperty("com.seedollar.spEL.awesome", Boolean.class, true));
        System.out.println("Is capture flag available? = " + environment.containsProperty("com.seedollar.spEL.capture"));

        System.out.println("spELStaticValue = " + spELStaticValue);
        System.out.println("spELClassMethodInvocation = " + spELClassMethodInvocation);
        System.out.println("systemPropertiesValue = " + systemPropertiesValue);
        System.out.println("spELUserBeanUsername = " + spELUserBeanUsername);
        System.out.println("spELUserBeanIsActiveMethod = " + spELUserBeanIsActiveMethod);
        System.out.println("spELUserBeanCommentUppercase = " + spELUserBeanCommentUppercase);
        System.out.println("spELUserBeanSalaryElvisOperator = " + spELUserBeanSalaryElvisOperator);
        System.out.println("isActiveEqualOperator = " + isActiveEqualOperator);
        System.out.println("isActiveEqOperator = " + isActiveEqOperator);
        System.out.println("elvisOperator = " + elvisOperator);
        System.out.println("fifthTag = " + fifthTag);
        System.out.println("secondRolePermission = " + secondRolePermission);
        System.out.println("level2RolesFiltered = " + level2RolesFiltered);
        System.out.println("getPermisssionsWithProjectorOperator = " + getPermisssionsWithProjectorOperator);
    }
}
