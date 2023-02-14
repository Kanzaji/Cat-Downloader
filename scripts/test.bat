:START
@echo off
call clear_data.bat
java -jar ..\.\target\CatDownloader-DEVELOP-jar-with-dependencies.jar
pause
cls
goto START