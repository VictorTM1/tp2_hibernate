Coding Standard
===============
File Names
----------

As in the Java language coding standards, source files have the .java extension and complied bytecode files have the .class extension.. 

Java Source Files
-----------------

Each Java source file contains a single public class or interface.

Java source files have the following ordering: 

·        Beginning comments

·        Package and Import statements 

·        Class Header and Declaration

·        Method Headers and Declarations

### Beginning Comments

Filename.java  where Filename matches class name.

### Package and Import Statements

The first non-comment line of most Java source files is a package statement. After that, import statements can follow. For example: 
                   package java.awt;
                   import java.awt.peer.CanvasPeer;

### Class Headers and Declaration

All source files should contain a comment that lists the class name, inheritance, attributes, methods, functionality, visibility, requirement number and the statement which declares the class.

-----------------------------------

 *  Class name:

             *  Inheritance:

             *  Attributes:

             *  Methods:

             *  Functionality:

             *  Visibility:

             *  From requirement number

             * public class ClassName

### Method Headers and Declarations

Every method included in a class should contain a comment that lists the method name, inheritance, attributes, precondition, postcondition, functionality, visibility, @param, @return, and requirement number which it supports, if any.

-----------------------------------

 *  Method name:

             *  Inheritance:

             *  Attributes:

             *  Precondition:

             *  Postcondition:

             *  Functionality:

             *  Visibility:

             *  @param:

             *  @return:

             *  From requirement number  

-----------------------------------

### Indentation

            Four spaces should be used as the unit of indentation.

### Line Length

Avoid lines longer than 80 characters, since they're not handled well by many terminals and tools.

Wrapping Lines

When an expression will not fit on a single line, break it according to these general principles: 

·         Break after a comma. 

·         Break before an operator. 

·         Prefer higher-level breaks to lower-level breaks. 

·         Align the new line with the beginning of the expression at the same level on the previous line. 

·         If the above rules lead to confusing code or to code that's squished up against the right margin, just indent 8 spaces instead. 

### Comments

**Implementation Comment Formats**

Programs can have four styles of implementation comments: block, single-line, trailing, and end-of-line.

**Block Comments**

Block comments are used to provide descriptions of files, methods, data structures and algorithms. Block comments may be used at the beginning of each file and before each method. They can also be used in other places, such as within methods. Block comments inside a function or method   should be indented to the same level as the code they describe. 

A block comment should be preceded by a blank line to set it apart from the rest of the code. 

**Single-line comments**

Short comments can appear on a single line indented to the level of the code that follows. If a comment can't be written in a single line, it should follow the block comment format. A single-line comment should be preceded by a blank line. Here's an example of a single-line comment in Java code: 

**Trailing Comments**

Very short comments can appear on the same line as the code they describe, but should be shifted far enough to separate them from the statements. If more than one short comment appears in a chunk of code, they should all be indented to the same tab setting. 

**End-Of-Line Comments**

The // comment delimiter can comment out a complete line or only a partial line. It shouldn't be used on consecutive multiple lines for text comments; however, it can be used in consecutive multiple lines for commenting out sections of code. Examples of all three styles follow: 
                   if  ( foo > 1 ) {
                       // Do a double-flip.
                                   ...
                   }
                   else {
                                      return false;          // Explain why here.
                   }
                   //if  ( bar > 1 ) {
                   //
                   //    // Do a triple-flip.
                   //    ...
                   //}
                   //else {
                   //    return false;
                   //}
 

**Documentation Comments**

Top-level classes and interfaces are not indented, while their members are. The first line of comment (/**) for classes and interfaces is not indented; subsequent comment lines each have 1 space of indentation (to vertically align the asterisks). Members, including constructors, have 4 spaces for the first comment line and 5 spaces thereafter. 

If you need to give information about a class, interface, variable, or method that isn't appropriate for documentation, use an implementation block comment or single-line comment immediately after the declaration. For example, details about the implementation of a class should go in in such an implementation block comment following the class statement, not in the class comment. 

### Declarations

**Number Per Line**

One declaration per line which is useful for commenting.  Variable names should be sorted by type and in alphabetical order.  Example:
                   int            level;                // indentation level
                   int            size;                 // size of table
                               String  stringA;
                               String  stringB;

**Initialization**

Local variables are initialized where they're declared. The only reason not to initialize a variable where it's declared is if the initial value depends on some computation occurring first. 

**Placement**

Declarations are only put at the beginning of blocks. (A block is any code surrounded by curly braces "{" and "}".)

**Class and Interface Declarations**

When coding Java classes and interfaces, the following formatting rules should be followed: 

·         No space between a method name and the parenthesis "(" starting its parameter list 

·         Open brace "{" appears at the end of the same line as the declaration statement 

·         Closing brace "}" starts a line by itself indented to match its corresponding opening statement, except when it is a null statement the "}" should appear immediately after the "{" 
                   class Sample extends Object {
                                  int ivar1;
                                  int ivar2;
 
                                  Sample( int i, int j ) {
                                     ivar1 = i;
                                     ivar2 = j;
                                                 }
 
                                                  int emptyMethod() {}
                                                  ...
                                  }

·              Methods are separated by a blank line.

### Statements

**Simple Statements**

Each line should contain at most one statement. Example: 
                   argv++;                                  // Correct
                   argc--;                                   // Correct  
                   argv++; argc--;                      // AVOID!

**Compound Statements**

Compound statements are statements that contain lists of statements enclosed in braces   "{  statements }". See the following sections for examples. 

·         The enclosed statements should be indented one more level than the compound statement. 

·         The opening brace should be at the end of the line that begins the compound statement; the closing brace should begin a line and be indented to the beginning of the compound statement. 

·         Braces are used around all statements, even single statements, when they are part of a control structure, such as a if-else or for statement. This makes it easier to add statements without accidentally introducing bugs due to forgetting to add braces. 

**return Statements**

A return statement with a value should not use parentheses.  Example:

                        return returnValue;

if, if-else, if else-if else Statements

The if-else class of statements should have the following form: 
                   if  ( condition ) {
                                      statements;
                   }
 
                   if ( condition ) {
                                      statements;
                   } else {
                     statements;
                   }
 
                   if  ( condition ) {
                                      statements;
                   } else if ( condition ) {
                          statements;
                   } else {
                         statements;
                   }

**for Statements**

A for statement should have the following form: 
      for ( initialization; condition; update ) {
           statements;
     }

**while Statements**

A while statement should have the following form: 
       while ( condition ) {
           statements;
        }

**switch Statements**

A switch statement should have the following form: 
                   switch ( condition ) {
                   case ABC:
                                      statements;
                       /* falls through */
 
                   case DEF:
                       statements;
                   break;
 
                   case XYZ:
                       statements;
                   break;
 
                   default:
                       statements;
                   break;
                   }

Every time a case falls through (doesn't include a break statement), add a comment where the break statement would normally be. This is shown in the preceding code example with the   /*  falls through */ comment. 

Every switch statement should include a default case. The break in the default case is redundant, but it prevents a fall-through error if later another case is added. 

**try-catch Statements**

A try-catch statement should have the following format: 
   try {
     statements;
   } catch ( ExceptionClass e ) {
          statements;
   }

A try-catch statement may also be followed by finally, which executes regardless of whether or not the try block has completed successfully. 
   try {
        statements;
   } catch ( ExceptionClass e ) {
          statements;
   } finally {
          statements;
   }
 
###  White Space

**Blank Lines**

Blank lines improve readability by setting off sections of code that are logically related. 

Two blank lines should always be used in the following circumstances: 

·         Between sections of a source file 

·         Between class and interface definitions 

One blank line should always be used in the following circumstances: 

·         Between methods 

·         Between the local variables in a method and its first statement 

·         Before a block or single-line comment 

·         Between logical sections inside a method to improve readability 

**Blank Spaces**

            Blank spaces should be used in the following circumstances: 

·         A keyword followed by a parenthesis should be separated by a space. Example: 
       while ( true ) {
           ...
       }

Note that a blank space should not be used between a method name and its opening parenthesis. This helps to distinguish keywords from method calls. 

·         A blank space should appear after commas in argument lists. 

·         All binary operators except . should be separated from their operands by spaces. Blank spaces should never separate unary operators such as unary minus, increment ("++"), and decrement ("--") from their operands. Example: 
                   a += c + d;
                   a = ( a + b ) / ( c * d );
    
                   while ( d++ = s++ ) {
                      n++;
                   }
                   printSize( "size is " + foo + "\n" );

·         The expressions in a for statement should be separated by blank spaces. Example: 
                                  for ( expr1; expr2; expr3 )

·         Casts should be followed by a blank space. Examples: 
                           myMethod( ( byte ) aNum, ( Object ) x );
                           myMethod( ( int ) ( cp + 5 ),  ( ( int ) ( i + 3 ) )  + 1 );

### Naming Conventions

**Class Names**

Class names should be nouns, in mixed case with the first letter of each internal    word capitalized. Class names should be simple and descriptive. Use whole words-avoid acronyms and abbreviations .   Examples:

class States;   class GameHandler;                            

**Method Names**

Methods should be verbs, in mixed case with the first letter lowercase, with the first letter of each internal word capitalized.  Examples:

run();                                                                                        getBackground();                     

**Variable Names**

Except for variables, all instance, class, and class constants are in mixed case with a lowercase first letter. Internal words start with capital letters. Variable names should not start with underscore _ or dollar sign $ characters, even though both are allowed. 

Variable names should be short yet meaningful. The choice of a variable name should be mnemonic- that is, designed to indicate to the casual observer the intent of its use. One-character variable names should be avoided except for temporary "throwaway" variables. Common names for temporary variables are i, j, k, m, and n for integers; c, d, and e for characters.  Example:

            String tempWord;

**Constant Names**

The names of variables declared class constants and of ANSI constants should be all uppercase with words separated by underscores ("_"). (ANSI constants should be avoided, for ease of debugging.)  Example:

            statis final int MAX_COUNTDOWN = 6;
