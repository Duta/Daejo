# Daejo
*My little experiment*

## What is Daejo?
Daejo is a toy language of mine. To be frank, it's
quite a dull language, syntax-wise. I'm following -
for now, anyway, very C/Java-like syntax. I have a
much more interesting language floating through my
mind, but I want to experiment in parsing first.

In short, Daejo is my attempt to learn parsing.

## How do I parse the input?
My approach is fairly 'normal', I think.

1. First we use the lexer to convert the input
stream of characters to a stream of tokens.

2. We then pass that stream of tokens to the
parser, which builds a tree of the program
structure. At this point, we've parsed the source code.
However, that's slightly pointless on it's own.

3. Finally, the syntax tree is passed to an
interpreter which actually executes the code.

##The lexer
The job of the lexer is to turn something like:

    [bacon, lettuce, tomato]

into:

    LSqBrackToken - "["
    WordToken - "bacon"
    CommaToken - ","
    WhitespaceToken - " "
    WordToken - "lettuce"
    CommaToken - ","
    WhitespaceToken - " "
    WordToken - "tomato"
    RSqBrackToken - "]"
// TODO: Explain how the lexer works

##The parser
// TODO: Explain how the parser works

##The interpreter
// TODO: Explain how the interpreter works