# 一键编译 BLChat（仅 1.20.1）并把产物收进仓库根目录的 all\ 文件夹。
#
# 用法：
#   powershell -File tools\build-all-versions.ps1            # 完整构建 + 收集
#   powershell -File tools\build-all-versions.ps1 -SkipBuild # 只收集已有产物
#   powershell -File tools\build-all-versions.ps1 -DryRun    # 只打印将要执行的动作

param(
    [switch]$SkipBuild,
    [switch]$DryRun
)

$ErrorActionPreference = 'Stop'
$root = Split-Path -Parent $PSScriptRoot
$outDir = Join-Path $root 'all'
if (-not (Test-Path $outDir)) {
    New-Item -ItemType Directory -Path $outDir | Out-Null
    Write-Host "已创建输出目录 all\"
}

$jdk21 = 'C:\Users\Administrator\.gradle\jdks\jdk-21\jdk-21.0.12+8'

Write-Host ""
Write-Host "---- 构建 BLChat 1.20.1 ----" -ForegroundColor Cyan
if (-not $SkipBuild -and -not $DryRun) {
    $env:JAVA_HOME = $jdk21
    $env:PATH = "$jdk21\bin;$env:PATH"
    Push-Location $root
    try {
        cmd /c "gradlew.bat build --no-daemon" | Write-Host
        if ($LASTEXITCODE -ne 0) {
            Write-Host "[FAIL] 构建失败" -ForegroundColor Red
            exit 1
        }
    } finally {
        Pop-Location
    }
}

if ($DryRun) {
    Write-Host "DryRun 结束，未实际构建或移动文件。"
    exit 0
}

$jar = Get-ChildItem -Path (Join-Path $root 'build\libs') -Filter '*.jar' -ErrorAction SilentlyContinue |
    Where-Object { $_.Name -notmatch '-(sources|javadoc)\.jar$' } |
    Sort-Object LastWriteTime -Descending |
    Select-Object -First 1

if ($null -eq $jar) {
    Write-Warning "没有找到构建产物"
    exit 1
}

$target = Join-Path $outDir $jar.Name
Move-Item -LiteralPath $jar.FullName -Destination $target -Force
Write-Host "[OK] $($jar.Name) -> all\$($jar.Name)" -ForegroundColor Green
