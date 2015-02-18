This module provides a server side integration between the Nuxeo platform and the Google Maps API

## Build

Enter your API key in the following files:

```
/src/main/resources/OSGI-INF/extensions/google-api-contrib.xml
/src/test/java/TestGoogleProvider.java
```

Assuming [maven](http://maven.apache.org/) (3.2.1) is installed on your system, after downloading the whole repository, execute the following:

```
mvn install
```

## Deploy

Put the jar file in nxserver/bundles

Also make sure that you have the [gson](https://code.google.com/p/google-gson/) library in nserver/lib

## Nuxeo Studio

Import the address schema and the automation operations in your project registry

Operations:
- GetPlaceSuggestionsOp can be used with a generic suggestion widget bound to the address:identification field
- GetPlaceDetailOp is an operation which can be used to retreive a complete address from an ID


## Important Note

**These features are not part of the Nuxeo Production platform.**

These solutions are provided for inspiration and we encourage customers to use them as code samples and learning resources.

This is a moving project (no API maintenance, no deprecation process, etc.) If any of these solutions are found to be useful for the Nuxeo Platform in general, they will be integrated directly into platform, not maintained here.


## About Nuxeo

Nuxeo provides a modular, extensible Java-based [open source software platform for enterprise content management](http://www.nuxeo.com/en/products/ep) and packaged applications for [document management](http://www.nuxeo.com/en/products/document-management), [digital asset management](http://www.nuxeo.com/en/products/dam) and [case management](http://www.nuxeo.com/en/products/case-management). Designed by developers for developers, the Nuxeo platform offers a modern architecture, a powerful plug-in model and extensive packaging capabilities for building content applications.

More information at <http://www.nuxeo.com/>