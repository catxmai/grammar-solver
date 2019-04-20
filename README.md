# grammar-solver
A client that reads grammar in Backus-Naur Form and allows the user to randomly generate sentences.

(See project description)

A BNF grammar is specified as an input file containing one or more rules, each on its
own line, of the form:

non-terminal::=rule|rule|rule|...|rule

A ::= (colon colon equals) separator divides the non-terminal from its expansion rules.
There will be exactly one ::= per line. A | (pipe) separates each rule; if there is only
one rule for a given non-terminal, there will be no pipe characters.
