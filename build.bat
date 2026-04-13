@echo off
setlocal enabledelayedexpansion

REM Create output directory if it doesn't exist
if not exist out (
    mkdir out
)

echo =========================
echo Generating ANTLR sources
echo =========================

REM Run ANTLR on grammar file
java -jar lib\antlr-4.13.1-complete.jar src/cloodsee/language/grammar/Tiggrammar.g4 -o src/generated/cloodsee/language/grammar

echo =========================
echo Compiling Java sources
echo =========================

REM Collect all Java source files
set SRC_FILES=

for /R src %%f in (*.java) do (
    set SRC_FILES=!SRC_FILES! %%f
)

REM Compile
javac -d out -cp "lib\antlr-4.13.1-complete.jar;lib\junit-4.12.jar;src;src\grammar" %SRC_FILES%
IF NOT EXIST "./out/org" (
cd lib && ^
jar xf antlr-4.13.1-complete.jar && ^
rmdir /Q /S META-INF && ^
xcopy /S /I "org" "../out/org" && ^
rmdir /Q /S org && ^
cd ..
)
cd ./out
jar cfm ../env/ClooLang.jar ../src/META-INF/MANIFEST.MF *


echo =========================
echo Build finished
echo =========================

goto :eof


:clean
echo Cleaning output...

if exist out (
    rmdir /s /q out
    mkdir out
)

del /q src\grammar\*.java 2>nul
del /q src\grammar\*.tokens 2>nul
del /q src\grammar\*.interp 2>nul

echo Clean complete