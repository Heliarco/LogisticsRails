# This script relies on a HEAVY set of assumptions. 
# 1. That blockbench is tied to the bbmodel autolaunch config
# 2. that the multiexport is installed correctly,
# 3. that the running machine has the horsepower to load blockbench models quickly enough to not still be doing loading when the export process triggers.
# 4. Luck
# ANYWAY, here is what it does
# Remove old models
# Remove old textures
# Foreach bbmodel file, open it with an env arg that is caught from the multiexport plugin, telling it to wait a bit then autostart the exporting procedure
# Copy over all textures

# If it works, its one-click rebuild all models... 

Set-Location $PSScriptRoot
Set-StrictMode -Version Latest
$ErrorActionPreference = "Stop"
$PSDefaultParameterValues['*:ErrorAction']='Stop'

$env:BBAUTOEXPORT = 'true'; 
Get-ChildItem *bbmodel | foreach { 
    $file = $_

    Start-Process $file
    Write-Host $file
    # wait for BB to exit
    while($true){
        Start-Sleep -s 2
        $x = Get-Process BlockBench* -ErrorAction SilentlyContinue
        if (!$x){
            break;
        }
    }
    break;


    #    Remove-Item -Path $_.FullName 

}

Remove-Item Env:\BBAUTOEXPORT