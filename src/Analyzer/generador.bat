SET JFLEX_HOME= D:\libs
cd .\
java -jar %JFLEX_HOME%\jflex-1.6.1.jar lexer.jflex
pause
cd .\
java -jar %JFLEX_HOME%\java-cup-11b.jar -parser parser parser.cup
pause 