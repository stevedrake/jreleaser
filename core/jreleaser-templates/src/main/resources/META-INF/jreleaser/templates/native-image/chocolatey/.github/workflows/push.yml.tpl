name: Push-{{distributionName}}

on:
  push:
    tags:

jobs:
  push:
    runs-on: windows-latest
    
    steps:
      - uses: actions/checkout@v2
      
      - uses: actions/setup-dotnet@v1
        with:
          dotnet-version: '2.2.204'

      - name: Find package
        shell: bash
        run: |
          echo "PACKAGE_NAME=$(ls {{distributionName}}/*.nuspec)" >> $GITHUB_ENV

      - name: Pack
        shell: powershell
        run: |
          choco pack {{distributionName}}/${{=<% %>=}}{{ env.PACKAGE_NAME }}<%={{ }}=%>.nuspec

      - name: Publish
        shell: powershell
        run: |
          choco apikey -k ${{=<% %>=}}{{ secrets.CHOCOLATEY_API_KEY  }}<%={{ }}=%> -s {{chocolateySource}}
          choco push $(ls *.nupkg | % {$_.FullName}) -s {{chocolateySource}}
