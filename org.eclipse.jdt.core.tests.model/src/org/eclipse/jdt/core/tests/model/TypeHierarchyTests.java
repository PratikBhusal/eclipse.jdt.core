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
package org.eclipse.jdt.core.tests.model;

import org.eclipse.core.runtime.OperationCanceledException;

import org.eclipse.jdt.core.*;

import junit.framework.Test;

public class TypeHierarchyTests extends AbstractJavaModelTests {
	/**
	 * A placeholder for a type hierarchy used in some test cases.
	 */
	ITypeHierarchy typeHierarchy;

public TypeHierarchyTests(String name) {
	super(name);
}
public static Test suite() {
	return new Suite(TypeHierarchyTests.class);
}

/* (non-Javadoc)
 * @see org.eclipse.jdt.core.tests.model.AbstractJavaModelTests#setUpSuite()
 */
public void setUpSuite() throws Exception {
	super.setUpSuite();

	setUpJavaProject("TypeHierarchy");
	
	IJavaProject project = this.getJavaProject("TypeHierarchy");
	IPackageFragmentRoot root = project.getPackageFragmentRoot(project.getProject().getFile("lib.jar"));
	IRegion region = JavaCore.newRegion();
	region.add(root);
	this.typeHierarchy = project.newTypeHierarchy(region, null);
}

/* (non-Javadoc)
 * @see org.eclipse.jdt.core.tests.model.SuiteOfTestCases#tearDownSuite()
 */
public void tearDownSuite() throws Exception {
	this.typeHierarchy = null;
	deleteProject("TypeHierarchy");
	
	super.tearDownSuite();
}
/*
 * Ensures that a hierarchy on an anonymous type in an initializer is correct.
 */
public void testAnonymousType1() throws JavaModelException {
	IType typeA = getCompilationUnit("TypeHierarchy", "src", "p7", "A.java").getType("A");
	IType type = typeA.getInitializer(1).getType("", 1);
	ITypeHierarchy hierarchy = type.newTypeHierarchy(null);
	assertHierarchyEquals(
		"Focus: <anonymous #1> [in <initializer #1> [in A [in A.java [in p7 [in src [in TypeHierarchy]]]]]]\n" + 
		"Super types:\n" + 
		"  X [in X.java [in p7 [in src [in TypeHierarchy]]]]\n" + 
		"    Object [in Object.class [in java.lang [in "+  getExternalJCLPath() +" [in TypeHierarchy]]]]\n" + 
		"Sub types:\n",
		hierarchy);
}
/*
 * Ensures that a hierarchy on an anonymous type in a second initializer is correct.
 */
public void testAnonymousType2() throws JavaModelException {
	IType typeA = getCompilationUnit("TypeHierarchy", "src", "p7", "A.java").getType("A");
	IType type = typeA.getInitializer(2).getType("", 1);
	ITypeHierarchy hierarchy = type.newTypeHierarchy(null);
	assertHierarchyEquals(
		"Focus: <anonymous #1> [in <initializer #2> [in A [in A.java [in p7 [in src [in TypeHierarchy]]]]]]\n" + 
		"Super types:\n" + 
		"  X [in X.java [in p7 [in src [in TypeHierarchy]]]]\n" + 
		"    Object [in Object.class [in java.lang [in "+  getExternalJCLPath() +" [in TypeHierarchy]]]]\n" + 
		"Sub types:\n",
		hierarchy);
}
/*
 * Ensures that a hierarchy on an anonymous type in a field declaration is correct.
 */
public void testAnonymousType3() throws JavaModelException {
	IType typeA = getCompilationUnit("TypeHierarchy", "src", "p7", "A.java").getType("A");
	IType type = typeA.getField("field1").getType("", 1);
	ITypeHierarchy hierarchy = type.newTypeHierarchy(null);
	assertHierarchyEquals(
		"Focus: <anonymous #1> [in field1 [in A [in A.java [in p7 [in src [in TypeHierarchy]]]]]]\n" + 
		"Super types:\n" + 
		"  X [in X.java [in p7 [in src [in TypeHierarchy]]]]\n" + 
		"    Object [in Object.class [in java.lang [in "+  getExternalJCLPath() +" [in TypeHierarchy]]]]\n" + 
		"Sub types:\n",
		hierarchy);
}
/*
 * Ensures that a hierarchy on an anonymous type in a field declaration is correct.
 */
public void testAnonymousType4() throws JavaModelException {
	IType typeA = getCompilationUnit("TypeHierarchy", "src", "p7", "A.java").getType("A");
	IType type = typeA.getField("field2").getType("", 1);
	ITypeHierarchy hierarchy = type.newTypeHierarchy(null);
	assertHierarchyEquals(
		"Focus: <anonymous #1> [in field2 [in A [in A.java [in p7 [in src [in TypeHierarchy]]]]]]\n" + 
		"Super types:\n" + 
		"  X [in X.java [in p7 [in src [in TypeHierarchy]]]]\n" + 
		"    Object [in Object.class [in java.lang [in "+  getExternalJCLPath() +" [in TypeHierarchy]]]]\n" + 
		"Sub types:\n",
		hierarchy);
	type = typeA.getField("field2").getType("", 2);
	hierarchy = type.newTypeHierarchy(null);
	assertHierarchyEquals(
		"Focus: <anonymous #2> [in field2 [in A [in A.java [in p7 [in src [in TypeHierarchy]]]]]]\n" + 
		"Super types:\n" + 
		"  X [in X.java [in p7 [in src [in TypeHierarchy]]]]\n" + 
		"    Object [in Object.class [in java.lang [in "+  getExternalJCLPath() +" [in TypeHierarchy]]]]\n" + 
		"Sub types:\n",
		hierarchy);
}
/*
 * Ensures that a hierarchy on an anonymous type in a method declaration is correct.
 */
public void testAnonymousType5() throws JavaModelException {
	IType typeA = getCompilationUnit("TypeHierarchy", "src", "p7", "A.java").getType("A");
	IType type = typeA.getMethod("foo", new String[] {}).getType("", 1);
	ITypeHierarchy hierarchy = type.newTypeHierarchy(null);
	assertHierarchyEquals(
		"Focus: <anonymous #1> [in foo() [in A [in A.java [in p7 [in src [in TypeHierarchy]]]]]]\n" + 
		"Super types:\n" + 
		"  X [in X.java [in p7 [in src [in TypeHierarchy]]]]\n" + 
		"    Object [in Object.class [in java.lang [in "+  getExternalJCLPath() +" [in TypeHierarchy]]]]\n" + 
		"Sub types:\n",
		hierarchy);
}
/*
 * Ensures that a hierarchy on an anonymous type that uses a non-default constructor is correct.
 */
public void testAnonymousType6() throws JavaModelException {
	IType typeA = getCompilationUnit("TypeHierarchy", "src", "p8", "X.java").getType("X");
	IType type = typeA.getMethod("foo", new String[] {}).getType("", 1);
	ITypeHierarchy hierarchy = type.newTypeHierarchy(null);
	assertHierarchyEquals(
		"Focus: <anonymous #1> [in foo() [in X [in X.java [in p8 [in src [in TypeHierarchy]]]]]]\n" + 
		"Super types:\n" + 
		"  X [in X.java [in p8 [in src [in TypeHierarchy]]]]\n" + 
		"    Object [in Object.class [in java.lang [in "+  getExternalJCLPath() +" [in TypeHierarchy]]]]\n" + 
		"Sub types:\n",
		hierarchy);
}
/**
 * Ensures that the superclass can be retrieved for a binary inner type.
 */
public void testBinaryInnerTypeGetSuperclass() throws JavaModelException {
	IClassFile cf = getClassFile("TypeHierarchy", "lib.jar", "binary", "Y$Inner.class");
	IType type = cf.getType();
	ITypeHierarchy h = type.newSupertypeHierarchy(null);
	IType superclass = h.getSuperclass(type);
	assertTrue("Superclass not found for Y$Inner", superclass != null);
	assertEquals("Unexpected super class", "Z", superclass.getElementName());
}
/**
 * Ensures that the superinterfaces can be retrieved for a binary inner type.
 */
public void testBinaryInnerTypeGetSuperInterfaces() throws JavaModelException {
	IClassFile cf = getClassFile("TypeHierarchy", "lib.jar", "binary", "Y$Inner.class");
	IType type = cf.getType();
	ITypeHierarchy h = type.newSupertypeHierarchy(null);
	assertTypesEqual(
		"Unexpected super interfaces", 
		"binary.I\n", 
		h.getSuperInterfaces(type));
}
/**
 * Ensures that the superclass can be retrieved for a binary type's superclass.
 */
public void testBinaryTypeGetSuperclass() throws JavaModelException {
	IClassFile cf = getClassFile("TypeHierarchy", "lib.jar", "binary", "Y.class");
	IType type = cf.getType();
	ITypeHierarchy h= type.newSupertypeHierarchy(null);
	IType superclass= h.getSuperclass(type);
	assertTrue("Superclass not found forY", superclass != null);
	assertEquals("Unexpected superclass of Y", "X", superclass.getElementName());
}
/**
 * Ensures that the superclass can be retrieved for a binary type's superclass.
 * This is a relatively deep type hierarchy.
 */
public void testBinaryTypeGetSuperclass2() throws JavaModelException {
	IClassFile cf = getClassFile("TypeHierarchy", "lib.jar", "binary", "Deep.class");
	IType type = cf.getType();
	ITypeHierarchy h= type.newSupertypeHierarchy(null);
	IType superclass= h.getSuperclass(type);
	assertTrue("Superclass not found for Deep", superclass != null);
	assertEquals("Unexpected superclass of Deep", "Z", superclass.getElementName());
}
/**
 * Ensures that the superinterfaces can be retrieved for a binary type's superinterfaces.
 */
public void testBinaryTypeGetSuperInterfaces() throws JavaModelException {
	IClassFile cf = getClassFile("TypeHierarchy", "lib.jar", "binary", "X.class");
	IType type = cf.getType();
	ITypeHierarchy h = type.newSupertypeHierarchy(null);
	IType[] superInterfaces = h.getSuperInterfaces(type);
	assertTypesEqual(
		"Unexpected super interfaces of X", 
		"binary.I\n", 
		superInterfaces);
}
/**
 * Ensures that the superinterfaces can be retrieved for a binary type's superinterfaces.
 * Test with type that has a "rich" super hierarchy
 */
public void testBinaryTypeGetSuperInterfaces2() throws JavaModelException {
	IClassFile cf = getClassFile("TypeHierarchy", "lib.jar", "rich", "C.class");
	IType type = cf.getType();
	ITypeHierarchy h = type.newSupertypeHierarchy(null);
	IType[] superInterfaces = h.getSuperInterfaces(type);
	assertTypesEqual(
		"Unexpected super interfaces of C", 
		"rich.I\n" +
		"rich.I3\n", 
		superInterfaces);
}
/**
 * Ensures that contains(...) returns true for a type that is part of the
 * hierarchy and false otherwise.
 */
public void testContains() throws JavaModelException {
	// regular class
	IClassFile cf = getClassFile("TypeHierarchy", "lib.jar", "binary", "X.class");
	IType type = cf.getType();
	assertTrue("X must be included", this.typeHierarchy.contains(type));

	// root class
	cf = getClassFile("TypeHierarchy", getExternalJCLPathString(), "java.lang", "Object.class");
	type = cf.getType();
	assertTrue("Object must be included", this.typeHierarchy.contains(type));

	// interface
	cf = getClassFile("TypeHierarchy", "lib.jar", "binary", "I.class");
	assertTrue("I must be included", this.typeHierarchy.contains(type));
}
/*
 * Ensures that a hierarchy on a type with local and anonymous types is correct.
 */
public void testFocusWithLocalAndAnonymousTypes() throws JavaModelException {
	IType type = getCompilationUnit("TypeHierarchy", "src", "p7", "X.java").getType("X");
	ITypeHierarchy hierarchy = type.newTypeHierarchy(null);
	assertHierarchyEquals(
		"Focus: X [in X.java [in p7 [in src [in TypeHierarchy]]]]\n" + 
		"Super types:\n" + 
		"  Object [in Object.class [in java.lang [in "+  getExternalJCLPath() +" [in TypeHierarchy]]]]\n" + 
		"Sub types:\n" + 
		"  Y1 [in foo() [in A [in A.java [in p7 [in src [in TypeHierarchy]]]]]]\n" + 
		"    Y2 [in foo() [in A [in A.java [in p7 [in src [in TypeHierarchy]]]]]]\n" + 
		"  <anonymous #1> [in foo() [in A [in A.java [in p7 [in src [in TypeHierarchy]]]]]]\n" + 
		"  <anonymous #2> [in field2 [in A [in A.java [in p7 [in src [in TypeHierarchy]]]]]]\n" + 
		"  <anonymous #1> [in field2 [in A [in A.java [in p7 [in src [in TypeHierarchy]]]]]]\n" + 
		"  <anonymous #1> [in field1 [in A [in A.java [in p7 [in src [in TypeHierarchy]]]]]]\n" + 
		"  <anonymous #1> [in <initializer #2> [in A [in A.java [in p7 [in src [in TypeHierarchy]]]]]]\n" + 
		"  Y1 [in <initializer #1> [in A [in A.java [in p7 [in src [in TypeHierarchy]]]]]]\n" + 
		"    Y2 [in <initializer #1> [in A [in A.java [in p7 [in src [in TypeHierarchy]]]]]]\n" + 
		"  <anonymous #1> [in <initializer #1> [in A [in A.java [in p7 [in src [in TypeHierarchy]]]]]]\n",
		hierarchy);
}
/**
 * Ensures that getType() returns the type the hierarchy was created for.
 */
public void testGetType() throws JavaModelException {
	// hierarchy created on a type
	IClassFile cf = getClassFile("TypeHierarchy", "lib.jar", "binary", "Y.class");
	IType type = cf.getType();
	ITypeHierarchy hierarchy = null;
	try {
		hierarchy = type.newSupertypeHierarchy(null);
	} catch (IllegalArgumentException iae) {
		assertTrue("IllegalArgumentException", false);
	}
	assertEquals("Unexpected focus type", type, hierarchy.getType());

	// hierarchy created on a region
	assertTrue("Unexpected focus type for hierarchy on region", this.typeHierarchy.getType() == null);
}
/**
 * Ensures that a hierarchy on an inner type is correctly rooted.
 */
public void testInnerType1() throws JavaModelException {
	IType type = getCompilationUnit("TypeHierarchy", "src", "p5", "X.java").getType("X").getType("Inner");
	ITypeHierarchy hierarchy = null;
	try {
		hierarchy = type.newTypeHierarchy(null);
	} catch (IllegalArgumentException iae) {
		assertTrue("IllegalArgumentException", false);
	}
	assertHierarchyEquals(
		"Focus: Inner [in X [in X.java [in p5 [in src [in TypeHierarchy]]]]]\n" + 
		"Super types:\n" + 
		"  Object [in Object.class [in java.lang [in "+  getExternalJCLPath() +" [in TypeHierarchy]]]]\n" + 
		"Sub types:\n",
		hierarchy);
}
/**
 * Ensures that a hierarchy on an inner type has the correct subtype.
 * (regression test for bug 43274 Type hierarchy broken)
 */
public void testInnerType2() throws JavaModelException {
	IType type = getCompilationUnit("TypeHierarchy", "src", "p6", "A.java").getType("A").getType("Inner");
	ITypeHierarchy hierarchy = null;
	try {
		hierarchy = type.newTypeHierarchy(null);
	} catch (IllegalArgumentException iae) {
		assertTrue("IllegalArgumentException", false);
	}
	assertHierarchyEquals(
		"Focus: Inner [in A [in A.java [in p6 [in src [in TypeHierarchy]]]]]\n" + 
		"Super types:\n" + 
		"  Object [in Object.class [in java.lang [in "+  getExternalJCLPath() +" [in TypeHierarchy]]]]\n" + 
		"Sub types:\n" + 
		"  B [in A.java [in p6 [in src [in TypeHierarchy]]]]\n",
		hierarchy);
}
/*
 * Ensures that a hierarchy on a local type in an initializer is correct.
 */
public void testLocalType1() throws JavaModelException {
	IType typeA = getCompilationUnit("TypeHierarchy", "src", "p7", "A.java").getType("A");
	IType type = typeA.getInitializer(1).getType("Y1", 1);
	ITypeHierarchy hierarchy = type.newTypeHierarchy(null);
	assertHierarchyEquals(
		"Focus: Y1 [in <initializer #1> [in A [in A.java [in p7 [in src [in TypeHierarchy]]]]]]\n" + 
		"Super types:\n" + 
		"  X [in X.java [in p7 [in src [in TypeHierarchy]]]]\n" + 
		"    Object [in Object.class [in java.lang [in "+  getExternalJCLPath() +" [in TypeHierarchy]]]]\n" + 
		"Sub types:\n" + 
		"  Y2 [in <initializer #1> [in A [in A.java [in p7 [in src [in TypeHierarchy]]]]]]\n",
		hierarchy);
}
/*
 * Ensures that a hierarchy on a local type in a second initializer is correct.
 */
public void testLocalType2() throws JavaModelException {
	IType typeA = getCompilationUnit("TypeHierarchy", "src", "p7", "A.java").getType("A");
	IType type = typeA.getInitializer(2).getType("Y3", 1);
	ITypeHierarchy hierarchy = type.newTypeHierarchy(null);
	assertHierarchyEquals(
		"Focus: Y3 [in <initializer #2> [in A [in A.java [in p7 [in src [in TypeHierarchy]]]]]]\n" + 
		"Super types:\n" + 
		"  Object [in Object.class [in java.lang [in "+  getExternalJCLPath() +" [in TypeHierarchy]]]]\n" + 
		"Sub types:\n",
		hierarchy);
}
/*
 * Ensures that a hierarchy on a local type in a method declaration is correct.
 */
public void testLocalType3() throws JavaModelException {
	IType typeA = getCompilationUnit("TypeHierarchy", "src", "p7", "A.java").getType("A");
	IType type = typeA.getMethod("foo", new String[] {}).getType("Y2", 1);
	ITypeHierarchy hierarchy = type.newTypeHierarchy(null);
	assertHierarchyEquals(
		"Focus: Y2 [in foo() [in A [in A.java [in p7 [in src [in TypeHierarchy]]]]]]\n" + 
		"Super types:\n" + 
		"  Y1 [in foo() [in A [in A.java [in p7 [in src [in TypeHierarchy]]]]]]\n" + 
		"    X [in X.java [in p7 [in src [in TypeHierarchy]]]]\n" + 
		"      Object [in Object.class [in java.lang [in "+  getExternalJCLPath() +" [in TypeHierarchy]]]]\n" + 
		"Sub types:\n",
		hierarchy);
}
/*
 * Ensures that a super type hierarchy on a local type in a method declaration is correct.
 * (regression test for bug 44073 Override methods action does not work for local types [code manipulation])
 */
public void testLocalType4() throws JavaModelException {
	IType typeA = getCompilationUnit("TypeHierarchy", "src", "p7", "A.java").getType("A");
	IType type = typeA.getMethod("foo", new String[] {}).getType("Y1", 1);
	ITypeHierarchy hierarchy = type.newSupertypeHierarchy(null);
	assertHierarchyEquals(
		"Focus: Y1 [in foo() [in A [in A.java [in p7 [in src [in TypeHierarchy]]]]]]\n" + 
		"Super types:\n" + 
		"  X [in X.java [in p7 [in src [in TypeHierarchy]]]]\n" + 
		"    Object [in Object.class [in java.lang [in "+  getExternalJCLPath() +" [in TypeHierarchy]]]]\n" + 
		"Sub types:\n",
		hierarchy);
}
/**
 * Ensures that a hierarchy on a type that implements a missing interface is correctly rooted.
 * (regression test for bug 24691 Missing interface makes hierarchy incomplete)
 */
public void testMissingInterface() throws JavaModelException {
	IType type = getCompilationUnit("TypeHierarchy", "src", "p4", "X.java").getType("X");
	ITypeHierarchy hierarchy = null;
	try {
		hierarchy = type.newTypeHierarchy(null);
	} catch (IllegalArgumentException iae) {
		assertTrue("IllegalArgumentException", false);
	}
	assertHierarchyEquals(
		"Focus: X [in X.java [in p4 [in src [in TypeHierarchy]]]]\n" + 
		"Super types:\n" + 
		"  Object [in Object.class [in java.lang [in "+  getExternalJCLPath() +" [in TypeHierarchy]]]]\n" + 
		"Sub types:\n",
		hierarchy);
}
/**
 * Ensures that a potential subtype that is not in the classpth is handle correctly.
 * (Regression test for PR #1G4GL9R)
 */
public void testPotentialSubtypeNotInClasspath() throws JavaModelException {
	IJavaProject project = getJavaProject("TypeHierarchy");
	ICompilationUnit cu = getCompilationUnit("TypeHierarchy", "src", "p1", "X.java");
	IType type = cu.getType("X");
	ITypeHierarchy h = type.newTypeHierarchy(project, null);
	IType[] types = h.getSubtypes(type);
	this.assertTypesEqual(
		"Unexpected sub types of X",
		"p1.Y\n",
		types
	);
}
/**
 * Ensures that the superclass can be retrieved for a source type's unqualified superclass.
 */
public void testSourceTypeGetSuperclass() throws JavaModelException {
	//unqualified superclass in a source type
	ICompilationUnit cu = getCompilationUnit("TypeHierarchy", "src", "p1", "Y.java");
	IType type = cu.getType("Y");
	ITypeHierarchy h = type.newSupertypeHierarchy(null);
	IType superclass = h.getSuperclass(type);
	assertTrue("Superclass not found for Y", superclass != null);
	assertEquals("Unexpected super class for Y", "X", superclass.getElementName());
}
/**
 * Ensures that the superclass can be retrieved for a source type's superclass when no superclass is specified
 * in the source type.
 */
public void testSourceTypeGetSuperclass2() throws JavaModelException {
	//no superclass specified for a source type
	ICompilationUnit cu = getCompilationUnit("TypeHierarchy", "src", "p1", "X.java");
	IType type = cu.getType("X");
	ITypeHierarchy h = type.newSupertypeHierarchy(null);
	IType superclass = h.getSuperclass(type);
	assertTrue("Superclass not found for X", superclass != null);
	assertEquals("Unexpected super class for X", "Object", superclass.getElementName());
}
/**
 * Ensures that the superclass can be retrieved for a source type's superclass.
 * This type hierarchy is relatively deep.
 */
public void testSourceTypeGetSuperclass3() throws JavaModelException {
	//no superclass specified for a source type
	ICompilationUnit cu = getCompilationUnit("TypeHierarchy", "src", "p1", "Deep.java");
	IType type = cu.getType("Deep");
	ITypeHierarchy h = type.newSupertypeHierarchy(null);
	IType superclass = h.getSuperclass(type);
	assertTrue("Superclass not found for Deep", superclass != null);
	assertEquals("Unexpected super class for Deep", "Z", superclass.getElementName());
}
/**
 * Ensures that the superclass can be retrieved when it is defined
 * in the same compilation unit.
 */
public void testSourceTypeGetSuperclass4() throws JavaModelException {
	ICompilationUnit cu = getCompilationUnit("TypeHierarchy", "src", "p1", "A.java");
	IType type = cu.getType("A");
	ITypeHierarchy h = type.newSupertypeHierarchy(null);
	IType superclass = h.getSuperclass(type);
	assertTrue("Superclass not found for A", superclass != null);
	assertEquals("Unexpected super class for A", "B", superclass.getElementName());
}
/**
 * Ensures that the superinterfaces can be retrieved for a source type's superinterfaces.
 */
public void testSourceTypeGetSuperInterfaces() throws JavaModelException {
	ICompilationUnit cu = getCompilationUnit("TypeHierarchy", "src", "p1", "Y.java");
	IType type = cu.getType("Y");
	ITypeHierarchy h = type.newSupertypeHierarchy(null);
	IType[] superInterfaces = h.getSuperInterfaces(type);
	assertTypesEqual("Unexpected super interfaces for Y", 
		"p1.I1\n" +
		"p1.I2\n", 
		superInterfaces);
}
/**
 * Ensures that no subclasses exist in a super type hierarchy for the focus type.
 */
public void testSupertypeHierarchyGetSubclasses() throws JavaModelException {
	IType type = getClassFile("TypeHierarchy", getExternalJCLPathString(), "java.lang", "Object.class").getType();
	ITypeHierarchy hierarchy = type.newSupertypeHierarchy(null);
	IType[] types = hierarchy.getSubclasses(type);
	assertTypesEqual(
		"Unexpected subclasses of Object", 
		"", 
		types);
	
	ICompilationUnit cu = getCompilationUnit("TypeHierarchy", "src", "p1", "Y.java");
	type = cu.getType("Y");
	hierarchy = type.newSupertypeHierarchy(null);
	types = hierarchy.getSubclasses(type);
	assertTypesEqual(
		"Unexpected subclasses of Y", 
		"", 
		types);
}
/**
 * Ensures that no subtypes exist in a super type hierarchy for the focus type.
 */
public void testSupertypeHierarchyGetSubtypes() throws JavaModelException {
	IType type = getClassFile("TypeHierarchy", getExternalJCLPathString(), "java.lang", "Object.class").getType();
	ITypeHierarchy hierarchy = type.newSupertypeHierarchy(null);
	IType[] types = hierarchy.getSubtypes(type);
	assertTypesEqual(
		"Unexpected subtypes of Object", 
		"", 
		types);
	
	ICompilationUnit cu = getCompilationUnit("TypeHierarchy", "src", "p1", "Y.java");
	type = cu.getType("Y");
	hierarchy = type.newSupertypeHierarchy(null);
	types = hierarchy.getSubtypes(type);
	assertTypesEqual(
		"Unexpected subtypes of Y", 
		"", 
		types);
}
/**
 * Ensures that a super type hierarchy can be created on a working copy.
 * (regression test for bug 3446 type hierarchy: incorrect behavior wrt working copies (1GLDHOA))
 */
public void testSupertypeHierarchyOnWorkingCopy() throws JavaModelException {
	ICompilationUnit cu = this.getCompilationUnit("TypeHierarchy", "src", "wc", "X.java");
	ICompilationUnit workingCopy = null;
	try {
		workingCopy = (ICompilationUnit)cu.getWorkingCopy();
		workingCopy.createType(
			"class B{\n" +
			"	void m(){\n" +
			"	}\n" +
			"	void f(){\n" +
			"		m();\n" +
			"	}\n" +
			"}\n",
			null,
			true,
			null);
		workingCopy.createType(
			"class A extends B{\n" +
			"	void m(){\n" +
			"	}\n" +
			"}",
			null,
			true,
			null);
		IType typeA = workingCopy.getType("A");
		ITypeHierarchy hierarchy = typeA.newSupertypeHierarchy(null);
		IType typeB = workingCopy.getType("B");
		assertTrue("hierarchy should contain B", hierarchy.contains(typeB));
	} finally {
		if (workingCopy != null) {
			workingCopy.destroy();
		}
	}
}
/**
 * Ensures that the creation of a type hierarchy can be cancelled.
 */
public void testCancel() throws JavaModelException {
	boolean isCanceled = false;
	IType type = getCompilationUnit("TypeHierarchy", "src", "p1", "X.java").getType("X");
	IRegion region = JavaCore.newRegion();
	region.add(getPackageFragmentRoot("TypeHierarchy", "src"));
	try {
		TestProgressMonitor monitor = TestProgressMonitor.getInstance();
		monitor.setCancelledCounter(1);
		type.getJavaProject().newTypeHierarchy(type, region, monitor);
	} catch (OperationCanceledException e) {
		isCanceled = true;
	}
	assertTrue("Operation should have thrown an operation canceled exception", isCanceled);
}
/**
 * Ensures the correctness of all classes in a type hierarchy based on a region.
 */
public void testGetAllClassesInRegion() {
	IType[] types = this.typeHierarchy.getAllClasses();
	assertTypesEqual(
		"Unexpected all classes in hierarchy", 
		"binary.Deep\n" +
		"binary.X\n" +
		"binary.Y\n" +
		"binary.Y$Inner\n" +
		"binary.Z\n" +
		"java.lang.Object\n" +
		"rich.A\n" +
		"rich.B\n" +
		"rich.C\n", 
		types);
}


/**
 * Ensures the correctness of all interfaces in a type hierarchy based on a region.
 */
public void testGetAllInterfacesInRegion() {
	IType[] types = this.typeHierarchy.getAllInterfaces();
	assertTypesEqual(
		"Unexpected all interfaces in hierarchy", 
		"binary.I\n" +
		"rich.I\n" +
		"rich.I2\n" +
		"rich.I3\n", 
		types);
}
/**
 * Ensures that the correct subtypes of a type exist in the type 
 * hierarchy.
 */
public void testGetAllSubtypes() throws JavaModelException {
	IType type = getCompilationUnit("TypeHierarchy", "src", "p1", "X.java").getType("X");
	ITypeHierarchy hierarchy = type.newTypeHierarchy(null);
	IType[] types = hierarchy.getAllSubtypes(type);
	this.assertTypesEqual(
		"Unexpected sub types of X",
		"p1.Deep\n" +
		"p1.Y\n" +
		"p1.Z\n",
		types
	);
}
/**
 * Ensures that the correct subtypes of a binary type
 * exit in the type hierarchy created on a region.
 */
public void testGetAllSubtypesFromBinary() throws JavaModelException {
	IType type = getClassFile("TypeHierarchy", "lib.jar", "binary", "X.class").getType();
	IRegion region = JavaCore.newRegion();
	region.add(type.getPackageFragment());
	ITypeHierarchy hierarchy = type.getJavaProject().newTypeHierarchy(type, region, null);
	IType[] types = hierarchy.getAllSubtypes(type);
	assertTypesEqual(
		"Unexpected all subtypes of binary.X", 
		"binary.Deep\n" +
		"binary.Y\n" +
		"binary.Y$Inner\n" +
		"binary.Z\n", 
		types);
}
/**
 * Ensures that the correct superclasses of a type exist in the type 
 * hierarchy.
 */
public void testGetAllSuperclasses() throws JavaModelException {
	IType type = getCompilationUnit("TypeHierarchy", "src", "p1", "Z.java").getType("Z");
	ITypeHierarchy hierarchy = type.newTypeHierarchy(null);
	IType[] types = hierarchy.getAllSuperclasses(type);
	assertTypesEqual(
		"Unexpected all super classes of Z", 
		"java.lang.Object\n" + 
		"p1.X\n" + 
		"p1.Y\n",
		types);
}
/**
 * Ensures that the correct superinterfaces of a type exist in the type 
 * hierarchy.
 */
public void testGetAllSuperInterfaces() throws JavaModelException {
	IType type = getCompilationUnit("TypeHierarchy", "src", "p1", "Z.java").getType("Z");
	ITypeHierarchy hierarchy = type.newTypeHierarchy(null);
	IType[] types = hierarchy.getAllSuperInterfaces(type);
	assertTypesEqual(
		"Unexpected super interfaces of Z", 
		"p1.I1\n" + 
		"p1.I2\n",
		types);
}
/**
 * Ensures that the correct supertypes of a type exist in the type 
 * hierarchy.
 */
public void testGetAllSupertypes() throws JavaModelException {
	IType type = getCompilationUnit("TypeHierarchy", "src", "p1", "Z.java").getType("Z");
	ITypeHierarchy hierarchy = type.newTypeHierarchy(null);
	IType[] types = hierarchy.getAllSupertypes(type);
	assertTypesEqual(
		"Unexpected all super types of Z", 
		"java.lang.Object\n" + 
		"p1.I1\n" + 
		"p1.I2\n" + 
		"p1.X\n" + 
		"p1.Y\n",
		types);
}
/**
 * Ensures that the correct supertypes of a type exist in the type 
 * hierarchy.
 * (regression test for bug 23644 hierarchy: getAllSuperTypes does not include all superinterfaces?)
 */
public void testGetAllSupertypes2() throws JavaModelException {
	IType type = getCompilationUnit("TypeHierarchy", "src", "p3", "B.java").getType("B");
	ITypeHierarchy hierarchy = type.newTypeHierarchy(null);
	IType[] types = hierarchy.getAllSupertypes(type);
	assertTypesEqual(
		"Unexpected all super types of B", 
		"java.lang.Object\n" +
		"p3.A\n" +
		"p3.I\n" +
		"p3.I1\n",
		types);
}
/**
 * Ensures that the correct types exist in the type 
 * hierarchy.
 */
public void testGetAllTypes() throws JavaModelException {
	IType type = getCompilationUnit("TypeHierarchy", "src", "p1", "Y.java").getType("Y");
	ITypeHierarchy hierarchy = type.newTypeHierarchy(null);
	this.assertTypesEqual(
		"Unexpected types in hierarchy of Y",
		"java.lang.Object\n" + 
		"p1.Deep\n" + 
		"p1.I1\n" + 
		"p1.I2\n" + 
		"p1.X\n" + 
		"p1.Y\n" + 
		"p1.Z\n",
		hierarchy.getAllTypes()
	);
}
/**
 * Ensures that the correct extending interfaces exist in the type 
 * hierarchy.
 */
public void testGetExtendingInterfaces() throws JavaModelException {
	IType type = getCompilationUnit("TypeHierarchy", "src", "p2", "I.java").getType("I");
	ITypeHierarchy hierarchy = type.newTypeHierarchy(null);
	IType[] types = hierarchy.getExtendingInterfaces(type);
	this.assertTypesEqual(
		"Unexpected extending interfaces of I",
		"p2.I1\n" + 
		"p2.I2\n",
		types
	);

	type = getCompilationUnit("TypeHierarchy", "src", "p2", "X.java").getType("X");
	hierarchy = type.newTypeHierarchy(null);
	types = hierarchy.getExtendingInterfaces(type);
	this.assertTypesEqual(
		"Unexpected extending interfaces of X",
		"", // interfaces cannot extend a class
		types
	);
}
/**
 * Ensures that the correct implementing interfaces exist in the type 
 * hierarchy.
 */
public void testGetImplementingClasses() throws JavaModelException {
	IType type = getCompilationUnit("TypeHierarchy", "src", "p2", "I.java").getType("I");
	ITypeHierarchy hierarchy = type.newTypeHierarchy(null);
	IType[] types = hierarchy.getImplementingClasses(type);
	this.assertTypesEqual(
		"Unexpected implementing classes of I",
		"p2.X\n",
		types
	);

	type = getCompilationUnit("TypeHierarchy", "src", "p2", "X.java").getType("X");
	hierarchy = type.newTypeHierarchy(null);
	types = hierarchy.getImplementingClasses(type);
	this.assertTypesEqual(
		"Unexpected implementing classes of X",
		"", // classes cannot implement a class
		types
	);
}
/**
 * Ensures that the correct root classes exist in the type 
 * hierarchy.
 */
public void testGetRootClasses() {
	IType[] types = this.typeHierarchy.getRootClasses();
	assertTypesEqual(
		"Unexpected root classes",
		"java.lang.Object\n",
		types);
}
/**
 * Ensures that the correct root interfaces exist in the type 
 * hierarchy.
 */
public void testGetRootInterfaces() throws JavaModelException {
	IType type = getCompilationUnit("TypeHierarchy", "src", "p2", "Y.java").getType("Y");
	ITypeHierarchy hierarchy = type.newTypeHierarchy(null);
	IType[] types = hierarchy.getRootInterfaces();
	assertTypesEqual(
		"Unexpected root classes",
		"p2.I\n",
		types);
}
/**
 * Ensures that getRootInterfaces() works on a IRegion.
 */
public void testGetRootInterfacesFromRegion() {
	IType[] types = this.typeHierarchy.getRootInterfaces();
	assertTypesEqual(
		"Unexpected root classes",
		"binary.I\n" + 
		"rich.I\n" + 
		"rich.I3\n",
		types);
}
/**
 * Ensures that the correct number of subclasses exist in the type 
 * hierarchy created on a region.
 */
public void testGetSubclasses() throws JavaModelException {
	IType type = getClassFile("TypeHierarchy", "lib.jar", "binary", "X.class").getType();
	IType[] types = this.typeHierarchy.getSubclasses(type);
	this.assertTypesEqual(
		"Unexpected subclasses of binary.X",
		"binary.Y\n",
		types
	);
	
	type = getClassFile("TypeHierarchy", "lib.jar", "binary", "I.class").getType();
	types = this.typeHierarchy.getSubclasses(type);
	this.assertTypesEqual(
		"Unexpected subclasses of binary.I",
		"", // interfaces cannot have a subclass
		types
	);
}
/**
 * Ensures that the correct number of subtypes exist in the type 
 * hierarchy created on a region.
 */
public void testGetSubtypes() throws JavaModelException {
	IType type = getClassFile("TypeHierarchy", "lib.jar", "binary", "X.class").getType();
	IType[] types = this.typeHierarchy.getSubtypes(type);
	this.assertTypesEqual(
		"Unexpected subtypes of binary.X",
		"binary.Y\n",
		types
	);
	
	type = getClassFile("TypeHierarchy", "lib.jar", "binary", "I.class").getType();
	types = this.typeHierarchy.getSubtypes(type);
	this.assertTypesEqual(
		"Unexpected subtypes of binary.I",
		"binary.X\n" + 
		"binary.Y$Inner\n",
		types
	);
}

/**
 * Ensures that the superclass is correct in the type 
 * hierarchy a type created on a region containing a package.
 */
public void testGetSuperclassInRegion() throws JavaModelException {
	IRegion r = JavaCore.newRegion();
	IPackageFragment p = getPackageFragment("TypeHierarchy", "src", "p1");
	r.add(p);
	ITypeHierarchy hierarchy = p.getJavaProject().newTypeHierarchy(r, null);

	IType type = getCompilationUnit("TypeHierarchy", "src", "p1", "Y.java").getType("Y");
	IType superclass= hierarchy.getSuperclass(type);
	assertEquals("Unexpected super class of Y", "X", superclass.getElementName());
}

/**
 * Ensures that the correct supertypes exist in the type 
 * hierarchy created on a region.
 */
public void testGetSupertypesInRegion() throws JavaModelException {
	IType type = getClassFile("TypeHierarchy", "lib.jar", "binary", "Y.class").getType();
	IType[] superTypes = this.typeHierarchy.getSupertypes(type);
	assertTypesEqual(
		"Unexpected super types of Y",
		"binary.X\n",
		superTypes);
}
/**
 * Ensures that the correct supertypes exist in the type 
 * hierarchy created on a region containing a project.
 */
public void testGetSupertypesWithProjectRegion() throws JavaModelException {
	IJavaProject project = getJavaProject("TypeHierarchy");
	IRegion region= JavaCore.newRegion();
	region.add(project);
	IType type = getClassFile("TypeHierarchy", "lib.jar", "binary", "Y.class").getType();
	ITypeHierarchy hierarchy = project.newTypeHierarchy(type, region, null);
	IType[] superTypes = hierarchy.getSupertypes(type);
	assertTypesEqual(
		"Unexpected super types of Y",
		"binary.X\n",
		superTypes);
}
}
