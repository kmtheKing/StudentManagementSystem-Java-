# ---------------------------------------------------------------------------
# Student Management System - Run Script (PowerShell)
# ---------------------------------------------------------------------------

# Set paths
$JDK_PATH = "C:\Program Files\Eclipse Adoptium\jdk-21.0.10.7-hotspot\bin"
$JAVAC = Join-Path $JDK_PATH "javac.exe"
$JAVA = Join-Path $JDK_PATH "java.exe"
$LIB_DIR = "lib"
$JAR_FILE = Get-ChildItem "$LIB_DIR\mysql-connector-j-*.jar" | Select-Object -ExpandProperty FullName
$CP = ".;$JAR_FILE;src"

# 1. Compile
Write-Host "Compiling project..." -ForegroundColor Cyan
& $JAVAC -cp $CP -d bin src/com/project/model/Student.java src/com/project/dao/*.java src/com/project/controller/StudentController.java src/com/project/view/StudentView.java src/com/project/Main.java

if ($LASTEXITCODE -ne 0) {
    Write-Host "Compilation failed!" -ForegroundColor Red
    exit $LASTEXITCODE
}

# 2. Run
Write-Host "Starting application..." -ForegroundColor Green
& $JAVA -cp "bin;$JAR_FILE" com.project.Main
