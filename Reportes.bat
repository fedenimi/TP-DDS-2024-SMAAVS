@echo off
cd /d "%~dp0"

:: Ejecutar el .jar
java -jar "%~dp0target\ejercicio-1.0-SNAPSHOT-jar-with-dependencies.jar"

:: Mover los reportes
move "%~dp0reporteFallasHeladera.pdf" "%~dp0src\main\resources\public\reportes"
move "%~dp0reporteViandasColaborador.pdf" "%~dp0src\main\resources\public\reportes"
move "%~dp0reporteViandasHeladera.pdf" "%~dp0src\main\resources\public\reportes"

pause
