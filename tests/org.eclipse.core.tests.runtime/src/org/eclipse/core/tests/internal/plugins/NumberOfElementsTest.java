/*******************************************************************************
 * Copyright (c) 2000, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.core.tests.internal.plugins;

import java.net.URL;
import junit.framework.Test;
import junit.framework.TestSuite;
import org.eclipse.core.internal.plugins.InternalFactory;
import org.eclipse.core.internal.plugins.PluginDescriptor;
import org.eclipse.core.internal.runtime.Policy;
import org.eclipse.core.runtime.*;
import org.eclipse.core.runtime.model.LibraryModel;
import org.eclipse.core.runtime.model.PluginDescriptorModel;
import org.eclipse.core.tests.harness.EclipseWorkspaceTest;

public class NumberOfElementsTest extends EclipseWorkspaceTest {

public NumberOfElementsTest() {
	super(null);
}

public NumberOfElementsTest(String name) {
	super(name);
}

public static Test suite() {
	TestSuite suite = new TestSuite();
	suite.addTest(new NumberOfElementsTest("numberRequiresTest"));
	suite.addTest(new NumberOfElementsTest("numberRuntimeTest"));
	return suite;
}

public void numberRequiresTest() {
	MultiStatus problems = new MultiStatus(Platform.PI_RUNTIME, Platform.PARSE_PROBLEM, Policy.bind("registryTestProblems", new String[0]), null);
	InternalFactory factory = new InternalFactory(problems);
	PluginDescriptor tempPlugin = (PluginDescriptor)Platform.getPluginRegistry().getPluginDescriptor("org.eclipse.core.tests.runtime");
	String pluginPath = tempPlugin.getLocation().concat("Plugin_Testing/numberOfRequires/plugin.xml");
	URL pluginURLs[] = new URL[1];
	URL pURL = null;
	try {
		pURL = new URL (pluginPath);
	} catch (java.net.MalformedURLException e) {
		assertTrue("Bad URL for " + pluginPath, true);
	}
	pluginURLs[0] = pURL;
	IPluginRegistry registry = ParseHelper.doParsing (factory, pluginURLs, true);
	IPluginDescriptor[] pluginDescriptors = registry.getPluginDescriptors();
	assertTrue ("1.0 Plugins should not validate", pluginDescriptors.length == 0);
}

public void numberRuntimeTest() {
	MultiStatus problems = new MultiStatus(Platform.PI_RUNTIME, Platform.PARSE_PROBLEM, Policy.bind("registryTestProblems", new String[0]), null);
	InternalFactory factory = new InternalFactory(problems);
	PluginDescriptor tempPlugin = (PluginDescriptor)Platform.getPluginRegistry().getPluginDescriptor("org.eclipse.core.tests.runtime");
	String pluginPath = tempPlugin.getLocation().concat("Plugin_Testing/numberOfRuntimes/plugin.xml");
	URL pluginURLs[] = new URL[1];
	URL pURL = null;
	try {
		pURL = new URL (pluginPath);
	} catch (java.net.MalformedURLException e) {
		assertTrue("Bad URL for " + pluginPath, true);
	}
	pluginURLs[0] = pURL;
	IPluginRegistry registry = ParseHelper.doParsing (factory, pluginURLs, true);
	IPluginDescriptor[] pluginDescriptors = registry.getPluginDescriptors();
	assertTrue ("2.0 Should have only one plugin", pluginDescriptors.length == 1);
	PluginDescriptorModel plugin = (PluginDescriptorModel)pluginDescriptors[0];
	LibraryModel libraryArray[] = plugin.getRuntime();
	// There should only be 3 library entries (not 6)
	assertTrue ("2.1 Three library entries", libraryArray.length == 3);
}

}
