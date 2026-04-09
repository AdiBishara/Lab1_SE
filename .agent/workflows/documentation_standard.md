---
description: Custom code documentation format standard for this workspace
---
# Documentation Format
When documenting code in this workspace, ALWAYS use this exact block comment structure instead of standard JavaDoc or Python docstrings:

```
/*
 * <Line 1 - what does the function do>
 * <line 2+ - parameters of the function and what purpose they serve>
 * <last line - return value of the function>
 */
 ```

 If a function has no parameters, explicitly state `parameters: none` on line 2.
