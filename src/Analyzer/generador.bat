SET JFLEX_HOME= C:\libs
cd .\
java -jar C:\Users\D_ALCAZAR\Documents\compi 2 k\OLC2_DIC_19_PY1\FlexCup\jflex-1.6.1\lib\jflex-1.6.1.jar lexer.jflex
C:\Users\D_ALCAZAR\Documents\NetBeansProjects\cup_flex_junio_2019_ev2\librerias\jflex-1.6.1.jar
cd .\
java -jar %JFLEX_HOME%\java-cup-11b.jar -parser parser parser.cup
pause