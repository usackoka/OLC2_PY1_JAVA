SET JFLEX_HOME= C:\libs
cd .\
java -jar %JFLEX_HOME%\jflex-1.7.0.jar lexer.jflex
pause
cd .\
java -jar %JFLEX_HOME%\java-cup-11b.jar -parser parser parser.cup
pause