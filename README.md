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

```
STATEMENTS -> STATEMENT | STATEMENT STATEMENTS

STATEMENT -> LABEL NEWLINE | INSTRUCTION NEWLINE

LABEL -> WORD:

NEWLINE -> \\n

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
