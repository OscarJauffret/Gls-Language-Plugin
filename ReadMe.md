# Gilles Language Plugin
> [!NOTE]
> The Gilles Language is a custom language

This plugin enhances the development experience for the custom Gilles language by providing features like syntax highlighting, custom file support, and more.

## Installation
You can find the plugin on the JetBrains Plugin Repository under the name "Gilles Language", or directly [here](https://plugins.jetbrains.com/plugin/25619-gilles-language?noRedirect=true).

## Features
- Syntax highlighting for easy readability
- Custom file type registration for .gls files
- Color settings customization for your code
- Error detection for cleaner code
- Code completion to speed up development
- Live templates for common code patterns
- Automatic code commenting
- Extension of the Gilles language with additional features

In addition to supporting the core features of the Gilles language, this plugin extends its functionality with:
- Function definitions with the `FUNCTION` keyword
- Function calls
- Data types: `INT`, `BOOL`, `FLOAT`, and `LIST OF <type>`
- FOR loops for iterative operations
- Printing expressions directly without using a variable
- Additional comparison operators: `!=`, `>=`, and `>`
- `%` operator for modulo operations, with the same precedence as `*` and `/`
- AND, OR, and NOT logical operators
- TRUE and FALSE keywords for boolean values

### Syntax for additional features


<ins>For loops</ins>

<code>FOR INT \$Var\$ = \$Init\$ TO \$Expr\$ REPEAT \$Code\$ END</code>

<ins>Function definitions</ins>

<code>FUNCTION \$ProgName\$(\$ParamList\$) RETURNS \$Type\$ \$Code\$ RETURN \$Value\$ END</code>

<ins>Function calls</ins>

When calling a function, it is mandatory to assign the result to a variable or use it in an expression.

<code>\$Variable\$ = \$FunctionName\$(\$ArgList\$)</code>

<ins>Data types</ins>

<code>INT \$VarName\$ = \$Value\$</code>, <code>BOOL \$VarName\$ = \$Value\$</code>, <code>FLOAT \$VarName\$ = \$Value\$</code>, <code>LIST OF <type> \$VarName\$ = [\$ValueList\$]</code>

<ins>Printing expressions</ins>

<code>OUT(\$Expression\$)</code>
<br>

> [!IMPORTANT]
> Please note that some code may not be identified as invalid by the plugin because the plugin does not run a full semantic analysis of the code.
## Live Templates
> [!TIP]
> Live templates make coding in Gilles faster by offering predefined code snippets. Just type the template name and press the Tab key to insert it. If the live template includes a selection, such as <code>ifs</code>, select the code, and press CTRL + ALT + J (Windows) or OPTION + COMMAND + J (Mac)

Currently available templates include:
- <code>prog</code> - Boilerplate for defining a new program
- <code>func</code> - Boilerplate for defining a new function
- <code>if</code> - Standard conditional block
- <code>ifel</code> - Conditional block with an `else` statement
- <code>ifs</code> - Surrounds the selected code with an `if` block
- <code>while</code> - Loop structure for repeating actions
- <code>for</code> - Iterative loop

## Feedback and Support
If you encounter any issues or have suggestions, feel free to submit them on our <a href="https://github.com/OscarJauffret/Gls-Language-Plugin/issues">GitHub page</a>
