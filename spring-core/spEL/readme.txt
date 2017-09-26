This module will show some examples of how you can use Spring Expression Language (spEL) to dynamically represent property values.

spEL values are enclosed as: #{...}

You can also reference the SystemProperties for example: #{systemProperties['given.property.value']}

To test the system properties value, execute the following command: gradle -Dcom.seedollar.spEL.sysProps='This is a system property value' bootRun
