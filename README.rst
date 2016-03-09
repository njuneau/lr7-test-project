Liferay 7 Demonstration project
===============================

This project is a demonstration of how a Liferay 7 module can be developped
solely using the Maven build system.

The project provides a demo portlet that lets a user remove portlets on a page,
whether they are deployed or not.

Prerequisites
-------------

You will need at least **Java 8** and **Maven** to compile the project. Make
sure that the Liferay Maven Repositories are configured in your settings.

Example Maven repository configuration in **~/.m2/settings.xml**:

.. code:: xml

    <repository>
        <id>liferay-ce</id>
        <url>https://repository.liferay.com/nexus/content/groups/liferay-ce/</url>
    </repository>

The repository must reside in an active Maven profile when you compile the
project.

The project can be run on Liferay 7 **Beta 8**.

Compiling the components
------------------------

.. code:: sh

    cd code/
    mvn -C clean install

This mill produce **jar** files in each of the 3 components build folders:

* portlet-remover-api/target
* portlet-remover-svc/target
* portlet-remover-web/target

Deploy the component JARs to Liferay 7's **osgi** folder and voilà! You should
see a portlet appear in the **Training** section.
