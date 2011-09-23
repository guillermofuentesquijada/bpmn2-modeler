/******************************************************************************* 
 * Copyright (c) 2011 Red Hat, Inc. 
 *  All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html 
 * 
 * Contributors: 
 * Red Hat, Inc. - initial API and implementation 
 *
 * @author Innar Made
 ******************************************************************************/
package org.eclipse.bpmn2.modeler.ui;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.bpmn2.modeler.ui"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;

	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	public static void logStatus(IStatus status) {
		Platform.getLog(plugin.getBundle()).log(status);
	}

	public static void logError(Exception e) {
		logStatus(createStatus(e));
	}

	private static Status createStatus(Exception e) {
		return new Status(IStatus.ERROR, Activator.PLUGIN_ID, e.getMessage(), e);
	}

	public static void showErrorWithLogging(Exception e){
		Status s = createStatus(e);
		logStatus(s);
		ErrorDialog.openError(PlatformUI.getWorkbench().getDisplay().getActiveShell(), "An error occured", null, s);
	}

	/**
	 * Return the dialog settings for a given object. The object may be a string
	 * or any other java object. In that case, the object's class name will be used
	 * to retrieve that section name.
	 * 
	 * @param object 
	 * @return the dialog settings for that object
	 * 
	 */
	public IDialogSettings getDialogSettingsFor ( Object object ) 
	{
	    String name = object.getClass().getName();
	    if (object instanceof String) {
	        name = (String) object;
	    }
	    
	    IDialogSettings main = getDialogSettings();	    
	    IDialogSettings settings = main.getSection( name );
	    if (settings == null) {
	        settings = main.addNewSection(name);
	    }
	    return settings;
	}

	/**
	 * @return
	 */
	public String getID() {
		return getBundle().getSymbolicName();
	}
	
	public Image getImage(String id) {
		return getImageRegistry().get(id);
	}
}
