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

package npanday.plugin.compile;

import org.apache.maven.artifact.Artifact;
import npanday.PlatformUnsupportedException;
import npanday.executable.ExecutionException;
import npanday.executable.compiler.CompilerConfig;
import npanday.executable.compiler.CompilerExecutable;
import npanday.executable.compiler.CompilerRequirement;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Set;

/**
 * Abstract Class for compile mojos for both test-compile and compile.
 *
 * @author Shane Isbell, Leopoldo Lee Agdeppa III
 */
public abstract class AbstractCompilerMojo
        extends AbstractMojo
{


    /**
     * Skips compiling of unit tests
     *
     * @parameter expression = "${skipTestCompile}" default-value = "false"
     */
    protected boolean skipTestCompile;


    /**
     * The maven project.
     *
     * @parameter expression="${project}"
     * @required
     */
    protected MavenProject project;

    /**
     * The location of the local Maven repository.
     *
     * @parameter expression="${settings.localRepository}"
     */
    protected File localRepository;

    /**
     * Additional compiler commands
     *
     * @parameter expression = "${parameters}"
     */
    protected ArrayList<String> parameters;


    /**
     * Additional compiler commands for test classes
     *
     * @parameter expression = "${testParameters}"
     */
    protected ArrayList<String> testParameters;


    /**
     * Specify a strong name key file.
     *
     * @parameter expression = "${keyfile}"
     */
    protected File keyfile;

    /**
     * Specify a strong name key file.
     *
     * @parameter expression = "${testKeyfile}"
     */
    protected File testKeyfile;


    /**
     * The starup object class
     *
     * @parameter expression = "${main}"
     */
    protected String main;


    /**
     * The starup object class
     *
     * @parameter expression = "${testMain}"
     */
    protected String testMain;


    /**
     * define
     *
     * @parameter expression = "${define}"
     */
    protected String define;


    /**
     * define
     *
     * @parameter expression = "${testDefine}"
     */
    protected String testDefine;

    /**
     * Specifies a strong name key container. (not currently supported)
     *
     * @parameter expression = "${keycontainer}"
     */
    protected String keycontainer;


    /**
     * Specifies a strong name key container. (not currently supported)
     *
     * @parameter expression = "${testKeycontainer}"
     */
    protected String testKeycontainer;

    /**
     * Limit the platforms this code can run on. (not currently supported)
     *
     * @parameter expression = "${platform} default-value = "anycpu"
     */
    protected String platform;

    /**
     * Limit the platforms this code can run on. (not currently supported)
     *
     * @parameter expression = "${testPlatform} default-value = "anycpu"
     */
    protected String testPlatform;

    /**
     * The framework version to compile under: 1.1, 2.0, 3.0
     *
     * @parameter expression = "${frameworkVersion}"
     */
    protected String frameworkVersion;


    /**
     * The framework version to compile the test classes: 1.1, 2.0, 3.0
     *
     * @parameter expression = "${testFrameworkVersion}"
     */
    protected String testFrameworkVersion;

    /**
     * The profile that the compiler should use to compile classes: FULL, COMPACT, (or a custom one specified in a
     * compiler-plugins.xml).
     *
     * @parameter expression = "${profile}" default-value = "FULL"
     */
    protected String profile;


    /**
     * The profile that the compiler should use to compile classes: FULL, COMPACT, (or a custom one specified in a
     * compiler-plugins.xml).
     *
     * @parameter expression = "${testProfile}" default-value = "FULL"
     */
    protected String testProfile;


    /**
     * .NET Language. The default value is <code>C_SHARP</code>. Not case or white-space sensitive.
     *
     * @parameter expression="${language}" default-value = "C_SHARP"
     * @required
     */
    protected String language;


    /**
     * .NET Language. The default value is <code>C_SHARP</code>. Not case or white-space sensitive.
     *
     * @parameter expression="${testLanguage}"
     */
    protected String testLanguage;


    /**
     * Returns the rootnamespace of the project. Used by VB project only.
     *
     * @parameter expression="${rootNamespace}"
     */
    protected String rootNamespace;


    /**
     * Returns the rootnamespace of the project. Used by VB project only.
     *
     * @parameter expression="${testRootNamespace}" default-value="${project.groupId}.${project.artifactId}"
     */
    protected String testRootNamespace;


    /**
     * The Vendor for the Compiler. Not
     * case or white-space sensitive.
     *
     * @parameter expression="${vendor}"
     */
    protected String vendor;

    /**
     * The Vendor for the Compiler. Supports MONO and MICROSOFT: the default value is <code>MICROSOFT</code>. Not
     * case or white-space sensitive.
     *
     * @parameter expression="${testVendor}"
     */
    protected String testVendor;


    /**
     * This over-rides the defaultAssemblyPath for the compiler plugin.
     *
     * @parameter expression = "${profileAssemblyPath}
     */
    protected File profileAssemblyPath;


    /**
     * This over-rides the defaultAssemblyPath for the compiler plugin.
     *
     * @parameter expression = "${testProfileAssemblyPath}
     */
    protected File testProfileAssemblyPath;

    /**
     * @parameter expression = "${vendorVersion}"
     */
    protected String vendorVersion;

    /**
     * @parameter expression = "${testVendorVersion}"
     */
    protected String testVendorVersion;


    /**
     * @parameter expression = "${isDebug}" default-value="false"
     */
    protected boolean isDebug;

    /**
     * @component
     */
    protected npanday.executable.NetExecutableFactory netExecutableFactory;

    /**
     * @parameter expression="${project.file}"
     * @required
     * @readonly
     */
    protected File pomFile;

    /**
     * Delay-sign the assembly using only the public portion of the strong name key
     *
     * @parameter
     */
    protected boolean delaysign;


    /**
     * Delay-sign the assembly using only the public portion of the strong name key
     *
     * @parameter
     */
    protected boolean testDelaysign;

    /**
     * Link the specified modules into this assembly
     *
     * @parameter expression="${addmodules}"
     */
    protected String[] addModules;

    /**
     * Link the specified modules into this assembly
     *
     * @parameter expression="${testAddmodules}"
     */
    protected String[] testAddModules;

    /**
     * Specify a Win32 resource file (.res)
     *
     * @parameter expression = "${win32res}"
     */
    protected String win32Res;

    /**
     * Specify a Win32 resource file (.res)
     *
     * @parameter expression = "${testWin32res}"
     */
    protected String testWin32Res;

    /**
     * Remove integer checks.
     *
     * @parameter
     */
    protected boolean removeintchecks;


    /**
     * Remove integer checks.
     *
     * @parameter
     */
    protected boolean testRemoveintchecks;

    /**
     * Specifies a Win32 icon file (.ico) for the default Win32 resources.
     *
     * @parameter expression = "${win32icon}"
     */
    protected String win32Icon;

    /**
     * Specifies a Win32 icon file (.ico) for the default Win32 resources.
     *
     * @parameter expression = "${testWin32icon}"
     */
    protected String testWin32Icon;

    /**
     * Declare global Imports for namespaces in referenced metadata files.
     *
     * @parameter expression = "${imports}"
     */
    protected String[] imports;

    /**
     * Declare global Imports for namespaces in referenced metadata files.
     *
     * @parameter expression = "${testImports}"
     */
    protected String[] testImports;

    /**
     * Included Source Codes
     *
     * @parameter expression = "${includeSources}"
     */
    protected File[] includeSources;

    /**
     * Included Source Codes
     *
     * @parameter expression = "${testIncludeSources}"
     */
    protected File[] testIncludeSources;

    /**
     * Embed the specified resource
     *
     * @parameter expression = "${resource}"
     */
    protected String resource;

    /**
     * Embed the specified resource
     *
     * @parameter expression = "${testResource}"
     */
    protected String testResource;

    /**
     * Link the specified resource to this assembly
     *
     * @parameter expression = "${linkresource}"
     */
    protected String linkResource;

    /**
     * Link the specified resource to this assembly
     *
     * @parameter expression = "${testLinkresource}"
     */
    protected String testLinkResource;


    /**
     * Require explicit declaration of variables.
     *
     * @parameter
     */
    protected boolean optionexplicit;


    /**
     * Require explicit declaration of variables.
     *
     * @parameter
     */
    protected boolean testOptionexplicit;

    /**
     * Enforce strict language semantics / Warn when strict language semantics are not respected.
     *
     * @parameter expression = "${optionstrict}"
     */
    protected String optionStrict;

    /**
     * Enforce strict language semantics / Warn when strict language semantics are not respected.
     *
     * @parameter expression = "${testOptionstrict}"
     */
    protected String testOptionStrict;

    /**
     * Enable optimizations.
     *
     * @parameter
     */
    protected boolean optimize;

    /**
     * Enable optimizations.
     *
     * @parameter
     */
    protected boolean testOptimize;

    /**
     * Specifies binary or text style string comparisons
     *
     * @parameter expression = "${optioncompare}"
     */
    protected String optionCompare;


    /**
     * Specifies binary or text style string comparisons
     *
     * @parameter expression = "${testOptioncompare}"
     */
    protected String testOptionCompare;

    /**
     * Generate overflow checks
     *
     * @parameter
     */
    protected boolean checked;


    /**
     * Generate overflow checks
     *
     * @parameter
     */
    protected boolean testChecked;

    /**
     * Allow 'unsafe' code
     */
    protected boolean unsafe;


    /**
     * Allow 'unsafe' code
     */
    protected boolean testUnsafe;

    /**
     * Do not auto include CSC.RSP/VBC.RSP file
     *
     * @parameter
     */
    protected boolean noconfig;


    /**
     * Do not auto include CSC.RSP/VBC.RSP file
     *
     * @parameter
     */
    protected boolean testNoconfig;

    /**
     * Base address for the library to be built
     *
     * @parameter expression = "${baseaddress}"
     */
    protected String baseAddress;

    /**
     * Base address for the library to be built
     *
     * @parameter expression = "${testBaseaddress}"
     */
    protected String testBaseAddress;

    /**
     * Create a 'Bug Report' file.
     *
     * @parameter expression = "${bugreport}"
     */
    protected String bugReport;

    /**
     * Create a 'Bug Report' file.
     *
     * @parameter expression = "${testBugreport}"
     */
    protected String testBugReport;

    /**
     * Specify the codepage to use when opening source files
     *
     * @parameter expression = "${codepage}"
     */
    protected String codePage;

    /**
     * Specify the codepage to use when opening source files
     *
     * @parameter expression = "${testCodepage}"
     */
    protected String testCodePage;

    /**
     * Output compiler messages in UTF-8 encoding
     *
     * @parameter
     */
    protected boolean utf8output;

    /**
     * Output compiler messages in UTF-8 encoding
     *
     * @parameter
     */
    protected boolean testUtf8output;

    /**
     * Specify debug information file name (default: output file name with .pdb extension)
     *
     * @parameter expression = "${pdb}"
     */
    protected String pdb;

    /**
     * Specify debug information file name (default: output file name with .pdb extension)
     *
     * @parameter expression = "${testPdb}"
     */
    protected String testPdb;

    /**
     * Specify how to handle internal compiler errors: prompt, send, queue, or none. The default is queue.
     *
     * @parameter expression = "${errorreport}"
     */
    protected String errorReport;

    /**
     * Specify how to handle internal compiler errors: prompt, send, queue, or none. The default is queue.
     *
     * @parameter expression = "${testErrorreport}"
     */
    protected String testErrorReport;

    /**
     * Name of the assembly which this module will be a part of
     *
     * @parameter expression = "${moduleassemblyname}"
     */
    protected String moduleAssemblyName;

    /**
     * Name of the assembly which this module will be a part of
     *
     * @parameter expression = "${testModuleassemblyname}"
     */
    protected String testModuleAssemblyName;

    /**
     * Specify additional directories to search in for references
     *
     * @parameter expression = "${libs}"
     */
    protected String[] libs;


    /**
     * Specify additional directories to search in for references
     *
     * @parameter expression = "${testLibs}"
     */
    protected String[] testLibs;


    /**
     * The artifact acts as an Integration test project
     *
     * @parameter
     */
    protected boolean integrationTest;



    /**
     * The directory for the compilated web application
     *
     * @parameter  expression = "${outputDirectory}"
     */
    protected File outputDirectory;
    




    /**
     * Compiles the class files.
     *
     * @throws MojoExecutionException thrown if MOJO is unable to compile the class files or if the environment is not
     *                                properly set.
     */


    public void execute() throws MojoExecutionException
    {
        execute(false);
    }


    protected void execute(boolean test)
            throws MojoExecutionException
    {
        long startTime = System.currentTimeMillis();


        if (localRepository == null)
        {
            localRepository = new File(System.getProperty("user.home"), ".m2/repository");
        }

        initializeDefaults();


        try
        {
            CompilerExecutable compilerExecutable = netExecutableFactory.getCompilerExecutableFor(getCompilerRequirement(),
                    getCompilerConfig(),
                    project,
                    profileAssemblyPath);

            if (!test)
            {

                Boolean sourceFilesUpToDate = (Boolean) super.getPluginContext().get("SOURCE_FILES_UP_TO_DATE");
                if (((sourceFilesUpToDate == null) || sourceFilesUpToDate) &&
                        System.getProperty("forceCompile") == null && compilerExecutable.getCompiledArtifact() != null &&
                        compilerExecutable.getCompiledArtifact().exists())
                {
                    if (isUpToDateWithPomAndSettingsAndDependencies(compilerExecutable.getCompiledArtifact()))
                    {
                        getLog().info("NPANDAY-900-003: Nothing to compile - all classes are up-to-date");
                        project.getArtifact().setFile(compilerExecutable.getCompiledArtifact());
                        return;
                    }
                }
            }


//            FileUtils.mkdir("target");
            FileUtils.mkdir(project.getBuild().getDirectory());


            long startTimeCompile = System.currentTimeMillis();
            compilerExecutable.execute();
            long endTimeCompile = System.currentTimeMillis();

            getLog().info("NPANDAY-900-004: Compile Time = " + (endTimeCompile - startTimeCompile) + " ms");


            if (!test)
            {
                project.getArtifact().setFile(compilerExecutable.getCompiledArtifact());
            }

        }
        catch (PlatformUnsupportedException e)
        {
            throw new MojoExecutionException("NPANDAY-900-005: Unsupported Platform: Language = " + language +
                    ", Vendor = " + vendor + ", ArtifactType = " + project.getArtifact().getType(), e);
        }
        catch (ExecutionException e)
        {
            throw new MojoExecutionException("NPANDAY-900-006: Unable to Compile: Language = " + language +
                    ", Vendor = " + vendor + ", ArtifactType = " + project.getArtifact().getType() + ", Source Directory = " +
                    project.getBuild().getSourceDirectory(), e);
        }
        long endTime = System.currentTimeMillis();
        getLog().info("Mojo Execution Time = " + (endTime - startTime));
    }


    protected abstract void initializeDefaults() throws MojoExecutionException;

    protected abstract ArrayList<String> getParameters();

    protected abstract CompilerRequirement getCompilerRequirement() throws MojoExecutionException;

    protected abstract CompilerConfig getCompilerConfig() throws MojoExecutionException;


    protected String listToCommaDelimitedString(String[] list)
    {
        StringBuffer sb = new StringBuffer();
        boolean flag = false;

        if (list == null || list.length == 0) return "";

        for (String item : list)
        {
            sb.append(flag == true ? "," : "").append(item.trim());

            if (!flag)
            {
                flag = true;
            }
        }
        return sb.toString();

    }


    protected boolean isUpToDateWithPomAndSettingsAndDependencies(File targetFile)
    {
        File settingsFile = new File(localRepository, ".m2/npanday-settings.xml");
        Artifact latestDependencyModification =
                this.getLatestDependencyModification(project.getDependencyArtifacts());

        //TODO: Different parameters from the command line should also cause an update
        //TODO: Change in resource should cause an update
        if (targetFile.lastModified() < pomFile.lastModified())
        {
            getLog().info("NPANDAY-900-007: Project pom has changed. Forcing a recompile.");
            return false;
        }
        else if (settingsFile.exists() && targetFile.lastModified() < settingsFile.lastModified())
        {
            getLog().info("NPANDAY-900-008:Project settings has changed. Forcing a recompile.");
            return false;
        }
        else if (latestDependencyModification != null &&
                targetFile.lastModified() < latestDependencyModification.getFile().lastModified())
        {
            getLog().info(
                    "NPANDAY-900-009: Detected change in module dependency. Forcing a recompile: Changed Artifact = " +
                            latestDependencyModification);
            return false;
        }
        return true;
    }


    protected Artifact getLatestDependencyModification(Set<Artifact> artifacts)
    {
        if (artifacts == null)
        {
            return null;
        }
        Artifact lastModArtifact = null;
        for (Artifact artifact : artifacts)
        {
            if (lastModArtifact == null && !artifact.getType().startsWith("gac"))
            {
                lastModArtifact = artifact;
            }
            else if (!artifact.getType().startsWith("gac") &&
                    artifact.getFile().lastModified() > lastModArtifact.getFile().lastModified())
            {
                lastModArtifact = artifact;
            }
        }
        return lastModArtifact;
    }


}