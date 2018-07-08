@echo off
SET /A CNT=0
SET target=tool/MakeGraph.R
SET toolpath=hoge
SET CUD=%~dp1
cd %CUD%
:LOOP


IF EXIST "%target%" ( 
@echo on
echo fileexit

SET toolpath=%CD%/tool/
echo %toolpath%
@ECHO OFF
cd %CUD%
GOTO GOOD
)

cd ../

IF "%CNT%"=="5" GOTO BAD
SET /A CNT+=1
GOTO LOOP

:GOOD

for %%f in (%*) do (
  rscript --vanilla %toolpath%MakeGraph.R %%~nf
)
GOTO END

:BAD
@echo on
echo filenotexti
@ECHO OFF

:END
cd %~dp0

