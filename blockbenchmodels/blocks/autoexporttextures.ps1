
Set-Location $PSScriptRoot
Set-StrictMode -Version Latest
$ErrorActionPreference = "Stop"
$PSDefaultParameterValues['*:ErrorAction']='Stop'

$blocktexturespath = "..\..\src\main\resources\assets\vertexspace_logisticsrails\textures\blocks"

Remove-Item -Path ($blocktexturespath+ "\*")  -Verbose -ErrorAction SilentlyContinue

#Copy textures
Get-ChildItem *.png | foreach {
    Copy-item -Verbose $_ -Destination $blocktexturespath
}