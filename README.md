#  cloodsee's language  #

---

## Description:

- Arithmetics and comparison of integers.
- Concatenation and comparison of strings.
- Evaluation is from left to right (even for division and multiplication). When it enconters a string it converts everything to a string.
- Exemple : '1+2+"test"+3+4' = "3test34"
- 3 primitives : print, println and exit.
- print takes at least 1 argument
- println takes at least 0 argument
- exit takes 0 argument (for return code 0) or 1 argument(an int for specifying return code).
- Constants 'true' and 'false' in lowercase
- Operators like or (||) & and (&&) for booleans.
- Comes with if-then-else which returns the last value calculated. **The else is mandatory**.
- Comes with while do.
- Sequences of instructions with parenthesis. You can add a ';' at the end of an instruction but it doesn't change anything.
- Only global variables. Keyword 'let' for declaration.
- Affectation of variables.

## FAQ

- Q: Do you plan on adding Floating Points ?
- A: No, use ints dummy.
<br>

- Q: This is useless.
- A: Yes.

## Licenses

Hell no, why would you even want to use this ?!

## Building the program

- Put the correct libraries in the '/lib' folder.
- Use './build.bat'.

## Launching the program

- Go to the 'env' folder.
- Simply run the jar with java. Follow with path to the cloo src then parameters (like /debug).

