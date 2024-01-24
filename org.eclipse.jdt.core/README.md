Introduction
================================================================================
This fork aims to fix the following bug report:

https://bugs.eclipse.org/bugs/show_bug.cgi?id=176820


Problem
================================================================================

With the latest eclipse formatter version, we would have to settle with one of
the following situations:



Enabled: Before closing brace of array initializer
--------------------------------------------------------------------------------
```java
String[] short = { "foo", "bar"
};


int[] long = {
    1,
    2,
    3,
    4,
    5,
    6,
    7,
    8,
    9,
    10
};
```

Disabled: Before closing brace of array initializer
--------------------------------------------------------------------------------
```java
String[] short = { "foo", "bar" };


int[] long = {
    1,
    2,
    3,
    4,
    5,
    6,
    7,
    8,
    9,
    10 };
```

Goal/Ideal Scenario
================================================================================

In an ideal world, array brace initalization would have different formatting
depeneding on whether all of it can fit in one line or not. Assume the following
has a 40-wide column length.

```java
String[] short = { "foo", "bar" };


int[] long = {
    1,
    2,
    3,
    4,
    5,
    6,
    7,
    8,
    9,
    10
};
```

Proposed Solution
================================================================================

Introduce new configuration that does not add new line if wrapping is not
required.

```diff
 FORMATTER / Option to insert a new line before the closing brace in an initializer list
     - option id:         "org.eclipse.cdt.core.formatter.insert_new_line_before_closing_brace_in_array_initializer"
-    - possible values:   { INSERT, DO_NOT_INSERT }
+    - possible values:   { INSERT, DO_NOT_INSERT }
     - default:           DO_NOT_INSERT
```

See:
- https://help.eclipse.org/latest/index.jsp?topic=%2Forg.eclipse.cdt.doc.isv%2Freference%2Fapi%2Forg%2Feclipse%2Fcdt%2Fcore%2Fformatter%2FDefaultCodeFormatterConstants.html
- https://github.com/eclipse-jdt/eclipse.jdt.core/blob/20a24392b7c6106009300e8d31ab84ced98c1bb6/org.eclipse.jdt.core/formatter/org/eclipse/jdt/internal/formatter/DefaultCodeFormatterOptions.java#L41
