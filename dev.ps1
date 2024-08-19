param (
    [string]$action
)

if ($action -eq "start") {
    $containerExists = docker ps -a --format '{{.Names}}' | Select-String -Pattern '^mi-proyecto-dev-container$'
    if ($containerExists) {
        docker start -i mi-proyecto-dev-container
    } else {
        docker run -it --name mi-proyecto-dev-container -v ${PWD}:/app mi-proyecto-dev /bin/bash
    }
} elseif ($action -eq "build") {
    docker build -t mi-proyecto-dev -f Dockerfile.dev .
} else {
    Write-Host "Uso: .\dev.ps1 [start|build]"
}