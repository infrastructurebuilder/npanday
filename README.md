# npanday


## Goals

In no particular order

* Get a Maven-style release/test/CI system for .NET

## Anti-Goals

Also in no order

* Fit directly within the .NET development cycle.  Specifically, many of the complaints appear to be in a form of "NPanday looks too much like Maven."


Things we're likely to do

1. DEFINITELY NOT REFORMAT THE SOURCE.  Figure out the source formatting so that we don't break our ability to merge upstream unless we absolutely have to.
1. DEFINITELY Update ALL dependencies to the latest versions.
1. DEFINITELY Convert to require a more-recent version of Java and Maven.
1. DEFINITELY Swap to CodeCover from PartCover for testing coverage.
1. DEFINITELY Convert to use sisu-injection and config instead of Javadoc config for components. The javadoc config appears to be a leftover from being very old.
1. Convert internal testing to be more abstract
1. Expect to target only latest versions of MSDN-available Visual Studio or VSCode
1. Streamline the installation process by creating an SDKMan package that will do some of the heavy lifting for setups.
1. Recognize that this is likely to ultimately be an opinionated MSBuild generator
1. Change the name.  Npanday has history, good and bad, but rebranding might be a thing
