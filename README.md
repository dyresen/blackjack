## Introduction

This is a blackjack bot. It doesn't do much, apart from playing blackjack based on certain rules. It will
either generate a fresh randomized deck or use one provided on the command line.

To have the bot play, you first need to clone the repository and compile it. 

Clone it by: 
```shell
git clone https://github.com/dyresen/blackjack.git
```

Compile it with kotlinc 
```
kotlinc ./src/main/kotlin/main.kt -include-runtime -d blackjack.jar
```

Then run it with the following command:
```
java -jar blackjack.jar
```

It can also take a parameter on the command line
```
java -jar blackjack.jar testDeck.txt
```

If you want to provide your own deck, have a look at the testDeck.txt to see the format used. 


