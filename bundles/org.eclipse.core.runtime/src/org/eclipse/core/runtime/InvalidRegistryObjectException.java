/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.core.runtime;

import org.eclipse.core.internal.runtime.Policy;

/** 
 * An unchecked exception indicating that an attempt to access
 * an extension registry object that is no longer valid.
 * @since 3.1
 */
public class InvalidRegistryObjectException extends RuntimeException {
	/*
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;

	/* (non-javadoc)
	 * @see Throwable#getMessage()
	 */
	public String getMessage() {
		return Policy.bind("registry.staleHandle"); //$NON-NLS-1$
	}
	
}
