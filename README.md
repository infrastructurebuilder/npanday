# npanday


## Goals

In no particular order

* Get a Maven-style release/test/CI system for .NET
* Defining a means for reliably breaking a CI build through various processes (code coverage, enforcer, versioning, etc)
* `mvn -B release:prepare release:perform` should produce a fully released artifact set in either a NuGet repo or some other repository target.  Wher
necessary, Chocolately packaging should also be generated and delivered.

## Anti-Goals

Also in no order

* Fit directly within the .NET development cycle.  Specifically, many of the complaints appear to be in a form of "NPanday looks too much like Maven."


Things we're likely to do

1. DEFINITELY NOT REFORMAT THE SOURCE.  Figure out the source formatting so that we don't break our ability to merge upstream unless we absolutely have to.
1. DEFINITELY Force resolution primarily from NuGet rather than from a Maven repository.  This is available inside VS now but how to integrate it?
1. DEFINITELY Update ALL dependencies to the latest versions.  Esp plugins and Groovy to new version and gmaven-plus.
1. DEFINITELY Convert to require a more-recent version of Java and Maven.  Removing use of Guava is a seconday goal there.
1. DEFINITELY Swap to [OpenCover](https://github.com/OpenCover/opencover) ( or [this?](https://github.com/sawilde/opencover) ) from PartCover for testing coverage.
1. DEFINITELY Convert to use sisu-injection and config instead of Javadoc config for components. The javadoc config appears to be a leftover from being very old.
1. Convert internal testing to be more abstract.
1. Expect to target only latest versions of MSDN-available Visual Studio or VSCode
1. Streamline the installation process by creating an SDKMan package that will do some of the heavy lifting for setups.
1. Recognize that this is likely to ultimately be an opinionated MSBuild generator
1. Change the name.  Npanday has history, good and bad, but rebranding might be a thing
