/*******************************************************************************
 *  Copyright (c) 2010 BestSolution.at and others.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 * 
 *  Contributors:
 *      Tom Schind<tom.schindl@bestsolution.at> - initial API and implementation
 ******************************************************************************/
package org.eclipse.e4.core.services.translation;

public @interface Message {
	public enum ReferenceType {
		NONE, SOFT, WEAK
	}

	String providerId() default "";

	ReferenceType referenceType() default ReferenceType.SOFT;
}