/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.maven.dotnet.executable.impl;

import org.apache.maven.dotnet.executable.*;
import org.apache.maven.dotnet.executable.compiler.CompilerRequirement;
import org.apache.maven.dotnet.executable.compiler.CompilerConfig;
import org.apache.maven.dotnet.executable.compiler.CompilerContext;
import org.apache.maven.dotnet.executable.compiler.CompilerExecutable;
import org.apache.maven.dotnet.vendor.*;
import org.apache.maven.dotnet.vendor.IllegalStateException;
import org.apache.maven.dotnet.registry.RepositoryRegistry;
import org.apache.maven.dotnet.artifact.ArtifactContext;
import org.apache.maven.dotnet.artifact.AssemblyRepositoryLayout;
import org.apache.maven.dotnet.InitializationException;
import org.apache.maven.dotnet.PlatformUnsupportedException;
import org.apache.maven.project.MavenProject;
import org.apache.maven.artifact.Artifact;
import org.codehaus.plexus.logging.LogEnabled;
import org.codehaus.plexus.logging.Logger;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;

/**
 * Provides an implementation of <code>NetExecutableFactory</code>.
 *
 * @author Shane Isbell
 */
public class NetExecutableFactoryImpl
    implements NetExecutableFactory, LogEnabled
{

    private CapabilityMatcher capabilityMatcher;

    private ArtifactContext artifactContext;

    private RepositoryExecutableContext repositoryExecutableContext;

    private ExecutableContext executableContext;

    private CompilerContext compilerContext;

    private RepositoryRegistry repositoryRegistry;

    private VendorInfoRepository vendorInfoRepository;

    private StateMachineProcessor processor;

    private Logger logger;

    public void enableLogging( Logger logger )
    {
        this.logger = logger;
    }

    /**
     * @see
     */
    public CompilerExecutable getCompilerExecutableFor( CompilerRequirement compilerRequirement,
                                                        CompilerConfig compilerConfig, MavenProject project,
                                                        File assemblyPath )
        throws PlatformUnsupportedException
    {

        VendorInfo vendorInfo = VendorInfo.Factory.createDefaultVendorInfo();
        vendorInfo.setVendorVersion( compilerRequirement.getVendorVersion() );
        vendorInfo.setFrameworkVersion( compilerRequirement.getFrameworkVersion() );
        vendorInfo.setVendor( compilerRequirement.getVendor() );
        try
        {
            processor.process( vendorInfo );
        }
        catch ( IllegalStateException e )
        {
            throw new PlatformUnsupportedException( "NMAVEN-066-011: Illegal State: Vendor Info = " + vendorInfo, e );
        }

        if ( vendorInfo.getVendor() == null )
        {
            throw new PlatformUnsupportedException( "NMAVEN-066-012: Vendor could not be found: " + vendorInfo );
        }

        logger.info( "NMAVEN-066-013: Found Vendor = " + vendorInfo );
        compilerRequirement.setVendor( vendorInfo.getVendor() );
        compilerRequirement.setVendorVersion( vendorInfo.getVendorVersion() );
        compilerRequirement.setFrameworkVersion( vendorInfo.getFrameworkVersion() );

        if ( vendorInfoRepository != null && vendorInfoRepository.exists() )
        {
            File sdkInstallRoot = null;
            try
            {
                sdkInstallRoot = vendorInfoRepository.getSdkInstallRootFor( vendorInfo );
            }
            catch ( PlatformUnsupportedException e )
            {
                logger.debug( "NMAVEN-066-017: Did not find an SDK install root: " + vendorInfo, e);
            }
            File installRoot = vendorInfoRepository.getInstallRootFor( vendorInfo );
            List<String> executionPaths = new ArrayList<String>();
            if ( installRoot != null )
            {
                executionPaths.add( installRoot.getAbsolutePath() );
            }
            if ( sdkInstallRoot != null )
            {
                executionPaths.add( sdkInstallRoot.getAbsolutePath() );
            }
            compilerConfig.setExecutionPaths( executionPaths );
        }

        compilerContext.init( compilerRequirement, compilerConfig, project, capabilityMatcher );
        if ( assemblyPath != null )
        {
            compilerContext.getCompilerCapability().setAssemblyPath( assemblyPath.getAbsolutePath() );
        }
        try
        {
            return compilerContext.getCompilerExecutable();
        }
        catch ( ExecutionException e )
        {
            throw new PlatformUnsupportedException( "NMAVEN-066-007: Unable to find net executable", e );
        }
    }

    public NetExecutable getNetExecutableFromRepository( String groupId, String artifactId, VendorInfo vendorInfo,
                                                         MavenProject project, String localRepository,
                                                         List<String> commands )
        throws PlatformUnsupportedException
    {
        if ( commands == null )
        {
            commands = new ArrayList<String>();
        }

        try
        {
            processor.process( vendorInfo );
        }
        catch ( IllegalStateException e )
        {
            throw new PlatformUnsupportedException( "NMAVEN-066-010: Illegal State: Vendor Info = " + vendorInfo, e );
        }
        Artifact artifact = artifactContext.getArtifactsFor( groupId, artifactId, null, null ).get( 0 );
        logger.debug( "NMAVEN-066-003: Found Vendor: " + vendorInfo );

        AssemblyRepositoryLayout layout = new AssemblyRepositoryLayout();
        File artifactPath = new File( localRepository + File.separator + layout.pathOf( artifact ) );
        List<String> modifiedCommands = new ArrayList<String>();
        String exe = null;
        if ( vendorInfo.getVendor().equals( Vendor.MONO ) )
        {
            List<File> executablePaths = vendorInfo.getExecutablePaths();
            if ( executablePaths != null )
            {
                for ( File executablePath : executablePaths )
                {
                    if ( new File( executablePath.getAbsolutePath() + File.separator + "mono.exe" ).exists() )
                    {
                        exe = new File( executablePath.getAbsolutePath() + File.separator + "mono" ).getAbsolutePath();
                        break;
                    }
                }
            }

            if ( exe == null )
            {
                logger.info(
                    "NMAVEN-066-005: Executable path for mono does not exist. Will attempt to execute MONO using" +
                        " the main PATH variable." );
                exe = "mono";
            }
            modifiedCommands.add( artifactPath.getAbsolutePath() );
            for ( String command : commands )
            {
                modifiedCommands.add( command );
            }
        }
        else
        {
            exe = artifactPath.getAbsolutePath();
            modifiedCommands = commands;
        }
        //TODO: DotGNU on Linux?

        ExecutableConfig executableConfig = ExecutableConfig.Factory.createDefaultExecutableConfig();
        executableConfig.setExecutionPaths( Arrays.asList( exe) );
        executableConfig.setCommands( modifiedCommands );

        try
        {
            repositoryExecutableContext.init( executableConfig, project );
        }
        catch ( InitializationException e )
        {
            throw new PlatformUnsupportedException(
                "NMAVEN-066-006: Unable to initialize the repository executable context", e );
        }

        try
        {
            return repositoryExecutableContext.getNetExecutable();
        }
        catch ( ExecutionException e )
        {
            throw new PlatformUnsupportedException( "NMAVEN-066-004: Unable to find net executable", e );
        }

    }

    public NetExecutable getNetExecutableFor( String vendor, String frameworkVersion, String profile,
                                              MavenProject project, List<String> commands, File netHome )
        throws PlatformUnsupportedException
    {

        VendorInfo vendorInfo = VendorInfo.Factory.createDefaultVendorInfo();
        vendorInfo.setVendorVersion( "" );
        vendorInfo.setFrameworkVersion( frameworkVersion );
        if ( vendor != null )
        {
            vendorInfo.setVendor( VendorFactory.createVendorFromName( vendor ) );
        }

        try
        {
            processor.process( vendorInfo );
        }
        catch ( IllegalStateException e )
        {
            throw new PlatformUnsupportedException( "NMAVEN-066-010: Illegal State: Vendor Info = " + vendorInfo, e );
        }
        logger.debug( "NMAVEN-066-003: Found Vendor: " + vendorInfo );
        ExecutableRequirement executableRequirement =
            ExecutableRequirement.Factory.createDefaultExecutableRequirement();
        executableRequirement.setVendor( vendorInfo.getVendor() );
        executableRequirement.setFrameworkVersion( vendorInfo.getFrameworkVersion() );
        executableRequirement.setVendorVersion( vendorInfo.getVendorVersion() );
        executableRequirement.setProfile( profile );

        ExecutableConfig executableConfig = ExecutableConfig.Factory.createDefaultExecutableConfig();
        executableConfig.setCommands( commands );

        List<String> executablePaths = new ArrayList<String>();
        if ( netHome != null && netHome.exists() )
        {
            logger.info( "NMAVEN-066-014: Found executable path: Path = " + netHome.getAbsolutePath() );
            executablePaths.add( netHome.getAbsolutePath() );
        }
        else if ( vendorInfo.getExecutablePaths() != null )
        {
            for ( File path : vendorInfo.getExecutablePaths() )
            {
                if ( path.exists() )
                {
                    logger.info( "NMAVEN-066-015: Found executable path: Path = " + path.getAbsolutePath() );
                    executablePaths.add( path.getAbsolutePath() );
                }
            }
        }
        else
        {
            logger.info( "NMAVEN-066-016: Did not find executable path, will try system path" );
        }
        executableConfig.setExecutionPaths( executablePaths );
        executableContext.init( executableRequirement, executableConfig, project, capabilityMatcher );

        try
        {
            return executableContext.getNetExecutable();
        }
        catch ( ExecutionException e )
        {
            throw new PlatformUnsupportedException( "NMAVEN-066-001: Unable to find net executable", e );
        }
    }
}
