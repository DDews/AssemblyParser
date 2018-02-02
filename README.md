# Simple Assembly Parser
This is a reference for those wanting to understand Recursive Descent Parsers for Principles of Programming Languages at MSU Denver.

This recursive descent parser attempts to give meaningful error messages when creating a parse-tree for the language from a text file.

For execution:
```
	javac *.java
	java Main
```
To use:

	Type up a text file in the assembly language, and type in the file name. File must be in same directory as .java files.


BNF for this simple assembly language:

*note: I have taken liberties of denoting more complex grammar in plain english instead, specified by enclosing [] brackets*
```
STATEMENTS -> STATEMENT | STATEMENT STATEMENTS

STATEMENT -> LABEL NEWLINE | INSTRUCTION NEWLINE

LABEL -> WORD:

NEWLINE -> \n

INSTRUCTION -> MOVE OPERAND, OPERAND |

		MOVEI VALUE, OPERAND |

		ADD OPERAND, OPERAND, OPERAND |

		INC LOCATION |

		SUB OPERAND, OPERAND, OPERAND |

		DEC LOCATION |

		MUL OPERAND, OPERAND, OPERAND |

		DIV OPERAND, OPERAND, OPERAND |

		BEQ OPERAND, OPERAND, LOCATION |

		BLT OPERAND, OPERAND, LOCATION |

		BGT OPERAND, OPERAND, LOCATION |

		BR LOCATION |

		END

OPERAND -> LOCATION | REGISTER | LABELNAME

LOCATION -> [up to 5 characters letters only]

REGISTER -> [R followed by number 1-12]

LABELNAME -> [up to 25 letters]
```

The point of all this is to give meaningful errors.

With input file test.txt:

```
MOVE s, d
MOVEI a, b, c
ADD R12, R2, d
BEQ R1, R2, hi
```

it gives this error:

```
Exception when parsing Token [PUNCTUATION: ,] in line 2:9
java.lang.Exception: Expected NEWLINE, but encountered [PUNCTUATION: ,]
	at Node.throwEx(Node.java:14)
	at NewLine.<init>(NewLine.java:7)
	at Statement.<init>(Statement.java:12)
	at Statements.<init>(Statements.java:3)
	at Statements.<init>(Statements.java:9)
	at Parser.<init>(Parser.java:8)
	at Main.main(Main.java:13)
```
